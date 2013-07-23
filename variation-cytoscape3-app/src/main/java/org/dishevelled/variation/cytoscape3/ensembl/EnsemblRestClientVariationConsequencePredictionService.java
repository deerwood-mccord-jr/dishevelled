/*

    dsh-variation-cytoscape3-app  Variation Cytoscape3 app.
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
package org.dishevelled.variation.cytoscape3.ensembl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.heuermh.ensemblrestclient.Allele;
import com.github.heuermh.ensemblrestclient.Transcript;
import com.github.heuermh.ensemblrestclient.VariationService;

import org.dishevelled.variation.cytoscape3.VariationConsequence;
import org.dishevelled.variation.cytoscape3.VariationConsequencePredictionService;

/**
 * Ensembl REST client variation consequence prediction service.
 */
final class EnsemblRestClientVariationConsequencePredictionService
    implements VariationConsequencePredictionService
{
    private final String species;
    private final String reference;
    private final VariationService variationService;
    private static final Pattern ALLELE_STRING = Pattern.compile("^(.*)/(.*)$");

    //@Inject
    EnsemblRestClientVariationConsequencePredictionService(final String species,
                                                           final String reference,
                                                           final VariationService variationService)
    {
        checkNotNull(species);
        checkNotNull(reference);
        checkNotNull(variationService);

        this.species = species;
        this.reference = reference;
        this.variationService = variationService;
    }


    @Override
    public List<VariationConsequence> predictVariationConsequences(final String species,
                                                                   final String reference,
                                                                   final String referenceAllele,
                                                                   final String alternateAllele,
                                                                   final String name,
                                                                   final int start,
                                                                   final int end,
                                                                   final int strand)
    {
        checkNotNull(species);
        checkNotNull(reference);
        checkNotNull(referenceAllele);
        checkNotNull(alternateAllele);
        checkNotNull(name);
        checkArgument(this.species.equals(species));
        checkArgument(this.reference.equals(reference));
        String region = name + ":" + start + "-" + end + ":" + strand;
        String alleleString = referenceAllele + "/" + alternateAllele;

        List<VariationConsequence> consequences = new ArrayList<VariationConsequence>();
        for (Transcript transcript : variationService.consequences(species, region, alleleString).getTranscripts())
        {
            // only use canonical transcript
            if (transcript.isCanonical())
            {
                for (Allele allele : transcript.getAlleles())
                {
                    // parse allele string
                    Matcher matcher = ALLELE_STRING.matcher(allele.getAlleleString());
                    if (matcher.matches())
                    {
                        String ref = matcher.group(1);
                        String alt = matcher.group(2);

                        for (String consequenceTerm : allele.getConsequenceTerms())
                        {
                            consequences.add(new VariationConsequence(ref, alt, consequenceTerm));
                        }
                    }
                }
            }
        }
        return consequences;
    }
}