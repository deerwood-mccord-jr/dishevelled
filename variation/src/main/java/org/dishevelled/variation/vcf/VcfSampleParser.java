/*

    dsh-variation  Variation.
    Copyright (c) 2013 held jointly by the individual authors.

    This library is free software; you can redistribute it and/or modify it
    under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation; either version 3 of the License, or (at
    your option) any later version.

    This library is distributed in the hope that it will be useful, but WITHOUT
    ANY WARRANTY; with out even the implied warranty of MERCHANTABILITY or
    FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
    License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this library;  if not, write to the Free Software Foundation,
    Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA.

    > http://www.fsf.org/licensing/licenses/lgpl.html
    > http://www.opensource.org/licenses/lgpl-license.php

*/
package org.dishevelled.variation.vcf;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Closeable;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;

import com.google.common.io.InputSupplier;

final class VcfSampleParser
{

    private VcfSampleParser()
    {
        // empty
    }

    static <R extends Readable & Closeable> Iterable<VcfSample> samples(final InputSupplier<R> supplier)
        throws IOException
    {
        checkNotNull(supplier);

        ParseListener parseListener = new ParseListener();
        VcfParser.parse(supplier, parseListener);
        return parseListener.getSamples().values();
    }

    static final class ParseListener extends VcfParseAdapter
    {
        private Map<String, VcfSample> samples = new HashMap<String, VcfSample>();

        /*
          ##SAMPLE=<ID=S_ID,Genomes=G1_ID;G2_ID; ...;GK_ID,Mixture=N1;N2; ...;NK,Description=S1;S2; ...; SK >

          ##SAMPLE=<ID=Blood,Genomes=Germline,Mixture=1.,Description="Patient germline genome">
          ##SAMPLE=<ID=TissueSample,Genomes=Germline;Tumor,Mixture=.3,.7,Description="Patient germline genome;Patient tumor genome">
        */

        @Override
        public void meta(final String meta) throws IOException
        {
            if (meta.startsWith("##SAMPLE="))
            {
                ListMultimap<String, String> values = ArrayListMultimap.create();
                String[] tokens = meta.substring(8).split(",");
                for (String token : tokens)
                {
                    String[] metaTokens = token.split("=");
                    String key = metaTokens[0];
                    String[] valueTokens = metaTokens[1].split(";");
                    for (String valueToken : valueTokens)
                    {
                        values.put(key, valueToken);
                    }
                }

                String id = values.get("ID").get(0);
                List<String> genomeIds = values.get("Genomes");
                List<String> mixtures = values.get("Mixture");
                List<String> descriptions = values.get("Description");

                List<VcfGenome> genomes = new ArrayList<VcfGenome>(genomeIds.size()); 
                for (int i = 0, size = genomes.size(); i < size; i++)
                {
                    genomes.add(new VcfGenome(genomeIds.get(i), Double.parseDouble(mixtures.get(i)), descriptions.get(i)));
                }
                samples.put(id, new VcfSample(id, genomes.toArray(new VcfGenome[0])));
            }
        }

        @Override
        public void samples(final String... samples) throws IOException
        {
            for (String sample : samples)
            {
                // add if missing in meta lines
                if (!this.samples.containsKey(sample))
                {
                    this.samples.put(sample, new VcfSample(sample, new VcfGenome[0]));
                }
            }
        }

        @Override
        public boolean complete() throws IOException
        {
            return false;
        }

        Map<String, VcfSample> getSamples()
        {
            return samples;
        }
    };
}