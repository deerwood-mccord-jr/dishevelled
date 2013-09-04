/*

    dsh-variation  Variation.
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
package org.dishevelled.variation.vcf;

import java.io.IOException;

import java.util.Map;

public interface VcfParseListener
{
    void lineNumber(long lineNumber) throws IOException;
    void chrom(String chrom) throws IOException;
    void pos(int pos) throws IOException;
    void id(String... id) throws IOException;
    void ref(String ref) throws IOException;
    void alt(String... alt) throws IOException;
    void qual(double qual) throws IOException;
    void filter(String filter) throws IOException;
    void info(Map<String, String> info) throws IOException;
    void gt(String sample, String gt) throws IOException;
    void complete() throws IOException;
}