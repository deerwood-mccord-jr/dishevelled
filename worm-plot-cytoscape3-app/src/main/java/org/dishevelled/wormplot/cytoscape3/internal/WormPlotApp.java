/*

    dsh-worm-plot-cytoscape3-app  Worm plot Cytoscape 3 app.
    Copyright (c) 2014 held jointly by the individual authors.

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
package org.dishevelled.wormplot.cytoscape3.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import org.dishevelled.layout.LabelFieldPanel;

/**
 * Worm plot app.
 *
 * @author  Michael Heuer
 */
final class WormPlotApp extends JPanel
{
    /** Worm plot model. */
    private final WormPlotModel model;

    /** Sequence file name. */
    private final JTextField sequenceFileName;

    /** Length. */
    private final JTextField length;

    /** Overlap. */
    private final JTextField overlap;

    /** Apply action. */
    private final AbstractAction apply = new AbstractAction("Apply")
        {
            @Override
            public void actionPerformed(final ActionEvent event)
            {
                apply();
            }
        };

    /** Property change listener. */
    private final PropertyChangeListener propertyChangeListener = new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent event)
            {
                if ("sequenceFile".equals(event.getPropertyName()))
                {
                    sequenceFileName.setText((String) event.getNewValue());
                }
                else if ("length".equals(event.getPropertyName()))
                {
                    length.setText(String.valueOf((Integer) event.getNewValue()));
                }
                else if ("overlap".equals(event.getPropertyName()))
                {
                    overlap.setText(String.valueOf((Integer) event.getNewValue()));
                }
            }
        };


    /**
     * Create a new worm plot app with the specified model.
     *
     * @param model model, must not be null
     */
    WormPlotApp(final WormPlotModel model)
    {
        super();

        checkNotNull(model);
        this.model = model;

        sequenceFileName = new JTextField(48);
        sequenceFileName.setText("example.fa");
        // add focus, action listeners to update model?

        length = new JTextField();
        length.setColumns(24);
        length.setText(String.valueOf(WormPlotModel.DEFAULT_LENGTH));

        overlap = new JTextField();
        overlap.setColumns(20);
        overlap.setText(String.valueOf(WormPlotModel.DEFAULT_OVERLAP));

        layoutComponents();
    }


    /**
     * Layout components.
     */
    private void layoutComponents()
    {
        setLayout(new BorderLayout());
        add("Center", createMainPanel());
        add("South", createButtonPanel());
    }

    /**
     * Create and return the main panel.
     *
     * @return the main panel
     */
    private LabelFieldPanel createMainPanel()
    {
        LabelFieldPanel panel = new LabelFieldPanel();
        panel.setBorder(new EmptyBorder(0, 0, 0, 40));
        panel.addField("Sequence file name:", createSequenceFileNamePanel());
        panel.addField("Length:", createLengthPanel());
        panel.addField("Overlap:", createOverlapPanel());
        panel.addFinalSpacing();
        return panel;
    }

    /**
     * Create and return the button panel.
     *
     * @return the button panel
     */
    private JPanel createButtonPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createHorizontalGlue());
        panel.add(Box.createHorizontalGlue());
        panel.add(Box.createHorizontalGlue());
        // cancel, exit buttons?
        panel.add(new JButton(apply));
        return panel;
    }

    /**
     * Create and return the sequence file name panel.
     *
     * @return the sequence file name panel
     */
    private JPanel createSequenceFileNamePanel()
    {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(sequenceFileName);
        panel.add(Box.createHorizontalStrut(4));
        panel.add(new JButton(new AbstractAction("...")
            {
                @Override
                public void actionPerformed(final ActionEvent event)
                {
                    JFileChooser fileChooser = new JFileChooser();
                    if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(WormPlotApp.this))
                    {
                        sequenceFileName.setText(fileChooser.getSelectedFile().getName());
                    }
                }
            }));
        return panel;
    }

    /**
     * Create and return the length panel.
     *
     * @return the length panel
     */
    private JPanel createLengthPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(length);
        panel.add(Box.createHorizontalStrut(12));
        panel.add(new JLabel("base pairs (bp)"));
        panel.add(Box.createHorizontalStrut(60));
        return panel;
        
    }

    /**
     * Create and return the overlap panel.
     *
     * @return the overlap panel
     */
    private JPanel createOverlapPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(overlap);
        panel.add(Box.createHorizontalStrut(12));
        panel.add(new JLabel("base pairs (bp)"));
        panel.add(Box.createHorizontalStrut(80));
        return panel;
        
    }

    /**
     * Apply (or run, or go, etc.).
     */
    private void apply()
    {
        // empty
    }
}
