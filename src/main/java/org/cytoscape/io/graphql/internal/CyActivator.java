package org.cytoscape.io.graphql.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.io.write.CyNetworkViewWriterFactory;
import org.cytoscape.property.CyProperty;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.task.create.NewNetworkSelectedNodesAndEdgesTaskFactory;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.work.swing.DialogTaskManager;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context) throws Exception {

		// OSGi service listener to import dependency dynamically
		final ViewWriterFactoryManager viewWriterManager = new ViewWriterFactoryManager();
		registerServiceListener(context, viewWriterManager, "addFactory", "removeFactory",
				CyNetworkViewWriterFactory.class);
		
		VisualMappingManager vmm = getService(context, VisualMappingManager.class);
		DialogTaskManager taskManager = getService(context, DialogTaskManager.class);
		NewNetworkSelectedNodesAndEdgesTaskFactory nFactory = getService(context, NewNetworkSelectedNodesAndEdgesTaskFactory.class);
		NewNetworkSelectedNodesOnlyTaskFactory networkFactory = getService(context, NewNetworkSelectedNodesOnlyTaskFactory.class);
		CyApplicationManager cyApplicationManagerService = getService(context, CyApplicationManager.class);
		@SuppressWarnings("unchecked")
		final CyProperty<Properties> props = getService(context, CyProperty.class,
				"(cyPropertyName=cytoscape3.props)");
		
		final CySwingApplication swingApplication = getService(context, CySwingApplication.class);
	}


}
