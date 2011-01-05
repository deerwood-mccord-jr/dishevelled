/*

    dsh-matrix-nonblocking  Non-blocking matrix implementations.
    Copyright (c) 2010-2011 held jointly by the individual authors.

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
package org.dishevelled.matrix.impl.nonblocking;

import org.dishevelled.matrix.AbstractMatrix3DTest;
import org.dishevelled.matrix.Matrix3D;
import org.dishevelled.matrix.impl.nonblocking.NonBlockingSparseMatrix3D;

/**
 * Unit test for NonBlockingSparseMatrix3D.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public class NonBlockingSparseMatrix3DTest
    extends AbstractMatrix3DTest
{

    /** {@inheritDoc} */
    protected <T> Matrix3D<T> createMatrix3D(final long slices, final long rows, final long columns)
    {
        return new NonBlockingSparseMatrix3D<T>(slices, rows, columns, Math.min(100, (int) (slices * rows * columns)));
    }
}