/*

    dsh-text-rendering-benchmark  Text rendering benchmarks.
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
package org.dishevelled.text.phtmlview;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

import static java.awt.RenderingHints.*;

import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import org.piccolo2d.PCanvas;

import org.piccolo2d.nodes.PHtmlView;

import org.piccolo2d.util.PPaintContext;

import org.dishevelled.text.TextRenderingBenchmark;

/**
 * Abstract PHtmlView text rendering benchmark.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public abstract class AbstractPHtmlViewTextRenderingBenchmark
    implements TextRenderingBenchmark
{
    /** PHtmlView to render text. */
    private final PHtmlView phtmlView = new PHtmlView()
        {
            /** {@inheritDoc} */
            protected void paint(final PPaintContext paintContext)
            {
                Graphics2D graphics = paintContext.getGraphics();
                graphics.setRenderingHint(KEY_FRACTIONALMETRICS, VALUE_FRACTIONALMETRICS_ON);
                prepareGraphics(graphics);
                super.paint(paintContext);
            }
        };

    /** Canvas. */
    private final PCanvas canvas;


    /**
     * Create a new abstract PHtmlView text rendering benchmark.
     */
    public AbstractPHtmlViewTextRenderingBenchmark()
    {
        canvas = new PCanvas();
        canvas.getLayer().addChild(phtmlView);
    }


    /**
     * Prepare the specified graphics for text rendering.
     *
     * @param graphics graphics to prepare
     */
    protected abstract void prepareGraphics(Graphics2D graphics);

    /** {@inheritDoc} */
    public void setFont(final Font font)
    {
        phtmlView.setFont(font);
    }

    /** {@inheritDoc} */
    public void setBounds(final Dimension bounds)
    {
        // todo:  this shouldn't be necessary
        phtmlView.setBounds(0.0d, 0.0d, bounds.getWidth(), bounds.getHeight());
        canvas.setSize(bounds);
        canvas.setMinimumSize(bounds);
        canvas.setPreferredSize(bounds);
    }

    /** {@inheritDoc} */
    public void setText(final String text)
    {
        phtmlView.setText("<html><body>" + text + "</body></html>");
    }

    /** {@inheritDoc} */
    public JComponent getComponent()
    {
        return canvas;
    }

    /** {@inheritDoc} */
    public BufferedImage call() throws Exception
    {
        int h = canvas.getHeight();
        int w = canvas.getWidth();
        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setRenderingHint(KEY_FRACTIONALMETRICS, VALUE_FRACTIONALMETRICS_ON);
        prepareGraphics(graphics);
        canvas.paint(graphics);
        graphics.dispose();
        return bufferedImage;
    }
}