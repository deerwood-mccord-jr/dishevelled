/*

    dsh-swarm  Framework for particle swarm optimization algorithms.
    Copyright (c) 2006-2007 held jointly by the individual authors.

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
package org.dishevelled.swarm;

import java.util.EventListener;

/**
 * A listener that receives notification of progress in a
 * particle swarm optimization algorithm function.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public interface ParticleSwarmOptimizationAlgorithmListener
    extends EventListener
{

    /**
     * Notify this listener of an exit failed event.
     *
     * @param event exit failed event
     */
    void exitFailed(ParticleSwarmOptimizationAlgorithmEvent event);

    /**
     * Notify this listener of an exit succeeded event.
     *
     * @param event exit succeeded event
     */
    void exitSucceeded(ParticleSwarmOptimizationAlgorithmEvent event);

    /**
     * Notify this listener of a velocity calculated event.
     *
     * @param event velocity calculated event
     */
    void velocityCalculated(ParticleSwarmOptimizationAlgorithmEvent event);

    /**
     * Notify this listener of a position updated event.
     *
     * @param event position updated event
     */
    void positionUpdated(ParticleSwarmOptimizationAlgorithmEvent event);

    /**
     * Notify this listener of a fitness calculated event.
     *
     * @param event fitness calculated event
     */
    void fitnessCalculated(ParticleSwarmOptimizationAlgorithmEvent event);

    /**
     * Notify this listener of a cognitive memory updated event.
     *
     * @param event cognitive memory updated event
     */
    void cognitiveMemoryUpdated(ParticleSwarmOptimizationAlgorithmEvent event);

    /**
     * Notify this listener of a social memory updated event.
     *
     * @param event social memory updated event
     */
    void socialMemoryUpdated(ParticleSwarmOptimizationAlgorithmEvent event);
}
