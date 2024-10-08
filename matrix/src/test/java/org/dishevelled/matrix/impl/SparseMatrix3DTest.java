/*

    dsh-matrix  long-addressable bit and typed object matrix implementations.
    Copyright (c) 2004-2013 held jointly by the individual authors.

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
package org.dishevelled.matrix.impl;

import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.dishevelled.functor.QuaternaryProcedure;

import org.dishevelled.matrix.Matrix3D;
import org.dishevelled.matrix.AbstractMatrix3DTest;

/**
 * Unit test for SparseMatrix3D.
 *
 * @author  Michael Heuer
 */
public class SparseMatrix3DTest
    extends AbstractMatrix3DTest
{

    /** @see AbstractMatrix3D */
    protected <T> Matrix3D<T> createMatrix3D(final long slices, final long rows, final long columns)
    {
        Matrix3D<T> m = new SparseMatrix3D<T>(slices, rows, columns);
        return m;
    }


    //
    // serialization tests

    public void testEmptySerialization()
        throws Exception
    {
        final SparseMatrix3D<String> m0 = new SparseMatrix3D<String>(10, 10, 10);
        assertNotNull("m0 not null", m0);
        assertEquals("m0 size == 1000", 1000, m0.size());
        assertEquals("m0 cardinality == 0", 0, m0.cardinality());
        assertTrue("m0 instanceof Serializable", (m0 instanceof Serializable));

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(buffer);
        out.writeObject(m0);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
        Object dest = in.readObject();
        in.close();

        assertNotNull("dest not null", dest);
        assertTrue("dest instanceof SparseMatrix3D", (dest instanceof SparseMatrix3D));

        final SparseMatrix3D<String> m1 = (SparseMatrix3D<String>) dest;

        assertEquals("m1 size == m0 size", m0.size(), m1.size());
        assertEquals("m1 cardinality == m0 cardinality", m0.cardinality(), m1.cardinality());

        m1.forEach(new QuaternaryProcedure<Long, Long, Long, String>()
            {
                public void run(final Long slice, final Long row, final Long column, final String ignore)
                {
                    assertEquals("m1 get(" + slice + ", " + row + ", " + column + ") == m0 get(" + slice + ", " + row + ", " + column + ")",
                                 m0.get(slice, row, column), m1.get(slice, row, column));
                }
            });
    }

    public void testFullSerialization()
        throws Exception
    {
        final SparseMatrix3D<String> m0 = new SparseMatrix3D<String>(10, 10, 10);
        assertNotNull("m0 not null", m0);

        m0.forEach(new QuaternaryProcedure<Long, Long, Long, String>()
            {
                public void run(final Long slice, final Long row, final Long column, final String ignore)
                {
                    m0.set(slice, row, column, slice + "x" + row + "x" + column);
                }
            });

        assertEquals("m0 size == 1000", 1000, m0.size());
        assertEquals("m0 cardinality == 1000", 1000, m0.cardinality());
        assertTrue("m0 instanceof Serializable", (m0 instanceof Serializable));

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(buffer);
        out.writeObject(m0);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
        Object dest = in.readObject();
        in.close();

        assertNotNull("dest not null", dest);
        assertTrue("dest instanceof SparseMatrix3D", (dest instanceof SparseMatrix3D));

        final SparseMatrix3D<String> m1 = (SparseMatrix3D<String>) dest;

        assertEquals("m1 size == m0 size", m0.size(), m1.size());
        assertEquals("m1 cardinality == m0 cardinality", m0.cardinality(), m1.size());

        m1.forEach(new QuaternaryProcedure<Long, Long, Long, String>()
            {
                public void run(final Long slice, final Long row, final Long column, final String ignore)
                {
                    assertEquals("m1 get(" + slice + ", " + row + ", " + column + ") == m0 get(" + slice + ", " + row + ", " + column + ")",
                                 m0.get(slice, row, column), m1.get(slice, row, column));
                }
            });
    }
}
