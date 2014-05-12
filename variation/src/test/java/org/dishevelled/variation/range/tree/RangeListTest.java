/*

    dsh-variation  Variation.
    Copyright (c) 2013-2014 held jointly by the individual authors.

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
package org.dishevelled.variation.range.tree;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Range;

/**
 * Unit test for RangeList.
 */
public final class RangeListTest extends AbstractRangeTreeTest {

    @Override
    protected <C extends Comparable> RangeTree<C> create(final Range<C>... ranges) {
        return create(ImmutableList.copyOf(ranges));
    }

    @Override
    protected <C extends Comparable> RangeTree<C> create(final List<Range<C>> ranges) {
        return RangeList.create(ranges);
    }
}
