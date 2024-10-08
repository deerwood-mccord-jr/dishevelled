/*

    dsh-variation  Variation.
    Copyright (c) 2013-2015 held jointly by the individual authors.

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
package org.dishevelled.variation;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Objects;

/**
 * Feature.
 *
 * @author  Michael Heuer
 */
@Immutable
public final class Feature
{
    /** Species, e.g. <code>"human"</code>. */
    private final String species;

    /** Reference, e.g. <code>"GRCh38"</code>. */
    private final String reference;

    /** Identifer, e.g. <code>"ENSG00000107404"</code>. */
    private final String identifier;

    /** Region or contig, using Ensembl-style names, e.g. <code>"1"</code>. */
    private final String region;

    /** Feature start, using interbase, zero-start (a.k.a. zero-based, closed-open) coordinate system. */
    private final long start;

    /** Feature end, using interbase, zero-start (a.k.a. zero-based, closed-open) coordinate system. */
    private final long end;

    /** Strand, <code>1</code> or <code>-1</code>. */
    private final int strand;

    /** Cached hash code. */
    private final int hashCode;


    /**
     * Create a new feature.
     *
     * @param species species, must not be null
     * @param reference reference, must not be null
     * @param identifier identifier, must not be null
     * @param region region, must not be null
     * @param start start, using interbase, zero-start coordinate system
     * @param end end, using interbase, zero-start coordinate system
     * @param strand strand
     */
    public Feature(final String species,
                   final String reference,
                   final String identifier,
                   final String region,
                   final long start,
                   final long end,
                   final int strand)
    {
        checkNotNull(species);
        checkNotNull(reference);
        checkNotNull(identifier);
        checkNotNull(region);

        this.species = species;
        this.reference = reference;
        this.identifier = identifier;
        this.region = region;
        this.start = start;
        this.end = end;
        this.strand = strand;

        hashCode = Objects.hashCode(this.species, this.reference, this.identifier, this.region, this.start, this.end, this.strand);
    }


    /**
     * Return the species for this feature.
     *
     * @return the species for this feature
     */
    public String getSpecies()
    {
        return species;
    }

    /**
     * Return the reference for this feature.
     *
     * @return the reference for this feature
     */
    public String getReference()
    {
        return reference;
    }

    /**
     * Return the identifier for this feature.
     *
     * @return the identifier for this feature
     */
    public String getIdentifier()
    {
        return identifier;
    }

    /**
     * Return the region for this feature.
     *
     * @return the region for this feature
     */
    public String getRegion()
    {
        return region;
    }

    /**
     * Return the start for this feature.
     *
     * @return the start for this feature
     */
    public long getStart()
    {
        return start;
    }

    /**
     * Return the end for this feature.
     *
     * @return the end for this feature
     */
    public long getEnd()
    {
        return end;
    }

    /**
     * Return the strand for this feature.
     *
     * @return the strand for this feature
     */
    public int getStrand()
    {
        return strand;
    }

    @Override
    public int hashCode()
    {
        return hashCode;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Feature))
        {
            return false;
        }
        Feature feature = (Feature) o;

        return Objects.equal(species, feature.species)
            && Objects.equal(reference, feature.reference)
            && Objects.equal(identifier, feature.identifier)
            && Objects.equal(region, feature.region)
            && Objects.equal(start, feature.start)
            && Objects.equal(end, feature.end)
            && Objects.equal(strand, feature.strand);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(region);
        sb.append(":");
        sb.append(start);
        sb.append("-");
        sb.append(end);
        sb.append(":");
        sb.append(strand);
        return sb.toString();
    }
}
