/*

    dsh-iconbundle  Bundles of variants for icon images.
    Copyright (c) 2003-2007 held jointly by the individual authors.

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
package org.dishevelled.iconbundle;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import java.io.Serializable;

/**
 * Typesafe enumeration of icon states.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class IconState
    implements Serializable
{
    /** State name. */
    private String name;


    /**
     * Create a new IconState with the specified name.
     *
     * @param name state name
     */
    private IconState(final String name)
    {
        this.name = name;
    }


    /**
     * Return the state name.
     *
     * @return the state name
     */
    public String toString()
    {
        return name;
    }

    /** Normal icon state. */
    public static final IconState NORMAL = new IconState("normal");

    /** Active icon state. */
    public static final IconState ACTIVE = new IconState("active");

    /** Mouseover icon state. */
    public static final IconState MOUSEOVER = new IconState("mouseover");

    /** Selected icon state. */
    public static final IconState SELECTED = new IconState("selected");

    /** Dragging icon state. */
    public static final IconState DRAGGING = new IconState("dragging");

    /** Disabled icon state. */
    public static final IconState DISABLED = new IconState("disabled");

    /**
     * Private array of enumeration values.
     */
    private static final IconState[] VALUES_ARRAY = {
                                                      NORMAL,
                                                      ACTIVE,
                                                      MOUSEOVER,
                                                      SELECTED,
                                                      DRAGGING,
                                                      DISABLED
                                                    };

    /**
     * Public list of enumeration values.
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
}
