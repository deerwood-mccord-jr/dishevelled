/*

    dsh-thumbnail  Implementation of the freedesktop.org thumbnail specification.
    Copyright (c) 2013-2015 held jointly by the individual authors.

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
package org.dishevelled.thumbnail.swing;

import java.awt.Component;
import java.awt.Image;

import java.io.File;
import java.io.IOException;

import java.net.URI;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import org.dishevelled.thumbnail.ThumbnailManager;

/**
 * List cell renderer that displays the large thumbnail for a given URI.
 *
 * @author  Michael Heuer
 */
public final class LargeThumbnailListCellRenderer extends DefaultListCellRenderer
{
    /** Last modified cache. */
    private final LastModifiedCache lastModifiedCache;

    /** Thumbnail manager. */
    private final ThumbnailManager thumbnailManager;

    /** ImageIcon wrapper for thumbnail image. */
    private transient ImageIcon imageIcon;


    /**
     * Create a new large thumbnail list cell renderer with the specified thumbnail manager.
     *
     * @param thumbnailManager thumbnail manager, must not be null
     */
    public LargeThumbnailListCellRenderer(final ThumbnailManager thumbnailManager)
    {
        super();
        if (thumbnailManager == null)
        {
            throw new IllegalArgumentException("thumbnailManager must not be null");
        }
        this.thumbnailManager = thumbnailManager;
        lastModifiedCache = LastModifiedCache.getInstance();
    }


    @Override
    public Component getListCellRendererComponent(final JList list,
                                                  final Object value,
                                                  final int index,
                                                  final boolean isSelected,
                                                  final boolean hasFocus)
    {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);

        if (value instanceof URI)
        {
            URI uri = (URI) value;
            try
            {
                Image thumbnail = thumbnailManager.createLargeThumbnail(uri, lastModifiedCache.get(uri));
                if (imageIcon == null)
                {
                    imageIcon = new ImageIcon(thumbnail);
                }
                else
                {
                    imageIcon.setImage(thumbnail);
                }
                label.setIcon(imageIcon);
            }
            catch (IOException e)
            {
                // ignore
            }
        }
        return label;
    }
}
