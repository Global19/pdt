/*******************************************************************************
 * Copyright (c) 2006 Zend Corporation and IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Zend and IBM - Initial implementation
 *******************************************************************************/
package org.eclipse.php.internal.core.project.properties.handlers;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.php.internal.core.preferences.CorePreferencesSupport;
import org.eclipse.php.internal.core.preferences.CorePreferenceConstants.Keys;

public class UseAspTagsHandler {
	
	private UseAspTagsHandler() {}
	
	public static final boolean useAspTagsAsPhp(IProject project) {
		return project == null ? false : Boolean.valueOf(CorePreferencesSupport.getInstance().getPreferencesValue(Keys.EDITOR_USE_ASP_TAGS, null, project)).booleanValue();
	}

	public static final boolean useAspTagsAsPhp(IFile file) {
		return useAspTagsAsPhp(file.getProject());
	}
    
    public static final boolean setUseAspTagsAsPhp(boolean value, IProject project) {
        return CorePreferencesSupport.getInstance().setProjectSpecificPreferencesValue(Keys.EDITOR_USE_ASP_TAGS, Boolean.toString(value), project);
    }
}