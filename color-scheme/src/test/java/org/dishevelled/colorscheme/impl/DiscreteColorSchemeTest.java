/*

    dsh-color-scheme  Color schemes.
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
package org.dishevelled.colorscheme.impl;

import java.awt.Color;

import junit.framework.TestCase;

//import static org.dishevelled.collect.Lists.*;
import static com.google.common.collect.Lists.*;

import org.dishevelled.colorscheme.ColorScheme;

import org.dishevelled.colorscheme.factory.DefaultColorFactory;

import org.dishevelled.colorscheme.interpolate.Interpolations;

/**
 * Unit test for DiscreteColorScheme.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class DiscreteColorSchemeTest
    extends TestCase
{

    public void testConstructor()
    {
        // empty
    }

    public void testTwoColorScheme()
    {
        //ColorScheme colorScheme = new DiscreteColorScheme("name", createList(Color.BLACK, Color.WHITE) 0.0d, 1.0d, new DefaultColorFactory(), Interpolations.LINEAR);
        ColorScheme colorScheme = new DiscreteColorScheme("name", newArrayList(Color.BLACK, Color.WHITE), 0.0d, 1.0d, new DefaultColorFactory(), Interpolations.LINEAR);
        assertNotNull(colorScheme);
        //assertEquals("name", colorScheme.getName());
        // out of bounds
        assertEquals(Color.BLACK, colorScheme.getColor(-1.0d));
        assertEquals(Color.WHITE, colorScheme.getColor(2.0d));
        // within bounds
        assertEquals(Color.BLACK, colorScheme.getColor(0.25));
        assertEquals(Color.WHITE, colorScheme.getColor(0.75));
        // at bounds
        assertEquals(Color.BLACK, colorScheme.getColor(0.50));
    }

    public void testEvenColorScheme()
    {
        //ColorScheme colorScheme = new DiscreteColorScheme("name", createList(Color.BLACK, Color.RED, Color.BLUE, Color.WHITE), 0.0d, 1.0d, new DefaultColorFactory(), Interpolations.LINEAR);
        ColorScheme colorScheme = new DiscreteColorScheme("name", newArrayList(Color.BLACK, Color.RED, Color.BLUE, Color.WHITE), 0.0d, 1.0d, new DefaultColorFactory(), Interpolations.LINEAR);
        assertNotNull(colorScheme);
        //assertEquals("name", colorScheme.getName());
        // out of bounds
        assertEquals(Color.BLACK, colorScheme.getColor(-1.0d));
        assertEquals(Color.WHITE, colorScheme.getColor(2.0d));
        // within bounds
        assertEquals(Color.BLACK, colorScheme.getColor(0.125d));
        assertEquals(Color.RED, colorScheme.getColor(0.375d));
        assertEquals(Color.BLUE, colorScheme.getColor(0.625d));
        assertEquals(Color.WHITE, colorScheme.getColor(0.875d));
        // at bounds
        assertEquals(Color.BLACK, colorScheme.getColor(0.25d));
        assertEquals(Color.RED, colorScheme.getColor(0.50d));
        assertEquals(Color.BLUE, colorScheme.getColor(0.75d));
        assertEquals(Color.WHITE, colorScheme.getColor(1.0d));
    }

    public void testOddColorScheme()
    {
        //ColorScheme colorScheme = new DiscreteColorScheme("name", createList(Color.BLACK, Color.RED, Color.WHITE), 0.0d, 1.0d, new DefaultColorFactory(), Interpolations.LINEAR);
        ColorScheme colorScheme = new DiscreteColorScheme("name", newArrayList(Color.BLACK, Color.RED, Color.WHITE), 0.0d, 1.0d, new DefaultColorFactory(), Interpolations.LINEAR);
        assertNotNull(colorScheme);
        //assertEquals("name", colorScheme.getName());
        // out of bounds
        assertEquals(Color.BLACK, colorScheme.getColor(-1.0d));
        assertEquals(Color.WHITE, colorScheme.getColor(2.0d));
        // within bounds
        assertEquals(Color.BLACK, colorScheme.getColor(0.25d));
        assertEquals(Color.RED, colorScheme.getColor(0.50d));
        assertEquals(Color.WHITE, colorScheme.getColor(0.75d));
        // at bounds
        assertEquals(Color.BLACK, colorScheme.getColor(0.33d));
        assertEquals(Color.RED, colorScheme.getColor(0.66d));
        assertEquals(Color.WHITE, colorScheme.getColor(1.0d));
    }
}