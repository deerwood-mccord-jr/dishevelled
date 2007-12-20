/*

    dsh-evolve  Framework for evolutionary algorithms.
    Copyright (c) 2005-2007 held jointly by the individual authors.

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
package org.dishevelled.evolve.exit;

import java.util.Collection;
import java.util.Collections;

import org.dishevelled.evolve.AbstractExitStrategyTest;
import org.dishevelled.evolve.ExitStrategy;

import org.dishevelled.weighted.HashWeightedMap;
import org.dishevelled.weighted.WeightedMap;

/**
 * Unit test for FitnessThresholdExitStrategy.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class FitnessThresholdExitStrategyTest
    extends AbstractExitStrategyTest
{
    /** Fitness threshold. */
    private static final double THRESHOLD = 0.75d;


    /** {@inheritDoc} */
    protected <T> ExitStrategy<T> createExitStrategy()
    {
        return new FitnessThresholdExitStrategy<T>(THRESHOLD);
    }

    public void testFitnessThresholdExitStrategy()
    {
        ExitStrategy<String> exitStrategy = createExitStrategy();
        Collection<String> population = Collections.singleton("foo");
        WeightedMap<String> scores = new HashWeightedMap<String>();

        scores.put("foo", THRESHOLD - 0.1d);
        assertFalse(exitStrategy.evaluate(population, scores, 0));

        scores.put("foo", THRESHOLD);
        assertFalse(exitStrategy.evaluate(population, scores, 0));

        scores.put("foo", THRESHOLD + 0.1d);
        assertTrue(exitStrategy.evaluate(population, scores, 0));
    }
}
