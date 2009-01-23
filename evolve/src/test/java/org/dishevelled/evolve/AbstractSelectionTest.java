/*

    dsh-evolve  Framework for evolutionary algorithms.
    Copyright (c) 2005-2009 held jointly by the individual authors.

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
package org.dishevelled.evolve;

import java.util.Collection;
import java.util.Collections;

import junit.framework.TestCase;

import org.dishevelled.weighted.WeightedMap;
import org.dishevelled.weighted.HashWeightedMap;

/**
 * Abstract unit test for implementations of Selection.
 *
 * @author  Michael Heuer
 * @version $Revision$ $Date$
 */
public abstract class AbstractSelectionTest
    extends TestCase
{

    /**
     * Create and return a new instance of Selection to test.
     *
     * @return a new instance of Selection to test
     */
    protected abstract <T> Selection<T> createSelection();

    public void testCreateSelection()
    {
        Selection<String> selection = createSelection();
        assertNotNull(selection);
    }

    public void testZeroTotalWeightScores()
    {
        Selection<String> selection = createSelection();
        Collection<String> population = Collections.singleton("foo");
        WeightedMap<String> scores = new HashWeightedMap<String>();
        scores.put("foo", 0.0d);
        try
        {
            Collection<String> selected = selection.select(population, scores);
            fail("scores with zero total weighted expected IllegalStateException");
        }
        catch (IllegalStateException e)
        {
            // expected
        }
    }
}
