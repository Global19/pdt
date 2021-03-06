/*******************************************************************************
 * Copyright (c) 2012, 2016, 2017 PDT Extension Group and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     PDT Extension Group - initial API and implementation
 *     Kaloyan Raev - [501269] externalize strings
 *******************************************************************************/
package org.eclipse.php.composer.core.model;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.php.composer.api.collection.Psr;
import org.eclipse.php.composer.api.json.ParseException;
import org.eclipse.php.composer.core.ComposerPlugin;
import org.eclipse.php.composer.core.log.Logger;
import org.osgi.service.prefs.BackingStoreException;

/**
 * 
 * Main ModelAccess to the workspaces composer model.
 * 
 * @author Robert Gruendler <r.gruendler@gmail.com>
 *
 */
public class ModelAccess {
	private PackageManager packageManager = null;
	private static ModelAccess instance = null;
	private Map<String, Psr> psr0Map = new HashMap<>();

	private ModelAccess() {
		try {
			initNamespaceMap();
		} catch (Exception e) {
			ComposerPlugin.logException(e);
		}
	}

	protected void initNamespaceMap() throws ParseException {
		IEclipsePreferences instancePreferences = ConfigurationScope.INSTANCE.getNode(ComposerPlugin.ID);
		for (IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			String prefKey = "namespacemap#" + project.getName(); //$NON-NLS-1$
			String json = instancePreferences.get(prefKey, "{}"); //$NON-NLS-1$
			psr0Map.put(project.getName(), new Psr(json));
			Logger.debug("loading namespacemap from preferences for project " + project.getName() + " " + json); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	public static ModelAccess getInstance() {
		if (instance == null) {
			instance = new ModelAccess();
		}

		return instance;
	}

	public PackageManager getPackageManager() {
		if (getInstance().packageManager == null) {
			PackageManager manager = new PackageManager();
			synchronized (instance) {
				if (instance.packageManager == null) { // ensure another
					// package manager
					// was not set while
					// creating the
					// instance above
					instance.packageManager = manager;
				}
			}
		}

		return instance.packageManager;
	}

	public void updatePsr0(Psr psr0, IScriptProject scriptProject) {
		// escape namespace separators to avoid deserialization problems
		String json = psr0.toJson().replace("\\", "\\\\"); //$NON-NLS-1$ //$NON-NLS-2$
		IEclipsePreferences instancePreferences = ConfigurationScope.INSTANCE.getNode(ComposerPlugin.ID);
		psr0Map.put(scriptProject.getProject().getName(), psr0);
		instancePreferences.put("namespacemap#" + scriptProject.getProject().getName(), json); //$NON-NLS-1$
		Logger.debug("updating namespacemap for project " + scriptProject.getProject().getName()); //$NON-NLS-1$
		try {
			instancePreferences.flush();
		} catch (BackingStoreException e) {
			Logger.logException(e);
		}
	}

	public Psr getNamespaceMappings(IProject project) {
		if (psr0Map.containsKey(project.getName())) {
			return psr0Map.get(project.getName());
		}

		return null;
	}

	public IResource getComposer(InstalledPackage installed, IScriptProject project) {
		/*
		 * if (!namespaceMap.containsKey(project.getProject().getName())) {
		 * return null; }
		 * 
		 * for (Namespace mapping :
		 * namespaceMap.get(project.getProject().getName())) {
		 * 
		 * if (mapping.getPath().contains(installed.name)) { IPath path = new
		 * Path(mapping.getPath().substring(0,
		 * mapping.getPath().lastIndexOf(installed.name)+installed.name.length()
		 * )); if (installed.targetDir != null && installed.targetDir.length() >
		 * 0) { path = path.append(installed.targetDir); } return
		 * project.getProject().findMember(path.append(ComposerConstants.
		 * COMPOSER_JSON)); } }
		 */
		return null;

	}
}
