/*

    dsh-iconbundle  Bundles of variants for icon images.
    Copyright (c) 2003-2005 held jointly by the individual authors.

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
package org.dishevelled.iconbundle.impl;

import java.io.File;

import java.net.URL;

import java.awt.Image;
import java.awt.Color;
import java.awt.Component;

import java.awt.image.BufferedImage;

import javax.swing.UIManager;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscoderException;

import org.apache.batik.transcoder.image.ImageTranscoder;

import org.dishevelled.iconbundle.IconSize;
import org.dishevelled.iconbundle.IconState;
import org.dishevelled.iconbundle.IconBundle;
import org.dishevelled.iconbundle.IconTextDirection;

/**
 * Icon bundle derived from a single scalable base image in SVG format.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class SVGIconBundle
    implements IconBundle
{
    /** SVG document or somesuch? */
    private URL url;

    /** Image transcoder. */
    private BufferedImageTranscoder transcoder = new BufferedImageTranscoder();


    /**
     * Create a new SVG icon bundle from a base scalable image that will
     * be read from the specified file.
     *
     * @param file file, must not be null
     */
    public SVGIconBundle(final File file)
    {
        if (file == null)
        {
            throw new IllegalArgumentException("file must not be null");
        }

        try
        {
            url = new URL("file://" + file.getAbsolutePath());
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("could not create base image URL", e);
        }
    }

    /**
     * Create a new SVG icon bundle from a base scalable image that will
     * the specified URL.
     *
     * @param url URL, must not be null
     */
    public SVGIconBundle(final URL url)
    {
        if (url == null)
        {
            throw new IllegalArgumentException("url must not be null");
        }

        this.url = url;
    }


    /** @see IconBundle */
    public Image getImage(final Component component,
                          final IconTextDirection direction,
                          final IconState state,
                          final IconSize size)
    {
        if (direction == null)
        {
            throw new IllegalArgumentException("direction must not be null");
        }
        if (state == null)
        {
            throw new IllegalArgumentException("state must not be null");
        }
        if (size == null)
        {
            throw new IllegalArgumentException("size must not be null");
        }

        int h = size.getHeight();
        int w = size.getWidth();

        transcoder.addTranscodingHint(ImageTranscoder.KEY_WIDTH, new Float(w));
        transcoder.addTranscodingHint(ImageTranscoder.KEY_HEIGHT, new Float(h));

        try
        {
            transcoder.transcode(new TranscoderInput(url.toString()), new TranscoderOutput());
        }
        catch (TranscoderException e)
        {
            // ignore
        }

        BufferedImage image = transcoder.getImage();

        if (IconState.ACTIVE == state)
        {
            return IconBundleUtils.makeActive(image);
        }
        else if (IconState.MOUSEOVER == state)
        {
            return IconBundleUtils.makeMouseover(image);
        }
        else if (IconState.SELECTED == state)
        {
            Color selectionColor = UIManager.getColor("List.selectionBackground");
            return IconBundleUtils.makeSelected(image, selectionColor);
        }
        else if (IconState.DRAGGING == state)
        {
            return IconBundleUtils.makeDragging(image);
        }
        else if (IconState.DISABLED == state)
        {
            return IconBundleUtils.makeDisabled(image);
        }
        else
        {
            return image;
        }
    }
}