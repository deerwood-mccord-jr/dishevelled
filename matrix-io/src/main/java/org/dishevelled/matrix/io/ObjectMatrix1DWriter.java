/*

    dsh-matrix-io  Matrix readers and writers.
    Copyright (c) 2008 held jointly by the individual authors.

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
package org.dishevelled.matrix.io;

import java.io.IOException;

import org.dishevelled.matrix.ObjectMatrix1D;

/**
 * Writer for matrices of objects in one dimension.
 *
 * @param <E> 1D matrix element type
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public interface ObjectMatrix1DWriter<E>
{

    /**
     * Append the specified 1D matrix to the specified appendable.
     *
     * @param <T> extends Appendable
     * @param matrix 1D matrix to append, must not be null
     * @param appendable appendable to append the specified 1D matrix to, must not be null
     * @return the specified appendable with the specified 1D matrix appended
     * @throws IOException if an error occurs
     */
    <T extends Appendable> T append(final ObjectMatrix1D<E> matrix, final T appendable) throws IOException;
}