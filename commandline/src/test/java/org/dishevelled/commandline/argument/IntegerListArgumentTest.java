/*

    dsh-commandline  Command line parser based on typed arguments.
    Copyright (c) 2004-2008 held jointly by the individual authors.

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
package org.dishevelled.commandline.argument;

import java.util.List;
import java.util.Arrays;

import junit.framework.TestCase;

import org.dishevelled.commandline.Argument;
import org.dishevelled.commandline.ArgumentList;
import org.dishevelled.commandline.CommandLine;
import org.dishevelled.commandline.CommandLineParser;
import org.dishevelled.commandline.CommandLineParseException;

/**
 * Unit test for IntegerListArgument.
 *
 * @author  Michael Heuer
 * @version $Revision: 1.2 $ $Date: 2006/01/02 23:16:28 $
 */
public class IntegerListArgumentTest
    extends TestCase
{

    public void testIntegerListArgument()
    {
        IntegerListArgument ila = new IntegerListArgument("i", "integer-list", "Integer list argument", true);
        assertNotNull("ila not null", ila);
        assertEquals("ila shortName == i", "i", ila.getShortName());
        assertEquals("ila longName == integer-list", "integer-list", ila.getLongName());
        assertEquals("ila description == Integer list argument", "Integer list argument", ila.getDescription());
        assertTrue("ila isRequired", ila.isRequired());
        assertFalse("ila wasFound == false", ila.wasFound());
        assertEquals("ila value == null", null, ila.getValue());
    }

    public void testValidArgumentShort()
        throws CommandLineParseException
    {
        Argument<List<Integer>> integerListArgument = new IntegerListArgument("i", "integer-list", "Integer list argument", true);
        ArgumentList arguments = new ArgumentList(Arrays.asList(new Argument[] { integerListArgument }));
        List<String> values = Arrays.asList(new String[] { "1", "-1", "1, -1", "1,-1", " 1, -1 " });

        for (String value : values)
        {
            String[] args = new String[] { "-i", value };
            CommandLine commandLine = new CommandLine(args);
            CommandLineParser.parse(commandLine, arguments);

            List<Integer> list = integerListArgument.getValue();
            assertNotNull("-i list not null", list);
            assertFalse("-i list not empty", list.isEmpty());
        }
    }

    public void testValidArgumentLong()
        throws CommandLineParseException
    {
        Argument<List<Integer>> integerListArgument = new IntegerListArgument("i", "integer-list", "Integer list argument", true);
        ArgumentList arguments = new ArgumentList(Arrays.asList(new Argument[] { integerListArgument }));
        List<String> values = Arrays.asList(new String[] { "1", "-1", "1, -1", "1,-1", " 1, -1 " });

        for (String value : values)
        {
            String[] args = new String[] { "--integer-list", value };
            CommandLine commandLine = new CommandLine(args);
            CommandLineParser.parse(commandLine, arguments);

            List<Integer> list = integerListArgument.getValue();
            assertNotNull("--integer-list list not null", list);
            assertFalse("--integer-list list not empty", list.isEmpty());
        }
    }

    public void testInvalidArgumentLong()
    {
        Argument<List<Integer>> integerListArgument = new IntegerListArgument("i", "integer-list", "Integer list argument", true);
        ArgumentList arguments = new ArgumentList(Arrays.asList(new Argument[] { integerListArgument }));
        List<String> values = Arrays.asList(new String[] { "not-a-integer" });

        for (String value : values)
        {
            try
            {
                String[] args = new String[] { "--integer-list", value };
                CommandLine commandLine = new CommandLine(args);
                CommandLineParser.parse(commandLine, arguments);

                fail("--integer-list " + value + " expected CommandLineParseException");
            }
            catch (CommandLineParseException e)
            {
                // expected
            }
        }
    }

    public void testInvalidArgumentShort()
    {
        Argument<List<Integer>> integerListArgument = new IntegerListArgument("i", "integer-list", "Integer list argument", true);
        ArgumentList arguments = new ArgumentList(Arrays.asList(new Argument[] { integerListArgument }));
        List<String> values = Arrays.asList(new String[] { "not-a-integer" });

        for (String value : values)
        {
            try
            {
                String[] args = new String[] { "-i", value };
                CommandLine commandLine = new CommandLine(args);
                CommandLineParser.parse(commandLine, arguments);

                fail("-i " + value + " expected CommandLineParseException");
            }
            catch (CommandLineParseException e)
            {
                // expected
            }
        }
    }

    public void testRequiredArgument()
    {
        try
        {
            Argument<List<Integer>> integerListArgument = new IntegerListArgument("i", "integer-list", "Integer list argument", true);
            ArgumentList arguments = new ArgumentList(Arrays.asList(new Argument[] { integerListArgument }));

            String[] args = new String[] { "not-an-argument", "not-a-integer" };
            CommandLine commandLine = new CommandLine(args);
            CommandLineParser.parse(commandLine, arguments);

            fail("not-an-argument expected CommandLineParseException");
        }
        catch (CommandLineParseException e)
        {
            // expected
        }
    }

    public void testNotRequiredArgument()
        throws CommandLineParseException
    {
        Argument<List<Integer>> integerListArgument = new IntegerListArgument("i", "integer-list", "Integer list argument", false);
        ArgumentList arguments = new ArgumentList(Arrays.asList(new Argument[] { integerListArgument }));

        String[] args = new String[] { "not-an-argument", "not-a-integer" };
        CommandLine commandLine = new CommandLine(args);
        CommandLineParser.parse(commandLine, arguments);

        assertFalse("integerListArgument isRequired == false", integerListArgument.isRequired());
        assertFalse("integerListArgument wasFound == false", integerListArgument.wasFound());
        assertEquals("integerListArgument value == null", null, integerListArgument.getValue());
    }
}
