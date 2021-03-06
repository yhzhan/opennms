/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2012 The OpenNMS Group, Inc.
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

package org.opennms.web.rest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opennms.core.test.MockLogAppender;
import org.opennms.netmgt.dao.DatabasePopulator;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class EventRestServiceTest extends AbstractSpringJerseyRestTestCase {
    private DatabasePopulator m_databasePopulator;

    @Override
    protected void afterServletStart() {
        MockLogAppender.setupLogging(true, "DEBUG");
        final WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        m_databasePopulator = context.getBean("databasePopulator", DatabasePopulator.class);
        m_databasePopulator.populateDatabase();
    }

    @Test
    public void testBetween() throws Exception {
        String xml;
        xml = sendRequest(GET, "/events/between", parseParamData("begin=2010-01-01T00:00:00Z"), 200);
        assertTrue(xml.contains("<createTime>"));
        xml = sendRequest(GET, "/events/between", parseParamData("begin=2010-01-01T00:00:00Z&end=2010-01-01T01:00:00Z"), 200);
        assertTrue(xml.contains("totalCount=\"0\""));
        xml = sendRequest(GET, "/events/between", parseParamData("end=2010-01-01T01:00:00Z"), 200);
        assertTrue(xml.contains("totalCount=\"0\""));
    }
}
