/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2006-2012 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2012 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.web.outage.filter;

import java.util.Date;

import org.opennms.web.filter.GreaterThanFilter;
import org.opennms.web.filter.SQLType;

/**
 * <p>RegainedServiceDateAfterFilter class.</p>
 *
 * @author ranger
 * @version $Id: $
 * @since 1.8.1
 */
public class RegainedServiceDateAfterFilter extends GreaterThanFilter<Date> {
    /** Constant <code>TYPE="regainedafter"</code> */
    public static final String TYPE = "regainedafter";

    /**
     * <p>Constructor for RegainedServiceDateAfterFilter.</p>
     *
     * @param date a {@link java.util.Date} object.
     */
    public RegainedServiceDateAfterFilter(Date date) {
        super(TYPE, SQLType.DATE, "OUTAGES.IFREGAINEDSERVICE", "ifRegainedService", date);
    }

    /**
     * <p>Constructor for RegainedServiceDateAfterFilter.</p>
     *
     * @param epochTime a long.
     */
    public RegainedServiceDateAfterFilter(long epochTime) {
        this(new Date(epochTime));
    }

    /**
     * <p>getTextDescription</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getTextDescription() {
        return ("regained service date after \"" + getValue() + "\"");
    }

    /**
     * <p>toString</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String toString() {
        return ("<Regained Service Date After Filter: " + this.getDescription() + ">");
    }

    /**
     * <p>getDate</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getDate() {
        return getValue();
    }

    /** {@inheritDoc} */
    public boolean equals(Object obj) {
        return (this.toString().equals(obj.toString()));
    }
}
