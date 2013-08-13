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
package org.dishevelled.variation.synthetic;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Random;

import com.google.inject.Inject;

import org.dishevelled.variation.Feature;
import org.dishevelled.variation.FeatureService;

/**
 * Synthetic feature service.
 */
final class SyntheticFeatureService
    implements FeatureService
{
    private final SyntheticGenome genome;
    private final Random random = new Random();

    @Inject
    SyntheticFeatureService(final SyntheticGenome genome)
    {
        checkNotNull(genome);
        this.genome = genome;
    }

    @Override
    public Feature feature(final String species, final String reference, final String identifier)
    {
        checkNotNull(species);
        checkNotNull(reference);
        checkNotNull(identifier);
        checkArgument(genome.getSpecies().equals(species));
        checkArgument(genome.getReference().equals(reference));

        String name = genome.getNames().get(random.nextInt(genome.getNames().size()));
        int length = genome.getLengths().get(name);
        int featureLength = 300 + random.nextInt(length/1000 - 300);
        int start = random.nextInt(length);
        int end = start + featureLength;

        return new Feature(genome.getSpecies(), genome.getReference(), identifier, name, start, end, 1);
    }

    @Override
    public String toString()
    {
        return "Synthetic features (" + genome.getSpecies() + " " + genome.getReference() + ")";
    }
}