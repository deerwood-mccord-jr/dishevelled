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
package org.dishevelled.variation.gemini;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import static org.dishevelled.variation.gemini.GeminiUtils.isValidIdentifier;
import static org.dishevelled.variation.gemini.GeminiUtils.variantId;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dishevelled.variation.Variation;
import org.dishevelled.variation.VariationConsequence;
import org.dishevelled.variation.VariationConsequenceService;

import com.google.common.collect.ImmutableList;

/**
 * GEMINI command line variation consequence service.
 */
final class GeminiVariationConsequenceService implements VariationConsequenceService
{
    private final String species;
    private final String reference;
    private final String databaseName;

    //@Inject
    GeminiVariationConsequenceService(final String species, final String reference, final String databaseName)
    {
        checkNotNull(species);
        checkNotNull(reference);
        checkNotNull(databaseName);

        this.species = species;
        this.reference = reference;
        this.databaseName = databaseName;
    }


    @Override
    public List<VariationConsequence> consequences(final Variation variation)
    {
        checkNotNull(variation);
        checkArgument(species.equals(variation.getSpecies()));
        checkArgument(reference.equals(variation.getReference()));

        // requires GEMINI variant_id primary key as identifier
        int variantId = -1;
        for (String identifier : variation.getIdentifiers())
        {
            if (isValidIdentifier(identifier))
            {
                variantId = variantId(identifier);
                break;
            }
        }
        if (variantId == -1)
        {
            throw new IllegalArgumentException("variation must contain a GEMINI variant_id identifier");
        }

        ProcessBuilder processBuilder = new ProcessBuilder("gemini", "query", "-q", "select v.rs_ids, v.ref, v.alt, vi.impact, v.chrom, v.start, v.end from variants v, variant_impacts vi where vi.variant_id = v.variant_id and v.variant_id=" + variantId, databaseName);

        BufferedReader reader = null;
        List<VariationConsequence> variationConsequences = new ArrayList<VariationConsequence>();
        try
        {
            Process process = processBuilder.start();
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while (reader.ready())
            {
                String line = reader.readLine();
                if (line == null)
                {
                    break;
                }
                String[] tokens = line.split("\t");
                // rs_ids is a comma-separated list of dbSNP ids
                List<String> identifiers = tokens[0] == "null" ? Collections.<String>emptyList() : ImmutableList.copyOf(tokens[0].split(","));
                String ref = tokens[1];
                String alt = tokens[2];
                // todo: impact may need to be mapped to sequence ontology
                String sequenceOntologyTerm = tokens[3];

                // note: gemini puts chr on the region name
                String region = tokens[4].replace("chr", "");

                int start = Integer.parseInt(tokens[5]);
                int end = Integer.parseInt(tokens[6]);

                variationConsequences.add(new VariationConsequence(species, reference, identifiers, ref, alt, sequenceOntologyTerm, region, start, end));
            }
        }
        catch (IOException e)
        {
            // ignore
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (Exception e)
            {
                // ignore
            }
        }
        return variationConsequences;
    }

    @Override
    public String toString()
    {
        return "GEMINI consequences (" + species + " " + reference + " " + databaseName + ")";
    }
}
