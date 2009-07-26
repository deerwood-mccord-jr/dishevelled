/*

    dsh-piccolo-identify-examples  Piccolo2D identifiable bean node examples.
    Copyright (c) 2007-2009 held jointly by the individual authors.

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
package org.dishevelled.piccolo.identify.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import java.awt.image.BufferedImage;

import java.io.IOException;

import java.util.Iterator;

import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.umd.cs.piccolo.PCanvas;
import edu.umd.cs.piccolo.PNode;

import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

import edu.umd.cs.piccolo.nodes.PImage;
import edu.umd.cs.piccolo.nodes.PText;

import edu.umd.cs.piccolo.util.PPaintContext;

import org.dishevelled.iconbundle.IconBundle;
import org.dishevelled.iconbundle.IconSize;

import org.dishevelled.iconbundle.tango.TangoProject;

import org.dishevelled.identify.Identifiable;

import org.dishevelled.piccolo.identify.AbstractIdNode;
import org.dishevelled.piccolo.identify.GenericIdNode;
import org.dishevelled.piccolo.identify.FinderIdNode;
import org.dishevelled.piccolo.identify.NautilusIdNode;
import org.dishevelled.piccolo.identify.ExplorerIdNode;

/**
 * IdNode example.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class IdNodeExample
    extends JPanel
    implements Runnable
{
    /** Custom 128x128 icon size. */
    private static final IconSize CUSTOM_128X128 = new IconSize(128, 128) {};


    /**
     * Create a new id node example.
     */
    public IdNodeExample()
    {
        super();

        final PCanvas canvas = new PCanvas();
        canvas.setAnimatingRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);
        canvas.setDefaultRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);
        canvas.setInteractingRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);
        canvas.setOpaque(true);
        //canvas.setBackgroundPaint(Color.BLACK);

        Image backgroundImage = loadImage("mandolux-liriope-r-1440-flipped.jpg");
        final PImage background = new PImage(backgroundImage);
        background.offset(0.0d, 0.0d);
        canvas.getLayer().addChild(background);

        PText genericLabel = new PText("Generic");
        genericLabel.offset(0, 50);
        genericLabel.setTextPaint(Color.WHITE);
        canvas.getLayer().addChild(genericLabel);
        Font defaultFont = PText.DEFAULT_FONT;
        GenericIdNode idNode0 = new GenericIdNode(new Doc(), TangoProject.EXTRA_SMALL);
        idNode0.setFont(defaultFont.deriveFont(10.0f));
        idNode0.offset(75, 0);
        canvas.getLayer().addChild(idNode0);
        GenericIdNode idNode1 = new GenericIdNode(new Mic(), TangoProject.SMALL);
        idNode1.setFont(defaultFont.deriveFont(11.0f));
        idNode1.offset(150, 25);
        canvas.getLayer().addChild(idNode1);
        GenericIdNode idNode2 = new GenericIdNode(new Computer(), TangoProject.MEDIUM);
        idNode2.offset(225, 50);
        canvas.getLayer().addChild(idNode2);
        GenericIdNode idNode3 = new GenericIdNode(new Doc(), TangoProject.LARGE);
        idNode3.offset(300, 75);
        canvas.getLayer().addChild(idNode3);
        GenericIdNode idNode4 = new GenericIdNode(new Mic(), CUSTOM_128X128);
        idNode4.setFont(defaultFont.deriveFont(14.0f));
        idNode4.offset(375, 100);
        canvas.getLayer().addChild(idNode4);

        PText finderLabel = new PText("Finder-style");
        finderLabel.offset(0, 250);
        finderLabel.setTextPaint(Color.WHITE);
        canvas.getLayer().addChild(finderLabel);
        FinderIdNode idNode5 = new FinderIdNode(new Doc(), TangoProject.EXTRA_SMALL);
        idNode5.setFont(defaultFont.deriveFont(10.0f));
        idNode5.offset(75, 200);
        canvas.getLayer().addChild(idNode5);
        FinderIdNode idNode6 = new FinderIdNode(new Mic(), TangoProject.SMALL);
        idNode6.setFont(defaultFont.deriveFont(11.0f));
        idNode6.offset(150, 225);
        canvas.getLayer().addChild(idNode6);
        FinderIdNode idNode7 = new FinderIdNode(new Computer(), TangoProject.MEDIUM);
        idNode7.offset(225, 250);
        canvas.getLayer().addChild(idNode7);
        FinderIdNode idNode8 = new FinderIdNode(new Doc(), TangoProject.LARGE);
        idNode8.offset(300, 275);
        canvas.getLayer().addChild(idNode8);
        FinderIdNode idNode9 = new FinderIdNode(new Mic(), CUSTOM_128X128);
        idNode9.setFont(defaultFont.deriveFont(14.0f));
        idNode9.offset(375, 300);
        canvas.getLayer().addChild(idNode9);

        PText explorerLabel = new PText("Explorer-style");
        explorerLabel.offset(0, 450);
        explorerLabel.setTextPaint(Color.WHITE);
        canvas.getLayer().addChild(explorerLabel);
        Font tahoma = new Font("Tahoma", Font.PLAIN, 11);
        ExplorerIdNode idNode10 = new ExplorerIdNode(new Doc(), TangoProject.EXTRA_SMALL);
        idNode10.setFont(tahoma.deriveFont(10.0f));
        idNode10.offset(75, 400);
        canvas.getLayer().addChild(idNode10);
        ExplorerIdNode idNode11 = new ExplorerIdNode(new Mic(), TangoProject.SMALL);
        idNode11.setFont(tahoma);
        idNode11.offset(150, 425);
        canvas.getLayer().addChild(idNode11);
        ExplorerIdNode idNode12 = new ExplorerIdNode(new Computer(), TangoProject.MEDIUM);
        idNode12.setFont(tahoma);
        idNode12.offset(225, 450);
        canvas.getLayer().addChild(idNode12);
        ExplorerIdNode idNode13 = new ExplorerIdNode(new Doc(), TangoProject.LARGE);
        idNode13.setFont(tahoma);
        idNode13.offset(300, 475);
        canvas.getLayer().addChild(idNode13);
        ExplorerIdNode idNode14 = new ExplorerIdNode(new Mic(), CUSTOM_128X128);
        idNode14.setFont(tahoma.deriveFont(13.0f));
        idNode14.offset(375, 500);
        canvas.getLayer().addChild(idNode14);

        PText nautilusLabel = new PText("Nautilus-style");
        nautilusLabel.offset(0, 650);
        nautilusLabel.setTextPaint(Color.WHITE);
        canvas.getLayer().addChild(nautilusLabel);
        NautilusIdNode idNode15 = new NautilusIdNode(new Doc(), TangoProject.EXTRA_SMALL);
        idNode15.setFont(defaultFont.deriveFont(10.0f));
        idNode15.offset(75, 600);
        canvas.getLayer().addChild(idNode15);
        NautilusIdNode idNode16 = new NautilusIdNode(new Mic(), TangoProject.SMALL);
        idNode16.setFont(defaultFont.deriveFont(11.0f));
        idNode16.offset(150, 625);
        canvas.getLayer().addChild(idNode16);
        NautilusIdNode idNode17 = new NautilusIdNode(new Computer(), TangoProject.MEDIUM);
        idNode17.offset(225, 650);
        canvas.getLayer().addChild(idNode17);
        NautilusIdNode idNode18 = new NautilusIdNode(new Doc(), TangoProject.LARGE);
        idNode18.offset(300, 675);
        canvas.getLayer().addChild(idNode18);        
        NautilusIdNode idNode19 = new NautilusIdNode(new Mic(), CUSTOM_128X128);
        idNode19.setFont(defaultFont.deriveFont(14.0f));
        idNode19.offset(375, 700);
        canvas.getLayer().addChild(idNode19);

        // simulate a selection model by deselecting all when clicking outside a pickable node
        canvas.getCamera().addInputEventListener(new PBasicInputEventHandler()
            {
                /** {@inheritDoc} */
                public void mousePressed(final PInputEvent event)
                {
                    if (event.getCamera().equals(event.getPickedNode()) || background.equals(event.getPickedNode()))
                    {
                        for (Iterator i = canvas.getLayer().getChildrenIterator(); i.hasNext(); )
                        {
                            PNode node = (PNode) i.next();
                            if (node instanceof AbstractIdNode)
                            {
                                AbstractIdNode idNode = (AbstractIdNode) node;
                                idNode.deselect();
                            }
                        }
                    }
                }
            });

        setLayout(new BorderLayout());
        add("Center", canvas);
    }


    /** {@inheritDoc} */
    public void run()
    {
        final JFrame f = new JFrame("IdNode Example");
        f.setContentPane(this);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBounds(100, 100, 800, 600);
        f.setVisible(true);
    }


    /**
     * Mic.
     */
    private static class Mic
        implements Identifiable
    {
        /** {@inheritDoc} */
        public IconBundle getIconBundle()
        {
            return TangoProject.AUDIO_INPUT_MICROPHONE;
        }

        /** {@inheritDoc} */
        public String getName()
        {
            return "Microphone";
        }
    }

    /**
     * Doc.
     */
    private static class Doc
        implements Identifiable
    {
        /** {@inheritDoc} */
        public IconBundle getIconBundle()
        {
            return TangoProject.TEXT_X_GENERIC;
        }

        /** {@inheritDoc} */
        public String getName()
        {
            return "Document";
        }
    }

    /**
     * Computer.
     */
    private static class Computer
        implements Identifiable
    {
        /** {@inheritDoc} */
        public IconBundle getIconBundle()
        {
            return TangoProject.COMPUTER;
        }

        /** {@inheritDoc} */
        public String getName()
        {
            return "My Computer";
        }
    }

    private Image loadImage(final String name)
    {
        BufferedImage image = null;
        try
        {
            image = ImageIO.read(getClass().getResource(name));
        }
        catch (IOException e)
        {
            // ignore
        }
        return image;
    }

    /**
     * Main.
     *
     * @param args command line arguments, ignored
     */
    public static void main(final String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
            {
                /** {@inheritDoc} */
                public void run()
                {
                    new IdNodeExample().run();
                }
            });
    }
}
