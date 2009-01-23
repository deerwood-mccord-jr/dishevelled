/*

    dsh-swarm  Framework for particle swarm optimization algorithms.
    Copyright (c) 2006-2009 held jointly by the individual authors.

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
package org.dishevelled.swarm.fitness;

import java.util.Random;

import org.dishevelled.swarm.Fitness;
import org.dishevelled.swarm.AbstractFitnessTest;

/**
 * Unit test for RandomFitness.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class RandomFitnessTest
    extends AbstractFitnessTest
{

    /** {@inheritDoc} */
    protected Fitness createFitness()
    {
        return new RandomFitness();
    }

    public void testConstructor()
    {
        RandomFitness randomFitness0 = new RandomFitness();
        RandomFitness randomFitness1 = new RandomFitness(new Random());

        try
        {
            RandomFitness randomFitness = new RandomFitness(null);
            fail("ctr(null) expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            // expected
        }
    }
}