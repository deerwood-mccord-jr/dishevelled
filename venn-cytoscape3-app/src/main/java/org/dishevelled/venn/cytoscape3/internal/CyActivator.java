/*

    dsh-venn-cytoscape3-app  Cytoscape3 app for venn diagrams.
    Copyright (c) 2012 held jointly by the individual authors.

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
package org.dishevelled.venn.cytoscape3.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.group.CyGroupManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.service.util.CyServiceRegistrar;

import org.osgi.framework.BundleContext;

/**
 * Activator.
 */
public final class CyActivator extends AbstractCyActivator
{
    @Override
    public void start(final BundleContext bundleContext)
    {
        if (bundleContext == null)
        {
            throw new NullPointerException("bundleContext must not be null");
        }
        CyApplicationManager applicationManager = getService(bundleContext, CyApplicationManager.class);
        CyGroupManager groupManager = getService(bundleContext, CyGroupManager.class);
        CyServiceRegistrar serviceRegistrar = getService(bundleContext, CyServiceRegistrar.class);

        VennDiagramsAction vennDiagramsAction = new VennDiagramsAction(applicationManager, groupManager, serviceRegistrar);
        Properties properties = new Properties();
        registerService(bundleContext, vennDiagramsAction, CyAction.class, properties);
    }
}