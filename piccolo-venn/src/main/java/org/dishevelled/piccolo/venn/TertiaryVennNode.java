/*

    dsh-piccolo-venn  Piccolo2D venn diagram nodes and supporting classes.
    Copyright (c) 2009 held jointly by the individual authors.

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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import java.util.Set;

import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;

import edu.umd.cs.piccolo.PNode;

import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.nodes.PText;

import org.dishevelled.venn.TertiaryVennModel;

import org.dishevelled.venn.model.TertiaryVennModelImpl;

/**
 * Tertiary venn diagram node.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class TertiaryVennNode<E>
    extends PNode
{
    /** Tertiary venn model. */
    private TertiaryVennModel<E> model;

    /** Label text for the first list view. */
    private String firstLabelText = DEFAULT_FIRST_LABEL_TEXT;

    /** Label text for the second list view. */
    private String secondLabelText = DEFAULT_SECOND_LABEL_TEXT;

    /** Label text for the third list view. */
    private String thirdLabelText = DEFAULT_THIRD_LABEL_TEXT;

    /** Label for the first list view. */
    private final PText firstLabel;

    /** Path node for the first list view. */
    private final PPath first;

    /** Label for the first list view's size. */
    private final PText firstSize;

    /** Label for the second list view. */
    private final PText secondLabel;

    /** Path node for the second list view. */
    private final PPath second;

    /** Label for the second list view's size. */
    private final PText secondSize;

    /** Label for the third list view. */
    private final PText thirdLabel;

    /** Path node for the third list view. */
    private final PPath third;

    /** Label for the third list view's size. */
    private final PText thirdSize;

    /** Area node for the intersection. */
    private final AreaNode intersection;

    /** Label for the intersection's size. */
    private final PText intersectionSize;

    /** Area node for the union of the first and second sets. */
    private final AreaNode firstSecond;

    /** Label for the union of the first and second sets. */
    private final PText firstSecondSize;

    /** Area node for the union of the first and third sets. */
    private final AreaNode firstThird;

    /** Label for the union of the first and third sets. */
    private final PText firstThirdSize;

    /** Area node for the union of the second and third sets. */
    private final AreaNode secondThird;

    /** Label for the union of the second and third sets. */
    private final PText secondThirdSize;

    /** True if labels should display sizes. */
    private static final boolean SHOW_SIZES = true;

    /** Default label text for the first list view, <code>"First set"</code>. */
    public static final String DEFAULT_FIRST_LABEL_TEXT = "First set";

    /** Default label text for the second list view, <code>"Second set"</code>. */
    public static final String DEFAULT_SECOND_LABEL_TEXT = "Second set";

    /** Default label text for the third list view, <code>"Third set"</code>. */
    public static final String DEFAULT_THIRD_LABEL_TEXT = "Third set";


    /**
     * Create a new tertiary venn node with the specified sets.
     *
     * @param firstLabelText label text for the first list view
     * @param first first set, must not be null
     * @param secondLabelText label text for the second list view
     * @param second second set, must not be null
     * @param thirdLabelText label text for the third list view
     * @param third third set, must not be null
     */
    public TertiaryVennNode(final String firstLabelText, final Set<? extends E> first,
                            final String secondLabelText, final Set<? extends E> second,
                            final String thirdLabelText, final Set<? extends E> third)
    {
        this(new TertiaryVennModelImpl<E>(first, second, third));
        // todo:  not using firstLabelText, secondLabelText, thirdLabelText
    }

    /**
     * Create a new tertiary venn node with the specified model.
     *
     * @param model model for this tertiary venn node, must not be null
     */
    public TertiaryVennNode(final TertiaryVennModel<E> model)
    {
        super();
        if (model == null)
        {
            throw new IllegalArgumentException("model must not be null");
        }
        this.model = model;

        firstLabel = new PText();
        // todo:  match reference
        //   using colors from wikipedia image, choose others or attribute correctly
        first = PPath.createEllipse(0.0f, 0.0f, 128.0f, 128.0f);
        first.setPaint(new Color(5, 37, 255, 50));
        first.setStroke(new BasicStroke(0.5f));
        first.setStrokePaint(new Color(20, 20, 20));
        firstSize = new PText();
        secondLabel = new PText();
        second = PPath.createEllipse(((2.0f * 128.0f) / 3.0f), 0.0f, 128.0f, 128.0f);
        second.setPaint(new Color(255, 100, 5, 50));
        second.setStroke(new BasicStroke(0.5f));
        second.setStrokePaint(new Color(20, 20, 20));
        secondSize = new PText();
        thirdLabel = new PText();
        third = PPath.createEllipse(128.0f / 3.0f, (2.0f * 128.0f) / 3.0f, 128.0f, 128.0f);
        third.setPaint(new Color(30, 30, 30, 50));
        third.setStroke(new BasicStroke(0.5f));
        third.setStrokePaint(new Color(20, 20, 20));
        thirdSize = new PText();
        intersection = new AreaNode();
        intersectionSize = new PText();
        firstSecond = new AreaNode();
        firstSecondSize = new PText();
        firstThird = new AreaNode();
        firstThirdSize = new PText();
        secondThird = new AreaNode();
        secondThirdSize = new PText();

        addChild(third);
        addChild(second);
        addChild(first);
        addChild(firstSecond);
        addChild(firstThird);
        addChild(secondThird);
        addChild(intersection);
        addChild(thirdSize);
        addChild(secondSize);
        addChild(firstSize);
        addChild(firstSecondSize);
        addChild(firstThirdSize);
        addChild(secondThirdSize);
        addChild(intersectionSize);
        addChild(thirdLabel);
        addChild(secondLabel);
        addChild(firstLabel);

        installListeners();
        initLabels();
        layoutNodes();
   }


    /**
     * Install listeners.
     */
    private void installListeners()
    {
        model.first().addListEventListener(new ListEventListener<E>()
            {
                /** {@inheritDoc} */
                public void listChanged(final ListEvent<E> event)
                {
                    firstLabel.setText(buildLabel(firstLabelText, model.first().size()));
                    firstSize.setText(String.valueOf(model.first().size() - model.intersection().size()));
                    firstSecondSize.setText(String.valueOf(model.union(model.first(), model.second()).size() - model.intersection().size()));
                    firstThirdSize.setText(String.valueOf(model.union(model.first(), model.third()).size() - model.intersection().size()));
                }
            });

        model.second().addListEventListener(new ListEventListener<E>()
            {
                /** {@inheritDoc} */
                public void listChanged(final ListEvent<E> event)
                {
                    secondLabel.setText(buildLabel(secondLabelText, model.second().size()));
                    secondSize.setText(String.valueOf(model.second().size() - model.intersection().size()));
                    firstSecondSize.setText(String.valueOf(model.union(model.first(), model.second()).size() - model.intersection().size()));
                    secondThirdSize.setText(String.valueOf(model.union(model.second(), model.third()).size() - model.intersection().size()));
                }
            });

        model.third().addListEventListener(new ListEventListener<E>()
            {
                /** {@inheritDoc} */
                public void listChanged(final ListEvent<E> event)
                {
                    thirdLabel.setText(buildLabel(thirdLabelText, model.third().size()));
                    thirdSize.setText(String.valueOf(model.third().size() - model.intersection().size()));
                    firstThirdSize.setText(String.valueOf(model.union(model.first(), model.third()).size() - model.intersection().size()));
                    secondThirdSize.setText(String.valueOf(model.union(model.second(), model.third()).size() - model.intersection().size()));
                }
            });

        model.intersection().addListEventListener(new ListEventListener<E>()
            {
                /** {@inheritDoc} */
                public void listChanged(final ListEvent<E> event)
                {
                    intersectionSize.setText(String.valueOf(model.intersection().size()));
                }
            });
    }

    /**
     * Initialize labels.
     */
    private void initLabels()
    {
        firstLabel.setText(buildLabel(firstLabelText, model.first().size()));
        firstSize.setText(String.valueOf(model.first().size() - model.intersection().size()));
        secondLabel.setText(buildLabel(secondLabelText, model.second().size()));
        secondSize.setText(String.valueOf(model.second().size() - model.intersection().size()));
        thirdLabel.setText(buildLabel(thirdLabelText, model.third().size()));
        thirdSize.setText(String.valueOf(model.third().size() - model.intersection().size()));
        intersectionSize.setText(String.valueOf(model.intersection().size()));
        firstSecondSize.setText(String.valueOf(model.union(model.first(), model.second()).size() - model.intersection().size()));
        firstThirdSize.setText(String.valueOf(model.union(model.first(), model.third()).size() - model.intersection().size()));
        secondThirdSize.setText(String.valueOf(model.union(model.second(), model.third()).size() - model.intersection().size()));
    }

    /**
     * Layout nodes.
     */
    // todo:  need to make some of this layout dynamic
    private void layoutNodes()
    {
        Shape firstShape = first.getPathReference();
        Shape secondShape = second.getPathReference();
        Shape thirdShape = third.getPathReference();

        Area firstArea = new Area(firstShape);
        Area secondArea = new Area(secondShape);
        Area thirdArea = new Area(thirdShape);
        firstArea.subtract(new Area(secondShape));
        firstArea.subtract(new Area(thirdShape));
        secondArea.subtract(new Area(firstShape));
        secondArea.subtract(new Area(thirdShape));
        thirdArea.subtract(new Area(firstShape));
        thirdArea.subtract(new Area(secondShape));

        Area intersectionArea = new Area(firstShape);
        intersectionArea.intersect(new Area(secondShape));
        intersectionArea.intersect(new Area(thirdShape));
        intersection.setArea(intersectionArea);

        Area firstSecondArea = new Area(firstShape);
        firstSecondArea.intersect(new Area(secondShape));
        firstSecondArea.subtract(new Area(thirdShape));
        firstSecond.setArea(firstSecondArea);

        Area firstThirdArea = new Area(firstShape);
        firstThirdArea.intersect(new Area(thirdShape));
        firstThirdArea.subtract(new Area(secondShape));
        firstThird.setArea(firstThirdArea);

        Area secondThirdArea = new Area(secondShape);
        secondThirdArea.intersect(new Area(thirdShape));
        secondThirdArea.subtract(new Area(firstShape));
        secondThird.setArea(secondThirdArea);

        Rectangle2D firstBounds = firstArea.getBounds2D();
        Rectangle2D secondBounds = secondArea.getBounds2D();
        Rectangle2D thirdBounds = thirdArea.getBounds2D();
        Rectangle2D intersectionBounds = intersectionArea.getBounds2D();
        Rectangle2D firstSecondBounds = firstSecondArea.getBounds2D();
        Rectangle2D firstThirdBounds = firstThirdArea.getBounds2D();
        Rectangle2D secondThirdBounds = secondThirdArea.getBounds2D();

        Rectangle2D firstLabelBounds = firstLabel.getFullBoundsReference();
        Rectangle2D secondLabelBounds = secondLabel.getFullBoundsReference();
        Rectangle2D thirdLabelBounds = thirdLabel.getFullBoundsReference();
        Rectangle2D firstSizeBounds = firstSize.getFullBoundsReference();
        Rectangle2D secondSizeBounds = secondSize.getFullBoundsReference();
        Rectangle2D thirdSizeBounds = thirdSize.getFullBoundsReference();
        Rectangle2D intersectionSizeBounds = intersectionSize.getFullBoundsReference();
        Rectangle2D firstSecondSizeBounds = firstSecondSize.getFullBoundsReference();
        Rectangle2D firstThirdSizeBounds = firstThirdSize.getFullBoundsReference();
        Rectangle2D secondThirdSizeBounds = secondThirdSize.getFullBoundsReference();

        firstLabel.setOffset(firstBounds.getX() + (firstBounds.getWidth() / 2.0d) - (firstLabelBounds.getWidth() / 2.0d),
                             -1.0d * firstLabelBounds.getHeight() - 4.0d);
        secondLabel.setOffset(secondBounds.getX() + (secondBounds.getWidth() / 2.0d) - (secondLabelBounds.getWidth() / 2.0d),
                              -1.0d * secondLabelBounds.getHeight() - 4.0d);
        thirdLabel.setOffset(thirdBounds.getX() + (thirdBounds.getWidth() / 2.0d) - (thirdLabelBounds.getWidth() / 2.0d),
                             thirdBounds.getY() + thirdBounds.getHeight() + 6.0d);
        firstSize.setOffset(firstBounds.getCenterX() - (firstSizeBounds.getWidth() / 2.0d),
                            firstBounds.getCenterY() - (firstSizeBounds.getHeight() / 2.0d));
        secondSize.setOffset(secondBounds.getCenterX() - (secondSizeBounds.getWidth() / 2.0d),
                            secondBounds.getCenterY() - (secondSizeBounds.getHeight() / 2.0d));
        thirdSize.setOffset(thirdBounds.getCenterX() - (thirdSizeBounds.getWidth() / 2.0d),
                            thirdBounds.getCenterY() - (thirdSizeBounds.getHeight() / 2.0d));

        // todo:  this label seems to be misplaced +y a bit, maybe the full bounds calc is off
        intersectionSize.setOffset(intersectionBounds.getCenterX() - (intersectionSizeBounds.getWidth() / 2.0d),
                                   intersectionBounds.getCenterY() - (2.0d * intersectionSizeBounds.getHeight() / 3.0d));

        firstSecondSize.setOffset(firstSecondBounds.getCenterX() - (firstSecondSizeBounds.getWidth() / 2.0d),
                                  firstSecondBounds.getCenterY() - (firstSecondSizeBounds.getHeight() / 2.0d));
        firstThirdSize.setOffset(firstThirdBounds.getCenterX() - (firstThirdSizeBounds.getWidth() / 2.0d),
                                 firstThirdBounds.getCenterY() - (firstThirdSizeBounds.getHeight() / 2.0d));
        secondThirdSize.setOffset(secondThirdBounds.getCenterX() - (secondThirdSizeBounds.getWidth() / 2.0d),
                                  secondThirdBounds.getCenterY() - (secondThirdSizeBounds.getHeight() / 2.0d));
    }

    /**
     * Build and return label text.
     *
     * @param labelText label text
     * @param size size
     * @return label text
     */
    private String buildLabel(final String labelText, final int size)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(labelText);
        if (SHOW_SIZES)
        {
            sb.append(" (");
            sb.append(size);
            sb.append(")");
        }
        sb.append(":");
        return sb.toString();
    }

    /**
     * Return the label text for the first list view.  Defaults to {@link #DEFAULT_FIRST_LABEL_TEXT}.
     *
     * @return the label text for the first list view
     */
    public String getFirstLabelText()
    {
        return firstLabelText;
    }

    /**
     * Set the label text for the first list view to <code>firstLabelText</code>.
     *
     * <p>This is a bound property.</p>
     *
     * @param firstLabelText label text for the first list view
     */
    public void setFirstLabelText(final String firstLabelText)
    {
        String oldFirstLabelText = this.firstLabelText;
        this.firstLabelText = firstLabelText;
        firstLabel.setText(buildLabel(this.firstLabelText, model.first().size()));
        firePropertyChange(-1, "firstLabelText", this.firstLabelText, oldFirstLabelText);
    }

    /**
     * Return the label text for the second list view.  Defaults to {@link #DEFAULT_SECOND_LABEL_TEXT}.
     *
     * @return the label text for the second list view
     */
    public String getSecondLabelText()
    {
        return secondLabelText;
    }

    /**
     * Set the label text for the second list view to <code>secondLabelText</code>.
     *
     * <p>This is a bound property.</p>
     *
     * @param secondLabelText label text for the second list view
     */
    public void setSecondLabelText(final String secondLabelText)
    {
        String oldSecondLabelText = this.secondLabelText;
        this.secondLabelText = secondLabelText;
        secondLabel.setText(buildLabel(this.secondLabelText, model.second().size()));
        firePropertyChange(-1, "secondLabelText", this.secondLabelText, oldSecondLabelText);
    }

    /**
     * Return the label text for the third list view.  Defaults to {@link #DEFAULT_THIRD_LABEL_TEXT}.
     *
     * @return the label text for the third list view
     */
    public String getThirdLabelText()
    {
        return thirdLabelText;
    }

    /**
     * Set the label text for the third list view to <code>thirdLabelText</code>.
     *
     * <p>This is a bound property.</p>
     *
     * @param thirdLabelText label text for the third list view
     */
    public void setThirdLabelText(final String thirdLabelText)
    {
        String oldThirdLabelText = this.thirdLabelText;
        this.thirdLabelText = thirdLabelText;
        thirdLabel.setText(buildLabel(this.thirdLabelText, model.third().size()));
        firePropertyChange(-1, "thirdLabelText", this.thirdLabelText, oldThirdLabelText);
    }

    /**
     * Area node.
     */
    private class AreaNode
        extends PNode
    {
        private PPath path = null;

        // todo:  implement this to allow for mouse-over, picking, etc.
        private void setArea(final Area area)
        {
            Rectangle2D rect = area.getBounds2D();
            path = PPath.createRectangle((float) rect.getX(), (float) rect.getY(), (float) rect.getWidth(), (float) rect.getHeight());
            path.setPaint(new Color(80, 80, 80, 20));
            path.setStrokePaint(new Color(80, 80, 80, 80));
            addChild(path);
        }
    }
}