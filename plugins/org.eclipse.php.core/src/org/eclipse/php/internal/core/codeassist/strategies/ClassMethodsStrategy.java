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
package org.eclipse.php.internal.core.codeassist.strategies;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.*;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.codeassist.ICompletionContext;
import org.eclipse.php.core.codeassist.ICompletionReporter;
import org.eclipse.php.core.codeassist.IElementFilter;
import org.eclipse.php.internal.core.PHPCorePlugin;
import org.eclipse.php.internal.core.codeassist.ProposalExtraInfo;
import org.eclipse.php.internal.core.codeassist.contexts.AbstractCompletionContext;
import org.eclipse.php.internal.core.codeassist.contexts.IClassMemberContext;
import org.eclipse.php.internal.core.codeassist.contexts.IClassMemberContext.Trigger;
import org.eclipse.php.internal.core.language.PHPMagicMethods;
import org.eclipse.php.internal.core.typeinference.PHPModelUtils;

/**
 * This strategy completes class methods
 * 
 * @author michael
 */
public class ClassMethodsStrategy extends ClassMembersStrategy {

	public ClassMethodsStrategy(ICompletionContext context, IElementFilter elementFilter) {
		super(context, elementFilter);
	}

	public ClassMethodsStrategy(ICompletionContext context) {
		super(context);
	}

	@Override
	public void apply(ICompletionReporter reporter) throws BadLocationException {
		ICompletionContext context = getContext();
		if (!(context instanceof IClassMemberContext) || !(context instanceof AbstractCompletionContext)) {
			return;
		}

		IClassMemberContext concreteContext = (IClassMemberContext) context;
		AbstractCompletionContext abstractContext = (AbstractCompletionContext) context;

		CompletionRequestor requestor = abstractContext.getCompletionRequestor();

		String prefix = abstractContext.getPrefix().isEmpty() ? abstractContext.getPreviousWord()
				: abstractContext.getPrefix();
		boolean isParentCall = isParentCall(concreteContext);
		String suffix = getSuffix(abstractContext);

		ISourceRange replaceRange = null;
		if (suffix.equals("")) { //$NON-NLS-1$
			replaceRange = getReplacementRange(concreteContext);
		} else {
			replaceRange = getReplacementRangeWithBraces(concreteContext);
		}

		PHPVersion phpVersion = getCompanion().getPHPVersion();
		Set<String> magicMethods = new HashSet<>();
		magicMethods.addAll(Arrays.asList(PHPMagicMethods.getMethods(phpVersion)));

		boolean exactName = requestor.isContextInformationMode();
		// for methodName(|),we need set exactName to true
		if (!exactName && getCompanion().getOffset() - 1 >= 0
				&& getCompanion().getDocument().getChar(getCompanion().getOffset() - 1) == '(') {
			exactName = true;
		}
		for (IType type : concreteContext.getLhsTypes()) {
			try {
				ITypeHierarchy hierarchy = getCompanion().getSuperTypeHierarchy(type, null);

				IMethod[] methods = isParentCall
						? PHPModelUtils.getSuperTypeHierarchyMethod(type, hierarchy, prefix, exactName, null)
						: PHPModelUtils.getTypeHierarchyMethod(type, hierarchy, prefix, exactName, null);

				boolean inConstructor = isInConstructor(type, type.getMethods(), concreteContext);
				for (IMethod method : removeOverriddenElements(Arrays.asList(methods))) {
					reportMethod(method, type, concreteContext, inConstructor, magicMethods, suffix, replaceRange,
							reporter);
				}
			} catch (CoreException e) {
				PHPCorePlugin.log(e);
			}
		}

	}

	protected void reportMethod(IMethod method, IType type, IClassMemberContext concreteContext, boolean inConstructor,
			Set<String> magicMethods, String suffix, ISourceRange replaceRange, ICompletionReporter reporter)
			throws ModelException {
		if ((!PHPModelUtils.isConstructor(method) || inConstructor && isSuperConstructor(method, type, concreteContext))
				&& !isFiltered(method, type, concreteContext)) {
			if (magicMethods.contains(method.getElementName())) {
				reporter.reportMethod(method, suffix, replaceRange,
						ProposalExtraInfo.MAGIC_METHOD | ProposalExtraInfo.FULL_NAME);
			} else {
				reporter.reportMethod(method, suffix, replaceRange, ProposalExtraInfo.FULL_NAME);
			}
		}
	}

	private boolean isInConstructor(IType type, IMethod[] methods, IClassMemberContext concreteContext) {
		try {
			for (int i = 0; i < methods.length; i++) {
				IMethod method = methods[i];
				if (PHPModelUtils.isConstructor(method) && method.getDeclaringType().equals(type)) {
					ISourceRange constructorRange = method.getSourceRange();
					if (getCompanion().getOffset() > constructorRange.getOffset() && getCompanion()
							.getOffset() < constructorRange.getOffset() + constructorRange.getLength()) {
						return true;
					}
				}
			}
		} catch (ModelException e) {
		}
		return false;
	}

	private boolean isSuperConstructor(IMethod method, IType type, IClassMemberContext context) {
		if (PHPModelUtils.isConstructor(method) && context.getTriggerType() == Trigger.CLASS && isParent(context)
				&& !method.getDeclaringType().equals(type)) {
			return true;
		}
		return false;
	}

	/**
	 * is 'parent' keyword
	 * 
	 * @param context
	 * @return
	 */
	private boolean isParent(IClassMemberContext context) {
		return !isThisCall(context) && isParentCall(context) && isDirectParentCall(context);
	}

	// private IMethod getConstructor(IType type, IMethod[] methods) {
	// for (int i = 0; i < methods.length; i++) {
	// IMethod method = methods[i];
	// if (PHPModelUtils.isConstructor(method)) {
	// return method;
	// }
	// }
	//
	// return null;
	// }

	@Override
	protected boolean showNonStaticMembers(IClassMemberContext context) {
		return super.showNonStaticMembers(context) || context.getTriggerType() == Trigger.CLASS;
	}

	public String getSuffix(AbstractCompletionContext abstractContext) throws BadLocationException {
		// look for method bracket or end of line
		IDocument document = getCompanion().getDocument();
		int offset = getCompanion().getOffset();
		while (document.getLength() > offset) {
			char ch = document.getChar(offset);
			if (ch == '(') {
				break;
			} else if (ch == '\n') {
				return "()"; //$NON-NLS-1$
			}
			offset++;
		}
		return ""; //$NON-NLS-1$
	}
}
