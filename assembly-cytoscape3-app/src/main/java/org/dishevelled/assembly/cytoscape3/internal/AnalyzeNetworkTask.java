/*

    dsh-assembly-cytoscape3-app  Assembly Cytoscape3 app.
    Copyright (c) 2019 held jointly by the individual authors.

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
package org.dishevelled.assembly.cytoscape3.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.google.common.collect.ImmutableList;

import org.cytoscape.application.CyApplicationManager;

import org.cytoscape.model.CyNetwork;

import org.cytoscape.task.analyze.AnalyzeNetworkCollectionTaskFactory;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;

/**
 * Analyze network task.
 *
 * @author  Michael Heuer
 */
final class AnalyzeNetworkTask extends AbstractTask
{
    /** Application manager. */
    private final CyApplicationManager applicationManager;

    /** Analyze network collection task factory. */
    private final AnalyzeNetworkCollectionTaskFactory taskFactory;


    /**
     * Create a new analyze network task.
     *
     * @param applicationManager application manager, must not be null
     * @param taskFactory analyze network collection task factory, must not be null
     */
    AnalyzeNetworkTask(final CyApplicationManager applicationManager, final AnalyzeNetworkCollectionTaskFactory taskFactory)
    {
        checkNotNull(applicationManager);
        checkNotNull(taskFactory);
        this.applicationManager = applicationManager;
        this.taskFactory = taskFactory;
    }


    @Override
    public void run(final TaskMonitor taskMonitor)
    {
        taskMonitor.setProgress(0.0d);
        taskMonitor.setTitle("Analyzing network...");
        taskMonitor.setStatusMessage("Analyzing network...");

        Collection<CyNetwork> networks = ImmutableList.of(applicationManager.getCurrentNetwork());
        TaskIterator taskIterator = taskFactory.createTaskIterator(networks);

        insertTasksAfterCurrentTask(taskIterator);
        taskMonitor.setProgress(1.0d);
    }
}
