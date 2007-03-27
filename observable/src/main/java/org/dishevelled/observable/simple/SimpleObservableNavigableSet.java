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
package org.dishevelled.observable.simple;

import java.util.NavigableSet;

import org.dishevelled.observable.AbstractObservableNavigableSet;

import org.dishevelled.observable.event.NavigableSetChangeEvent;
import org.dishevelled.observable.event.VetoableNavigableSetChangeEvent;

/**
 * Observable navigable set decorator that simply fires empty
 * vetoable navigable set change events in <code>preXxx</code> methods and
 * empty navigable set change events in <code>postXxx</code> methods.
 * Observable navigable set listeners may query the source of the events to determine
 * what may or may not have changed due to the event.
 *
 * @param <E> navigable set element type
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public class SimpleObservableNavigableSet<E>
    extends AbstractObservableNavigableSet<E>
{
    /** Cached navigable set change event. */
    private final NavigableSetChangeEvent<E> changeEvent;

    /** Cached vetoable navigable set change event. */
    private final VetoableNavigableSetChangeEvent<E> vetoableChangeEvent;


    /**
     * Create a new observable decorator for the specified
     * navigable set.
     *
     * @param navigableSet navigable set to decorate, must not be null
     */
    public SimpleObservableNavigableSet(final NavigableSet<E> navigableSet)
    {
        super(navigableSet);
        changeEvent = new NavigableSetChangeEvent<E>(this);
        vetoableChangeEvent = new VetoableNavigableSetChangeEvent<E>(this);
    }


    // TODO:
    // implement pre/post methods


}
