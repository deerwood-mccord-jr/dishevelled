/*

    dsh-variation  Variation.
    Copyright (c) 2013-2015 held jointly by the individual authors.

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
package org.dishevelled.variation;

/**
 * Feature service.
 *
 * @author  Michael Heuer
 */
public interface FeatureService
{

    /**
     * Return a feature representing the genomic coordinates for the specified identifier
     * within the specified reference, if any.
     *
     * @param species species, must not be null
     * @param reference reference, must not be null
     * @param identifier identifier, must not be null
     * @return a feature representing the genomic coordinates for the specified identifier
     *    within the specified reference or <code>null</code> if no such feature exists
     */
    Feature feature(String species,
                    String reference,
                    String identifier);

    /**
     * Return a feature representing the specified genomic coordinates within the
     * specified reference, if any.
     *
     * @param species species, must not be null
     * @param reference reference, must not be null
     * @param region region, must not be null
     * @param start start, using interbase, zero-start coordinate system
     * @param end end, using interbase, zero-start coordinate system
     * @param strand strand
     * @return a feature representing the specified genomic coordinates within the
     *    specified reference or <code>null</code> if no such feature exists
     */
    Feature feature(String species,
                    String reference,
                    String region,
                    long start,
                    long end,
                    int strand);
}
