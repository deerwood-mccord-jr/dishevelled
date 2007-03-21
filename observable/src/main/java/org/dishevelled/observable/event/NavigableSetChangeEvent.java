/*

    dsh-observable  Observable decorators for collection and map interfaces.
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
package org.dishevelled.observable.event;

import java.util.EventObject;

import org.dishevelled.observable.ObservableNavigableSet;

/**
 * An event object representing a change made to
 * an observable navigable set.
 *
 * @param <E> navigable set element type
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public class NavigableSetChangeEvent<E>
    extends EventObject
{

    /**
     * Create a new navigable set change event with the specified
     * observable navigable set as the event source.
     *
     * @param source source of the event
     */
    public NavigableSetChangeEvent(final ObservableNavigableSet<E> source)
    {
        super(source);
    }


    /**
     * Return the source of this navigable set change event as an
     * <code>ObservableNavigableSet</code>.
     *
     * @return the source of this navigable set change event as an
     *    <code>ObservableNavigableSet</code>
     */
    public final ObservableNavigableSet<E> getObservableNavigableSet()
    {
        return (ObservableNavigableSet<E>) super.getSource();
    }
}
