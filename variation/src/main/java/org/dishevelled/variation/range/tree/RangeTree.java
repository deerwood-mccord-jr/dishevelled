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

import java.util.Set;

import com.google.common.collect.Range;

/**
 * Range tree.
 *
 * @param <C> range endpoint type
 */
public interface RangeTree<C extends Comparable> {

    /**
     * Return the number of ranges in this range tree.
     *
     * @return the number of ranges in this range tree
     */
    int size();

    /**
     * Return true if the number of ranges in this range tree is zero.
     *
     * @return true if the number of ranges in this range tree is zero
     */
    boolean isEmpty();

    /**
     * Return true if the specified location intersects with any ranges in this range tree.
     *
     * @param location location to intersect
     * @return true if the specified location intersects with any ranges in this range tree
     */
    boolean contains(C location);

    /**
     * Return the number of ranges in this range tree at the specified location.
     *
     * @param location location
     * @return the number of ranges in this range tree at the specified location
     */
    int count(C location);

    /**
     * Return the ranges in this range tree at the specified location, if any.
     *
     * @param location location
     * @return the ranges in this range tree at the specified location, if any
     */
    Iterable<Range<C>> query(C location);

    /**
     * Return the number of ranges in this range tree that intersect the specified query range.
     *
     * @param query range to intersect, must not be null
     * @return the number of ranges in this range tree that intersect the specified query range
     */
    int count(Range<C> query);

    /**
     * Return the ranges in this range tree that intersect the specified query range, if any.
     *
     * @param query range to intersect, must not be null
     * @return the ranges in this range tree that intersect the specified query range, if any
     */
    Iterable<Range<C>> intersect(Range<C> query);

    /**
     * Return true if the specified query range intersects with any ranges in this range tree.
     *
     * @param query range to intersect, must not be null
     * @return true if the specified query range intersects with any ranges in this range tree
     */
    boolean intersects(Range<C> query);

    /**
     * Return the intersection of the ranges in this range tree with the specified query list of
     * ranges as intersecting pairs of ranges, if any.
     *
     * @param query list of ranges to intersect, must not be null
     * @return the intersection of the ranges in this range tree with the specified query list of
     *    ranges as intersecting pairs of ranges, if any
     */
    Iterable<Set<Range<C>>> intersect(Iterable<Range<C>> query);

    /**
     * Return true if any range in the specified query list of ranges intersects
     * with any ranges in this range tree.
     *
     * @param query list of ranges to intersect, must not be null
     * @return true if any range in the specified query list of ranges intersects
     *    with any ranges in this range tree
     */
    boolean intersects(Iterable<Range<C>> query);

    /*

       Additional queries:

       Iterable<Range<C>> closest(Range<C> query);
       Iterable<Set<Range<C>>> closest(Iterable<Range<C>> query);

       see
       http://bedtools.readthedocs.org/en/latest/content/tools/closest.html

       Iterable<Range<C>> window(Range<C> query, int window);
       Iterable<Range<C>> window(Range<C> query, int upstream, int downstream);
       Iterable<Range<C>> window(Iterable<Range<C>> query, int window);
       Iterable<Set<Range<C>>> window(Iterable<Range<C>> query, int upstream, int downstream);

       see
       http://bedtools.readthedocs.org/en/latest/content/tools/window.html

     */
}
