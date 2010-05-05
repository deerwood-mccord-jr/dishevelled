/*

    dsh-matrix-nonblocking  Non-blocking matrix implementations.
    Copyright (c) 2010 held jointly by the individual authors.

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
package org.dishevelled.matrix.nonblocking;

import org.dishevelled.matrix.AbstractMatrix2DTest;
import org.dishevelled.matrix.Matrix2D;

/**
 * Unit test for NonBlockingSparseMatrix2D.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public class NonBlockingSparseMatrix2DTest
    extends AbstractMatrix2DTest
{

    /** {@inheritDoc} */
    protected <T> Matrix2D<T> createMatrix2D(final long rows, final long columns)
    {
        return new NonBlockingSparseMatrix2D<T>(rows, columns, Math.min(100, (int) (rows * columns)));
    }
}