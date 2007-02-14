/*

    dsh-matrix  long-addressable bit and typed object matrix implementations.
    Copyright (c) 2004-2007 held jointly by the individual authors.

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
package org.dishevelled.matrix;

import java.util.Iterator;

import org.dishevelled.functor.BinaryFunction;
import org.dishevelled.functor.BinaryPredicate;
import org.dishevelled.functor.BinaryProcedure;
import org.dishevelled.functor.UnaryFunction;
import org.dishevelled.functor.UnaryPredicate;
import org.dishevelled.functor.UnaryProcedure;

/**
 * Static utility methods on object matrices.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class ObjectMatrixUtils
{

    /**
     * Private constructor.
     */
    private ObjectMatrixUtils()
    {
        // empty
    }


    /**
     * Return an unmodifiable view of the specified 1D object matrix.  Query operations
     * on the returned 1D object matrix read through to the specified matrix, and attempts
     * to modify the returned 1D object matrix, whether directly or via its iterator, result
     * in an <code>UnsupportedOperationException</code>.
     *
     * @param <T> 1D object matrix type
     * @param matrix 1D object matrix to view, must not be null
     * @return an unmodifiable view of the specified 1D object matrix
     */
    public static <T> ObjectMatrix1D<T> unmodifiableObjectMatrix(final ObjectMatrix1D<T> matrix)
    {
        return new UnmodifiableObjectMatrix1D<T>(matrix);
    }

    /**
     * Return an unmodifiable view of the specified 2D object matrix.  Query operations
     * on the returned 2D object matrix read through to the specified matrix, and attempts
     * to modify the returned 2D object matrix, whether directly or via its iterator, result
     * in an <code>UnsupportedOperationException</code>.
     *
     * @param <T> 2D object matrix type
     * @param matrix 2D object matrix to view, must not be null
     * @return an unmodifiable view of the specified 2D object matrix
     */
    public static <T> ObjectMatrix2D<T> unmodifiableObjectMatrix(final ObjectMatrix2D<T> matrix)
    {
        return null;
        //return new UnmodifiableObjectMatrix2D<T>(matrix);
    }

    /**
     * Return an unmodifiable view of the specified 3D object matrix.  Query operations
     * on the returned 3D object matrix read through to the specified matrix, and attempts
     * to modify the returned 3D object matrix, whether directly or via its iterator, result
     * in an <code>UnsupportedOperationException</code>.
     *
     * @param <T> 3D object matrix type
     * @param matrix 3D object matrix to view, must not be null
     * @return an unmodifiable view of the specified 3D object matrix
     */
    public static <T> ObjectMatrix3D<T> unmodifiableObjectMatrix(final ObjectMatrix3D<T> matrix)
    {
        return null;
        //return new UnmodifiableObjectMatrix3D<T>(matrix);
    }


    /**
     * Unmodifiable 1D object matrix view.
     */
    private static class UnmodifiableObjectMatrix1D<E>
        implements ObjectMatrix1D<E>
    {
        /** Wrapped 1D object matrix. */
        private final ObjectMatrix1D<E> matrix;


        /**
         * Create a new unmodifiable 1D object matrix view for the
         * specified 1D object matrix.
         *
         * @param matrix 1D object matrix to view, must not be null
         */
        UnmodifiableObjectMatrix1D(final ObjectMatrix1D<E> matrix)
        {
            if (matrix == null)
            {
                throw new IllegalArgumentException("matrix must not be null");
            }
            this.matrix = matrix;
        }


        /** {@inheritDoc} */
        public E aggregate(final BinaryFunction<E, E, E> aggr,
                           final UnaryFunction<E, E> function)
        {
            return matrix.aggregate(aggr, function);
        }

        /** {@inheritDoc} */
        public E aggregate(final ObjectMatrix1D<? extends E> other,
                           final BinaryFunction<E, E, E> aggr,
                           final BinaryFunction<E, E, E> function)
        {
            return matrix.aggregate(other, aggr, function);
        }

        /** {@inheritDoc} */
        public ObjectMatrix1D<E> assign(final E e)
        {
            throw new UnsupportedOperationException("assign operation not supported by this 1D object matrix");
        }

        /** {@inheritDoc} */
        public ObjectMatrix1D<E> assign(final ObjectMatrix1D<? extends E> other)
        {
            throw new UnsupportedOperationException("assign operation not supported by this 1D object matrix");
        }

        /** {@inheritDoc} */
        public ObjectMatrix1D<E> assign(final ObjectMatrix1D<? extends E> other,
                                        final BinaryFunction<E, E, E> function)
        {
            throw new UnsupportedOperationException("assign operation not supported by this 1D object matrix");
        }

        /** {@inheritDoc} */
        public ObjectMatrix1D<E> assign(final UnaryFunction<E, E> function)
        {
            throw new UnsupportedOperationException("assign operation not supported by this 1D object matrix");
        }

        /** {@inheritDoc} */
        public long cardinality()
        {
            return matrix.cardinality();
        }

        /** {@inheritDoc} */
        public void clear()
        {
            throw new UnsupportedOperationException("clear operation not supported by this 1D object matrix");
        }

        /** {@inheritDoc} */
        public void forEach(final BinaryPredicate<Long, E> predicate,
                            final BinaryProcedure<Long, E> procedure)
        {
            matrix.forEach(predicate, procedure);
        }

        /** {@inheritDoc} */
        public void forEach(final BinaryProcedure<Long, E> procedure)
        {
            matrix.forEach(procedure);
        }

        /** {@inheritDoc} */
        public void forEach(final UnaryPredicate<E> predicate,
                            final UnaryProcedure<E> procedure)
        {
            matrix.forEach(predicate, procedure);
        }

        /** {@inheritDoc} */
        public void forEach(final UnaryProcedure<E> procedure)
        {
            matrix.forEach(procedure);
        }

        /** {@inheritDoc} */
        public E get(final long index)
        {
            return matrix.get(index);
        }

        /** {@inheritDoc} */
        public E getQuick(final long index)
        {
            return matrix.getQuick(index);
        }

        /** {@inheritDoc} */
        public boolean isEmpty()
        {
            return matrix.isEmpty();
        }

        /** {@inheritDoc} */
        public Iterator<E> iterator()
        {
            // todo:  wrap iterator and block remove()
            return matrix.iterator();
        }

        /** {@inheritDoc} */
        public void set(final long index, final E e)
        {
            throw new UnsupportedOperationException("set operation not supported by this 1D object matrix");
        }

        /** {@inheritDoc} */
        public void setQuick(final long index, final E e)
        {
            throw new UnsupportedOperationException("setQuick operation not supported by this 1D object matrix");
        }

        /** {@inheritDoc} */
        public long size()
        {
            return matrix.size();
        }

        /** {@inheritDoc} */
        public ObjectMatrix1D<E> viewFlip()
        {
            ObjectMatrix1D<E> flip = matrix.viewFlip();
            return ObjectMatrixUtils.unmodifiableObjectMatrix(flip);
        }

        /** {@inheritDoc} */
        public ObjectMatrix1D<E> viewPart(final long index, final long width)
        {
            ObjectMatrix1D<E> part = matrix.viewPart(index, width);
            return ObjectMatrixUtils.unmodifiableObjectMatrix(part);
        }

        /** {@inheritDoc} */
        public ObjectMatrix1D<E> viewSelection(final long[] indices)
        {
            ObjectMatrix1D<E> selection = matrix.viewSelection(indices);
            return ObjectMatrixUtils.unmodifiableObjectMatrix(selection);
        }

        /** {@inheritDoc} */
        public ObjectMatrix1D<E> viewSelection(final UnaryPredicate<E> predicate)
        {
            ObjectMatrix1D<E> selection = matrix.viewSelection(predicate);
            return ObjectMatrixUtils.unmodifiableObjectMatrix(selection);
        }

        /** {@inheritDoc} */
        public ObjectMatrix1D<E> viewStrides(final long stride)
        {
            ObjectMatrix1D<E> strides = matrix.viewStrides(stride);
            return ObjectMatrixUtils.unmodifiableObjectMatrix(strides);
        }
    }
}
