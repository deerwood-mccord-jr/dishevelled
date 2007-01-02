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
package org.dishevelled.matrix.impl;

import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import org.dishevelled.matrix.ObjectMatrix2D;

/**
 * Sparse implementation of ObjectMatrix3D based on
 * a hash map whose keys are <code>Long</code>s.
 *
 * <p>The cardinality of this sparse object matrix is limited
 * by the <code>HashMap</code> underlying this implementation
 * to something less than <code>Integer.MAX_VALUE</code>.  The
 * addressable size, on the other hand, is limited to
 * <code>(slices * rows * columns) &lt; Long.MAX_VALUE</code>.</p>
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public class SparseObjectMatrix3D<E>
    extends AbstractObjectMatrix3D<E>
    implements Serializable
{
    /** Map of elements keyed by a <code>Long</code> index. */
    private Map<Long, E> elements;


    /**
     * Private no-arg constructor, to support serialization.
     */
    private SparseObjectMatrix3D()
    {
        elements = null;
    }

    /**
     * Create a new sparse 3D matrix with the specified number
     * of slices, rows, and columns.
     *
     * @param slices slices, must be <code>&gt;= 0</code>
     * @param rows rows, must be <code>&gt;= 0</code>
     * @param columns columns, must be <code>&gt;= 0</code>
     * @throws IllegalArgumentException if any of <code>slices</code>,
     *    <code>rows</code>, or <code>columns</code> is negative
     */
    public SparseObjectMatrix3D(final long slices, final long rows, final long columns)
    {
        this(slices, rows, columns,
             (int) Math.min(Integer.MAX_VALUE, ((slices * rows * columns) / 0.75)), 0.75f);
    }

    /**
     * Create a new sparse 3D matrix with the specified number
     * of slices, rows, and columns, initial capacity, and load factor.
     *
     * @param slices slices, must be <code>&gt;= 0</code>
     * @param rows rows, must be <code>&gt;= 0</code>
     * @param columns columns, must be <code>&gt;= 0</code>
     * @param initialCapacity initial capacity, must be <code>&gt;= 0</code>
     * @param loadFactor load factor, must be <code>&gt; 0</code>
     */
    public SparseObjectMatrix3D(final long slices,
                                final long rows,
                                final long columns,
                                final int initialCapacity,
                                final float loadFactor)
    {
        super(slices, rows, columns);
        elements = new HashMap<Long, E>(initialCapacity, loadFactor);
    }

    /**
     * Create a new instance of SparseObjectMatrix3D with the specified
     * number of slices, rows, and columns and map of elements.  Used
     * exclusively by the <code>clone()</code> method.
     *
     * @param slices slices, must be <code>&gt;= 0</code>
     * @param rows rows, must be <code>&gt;= 0</code>
     * @param columns columns, must be <code>&gt;= 0</code>
     * @param sliceZero slice of the first element
     * @param rowZero row of the first element
     * @param columnZero column of the first element
     * @param sliceStride number of slices between two elements
     * @param rowStride number of rows between two elements
     * @param columnStride number of columns between two elements
     * @param isView true if this instance is a view
     * @param elements map of elements
     */
    private SparseObjectMatrix3D(final long slices,
                                 final long rows,
                                 final long columns,
                                 final long sliceZero,
                                 final long rowZero,
                                 final long columnZero,
                                 final long sliceStride,
                                 final long rowStride,
                                 final long columnStride,
                                 final boolean isView,
                                 final Map<Long, E> elements)
    {
        super(slices, rows, columns,
              sliceZero, rowZero, columnZero,
              sliceStride, rowStride, columnStride, isView);
        this.elements = elements;
    }


    /** @see Object */
    public Object clone()
    {
        return new SparseObjectMatrix3D<E>(slices, rows, columns,
                                           sliceZero, rowZero, columnZero,
                                           sliceStride, rowStride, columnStride,
                                           isView, elements);
    }

    /** @see org.dishevelled.matrix.ObjectMatrix3D */
    public E getQuick(final long slice, final long row, final long column)
    {
        long index = sliceZero + (slice * sliceStride)
            + rowZero + (row * rowStride)
            + columnZero + (column * columnStride);
        return elements.get(index);
    }

    /** @see org.dishevelled.matrix.ObjectMatrix3D */
    public void setQuick(final long slice, final long row, final long column, final E e)
    {
        long index = sliceZero + (slice * sliceStride)
            + rowZero + (row * rowStride)
            + columnZero + (column * columnStride);
        if (e == null)
        {
            elements.remove(index);
        }
        else
        {
            elements.put(index, e);
        }
    }

    /** @see org.dishevelled.matrix.ObjectMatrix3D */
    public ObjectMatrix2D<E> viewSlice(final long slice)
    {
        return new SliceView(slice);
    }

    /** @see org.dishevelled.matrix.ObjectMatrix3D */
    public ObjectMatrix2D<E> viewRow(final long row)
    {
        return new RowView(row);
    }

    /** @see org.dishevelled.matrix.ObjectMatrix3D */
    public ObjectMatrix2D<E> viewColumn(final long column)
    {
        return new ColumnView(column);
    }

    /**
     * Write this 3D object matrix to the specified object output stream.
     *
     * @see java.io.ObjectOutputStream
     * @param out object output stream
     */
    private void writeObject(final ObjectOutputStream out)
        throws IOException
    {
        out.writeLong(slices);
        out.writeLong(rows);
        out.writeLong(columns);
        out.writeLong(sliceZero);
        out.writeLong(rowZero);
        out.writeLong(columnZero);
        out.writeLong(sliceStride);
        out.writeLong(rowStride);
        out.writeLong(columnStride);
        out.writeBoolean(isView);
        out.writeObject(elements);
    }

    /**
     * Read this 3D object matrix in from the specified object input stream.
     *
     * @see java.io.ObjectInputStream
     * @param in object input stream
     */
    private void readObject(final ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        super.slices = in.readLong();
        super.rows = in.readLong();
        super.columns = in.readLong();
        super.sliceZero = in.readLong();
        super.rowZero = in.readLong();
        super.columnZero = in.readLong();
        super.sliceStride = in.readLong();
        super.rowStride = in.readLong();
        super.columnStride = in.readLong();
        super.isView = in.readBoolean();
        this.elements = (Map<Long, E>) in.readObject();
    }

    /** @see Object */
    public String toString()
    {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("\n   slices=");
        sb.append(slices);
        sb.append("   rows=");
        sb.append(rows);
        sb.append("   columns=");
        sb.append(columns);
        sb.append("   sliceZero=");
        sb.append(sliceZero);
        sb.append("   rowZero=");
        sb.append(rowZero);
        sb.append("   columnZero=");
        sb.append(columnZero);
        sb.append("   sliceStride=");
        sb.append(sliceStride);
        sb.append("   rowStride=");
        sb.append(rowStride);
        sb.append("   columnStride=");
        sb.append(columnStride);
        sb.append("\n   elements=");
        sb.append(elements.toString());
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Slice view.
     */
    private class SliceView
        extends SparseObjectMatrix2D<E>
    {

        /**
         * Create a new slice view with the specified slice.
         *
         * @param slice slice to view
         */
        public SliceView(final long slice)
        {
            super(SparseObjectMatrix3D.this.rows,
                  SparseObjectMatrix3D.this.columns,
                  SparseObjectMatrix3D.this.rowZero,
                  SparseObjectMatrix3D.this.columnZero + (slice * SparseObjectMatrix3D.this.sliceStride) + SparseObjectMatrix3D.this.sliceZero,
                  SparseObjectMatrix3D.this.rowStride,
                  SparseObjectMatrix3D.this.columnStride,
                  true,
                  SparseObjectMatrix3D.this.elements);
        }
    }

    /**
     * Row view.
     */
    private class RowView
        extends SparseObjectMatrix2D<E>
    {

        /**
         * Create a new row view with the specified row.
         *
         * @param row row to view
         */
        public RowView(final long row)
        {
            super(SparseObjectMatrix3D.this.slices,
                  SparseObjectMatrix3D.this.columns,
                  SparseObjectMatrix3D.this.sliceZero,
                  SparseObjectMatrix3D.this.columnZero + (row * SparseObjectMatrix3D.this.rowStride) + SparseObjectMatrix3D.this.rowZero,
                  SparseObjectMatrix3D.this.sliceStride,
                  SparseObjectMatrix3D.this.columnStride,
                  true,
                  SparseObjectMatrix3D.this.elements);
        }
    }

    /**
     * Column view.
     */
    private class ColumnView
        extends SparseObjectMatrix2D<E>
    {

        /**
         * Create a new column view with the specified column.
         *
         * @param column column to view
         */
        public ColumnView(final long column)
        {
            super(SparseObjectMatrix3D.this.slices,
                  SparseObjectMatrix3D.this.rows,
                  SparseObjectMatrix3D.this.sliceZero,
                  SparseObjectMatrix3D.this.rowZero + (column * SparseObjectMatrix3D.this.columnStride) + SparseObjectMatrix3D.this.columnZero,
                  SparseObjectMatrix3D.this.sliceStride,
                  SparseObjectMatrix3D.this.rowStride,
                  true,
                  SparseObjectMatrix3D.this.elements);
        }
    }
}
