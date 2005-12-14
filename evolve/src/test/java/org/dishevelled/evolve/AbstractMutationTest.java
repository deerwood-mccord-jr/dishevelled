/*

    dsh-evolve  Simple framework for evolutionary algorithms.
    Copyright (c) 2005 held jointly by the individual authors.

    This library is free software; you can redistribute it and/or modify it
    under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation; either version 2.1 of the License, or (at
    your option) any later version.

    This library is distributed in the hope that it will be useful, but WITHOUT
    ANY WARRANTY; with out even the implied warranty of MERCHANTABILITY or
    FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
    License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this library;  if not, write to the Free Software Foundation,
    Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA.

    > http://www.gnu.org/copyleft/lesser.html
    > http://www.opensource.org/licenses/lgpl-license.php

*/
package org.dishevelled.evolve;

import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

/**
 * Abstract unit test for implementations of Mutation.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public abstract class AbstractMutationTest
    extends TestCase
{

    /**
     * Create and return a new instance of Mutation to test.
     *
     * @return a new instance of Mutation to test
     */
    protected abstract <T> Mutation<T> createMutation();

    /**
     * Return a map of expected values, where the keys are sets
     * of recombined individuals and the values are sets of mutated
     * individuals.
     *
     * @param t instance of generic type T
     * @return a map of expected values
     */
    protected abstract <T> Map<Set<T>, Set<T>> getExpectedValues(T t);


    public void testIntegerMutation()
    {
        Mutation<Integer> mutation = createMutation();
        Integer individual = Integer.valueOf(0);
        Map<Set<Integer>, Set<Integer>> expectedValues = getExpectedValues(individual);

        for (Set<Integer> recombined : expectedValues.keySet())
        {
            assertEquals(expectedValues.get(recombined), mutation.mutate(recombined));
        }
    }

    public void testDoubleMutation()
    {
        Mutation<Double> mutation = createMutation();
        Double individual = Double.valueOf(0.0d);
        Map<Set<Double>, Set<Double>> expectedValues = getExpectedValues(individual);

        for (Set<Double> recombined : expectedValues.keySet())
        {
            assertEquals(expectedValues.get(recombined), mutation.mutate(recombined));
        }
    }

    public void testStringMutation()
    {
        Mutation<String> mutation = createMutation();
        String individual = new String("foo");
        Map<Set<String>, Set<String>> expectedValues = getExpectedValues(individual);

        for (Set<String> recombined : expectedValues.keySet())
        {
            assertEquals(expectedValues.get(recombined), mutation.mutate(recombined));
        }
    }
}