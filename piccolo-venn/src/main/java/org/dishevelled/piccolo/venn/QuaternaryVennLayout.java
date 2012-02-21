/*

    dsh-piccolo-venn  Piccolo2D venn diagram nodes and supporting classes.
    Copyright (c) 2009-2012 held jointly by the individual authors.

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
package org.dishevelled.piccolo.venn;

import java.awt.Shape;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Result of a quaternary venn diagram layout operation.
 *
 * @author  Michael Heuer
 */
public final class QuaternaryVennLayout
{
    private final Shape firstShape;
    private final Shape secondShape;
    private final Shape thirdShape;
    private final Shape fourthShape;
    private final Point2D firstOnlyLuneCenter;
    private final Point2D secondOnlyLuneCenter;
    // todo:  rest of label centers . . .
    private final Point2D intersectionLuneCenter;
    private final Rectangle2D boundingRectangle;
    private final double score;


    QuaternaryVennLayout(final Shape firstShape,
                         final Shape secondShape,
                         final Shape thirdShape,
                         final Shape fourthShape,
                         final Point2D firstOnlyLuneCenter,
                         final Point2D secondOnlyLuneCenter,
                         final Point2D intersectionLuneCenter,
                         final Rectangle2D boundingRectangle,
                         final double score)
    {
        this.firstShape = firstShape;
        this.secondShape = secondShape;
        this.thirdShape = thirdShape;
        this.fourthShape = fourthShape;
        this.firstOnlyLuneCenter = firstOnlyLuneCenter;
        this.secondOnlyLuneCenter = secondOnlyLuneCenter;
        this.intersectionLuneCenter = intersectionLuneCenter;
        this.boundingRectangle = boundingRectangle;
        this.score = score;
    }


    public Shape firstShape()
    {
        return firstShape;
    }

    public Shape secondShape()
    {
        return secondShape;
    }

    public Shape thirdShape()
    {
        return thirdShape;
    }

    public Shape fourthShape()
    {
        return fourthShape;
    }

    public Point2D firstOnlyLuneCenter()
    {
        return firstOnlyLuneCenter;
    }

    public Point2D secondOnlyLuneCenter()
    {
        return secondOnlyLuneCenter;
    }

    public Point2D intersectionLuneCenter()
    {
        return intersectionLuneCenter;
    }

    public Rectangle2D boundingRectangle()
    {
        return boundingRectangle;
    }

    public double score()
    {
        return score;
    }
}