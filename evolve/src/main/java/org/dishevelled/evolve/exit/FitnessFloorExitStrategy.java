/*

    dsh-evolve  Framework for evolutionary algorithms.
    Copyright (c) 2005-2007 held jointly by the individual authors.

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
package org.dishevelled.evolve.exit;

import org.dishevelled.weighted.WeightedMap;

import org.dishevelled.evolve.ExitStrategy;

/**
 * Fitness floor exit strategy.  Exits as soon as every individual has a fitness score
 * above a set lower bound.
 *
 * @param <I> individual type
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class FitnessFloorExitStrategy<I>
    implements ExitStrategy<I>
{
    /** Lower bound for this fitness floor exit strategy. */
    private final double lowerBound;


    /**
     * Create a new fitness floor exit strategy with the specified lower bound.
     *
     * @param lowerBound lower bound for this fitness floor exit strategy
     */
    public FitnessFloorExitStrategy(final double lowerBound)
    {
        this.lowerBound = lowerBound;
    }


    /** {@inheritDoc} */
    public boolean evaluate(final WeightedMap<I> population, final int time)
    {
        for (I i : population.keySet())
        {
            double fitness = population.get(i);

            if (fitness <= lowerBound)
            {
                return false;
            }
        }
        return true;
    }
}
