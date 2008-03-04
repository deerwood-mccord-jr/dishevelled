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

import java.io.Serializable;

import javax.swing.event.EventListenerList;

import org.dishevelled.observable.graph.ObservableGraph;

/**
 * A support class that can be used via delegation to provide <code>GraphChangeListener</code>
 * and <code>VetoableGraphChangeListener</code> management.
 *
 * @param <N> node value type
 * @param <E> edge value type
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public class ObservableGraphChangeSupport<N, E>
    implements Serializable
{
    /** Event source. */
    private ObservableGraph<N, E> source;

    /** Listener list. */
    private final EventListenerList listenerList;


    /**
     * Create a new support class meant to be subclassed.
     */
    protected ObservableGraphChangeSupport()
    {
        listenerList = new EventListenerList();
    }

    /**
     * Create a new support class that fires graph change and
     * vetoable graph change events with the specified source as the
     * source of the events.
     *
     * @param source the event source
     */
    public ObservableGraphChangeSupport(final ObservableGraph<N, E> source)
    {
        this();
        setSource(source);
    }


    /**
     * Set the source of graph change and vetoable graph change events
     * to <code>source</code>.  Subclasses should call this method before
     * any of the <code>fireX</code> methods.
     *
     * @param source the event source
     */
    protected void setSource(final ObservableGraph<N, E> source)
    {
        this.source = source;
    }

    /**
     * Return the <code>EventListenerList</code> backing this
     * observable graph support class.
     *
     * @return the <code>EventListenerList</code> backing this
     *    observable graph support class.
     */
    protected final EventListenerList getEventListenerList()
    {
        return listenerList;
    }

    /**
     * Add the specified graph change listener.
     *
     * @param l graph change listener to add
     */
    public final void addGraphChangeListener(final GraphChangeListener<N, E> l)
    {
        listenerList.add(GraphChangeListener.class, l);
    }

    /**
     * Remove the specified graph change listener.
     *
     * @param l graph change listener to remove
     */
    public final void removeGraphChangeListener(final GraphChangeListener<N, E> l)
    {
        listenerList.remove(GraphChangeListener.class, l);
    }

    /**
     * Add the specified vetoable graph change listener.
     *
     * @param l vetoable graph change listener to add
     */
    public final void addVetoableGraphChangeListener(final VetoableGraphChangeListener<N, E> l)
    {
        listenerList.add(VetoableGraphChangeListener.class, l);
    }

    /**
     * Remove the specified vetoable graph change listener.
     *
     * @param l vetoable graph change listener to remove
     */
    public final void removeVetoableGraphChangeListener(final VetoableGraphChangeListener<N, E> l)
    {
        listenerList.remove(VetoableGraphChangeListener.class, l);
    }

    /**
     * Return an array of all <code>GraphChangeListener</code>s, or
     * an empty array if none are registered.
     *
     * @return an array of all <code>GraphChangeListener</code>s, or
     *    an empty array if none are registered
     */
    public final GraphChangeListener<N, E>[] getGraphChangeListeners()
    {
        return (GraphChangeListener<N, E>[]) listenerList.getListeners(GraphChangeListener.class);
    }

    /**
     * Return the number of <code>GraphChangeListener</code>s registered
     * to this observable graph support class.
     *
     * @return the number of <code>GraphChangeListener</code>s registered
     *    to this observable graph support class
     */
    public final int getGraphChangeListenerCount()
    {
        return listenerList.getListenerCount(GraphChangeListener.class);
    }

    /**
     * Return an array of all <code>VetoableGraphChangeListener</code>s,
     * or an empty array if none are registered.
     *
     * @return an array of all <code>VetoableGraphChangeListener</code>s,
     *    or an empty array if none are registered
     */
    public final VetoableGraphChangeListener<N, E>[] getVetoableGraphChangeListeners()
    {
        return (VetoableGraphChangeListener<N, E>[])
            listenerList.getListeners(VetoableGraphChangeListener.class);
    }

    /**
     * Return the number of <code>VetoableGraphChangeListener</code>s
     * registered to this observable graph support class.
     *
     * @return the number of <code>VetoableGraphChangeListener</code>s
     *    registered to this observable graph support class
     */
    public final int getVetoableGraphChangeListenerCount()
    {
        return listenerList.getListenerCount(VetoableGraphChangeListener.class);
    }

    /**
     * Fire a will change event to all registered
     * <code>VetoableGraphChangeListener</code>s.
     *
     * @throws GraphChangeVetoException if any of the listeners veto the change
     */
    public void fireGraphWillChange()
        throws GraphChangeVetoException
    {
        Object[] listeners = listenerList.getListenerList();
        VetoableGraphChangeEvent<N, E> e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2)
        {
            if (listeners[i] == VetoableGraphChangeListener.class)
            {
                // lazily create the event
                if (e == null)
                {
                    e = new VetoableGraphChangeEvent<N, E>(source);
                }
                ((VetoableGraphChangeListener<N, E>) listeners[i + 1]).graphWillChange(e);
            }
        }
    }

    /**
     * Fire the specified will change event to all registered
     * <code>VetoableGraphChangeListener</code>s.
     *
     * @param e will change event
     * @throws GraphChangeVetoException if any of the listeners veto the change
     */
    public void fireGraphWillChange(final VetoableGraphChangeEvent<N, E> e)
        throws GraphChangeVetoException
    {
        Object[] listeners = listenerList.getListenerList();

        for (int i = listeners.length - 2; i >= 0; i -= 2)
        {
            if (listeners[i] == VetoableGraphChangeListener.class)
            {
                ((VetoableGraphChangeListener<N, E>) listeners[i + 1]).graphWillChange(e);
            }
        }
    }

    /**
     * Fire a change event to all registered <code>GraphChangeListener</code>s.
     */
    public void fireGraphChanged()
    {
        Object[] listeners = listenerList.getListenerList();
        GraphChangeEvent<N, E> e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2)
        {
            if (listeners[i] == GraphChangeListener.class)
            {
                // lazily create the event
                if (e == null)
                {
                    e = new GraphChangeEvent<N, E>(source);
                }
                ((GraphChangeListener<N, E>) listeners[i + 1]).graphChanged(e);
            }
        }
    }

    /**
     * Fire the specified change event to all registered
     * <code>GraphChangeListener</code>s.
     *
     * @param e change event
     */
    public void fireGraphChanged(final GraphChangeEvent<N, E> e)
    {
        Object[] listeners = listenerList.getListenerList();

        for (int i = listeners.length - 2; i >= 0; i -= 2)
        {
            if (listeners[i] == GraphChangeListener.class)
            {
                ((GraphChangeListener<N, E>) listeners[i + 1]).graphChanged(e);
            }
        }
    }
}