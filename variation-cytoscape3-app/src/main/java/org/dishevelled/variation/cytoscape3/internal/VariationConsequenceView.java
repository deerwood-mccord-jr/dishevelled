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

import java.util.ArrayList;

import java.awt.Component;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JPopupMenu;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.gui.TableFormat;

import org.dishevelled.eventlist.view.CountLabel;
import org.dishevelled.eventlist.view.ElementsTable;

import org.dishevelled.iconbundle.tango.TangoProject;

import org.dishevelled.identify.IdentifiableAction;

import org.dishevelled.layout.LabelFieldPanel;

import org.dishevelled.variation.Feature;
import org.dishevelled.variation.Variation;
import org.dishevelled.variation.VariationConsequence;

/**
 * Variation consequence view.
 */
final class VariationConsequenceView
    extends LabelFieldPanel
{
    /** Variation model. */
    private final VariationModel model;

    /** Variation consequence table. */
    private final VariationConsequenceTable variationConsequenceTable;

    /** Refresh action. */
    private final IdentifiableAction refresh = new IdentifiableAction("Refresh...", TangoProject.VIEW_REFRESH)
        {
            @Override
            public void actionPerformed(final ActionEvent event)
            {
                refresh();
            }
        };

    // todo:  need to add node, feature, variation columns
    /** Table property names. */
    private static final String[] PROPERTY_NAMES = { "species", "reference", "identifier", "name", "start", "end", "strand", "referenceAllele", "alternateAllele", "sequenceOntologyTerm" };

    /** Table column labels. */
    private static final String[] COLUMN_LABELS = { "Species", "Reference", "Identifier", "Region", "Start", "End", "Strand", "Ref", "Alt", "Consequence" };

    /** Table format. */
    private static final TableFormat<VariationConsequence> TABLE_FORMAT = GlazedLists.tableFormat(VariationConsequence.class, PROPERTY_NAMES, COLUMN_LABELS);


    /**
     * Create a new variation consequence view with the specified model.
     *
     * @param model model, must not be null
     */
    VariationConsequenceView(final VariationModel model)
    {
        super();
        setOpaque(false);
        this.model = model;
        variationConsequenceTable = new VariationConsequenceTable(this.model.variationConsequences());

        layoutComponents();
    }


    /**
     * Layout components.
     */
    private void layoutComponents()
    {
        addSpacing(12);
        addField("Nodes:", new NodeCountLabel(model));
        addField("Nodes with features:", new CountLabel<Feature>(model.features()));
        addField("Variations associated with features:", new CountLabel<Variation>(model.variations()));
        addField("Variation consequences:", new CountLabel<VariationConsequence>(model.variationConsequences()));
        addSpacing(12);
        addField("Variation consequence service:", "Ensembl REST client");
        addField("Endpoint:", "http:///beta.rest.ensembl.org/");
        addField("Species:", "human");
        addField("Reference:", "GRCh37");
        addSpacing(12);
        addField("Variation consequence prediction service:", "Ensembl REST client");
        addField("Endpoint:", "http:///beta.rest.ensembl.org/");
        addField("Species:", "human");
        addField("Reference:", "GRCh37");
        addSpacing(12);
        addFinalField(variationConsequenceTable);
    }

    /**
     * Refresh.
     */
    private void refresh()
    {
        // empty
    }

    /**
     * Variation consequenc table.
     */
    private class VariationConsequenceTable extends ElementsTable<VariationConsequence>
    {

        /**
         * Create a new variation consequence table with the specified list of variation consequences.
         *
         * @param variationConsequences list of variation consequences, must not be null
         */
        VariationConsequenceTable(final EventList<VariationConsequence> variationConsequences)
        {
            super("Variation consequences:", variationConsequences, TABLE_FORMAT);

            Component refreshContextMenuComponent = getContextMenu().add(refresh);
            // place at index 8 before add action
            getContextMenu().remove(refreshContextMenuComponent);
            getContextMenu().add(refreshContextMenuComponent, 8);

            Component refreshToolBarComponent = getToolBar().add(refresh);
            // place at index 0 before add action
            getToolBar().remove(refreshToolBarComponent);
            getToolBar().add(refreshToolBarComponent, 0);
        }


        @Override
        public void add()
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(this);
        }
    }
}