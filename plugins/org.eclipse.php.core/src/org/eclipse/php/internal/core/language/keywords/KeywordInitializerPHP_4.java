/*******************************************************************************
 * Copyright (c) 2009, 2014 IBM Corporation and others.
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
 *     Dawid Pakuła [426054]
 *******************************************************************************/
package org.eclipse.php.internal.core.language.keywords;

import java.util.Collection;

import org.eclipse.php.internal.core.language.keywords.PHPKeywords.KeywordData;

/**
 * Keywords initializer for PHP 4.x
 */
public class KeywordInitializerPHP_4 implements IPHPKeywordsInitializer {

	@Override
	public void initialize(Collection<KeywordData> list) {
		list.add(new KeywordData("and", WHITESPACE_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("array", PARENTHESES_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("as", WHITESPACE_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("break", EMPTY_SUFFIX, 0)); //$NON-NLS-1$
		list.add(new KeywordData("case", WHITESPACE_COLON_SUFFIX, 2)); //$NON-NLS-1$
		list.add(new KeywordData("class", WHITESPACE_SUFFIX, 1, PHPKeywords.GLOBAL)); //$NON-NLS-1$
		list.add(new KeywordData("const", WHITESPACE_SUFFIX, 1, //$NON-NLS-1$
				PHPKeywords.CLASS_BODY));
		list.add(new KeywordData("continue", EMPTY_SUFFIX, 0)); //$NON-NLS-1$
		list.add(new KeywordData("declare", PARENTHESES_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("default", COLON_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("die", PARENTHESES_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("do", WHITESPACE_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("echo", WHITESPACE_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("elseif", WHITESPACE_PARENTHESES_SUFFIX, 2)); //$NON-NLS-1$
		list.add(new KeywordData("else", WHITESPACE_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("empty", PARENTHESES_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("enddeclare", SEMICOLON_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("endforeach", SEMICOLON_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("endfor", SEMICOLON_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("endif", SEMICOLON_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("endswitch", SEMICOLON_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("endwhile", SEMICOLON_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("eval", PARENTHESES_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("exit", PARENTHESES_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("extends", WHITESPACE_SUFFIX, 1, PHPKeywords.GLOBAL)); //$NON-NLS-1$
		list.add(new KeywordData("foreach", WHITESPACE_PARENTHESES_SUFFIX, 2)); //$NON-NLS-1$
		list.add(new KeywordData("for", WHITESPACE_PARENTHESES_SUFFIX, 2)); //$NON-NLS-1$
		list.add(new KeywordData("function", WHITESPACE_SUFFIX, 1, //$NON-NLS-1$
				PHPKeywords.CLASS_BODY | PHPKeywords.GLOBAL));
		list.add(new KeywordData("global", WHITESPACE_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("if", WHITESPACE_PARENTHESES_SUFFIX, 2)); //$NON-NLS-1$
		list.add(new KeywordData("include_once", //$NON-NLS-1$
				WS_QUOTES_SEMICOLON_SUFFIX, 2, PHPKeywords.GLOBAL));
		list.add(new KeywordData("include", WS_QUOTES_SEMICOLON_SUFFIX, 2, PHPKeywords.GLOBAL)); //$NON-NLS-1$
		list.add(new KeywordData("isset", PARENTHESES_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("list", PARENTHESES_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("new", WHITESPACE_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("or", WHITESPACE_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("parent", PAAMAYIM_NEKUDOTAYIM_SUFFIX, 2, //$NON-NLS-1$
				PHPKeywords.METHOD_BODY | PHPKeywords.METHOD_PARAM));
		list.add(new KeywordData("print", WHITESPACE_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("require_once", //$NON-NLS-1$
				WS_QUOTES_SEMICOLON_SUFFIX, 2));
		list.add(new KeywordData("require", WS_QUOTES_SEMICOLON_SUFFIX, 2, PHPKeywords.GLOBAL)); //$NON-NLS-1$
		list.add(new KeywordData("return", WHITESPACE_SUFFIX, 1, PHPKeywords.METHOD_BODY | PHPKeywords.GLOBAL)); //$NON-NLS-1$
		list.add(new KeywordData("static", WHITESPACE_SUFFIX, 1, //$NON-NLS-1$
				PHPKeywords.CLASS_BODY | PHPKeywords.GLOBAL));
		list.add(new KeywordData("switch", WHITESPACE_PARENTHESES_SUFFIX, 2)); //$NON-NLS-1$
		list.add(new KeywordData("unset", PARENTHESES_SUFFIX, 1)); //$NON-NLS-1$
		list.add(new KeywordData("var", WHITESPACE_SUFFIX, 1, //$NON-NLS-1$
				PHPKeywords.CLASS_BODY));
		list.add(new KeywordData("while", WHITESPACE_PARENTHESES_SUFFIX, 2)); //$NON-NLS-1$
		list.add(new KeywordData("xor", WHITESPACE_SUFFIX, 1)); //$NON-NLS-1$
	}

	@Override
	public void initializeSpecific(Collection<KeywordData> list) {
		list.add(new KeywordData("old_function", WHITESPACE_SUFFIX, 1)); //$NON-NLS-1$
	}

}
