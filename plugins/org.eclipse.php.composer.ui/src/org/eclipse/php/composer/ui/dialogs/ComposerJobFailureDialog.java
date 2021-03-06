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
package org.eclipse.php.composer.ui.dialogs;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

public class ComposerJobFailureDialog extends ErrorDialog {

	public ComposerJobFailureDialog(String message, IStatus status) {
		super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.ComposerJobFailureDialog_Title,
				message, status, IStatus.ERROR | IStatus.OK);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite main = (Composite) super.createDialogArea(parent);

		Composite space = new Composite(main, SWT.NONE);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, false, false);
		gridData.heightHint = 1;
		gridData.widthHint = 1;
		space.setLayoutData(gridData);

		return main;
	}

	@Override
	protected Control createMessageArea(Composite composite) {
		// create composite
		// create image
		Image image = getImage();
		if (image != null) {
			imageLabel = new Label(composite, SWT.NULL);
			image.setBackground(imageLabel.getBackground());
			imageLabel.setImage(image);
			GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.BEGINNING).applyTo(imageLabel);
		}
		// create message
		if (message != null) {
			messageLabel = new Label(composite, getMessageLabelStyle());
			messageLabel.setText(Messages.ComposerJobFailureDialog_Message);
			GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).grab(true, false)
					.hint(convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH), SWT.DEFAULT)
					.applyTo(messageLabel);
		}
		return composite;
	}

}
