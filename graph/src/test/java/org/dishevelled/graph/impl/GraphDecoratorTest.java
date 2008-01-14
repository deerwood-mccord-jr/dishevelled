/*

    dsh-graph  Directed graph interface and implementation.
    Copyright (c) 2004-2008 held jointly by the individual authors.

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
package org.dishevelled.graph.impl;

import org.dishevelled.graph.AbstractGraphTest;
import org.dishevelled.graph.Edge;
import org.dishevelled.graph.Graph;
import org.dishevelled.graph.Node;

/**
 * Unit test for AbstractGraphDecorator.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class GraphDecoratorTest
    extends AbstractGraphTest
{

    /** @{inheritDoc} */
    protected <N, E> Graph<N, E> createEmptyGraph()
    {
        return new Decorator(new GraphImpl<N, E>());
    }

    /** @{inheritDoc} */
    protected <N, E> Graph<N, E> createFullGraph(final N nodeValue, final E edgeValue)
    {
        Graph<N, E> graph = new GraphImpl<N, E>();
        Node node0 = graph.createNode(nodeValue);
        Node node1 = graph.createNode(nodeValue);
        Edge edge = graph.createEdge(node0, node1, edgeValue);
        return new Decorator(graph);
    }


    /**
     * Decorator.
     */
    private class Decorator<N, E>
        extends AbstractGraphDecorator<N, E>
    {

        /**
         * Create a new graph that decorates the specified graph.
         *
         * @param graph graph to decorate, must not be null
         */
        private Decorator(final Graph<N, E> graph)
        {
            super(graph);
        }
    }
}