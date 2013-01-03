/*

    dsh-venn  Lightweight components for venn diagrams.
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
package org.dishevelled.venn.layout;

import org.dishevelled.bitset.ImmutableBitSet;
import org.dishevelled.bitset.MutableBitSet;

/**
 * Static utility methods for venn diagram layouts.
 *
 * @author  Michael Heuer
 */
final class VennLayoutUtils
{

    /**
     * Private no-arg constructor.
     */
    private VennLayoutUtils()
    {
        // empty
    }


    /**
     * Create and return a new immutable bit set with the specified bits set to true.
     *
     * @param index first index to set to true
     * @param additional variable number of additional indices to set to true, if any
     * @return a new immutable bit set with the specified bits set to true
     */
    static ImmutableBitSet toImmutableBitSet(final int index, final int... additional)
    {
        int size = 1 + ((additional == null) ? 0 : additional.length);
        MutableBitSet mutableBitSet = new MutableBitSet(size);
        mutableBitSet.set(index);
        if (additional != null)
        {
            for (int i = 0; i < additional.length; i++)
            {
                mutableBitSet.set(additional[i]);
            }
        }
        mutableBitSet.trimTrailingZeros();
        return mutableBitSet.immutableCopy();
    }
}