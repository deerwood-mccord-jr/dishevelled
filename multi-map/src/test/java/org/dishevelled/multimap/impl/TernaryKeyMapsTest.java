/*

    dsh-multi-map  Multi-key map interfaces and implementation.
    Copyright (c) 2007-2011 held jointly by the individual authors.

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
package org.dishevelled.multimap.impl;

import junit.framework.TestCase;

import org.dishevelled.multimap.TernaryKeyMap;

import static org.dishevelled.multimap.impl.TernaryKeyMaps.*;

/**
 * Unit test for TernaryKeyMaps.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class TernaryKeyMapsTest
    extends TestCase
{

    public void testCreateTernaryKeyMap()
    {
        TernaryKeyMap<Float, Object, String, Integer> ternaryKeyMap = createTernaryKeyMap();
        assertNotNull(ternaryKeyMap);
    }

    public void testCreateTernaryKeyMapInitialCapacity()
    {
        TernaryKeyMap<Float, Object, String, Integer> ternaryKeyMap = createTernaryKeyMap(32);
        assertNotNull(ternaryKeyMap);
    }

    public void testCreateTernaryKeyMapInitialCapacityLoadFactorThreshold()
    {
        TernaryKeyMap<Float, Object, String, Integer> ternaryKeyMap = createTernaryKeyMap(32, 0.5f, 16);
        assertNotNull(ternaryKeyMap);
    }
}