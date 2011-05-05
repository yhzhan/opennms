/*
 * This file is part of the OpenNMS(R) Application.
 *
 * OpenNMS(R) is Copyright (C) 2011 The OpenNMS Group, Inc.  All rights reserved.
 * OpenNMS(R) is a derivative work, containing both original code, included code and modified
 * code that was published under the GNU General Public License. Copyrights for modified
 * and included code are below.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * Modifications:
 * 
 * Created: March 24, 2011
 *
 * Copyright (C) 2006-2007 The OpenNMS Group, Inc.  All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 *
 * For more information contact:
 *      OpenNMS Licensing       <license@opennms.org>
 *      http://www.opennms.org/
 *      http://www.opennms.com/
 */
package org.opennms.core.utils;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.Comparator;

/**
 * <p>This comparator will sort {@link InetAddress} instances in the following order:</p>
 * 
 * <ul>
 * <li><code>Inet4Address</code> instances</li>
 * <li><code>Inet6Address</code> instances that are routable with scopeId == 0</li>
 * <li><code>Inet6Address</code> instances that are link-local ordered by scopeId</li>
 * </ul>
 */
public class InetAddressComparator implements Comparator<InetAddress> {

    public int compare(InetAddress addr1, InetAddress addr2) {
        if (addr1 == null) {
            if (addr2 == null) {
                return 0;
            } else {
                return -1;
            }
        } else {
            if (addr2 == null) {
                return 1;
            } else {
                if (addr1 instanceof Inet4Address) {
                    if (addr2 instanceof Inet4Address) {
                        // Two Inet4Address instances
                        return new ByteArrayComparator().compare(addr1.getAddress(), addr2.getAddress());
                    } else {
                        return -1;
                    }
                } else {
                    if (addr2 instanceof Inet4Address) {
                        return 1;
                    } else {
                        // Two Inet6Address instances
                        int scopeComparison = new Integer(((Inet6Address)addr1).getScopeId()).compareTo(((Inet6Address)addr2).getScopeId());
                        if (scopeComparison == 0) {
                            // If the scope IDs are identical, then compare the addresses
                            return new ByteArrayComparator().compare(addr1.getAddress(), addr2.getAddress());
                        } else {
                           return scopeComparison;
                        }
                    }
                }
            }
        }
    }
}