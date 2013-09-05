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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import com.google.common.collect.ImmutableList;

import com.google.common.io.Files;
import com.google.common.io.Resources;

import org.dishevelled.variation.Variation;
import org.dishevelled.variation.VariationConsequence;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for SnpEffVcfVariationConsequenceService.
 */
public final class SnpEffVcfVariationConsequenceServiceTest
{
    private String species;
    private String reference;
    private File file;
    private SnpEffVcfVariationConsequenceService consequenceService;

    @Before
    public void setUp() throws Exception
    {
        species = "human";
        reference = "GRCh37";
        file = File.createTempFile("snpEffVcfVariationServiceTest", ".vcf");

        consequenceService = new SnpEffVcfVariationConsequenceService(species, reference, file);
    }

    @Test(expected=NullPointerException.class)
    public void testConstructorNullSpecies()
    {
        new SnpEffVcfVariationConsequenceService(null, reference, file);
    }

    @Test(expected=NullPointerException.class)
    public void testConstructorNullReference()
    {
        new SnpEffVcfVariationConsequenceService(species, null, file);
    }

    @Test(expected=NullPointerException.class)
    public void testConstructorNullFile()
    {
        new SnpEffVcfVariationConsequenceService(species, reference, null);
    }

    @Test
    public void testConstructor()
    {
        assertNotNull(consequenceService);
    }

    @Test(expected=NullPointerException.class)
    public void testConsequencesNullVariation()
    {
        consequenceService.consequences(null);
    }

    @Test
    public void testConsequences() throws Exception
    {
        Files.write(Resources.toByteArray(getClass().getResource("ALL.chr22.phase1_release_v3.20101123.snps_indels_svs.genotypes-2-indv-thin-20000bp-trim.eff.vcf")), file);

        Variation variation = new Variation(species, reference, "rs193189309", "C", ImmutableList.of("T"), "22", 17452052, 17452052, 1);
        boolean found = false;
        for (VariationConsequence consequence : consequenceService.consequences(variation))
        {
            // SnpEff provides effects for all transcripts, so there are duplicate consequences here
            //   consider using -canon command line option; although the canonical transcript that SnpEff uses might not match that defined by Ensembl
            //System.out.println(consequence.getIdentifier() + "\t" + consequence.getReferenceAllele() + "\t" + consequence.getAlternateAllele() + "\t" + consequence.getSequenceOntologyTerm());
            if ("rs193189309".equals(consequence.getIdentifier()))
            {
                found = true;
            }
        }
        assertTrue(found);
    }
}