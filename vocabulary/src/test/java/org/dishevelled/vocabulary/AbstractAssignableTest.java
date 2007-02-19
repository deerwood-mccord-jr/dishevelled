/*

    dsh-vocabulary  A description of concepts and their relations in a domain.
    Copyright (c) 2002-2007 held jointly by the individual authors.

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
package org.dishevelled.vocabulary;

import junit.framework.TestCase;

/**
 * Abstract unit test for the Assignable interface.
 *
 * @author  Michael Heuer
 * @version $Revision: 1.2.2.1 $ $Date: 2004/02/05 23:56:06 $
 */
public abstract class AbstractAssignableTest
    extends TestCase
{

    /**
     * Create and return a new instance of an implementation of Assignable to test.
     *
     * @return a new instance of an implementation of Assignable
     */
    protected abstract Assignable createAssignable();
}
