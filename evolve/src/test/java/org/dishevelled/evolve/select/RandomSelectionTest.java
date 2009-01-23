/*

    dsh-evolve  Framework for evolutionary algorithms.
    Copyright (c) 2005-2009 held jointly by the individual authors.

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
package org.dishevelled.evolve.select;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import org.dishevelled.evolve.Selection;
import org.dishevelled.evolve.AbstractSelectionTest;

import org.dishevelled.weighted.WeightedMap;
import org.dishevelled.weighted.HashWeightedMap;

/**
 * Unit test for RandomSelection.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class RandomSelectionTest
    extends AbstractSelectionTest
{

    /** {@inheritDoc} */
    protected <T> Selection<T> createSelection()
    {
        return new RandomSelection<T>();
    }

    public void testConstructor()
    {
        Selection<String> selection0 = new RandomSelection<String>();
        Selection<String> selection1 = new RandomSelection<String>(new Random());
        try
        {
            Selection<String> selection = new RandomSelection<String>(null);
            fail("ctr(null) expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            // expected
        }
    }

    public void testRandom()
    {
        RandomSelection<String> selection0 = new RandomSelection<String>();
        assertNotNull(selection0.getRandom());
        Random random = new Random();
        RandomSelection<String> selection1 = new RandomSelection<String>(random);
        assertNotNull(selection1.getRandom());
        assertEquals(random, selection1.getRandom());
    }

    public void testOneIndividual()
    {
        Selection<String> selection = createSelection();
        Collection<String> population = Collections.singleton("foo");
        WeightedMap<String> scores = new HashWeightedMap<String>();
        scores.put("foo", 1.0d);

        Collection<String> selected = selection.select(population, scores);
        assertNotNull(selected);
        assertEquals(1, selected.size());
        assertTrue(selected.contains("foo"));
    }

    public void testTwoIndividuals()
    {
        Selection<String> selection = createSelection();
        Collection<String> population = new ArrayList<String>();
        WeightedMap<String> scores = new HashWeightedMap<String>();
        population.add("foo");
        population.add("bar");
        scores.put("foo", 0.0d);
        scores.put("bar", 1.0d);

        Collection<String> selected = selection.select(population, scores);
        assertNotNull(selected);
        assertEquals(2, selected.size());
    }

    public void testManyIndividuals()
    {
        Selection<String> selection = createSelection();
        Collection<String> population = new ArrayList<String>();
        WeightedMap<String> scores = new HashWeightedMap<String>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++)
        {
            String individual = "individual" + i;
            population.add(individual);
            scores.put(individual, random.nextDouble());
        }

        Collection<String> selected = selection.select(population, scores);
        assertNotNull(selected);
        assertEquals(population.size(), selected.size());
    }
}
