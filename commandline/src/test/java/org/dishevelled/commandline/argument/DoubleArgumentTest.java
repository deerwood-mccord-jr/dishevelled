/*

    dsh-commandline  Simple command line parser based on typed arguments.
    Copyright (c) 2004-2005 held jointly by the individual authors.

    This library is free software; you can redistribute it and/or modify it
    under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation; either version 2.1 of the License, or (at
    your option) any later version.

    This library is distributed in the hope that it will be useful, but WITHOUT
    ANY WARRANTY; with out even the implied warranty of MERCHANTABILITY or
    FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
    License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this library;  if not, write to the Free Software Foundation,
    Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA.

    > http://www.gnu.org/copyleft/lesser.html
    > http://www.opensource.org/licenses/lgpl-license.php

*/
package org.dishevelled.commandline.argument;

import junit.framework.TestCase;

/**
 * Unit test for DoubleArgument.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Double$
 */
public class DoubleArgumentTest
    extends TestCase
{

    public void testDoubleArgument()
    {
        DoubleArgument da = new DoubleArgument("d", "double", "Double argument", true);
        assertNotNull("da not null", da);
        assertEquals("da shortName == d", "d", da.getShortName());
        assertEquals("da longName == double", "double", da.getLongName());
        assertEquals("da description == Double argument", "Double argument", da.getDescription());
        assertTrue("da isRequired", da.isRequired());
        assertFalse("da wasFound == false", da.wasFound());
        assertEquals("da value == null", null, da.getValue());
    }
}