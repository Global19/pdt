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
package org.eclipse.php.composer.api.repositories;

public class SubversionRepository extends VcsRepository implements Cloneable {

	public SubversionRepository() {
		super("svn"); //$NON-NLS-1$
	}

	public String getTrunkPath() {
		return getAsString("trunk-path"); //$NON-NLS-1$
	}

	public void setTrunkPath(String path) {
		set("trunk-path", path); //$NON-NLS-1$
	}

	public String getBranchesPath() {
		return getAsString("branches-path"); //$NON-NLS-1$
	}

	public void setBranchesPath(String path) {
		set("branches-path", path); //$NON-NLS-1$
	}

	public String getTagsPath() {
		return getAsString("tags-path"); //$NON-NLS-1$
	}

	public void setTagsPath(String path) {
		set("tags-path", path); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public SubversionRepository clone() {
		SubversionRepository clone = new SubversionRepository();
		cloneProperties(clone);
		return clone;
	}
}
