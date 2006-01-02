/*

    dsh-commandline  Simple command line parser based on typed arguments.
    Copyright (c) 2004-2006 held jointly by the individual authors.

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
package org.dishevelled.commandline.argument;

import org.dishevelled.commandline.Argument;
import org.dishevelled.commandline.CommandLine;

/**
 * Abstract implementation of Argument.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public abstract class AbstractArgument<E>
    implements Argument<E>
{
    /** Short name. */
    private final String shortName;

    /** Long name. */
    private final String longName;

    /** Description. */
    private final String description;

    /** Is required. */
    private final boolean required;

    /** Value. */
    private E value;

    /** True if this argument was found. */
    private boolean found;


    /**
     * Create a new abstract argument.
     *
     * @param shortName short argument name
     * @param longName long argument name
     * @param description argument description
     * @param required <code>true</code> if this argument is required
     */
    protected AbstractArgument(final String shortName,
                               final String longName,
                               final String description,
                               final boolean required)
    {
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.required = required;
        this.found = false;
    }

    /** @see Argument */
    public final void visit(final String current, final CommandLine commandLine)
        throws Exception
    {
        if (isArgumentString(current))
        {
            found = true;
            return;
        }

        String ignore;
        if (isValueString(current))
        {
            if (commandLine.hasPrevious())
            {
                ignore = commandLine.previous();

                if (commandLine.hasPrevious())
                {
                    String previous = commandLine.previous();
                    if (isArgumentString(previous))
                    {
                        value = convert(current);
                    }
                    ignore = commandLine.next();
                }
                
                ignore = commandLine.next();
            }
        }
    }


    /**
     * Return true if the specified string is an argument name.
     *
     * @param s string
     * @return true if the specified string is an argument name
     */
    protected boolean isArgumentString(final String s)
    {
        return (("-" + getShortName()).equals(s)) ||
            (("--" + getLongName()).equals(s));
    }

    /**
     * Return true if the specified string is a value.
     *
     * @param s string
     * @return true if the specified string is a value
     */
    protected boolean isValueString(final String s)
    {
        return !isArgumentString(s);
    }

    /**
     * Convert the specified string value into an instance
     * of class <code>E</code>.
     *
     * @param s string value
     * @return an instance of class <code>E</code> converted
     *    from the specified string value
     * @throws Exception if an error occurs
     */
    protected abstract E convert(final String s)
        throws Exception;


    /** @see Argument */
    public final String getShortName()
    {
        return shortName;
    }

    /** @see Argument */
    public final String getLongName()
    {
        return longName;
    }

    /** @see Argument */
    public final String getDescription()
    {
        return description;
    }

    /** @see Argument */
    public final boolean isRequired()
    {
        return required;
    }

    /** @see Argument */
    public final boolean wasFound()
    {
        return found;
    }

    /** @see Argument */
    public final E getValue()
    {
        return value;
    }


    /** @see Object */
    public final boolean equals(final Object o)
    {
        return super.equals(o);
    }

    /** @see Object */
    public final int hashCode()
    {
        return super.hashCode();
    }
}
