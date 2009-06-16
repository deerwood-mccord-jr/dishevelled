/*

    dsh-interpolate  Interpolation and easing functions.
    Copyright (c) 2009 held jointly by the individual authors.

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
package org.dishevelled.interpolate;

import java.util.Random;

/**
 * Random threshold interpolation function.  Generates random values (in red)
 * between <code>0.0d</code> and the value.  May be composed with an easing function (in blue).
 * <p><img src="../../../../images/random-threshold.png" alt="random threshold graph" /></p>
 *
 * @see EasingFunctions#compose
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class RandomThreshold
    implements EasingFunction
{
    /** Source of randomness. */
    private final Random random;


    /**
     * Create a new random threshold interpolation function with the specified source of randomness.
     *
     * @param random source of randomness, must not be null
     */
    public RandomThreshold(final Random random)
    {
        if (random == null)
        {
            throw new IllegalArgumentException("random must not be null");
        }
        this.random = random;
    }


    /** {@inheritDoc} */
    public final Double evaluate(final Double value)
    {
        return Math.max(0.0d, random.nextDouble() * value);
    }
}
