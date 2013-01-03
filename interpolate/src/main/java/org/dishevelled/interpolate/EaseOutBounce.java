/*

    dsh-interpolate  Interpolation and easing functions.
    Copyright (c) 2009-2013 held jointly by the individual authors.

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
 * Ease-out bounce interpolation function.
 * <p><img src="../../../../images/ease-out-bounce.png" alt="ease-out bounce graph" /></p>
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class EaseOutBounce
    extends AbstractBounceEasingFunction
{

    /** {@inheritDoc} */
    public String toString()
    {
        return "ease-out-bounce";
    }

    /** {@inheritDoc} */
    public Double evaluate(final Double value)
    {
        return easeOut(value);
    }
}