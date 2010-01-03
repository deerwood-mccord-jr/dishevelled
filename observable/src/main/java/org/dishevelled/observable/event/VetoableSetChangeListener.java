/*

    dsh-observable  Observable decorators for collection and map interfaces.
    Copyright (c) 2003-2010 held jointly by the individual authors.

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
package org.dishevelled.observable.event;

import java.util.EventListener;

/**
 * A listener that receives notification of vetoable
 * changes about to be made to an observable set.
 *
 * @param <E> set element type
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public interface VetoableSetChangeListener<E>
    extends EventListener
{

    /**
     * Notify this listener that a vetoable change is about
     * to be made to the observable set.
     *
     * @param e vetoable set change event
     * @throws SetChangeVetoException if this listener wishes
     *    the change about to be made to be rolled back
     */
    void setWillChange(VetoableSetChangeEvent<E> e)
        throws SetChangeVetoException;
}
