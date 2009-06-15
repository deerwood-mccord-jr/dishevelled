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

/**
 * Ease-in elastic interpolation function.
 * <img src="doc-files/ease-in-elastic.png" alt="ease-in elastic graph" />
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class EaseInElastic
    extends AbstractEasingFunction
{
    /** <code>Math.PI * 2.0d</code>. */
    private static final double PI_TIMES_2 = Math.PI * 2.0d;


    /** {@inheritDoc} */
    public String getName()
    {
        return "ease-in-elastic";
    }

    /** {@inheritDoc} */
    public String getDescription()
    {
        return "Ease-in elastic interpolation function";
    }

    /** {@inheritDoc} */
    public Double evaluate(final Double value)
    {
        double s = 0.3d / 4.0d;
        double q = value - 1.0d;
        return -1.0d * (Math.pow(2.0, 10.0d * q) * Math.sin((q - s) * (PI_TIMES_2) / 0.3d));
    }
}