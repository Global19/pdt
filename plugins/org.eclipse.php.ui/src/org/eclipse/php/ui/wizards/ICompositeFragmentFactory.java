/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Zend Technologies
 *******************************************************************************/
package org.eclipse.php.ui.wizards;

import org.eclipse.php.internal.ui.wizards.CompositeFragment;
import org.eclipse.php.internal.ui.wizards.IControlHandler;
import org.eclipse.php.internal.ui.wizards.WizardFragment;
import org.eclipse.swt.widgets.Composite;

/**
 * A factory for the creation of WizardFragment or Composites.
 */
public interface ICompositeFragmentFactory {

	/**
	 * Creates a WizardFragment used for any Wizard control that is related to the
	 * PHP servers infrastructure.
	 * 
	 * @param serverContainer
	 * @return
	 */
	public WizardFragment createWizardFragment();

	/**
	 * Creates a CompositeFragment used for any composite that
	 * 
	 * @param parent
	 * @param controlHandler
	 * @return
	 */
	public CompositeFragment createComposite(Composite parent, IControlHandler controlHandler);

}
