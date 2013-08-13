/*

    dsh-variation-cytoscape3-app  Variation Cytoscape3 app.
    Copyright (c) 2013 held jointly by the individual authors.

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
package org.dishevelled.variation.cytoscape3.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import org.cytoscape.model.CyNetwork;

import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;

import org.dishevelled.variation.FeatureService;

/**
 * Retrieve features task factory.
 *
 * @author  Michael Heuer
 */
final class RetrieveFeaturesTaskFactory
    extends AbstractTaskFactory
{
    /** Variation model. */
    private final VariationModel model;


    /**
     * Create a new retrieve features task factory.
     *
     * @param model model, must not be null
     */
    RetrieveFeaturesTaskFactory(final VariationModel model)
    {
        checkNotNull(model);
        this.model = model;
    }


    @Override
    public TaskIterator createTaskIterator()
    {
        // hmm...
        //FeatureService featureService = null;

        RetrieveFeaturesTask retrieveFeaturesTask = new RetrieveFeaturesTask(model, model.getFeatureService());
        return new TaskIterator(retrieveFeaturesTask);
    }
}