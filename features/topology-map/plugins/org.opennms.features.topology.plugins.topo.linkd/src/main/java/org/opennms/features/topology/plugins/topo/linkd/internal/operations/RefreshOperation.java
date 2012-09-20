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

package org.opennms.features.topology.plugins.topo.linkd.internal.operations;

import java.util.List;

import org.opennms.features.topology.api.Operation;
import org.opennms.features.topology.api.OperationContext;
import org.opennms.features.topology.api.TopologyProvider;

public class RefreshOperation implements Operation {

    TopologyProvider m_topologyProvider;
    
    public RefreshOperation(TopologyProvider topologyProvider) {
        m_topologyProvider=topologyProvider;
    }

    @Override
    public Undoer execute(List<Object> targets, OperationContext operationContext) {
            log("executing linkd topology refresh operation");
            m_topologyProvider.load(null);
            //if (operationContext != null && operationContext.getGraphContainer() != null) {
                //log("operationcontext and GraphContainer not null: executing redoLayout");
                //operationContext.getGraphContainer().setDataSource(m_topologyProvider);
                //operationContext.getGraphContainer().redoLayout();
            //}
            return null;
    }

    private void log(final String string) {
		System.err.println(getId()+": "+ string);
	}

	@Override
    public boolean display(List<Object> targets, OperationContext operationContext) {
        return true;
    }

    @Override
    public boolean enabled(List<Object> targets, OperationContext operationContext) {
    	return true;
    }

    @Override
    public String getId() {
    	return "LinkdTopologyProviderRefreshOperation";
    }

}