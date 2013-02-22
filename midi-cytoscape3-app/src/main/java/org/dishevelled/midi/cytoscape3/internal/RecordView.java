/*

    dsh-midi-cytoscape3-app  Cytoscape3 app for MIDI networks.
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
package org.dishevelled.midi.cytoscape3.internal;

import static org.dishevelled.midi.cytoscape3.internal.MidiNetworksUtils.nameOf;
import static org.dishevelled.midi.cytoscape3.internal.MidiNetworksUtils.typeOf;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;

import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;

import org.dishevelled.iconbundle.tango.TangoProject;

import org.dishevelled.identify.IdentifiableAction;
//import org.dishevelled.identify.IdToggleButton;

import org.dishevelled.layout.ButtonPanel;

import rwmidi.Controller;
import rwmidi.MidiInput;
import rwmidi.MidiInputDevice;
import rwmidi.Note;
import rwmidi.ProgramChange;
import rwmidi.SysexMessage;

/**
 * Record view.
 *
 * @author  Michael Heuer
 */
public final class RecordView extends JPanel // needs to be public for reflection to work
{
    /** Network. */
    private final CyNetwork network;

    /** Id. */
    private final AtomicInteger id = new AtomicInteger();

    /** Last timestamp. */
    private long last;

    /** Last node. */
    private CyNode lastNode;

    /** True if recording. */
    private boolean recording = false;

    /** Record action. */
    private final IdentifiableAction record = new IdentifiableAction("Record", TangoProject.MEDIA_RECORD)
        {
            @Override
            public void actionPerformed(final ActionEvent event)
            {
                start();
            }
        };

    /** Record button. */
    private final JToggleButton recordButton = new JToggleButton(record);


    /**
     * Create a new record view with the specified MIDI input device.
     *
     * @param inputDevice MIDI input device, must not be null
     * @param network current network, must not be null
     */
    RecordView(final MidiInputDevice inputDevice, final CyNetwork network)
    {
        super();
        if (inputDevice == null)
        {
            throw new IllegalArgumentException("inputDevice must not be null");
        }
        if (network == null)
        {
            throw new IllegalArgumentException("network must not be null");
        }
        MidiInput input = inputDevice.createInput(this);
        input.plug(this, 1); // pass channel in?
        this.network = network;

        layoutComponents();
    }


    /**
     * Layout components.
     */
    private void layoutComponents()
    {
        ButtonPanel buttonPanel = new ButtonPanel();
        buttonPanel.add(recordButton);

        setLayout(new BorderLayout());
        add("South", buttonPanel);
    }

    /**
     * Start recording.
     */
    public void start()
    {
        recording = true;
        System.out.println("start " + recording);
    }

    /**
     * Stop recording.
     */
    public void stop()
    {
        recording = false;
        System.out.println("stop " + recording);
    }

    /**
     * Toggle recording.
     */
    public void toggle()
    {
        recording = !recording;
        System.out.println("toggle " + recording);
    }

    /**
     * Note on callback.
     *
     * @param note note
     */
    public void noteOnReceived(final Note note)
    {
        System.out.println("note on " + note.getPitch() + " " + note.getVelocity() + " " + recording);
        // use nanopad button 12 as toggle
        if (note.getPitch() == 46)
        {
            toggle();
            recordButton.setSelected(recording);
        }
        if (recording)
        {
            CyNode currentNode = createNoteOn(note.getPitch(), note.getVelocity());
            long current = System.currentTimeMillis();
            if (((current - last) > 0) && (lastNode != null))
            {
                createWait(lastNode, currentNode, (current - last), 1.0d);
            }
            last = current;
            lastNode = currentNode;
        }
    }

    /**
     * Note off callback.
     *
     * @param note note
     */
    public void noteOffReceived(final Note note)
    {
        System.out.println("note off " + note.getPitch() + " " + note.getVelocity() + " " + recording);
        if (recording)
        {
            CyNode currentNode = createNoteOff(note.getPitch(), note.getVelocity());
            long current = System.currentTimeMillis();
            if (((current - last) > 0) && (lastNode != null))
            {
                createWait(lastNode, currentNode, (current - last), 1.0d);
            }
            last = current;
            lastNode = currentNode;
        }
    }

    /**
     * Controller change callback.
     *
     * @param controller controller
     */
    public void controllerChangeReceived(final Controller controller)
    {
        // empty
    }

    /**
     * Program change callback.
     *
     * @param programChange program change
     */
    public void programChangeReceived(final ProgramChange programChange)
    {
        // empty
    }

    /**
     * Sysex message callback.
     *
     * @param sysexMessage sysex message
     */
    public void sysexReceived(final SysexMessage sysexMessage)
    {
        // empty
    }

    /**
     * Create and return a new note on node.
     *
     * @param note note
     * @param velocity velocity
     * @return the new note on node
     */
    private CyNode createNoteOn(final int note, final int velocity)
    {
        return createNode("noteOn", note, velocity);
    }

    /**
     * Create and return a new note off node.
     *
     * @param note note
     * @param velocity velocity
     * @return the new note off node
     */
    private CyNode createNoteOff(final int note, final int velocity)
    {
        return createNode("noteOff", note, velocity);
    }

    /**
     * Create and return a new node.
     *
     * @param type type
     * @param note note
     * @param velocity velocity
     * @return the new node
     */
    private CyNode createNode(final String type, final int note, final int velocity)
    {
        CyNode node = network.addNode();
        CyTable table = network.getDefaultNodeTable();
        CyRow row = table.getRow(node.getSUID());

        if (table.getColumn(CyNetwork.NAME) == null)
        {
            table.createColumn(CyNetwork.NAME, String.class, false);
        }
        if (table.getColumn("type") == null)
        {
            table.createColumn("type", String.class, false);
        }
        if (table.getColumn("note") == null)
        {
            table.createColumn("note", Integer.class, false);
        }
        if (table.getColumn("velocity") == null)
        {
            table.createColumn("velocity", Integer.class, false);
        }

        row.set(CyNetwork.NAME, type + id.getAndIncrement());
        row.set("type", type);
        row.set("note", note);
        row.set("velocity", velocity);

        return node;
    }

    /**
     * Create a new wait edge.
     *
     * @param source source node
     * @param target target node
     * @param duration duration
     * @param weight weight
     * @return the new wait edge
     */
    private CyEdge createWait(final CyNode source, final CyNode target, final long duration, final double weight)
    {
        CyEdge edge = network.addEdge(source, target, true);
        CyTable table = network.getDefaultEdgeTable();
        CyRow row = table.getRow(edge.getSUID());

        if (table.getColumn(CyNetwork.NAME) == null)
        {
            table.createColumn(CyNetwork.NAME, String.class, false);
        }
        if (table.getColumn("type") == null)
        {
            table.createColumn("type", String.class, false, "wait");
        }
        if (table.getColumn("duration") == null)
        {
            table.createColumn("duration", Long.class, false);
        }
        if (table.getColumn("weight") == null)
        {
            table.createColumn("weight", Double.class, false);
        }

        String sourceName = nameOf(source, network);
        String targetName = nameOf(target, network);
        String sourceType = typeOf(source, network);
        String targetType = typeOf(target, network);

        row.set(CyNetwork.NAME, sourceName + " --> " + targetName);
        if ("noteOn".equals(sourceType) && "noteOff".equals(targetType))
        {
            row.set("type", "note");
        }
        else if ("noteOff".equals(sourceType) && "noteOn".equals(targetType))
        {
            row.set("type", "rest");
        }
        row.set("duration", duration);
        row.set("weight", weight);

        return edge;
    }
}