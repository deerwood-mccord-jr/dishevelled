/*

    dsh-matrix-io  Matrix readers and writers.
    Copyright (c) 2008-2010 held jointly by the individual authors.

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
package org.dishevelled.matrix.io.impl;

import java.io.IOException;

import org.dishevelled.matrix.Matrix2D;

import org.dishevelled.matrix.impl.SparseMatrix2D;

import org.dishevelled.matrix.io.AbstractMatrix2DWriterTest;
import org.dishevelled.matrix.io.Matrix2DWriter;

/**
 * Unit test for ValuesMatrix2DWriter.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class ValuesMatrix2DWriterTest
    extends AbstractMatrix2DWriterTest
{

    /** {@inheritDoc} */
    protected <E> Matrix2DWriter<E> createMatrix2DWriter()
    {
        return new ValuesMatrix2DWriter<E>();
    }

    public void testEmptyMatrix() throws IOException
    {
        Matrix2D<String> matrix = new SparseMatrix2D<String>(0L, 0L);
        Matrix2DWriter<String> writer = new ValuesMatrix2DWriter<String>();
        StringBuffer appendable = new StringBuffer();
        appendable = writer.append(matrix, appendable);
        assertEquals("[]", appendable.toString());
    }

    public void testOneElementMatrix() throws IOException
    {
        Matrix2D<String> matrix = new SparseMatrix2D<String>(1L, 1L);
        matrix.setQuick(0L, 0L, "foo");
        Matrix2DWriter<String> writer = new ValuesMatrix2DWriter<String>();
        StringBuffer appendable = new StringBuffer();
        appendable = writer.append(matrix, appendable);
        assertEquals("[foo]", appendable.toString());
    }
}