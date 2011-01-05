/*

    dsh-swarm  Framework for particle swarm optimization algorithms.
    Copyright (c) 2006-2011 held jointly by the individual authors.

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
package org.dishevelled.swarm;

/**
 * Adapter for the ParticleSwarmOptimizationAlgorithmListener interface
 * that provides empty implementation of all required methods.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public class ParticleSwarmOptimizationAlgorithmAdapter
    implements ParticleSwarmOptimizationAlgorithmListener
{

    /** {@inheritDoc} */
    public void exitFailed(final ParticleSwarmOptimizationAlgorithmEvent event)
    {
        // empty
    }

    /** {@inheritDoc} */
    public void exitSucceeded(final ParticleSwarmOptimizationAlgorithmEvent event)
    {
        // empty
    }

    /** {@inheritDoc} */
    public void velocityCalculated(final ParticleSwarmOptimizationAlgorithmEvent event)
    {
        // empty
    }

    /** {@inheritDoc} */
    public void positionUpdated(final ParticleSwarmOptimizationAlgorithmEvent event)
    {
        // empty
    }

    /** {@inheritDoc} */
    public void fitnessCalculated(final ParticleSwarmOptimizationAlgorithmEvent event)
    {
        // empty
    }

    /** {@inheritDoc} */
    public void cognitiveMemoryUpdated(final ParticleSwarmOptimizationAlgorithmEvent event)
    {
        // empty
    }

    /** {@inheritDoc} */
    public void socialMemoryUpdated(final ParticleSwarmOptimizationAlgorithmEvent event)
    {
        // empty
    }
}
