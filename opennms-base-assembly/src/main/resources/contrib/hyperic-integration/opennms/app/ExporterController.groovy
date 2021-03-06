/*
 * This file is part of the OpenNMS(R) Application.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * Modifications:
 *
 * Copyright (C) 2008 The OpenNMS Group, Inc.  All rights reserved.
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
 
/*
 * This script has the following bindings:
 *    org.hyperic.hq.events.AlertDefinitionInterface alertDef
 *    org.hyperic.hq.events.AlertInterface alert
 *    org.hyperic.hq.events.ActionExecutionInfo action
 *    org.hyperic.hq.authz.server.session.Resource resource
 *
 */
import org.hyperic.hq.hqu.rendit.BaseController

import java.text.SimpleDateFormat
import org.hyperic.hibernate.PageInfo
import org.hyperic.hq.authz.server.session.ResourceSortField
import org.hyperic.hq.appdef.server.session.PlatformManagerEJBImpl as PlatformManager

/**
 * @deprecated THIS CONTROLLER IS DEPRECATED. It has been replaced by 
 * ModelexportController.groovy which offers more precise service and
 * interface lists. If you are using OpenNMS 1.7.10+, configure it to
 * point to the /hqu/opennms/modelExport/list.hqu URL.
 */
class ExporterController 
    extends BaseController
{
    def ExporterController() {
        setXMLMethods(['list'])
    }

    def list(xml, params) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        def platforms = resourceHelper.findPlatforms(new PageInfo(ResourceSortField.NAME, true));
        def man = PlatformManager.one
        xml.'model-import'('foreign-source':'HQ', 'date-stamp':formatter.format(new Date())) {
            platforms.each { res ->   
                def p = man.findPlatformById(res.instanceId)
                node('node-label':p.fqdn, 'foreign-id':p.id) {
                    'interface'('ip-addr': p.agent.address, descr: 'agent-address', status: 1, 'snmp-primary': 'N') {
                        'monitored-service'('service-name': 'ICMP')
                        'monitored-service'('service-name': 'HypericAgent')
                    }
                }
            }
        }

        xml
    }
}
