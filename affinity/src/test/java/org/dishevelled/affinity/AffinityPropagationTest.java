/*

    dsh-affinity  Clustering by affinity propagation.
    Copyright (c) 2007 held jointly by the individual authors.

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
package org.dishevelled.affinity;

import org.dishevelled.cluster.ClusteringAlgorithm;
import org.dishevelled.cluster.AbstractClusteringAlgorithmTest;

import org.dishevelled.affinity.preference.UniformPreference;

/**
 * Unit test for AffinityPropagation.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public final class AffinityPropagationTest
    extends AbstractClusteringAlgorithmTest
{

    /** {@inheritDoc} */
    protected <T> ClusteringAlgorithm<T> createClusteringAlgorithm()
    {
        return new AffinityPropagation<T>(new UniformPreference<T>());
    }

    public void testAffinityPropagation()
    {
        // empty
    }
}
