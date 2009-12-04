/*

    dsh-venn  Models for venn diagrams.
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
package org.dishevelled.venn.impl;

import java.util.Set;

import org.dishevelled.venn.AbstractBinaryVennModelTest;
import org.dishevelled.venn.BinaryVennModel;

/**
 * Unit test for BinaryVennModelImpl.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class BinaryVennModelImplTest
    extends AbstractBinaryVennModelTest
{

    /** {@inheritDoc} */
    protected <T> BinaryVennModel<T> createBinaryVennModel(final Set<? extends T> set0, final Set<? extends T> set1)
    {
	return new BinaryVennModelImpl(set0, set1);
    }

    public void testConstructor()
    {
	try
        {
	    new BinaryVennModelImpl(null, SET1);
	    fail("ctr(, null) expected IllegalArgumentException");
        }
	catch (IllegalArgumentException e)
        {
	    // expected
        }
	try
        {
	    new BinaryVennModelImpl(SET0, null);
	    fail("ctr(null,) expected IllegalArgumentException");
        }
	catch (IllegalArgumentException e)
        {
	    // expected
        }
	try
        {
	    new BinaryVennModelImpl(null, null);
	    fail("ctr(null, null) expected IllegalArgumentException");
        }
	catch (IllegalArgumentException e)
        {
	    // expected
        }
    }
}