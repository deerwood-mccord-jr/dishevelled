/*

    dsh-color-scheme-view  Views for color schemes.
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
package org.dishevelled.color.scheme.view;

import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JPopupMenu.Separator;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import javax.swing.border.EmptyBorder;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;

import org.dishevelled.color.scheme.impl.ColorSchemes;
import org.dishevelled.color.scheme.impl.DiscreteColorScheme;

import org.dishevelled.layout.LabelFieldPanel;

/**
 * Temporary example.
 *
 * @author  Michael Heuer
 */
public final class Example extends LabelFieldPanel implements Runnable
{
    public Example()
    {
        super();
        layoutComponents();
    }

    private void layoutComponents()
    {
        setBorder(new EmptyBorder(20, 20, 20, 20));

        List<DiscreteColorScheme> colorSchemes = new ArrayList<>();
        colorSchemes.add((DiscreteColorScheme) ColorSchemes.getDiscreteColorScheme("aaas", 4));
        colorSchemes.add((DiscreteColorScheme) ColorSchemes.getDiscreteColorScheme("category", 4));
        colorSchemes.add((DiscreteColorScheme) ColorSchemes.getDiscreteColorScheme("jco", 4));
        colorSchemes.add((DiscreteColorScheme) ColorSchemes.getDiscreteColorScheme("npg", 4));
        colorSchemes.add((DiscreteColorScheme) ColorSchemes.getDiscreteColorScheme("tableau", 4));

        addFinalField(new DiscreteColorSchemeList(GlazedLists.eventList(colorSchemes)));            
    }

    @Override
    public void run()
    {
        JFrame frame = new JFrame("Example");
        frame.setContentPane(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(666, 500);
        frame.setVisible(true);
    }

    public static void main(final String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            // ignore
        }

        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.macos.useScreenMenuBar", "true");
        System.setProperty("com.apple.macos.use-file-dialog-packages", "true");
        System.setProperty("apple.awt.application.name", "Example");

        SwingUtilities.invokeLater(new Example());
    }
}
