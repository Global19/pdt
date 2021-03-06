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
package org.eclipse.php.composer.core.launch.environment;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.php.composer.core.launch.ScriptNotFoundException;

/**
 * 
 * An abstract environment for a user selected PHP executable but a project
 * specific script to be launched.
 * 
 * Implement getScript to pass your script to the launcher.
 * 
 */
public abstract class PrjPharEnvironment implements Environment {

	protected String phar;

	@Override
	public void setUp(IProject project) throws ScriptNotFoundException {
		IResource phar = getScript(project);
		if (phar == null) {
			throw new ScriptNotFoundException(
					NLS.bind(Messages.PrjPharEnvironment_NoScriptFoundError, project.getName()));
		}

		if (phar.getFullPath().segmentCount() != 2) {
			throw new ScriptNotFoundException(
					NLS.bind(Messages.PrjPharEnvironment_WrongLocationError, project.getName()));
		}

		this.phar = phar.getFullPath().removeFirstSegments(1).toOSString();
	}

	protected abstract IResource getScript(IProject project);

}
