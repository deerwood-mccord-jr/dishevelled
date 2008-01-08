/*

    dsh-piccolo-transition  Transitions implemented as Piccolo activities.
    Copyright (c) 2007-2008 held jointly by the individual authors.

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
package org.dishevelled.piccolo.transition;

import junit.framework.TestCase;

import edu.umd.cs.piccolo.PNode;

/**
 * Unit test for DimTransition.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class DimTransitionTest
    extends TestCase
{

    public void testConstructor()
    {
        PNode node = new PNode();
        DimTransition dimTransition0 = new DimTransition(node, 0);
        DimTransition dimTransition1 = new DimTransition(node, 1L);
        DimTransition dimTransition2 = new DimTransition(node, -1L);
        DimTransition dimTransition3 = new DimTransition(node, 500L);
        DimTransition dimTransition4 = new DimTransition(node, -500L);
        DimTransition dimTransition5 = new DimTransition(node, Long.MAX_VALUE);
        DimTransition dimTransition6 = new DimTransition(node, Long.MIN_VALUE);
        DimTransition dimTransition7 = new DimTransition(node, 500L, 0.0f);
        DimTransition dimTransition8 = new DimTransition(node, 500L, 0.5f);
        DimTransition dimTransition9 = new DimTransition(node, 500L, 1.0f);

        try
        {
            DimTransition dimTransition = new DimTransition(null, 500L);
            fail("ctr(null) expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            // expected
        }
        try
        {
            DimTransition dimTransition = new DimTransition(node, 500L, -0.1f);
            fail("ctr(,,-0.1f) expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            // expected
        }
        try
        {
            DimTransition dimTransition = new DimTransition(node, 500L, 1.1f);
            fail("ctr(,,1.1f) expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            // expected
        }
    }

    public void testSetRelativeValue()
    {
        PNode node = new PNode();
        DimTransition dimTransition = new DimTransition(node, 500L);
        dimTransition.setRelativeTargetValue(0.0f);
        dimTransition.setRelativeTargetValue(0.5f);
        dimTransition.setRelativeTargetValue(1.0f);
    }

    public void testDestinationTransparency()
    {
        PNode node = new PNode();
        DimTransition dimTransition0 = new DimTransition(node, 1L);
        assertEquals(DimTransition.DEFAULT_DESTINATION_TRANSPARENCY, dimTransition0.getDestinationTransparency(), 0.1f);
        DimTransition dimTransition1 = new DimTransition(node, 1L, 0.75f);
        assertEquals(0.75f, dimTransition1.getDestinationTransparency(), 0.1f);
    }
}