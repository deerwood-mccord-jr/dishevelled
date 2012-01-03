/*

    dsh-matrix-io  Matrix readers and writers.
    Copyright (c) 2008-2012 held jointly by the individual authors.

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

import org.dishevelled.functor.QuaternaryPredicate;
import org.dishevelled.functor.QuaternaryProcedure;

import org.dishevelled.matrix.Matrix3D;

/**
 * Tab-delimited text writer for matrices of objects in three dimensions.
 * The first line is the size of the matrix as <code>slices\trows\tcolumns\tcardinality</code>
 * and each additional line is formatted as <code>slice\trow\tcolumn\tvalue</code>.
 *
 * @param <E> 3D matrix element type
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class TextMatrix3DWriter<E>
    extends AbstractMatrix3DWriter<E>
{

    /** {@inheritDoc} */
    public <T extends Appendable> T append(final Matrix3D<? extends E> matrix, final T appendable)
        throws IOException
    {
        if (matrix == null)
        {
            throw new IllegalArgumentException("matrix must not be null");
        }
        if (appendable == null)
        {
            throw new IllegalArgumentException("appendable must not be null");
        }
        // first line is size as slices\trows\tcolumns\tcardinality
        appendable.append(String.valueOf(matrix.slices()));
        appendable.append('\t');
        appendable.append(String.valueOf(matrix.rows()));
        appendable.append('\t');
        appendable.append(String.valueOf(matrix.columns()));
        appendable.append('\t');
        appendable.append(String.valueOf(matrix.cardinality()));
        appendable.append('\n');
        matrix.forEach(new QuaternaryPredicate<Long, Long, Long, E>()
        {
            /** {@inheritDoc} */
            public boolean test(final Long slice, final Long row, final Long column, final E value)
            {
                return (value != null);
            }
        }, new QuaternaryProcedure<Long, Long, Long, E>()
        {
            /** {@inheritDoc} */
            public void run(final Long slice, final Long row, final Long column, final E value)
            {
                try
                {
                    appendable.append(String.valueOf(slice));
                    appendable.append('\t');
                    appendable.append(String.valueOf(row));
                    appendable.append('\t');
                    appendable.append(String.valueOf(column));
                    appendable.append('\t');
                    appendable.append(MatrixIOUtils.toCharSequence(value));
                    appendable.append('\n');
                }
                catch (IOException e)
                {
                    // todo: rethrow outside of inner class
                }
            }
        });
        return appendable;
    }
}