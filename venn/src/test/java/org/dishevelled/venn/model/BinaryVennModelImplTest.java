/*

    dsh-venn  Lightweight components for venn diagrams.
    Copyright (c) 2009-2019 held jointly by the individual authors.

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
package org.dishevelled.venn.model;

import java.util.Set;

import org.dishevelled.venn.AbstractBinaryVennModelTest;
import org.dishevelled.venn.BinaryVennModel;

import org.dishevelled.venn.model.BinaryVennModelImpl;

/**
 * Unit test for BinaryVennModelImpl.
 *
 * @author  Michael Heuer
 */
public final class BinaryVennModelImplTest
    extends AbstractBinaryVennModelTest
{

    /** {@inheritDoc} */
    protected <T> BinaryVennModel<T> createBinaryVennModel(final Set<? extends T> first, final Set<? extends T> second)
    {
        return new BinaryVennModelImpl<T>(first, second);
    }

    public void testConstructor()
    {
        assertNotNull(new BinaryVennModelImpl<String>());
        assertNotNull(new BinaryVennModelImpl<String>(FIRST, SECOND));

        try
        {
            new BinaryVennModelImpl<String>(null, SECOND);
            fail("ctr(, null) expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            // expected
        }
        try
        {
            new BinaryVennModelImpl<String>(FIRST, null);
            fail("ctr(null,) expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            // expected
        }
        try
        {
            new BinaryVennModelImpl<String>(null, null);
            fail("ctr(null, null) expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            // expected
        }
    }
}
