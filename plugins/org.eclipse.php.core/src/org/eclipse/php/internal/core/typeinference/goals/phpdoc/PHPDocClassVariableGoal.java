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
package org.eclipse.php.internal.core.typeinference.goals.phpdoc;

import org.eclipse.dltk.ti.goals.AbstractTypeGoal;
import org.eclipse.php.internal.core.typeinference.context.TypeContext;

public class PHPDocClassVariableGoal extends AbstractTypeGoal {

	private String variableName;

	private int offset;

	public PHPDocClassVariableGoal(TypeContext context, String variableName, int offset) {
		super(context);
		this.variableName = variableName;
		this.offset = offset;
	}

	public String getVariableName() {
		return variableName;
	}

	public int getOffset() {
		return offset;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((variableName == null) ? 0 : variableName.hashCode());
		result = prime * result + offset;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PHPDocClassVariableGoal other = (PHPDocClassVariableGoal) obj;
		if (this.offset != other.offset) {
			return false;
		}
		if (variableName == null) {
			if (other.variableName != null) {
				return false;
			}
		} else if (!variableName.equals(other.variableName)) {
			return false;
		}
		return true;
	}
}
