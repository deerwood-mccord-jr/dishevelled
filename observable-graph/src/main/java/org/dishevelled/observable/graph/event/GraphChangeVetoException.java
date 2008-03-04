/*

    dsh-observable-graph  Observable decorators for graph interfaces.
    Copyright (c) 2008 held jointly by the individual authors.

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
package org.dishevelled.observable.graph.event;

/**
 * Exception thrown in the event a graph change is vetoed by a <code>VetoableGraphChangeListener</code>.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public class GraphChangeVetoException
    extends Exception
{

    /**
     * Create a new graph change veto exception.
     */
    public GraphChangeVetoException()
    {
        super();
    }

    /**
     * Create a new graph change veto exception
     * with the specified error message.
     *
     * @param message error message
     */
    public GraphChangeVetoException(final String message)
    {
        super(message);
    }
}