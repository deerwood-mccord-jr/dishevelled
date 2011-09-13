/*

    dsh-timer  Timer with nanosecond resolution and summary statistics
    on recorded elapsed times.
    Copyright (c) 2004-2011 held jointly by the individual authors.

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
package org.dishevelled.timer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import java.util.Map;

/**
 * Timer report.
 */
public interface TimerReport
{

    /**
     * Append the specified map of timers keyed by runnables to the specified appendable.
     *
     * @param <T> extends Appendable
     * @param timers map of timers keyed by runnables to append, must not be null
     * @param appendable appendable to append the specified map of timers keyed by runnables to, must not be null
     * @return the specified appendable with the specified map of timers keyed by runnables appended
     * @throws IOException if an IO error occurs
     */
    <T extends Appendable> T append(Map<? extends Runnable, Timer> timers, T appendable) throws IOException;

    /**
     * Write the specified map of timers keyed by runnables to the specified file.
     *
     * @param timers map of timers keyed by runnables to append, must not be null
     * @param file file to write to, must not be null
     * @throws IOException if an IO error occurs
     */
    void write(Map<? extends Runnable, Timer> timers, File file) throws IOException;

    /**
     * Write the specified map of timers keyed by runnables to the specified output stream.
     *
     * @param timers map of timers keyed by runnables to append, must not be null
     * @param outputStream output stream to write to, must not be null
     * @throws IOException if an IO error occurs
     */
    void write(Map<? extends Runnable, Timer> timers, OutputStream outputStream) throws IOException;
}