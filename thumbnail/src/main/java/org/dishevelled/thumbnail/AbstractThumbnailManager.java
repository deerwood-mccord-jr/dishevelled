/*

    dsh-thumbnail  Implementation of the freedesktop.org thumbnail specification.
    Copyright (c) 2013 held jointly by the individual authors.

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
package org.dishevelled.thumbnail;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;

import com.google.common.io.Files;

import org.apache.commons.codec.digest.DigestUtils;

import org.imgscalr.Scalr;

/**
 * Abstract thumbnail manager.
 *
 * @author  Michael Heuer
 */
public abstract class AbstractThumbnailManager implements ThumbnailManager
{
    /** Root thumbnail directory. */
    private final File directory;

    /** Directory for normal size (128x128 pixel) thumbnails. */
    private final File normalDirectory;

    /** Directory for large size (256x256 pixel) thumbnails. */
    private final File largeDirectory;

    /** Directory for metadata about failed thumbnail images. */
    private final File failDirectory;

    protected AbstractThumbnailManager(final File directory)
    {
        if (directory == null)
        {
            throw new IllegalArgumentException("directory must not be null");
        }
        this.directory = directory;

        normalDirectory = new File(this.directory, "normal");
        largeDirectory = new File(this.directory, "large");
        failDirectory = new File(this.directory, "fail");
        normalDirectory.mkdirs();
        largeDirectory.mkdirs();
        failDirectory.mkdirs();

        fixDirectoryPermissions(this.directory);
        fixDirectoryPermissions(normalDirectory);
        fixDirectoryPermissions(largeDirectory);
        fixDirectoryPermissions(failDirectory);
    }


    private void fixPermissions(final File file)
    {
        // set to 600
        file.setReadable(true, true);
        file.setWritable(true, true);
        file.setExecutable(false);
    }

    private void fixDirectoryPermissions(final File directory)
    {
        // set to 700
        directory.setReadable(true, true);
        directory.setWritable(true, true);
        directory.setExecutable(true, true);
    }

    private BufferedImage createThumbnail(final URI uri, final long modificationTime, final File thumbnailDirectory, final int size) throws IOException
    {
        if (uri == null)
        {
            throw new IllegalArgumentException("uri must not be null");
        }

        File thumbnailFile = new File(thumbnailDirectory, DigestUtils.md5Hex(uri.toString()) + ".png");
        if (thumbnailFile.exists())
        {
            Thumbnail thumbnail = Thumbnail.read(thumbnailFile);
            if (thumbnail.getModificationTime() == modificationTime)
            {
                return thumbnail.getImage();
            }
        }

        URL url = uri.toURL();
        BufferedImage image = ImageIO.read(url);
        Thumbnail thumbnail = new Thumbnail(uri, modificationTime, image.getWidth(), image.getHeight(), Scalr.resize(image, size));

        File tmp = File.createTempFile("tmp", ".png", thumbnailDirectory);
        fixPermissions(tmp);
        thumbnail.write(tmp);
        Files.move(tmp, thumbnailFile);

        return thumbnail.getImage();
    }

    @Override
    public final BufferedImage createThumbnail(final URI uri, final long modificationTime) throws IOException
    {
        return createThumbnail(uri, modificationTime, normalDirectory, 128);
    }

    @Override
    public final BufferedImage createLargeThumbnail(final URI uri, final long modificationTime) throws IOException
    {
        return createThumbnail(uri, modificationTime, largeDirectory, 256);
    }
}