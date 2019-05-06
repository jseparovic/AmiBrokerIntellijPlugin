package com.jseppa.intellij.amibroker.syntax;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.jseppa.intellij.amibroker.AFLLexerAdapter;
import com.jseppa.intellij.amibroker.psi.AFLTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class AFLSyntaxHighlighter extends SyntaxHighlighterBase {
    private static final IElementType[] IET_KEYWORDS = new IElementType[]{
            AFLTypes.IF_KEYWORD,
            AFLTypes.ELSE_KEYWORD,
            AFLTypes.FOR_KEYWORD,
            AFLTypes.WHILE_KEYWORD,
            AFLTypes.LOCAL_KEYWORD,
            AFLTypes.BREAK_KEYWORD,
            AFLTypes.SWITCH_KEYWORD,
            AFLTypes.CASE_KEYWORD,
            AFLTypes.CONTINUE_KEYWORD,
            AFLTypes.DEFAULT_KEYWORD,
            AFLTypes.DO_KEYWORD,
            AFLTypes.FUNCTION_KEYWORD,
            AFLTypes.GLOBAL_KEYWORD,
            AFLTypes.PROCEDURE_KEYWORD,
            AFLTypes.RETURN_KEYWORD,
            AFLTypes.STATIC_KEYWORD,
            AFLTypes.TYPEOF_KEYWORD,
            AFLTypes.LOGICAL_AND,
            AFLTypes.LOGICAL_OR,
            AFLTypes.LOGICAL_NOT,
    };

    private static final IElementType[] IET_STRING = new IElementType[]{
            AFLTypes.STRING_LITERAL,
    };

    private static final IElementType[] IET_NUMBER = new IElementType[]{
            AFLTypes.DOUBLE_LITERAL,
            AFLTypes.INTEGER_LITERAL,
    };

    private static final IElementType[] IET_BUILTIN_ARRAYS = new IElementType[]{
            AFLTypes.BUILTIN_ARRAYS,
    };

    private static final IElementType[] IET_BUILTIN_FUNCTIONS = new IElementType[]{
            AFLTypes.BUILTIN_FUNCTIONS,
    };

    private static final IElementType[] IET_BUILTIN_HEADERS = new IElementType[]{
            AFLTypes.BUILTIN_HEADERS,
    };

    private static final IElementType[] IET_BUILTIN_COLORS_STYLES = new IElementType[]{
            AFLTypes.BUILTIN_COLORS,
            AFLTypes.BUILTIN_STYLES,
    };

    private static final IElementType[] IET_COMMENTS = new IElementType[]{
            AFLTypes.LINE_COMMENT,
            AFLTypes.BLOCK_COMMENT,
    };

    private static final IElementType[] IET_BAD_CHARACTER = new IElementType[]{
            AFLTypes.BAD_CHARACTER,
    };


    private static final TextAttributesKey[] RESERVED_KEYWORDS = getTextAttributesKeyArray(
            DefaultLanguageHighlighterColors.KEYWORD, IET_KEYWORDS);

    private static final TextAttributesKey[] STRING = getTextAttributesKeyArray(
            DefaultLanguageHighlighterColors.STRING, IET_STRING);

    private static final TextAttributesKey[] NUMBER = getTextAttributesKeyArray(
            DefaultLanguageHighlighterColors.NUMBER, IET_NUMBER);

    private static final TextAttributesKey[] BUILTIN_ARRAYS = getTextAttributesKeyArray(
            DefaultLanguageHighlighterColors.FUNCTION_DECLARATION, IET_BUILTIN_ARRAYS);

    private static final TextAttributesKey[] BUILTIN_FUNCTIONS = getTextAttributesKeyArray(
            DefaultLanguageHighlighterColors.INSTANCE_FIELD, IET_BUILTIN_FUNCTIONS);

    private static final TextAttributesKey[] BUILTIN_HEADERS = getTextAttributesKeyArray(
            DefaultLanguageHighlighterColors.STATIC_METHOD, IET_BUILTIN_HEADERS);

    private static final TextAttributesKey[] BUILTIN_COLORS_STYLES = getTextAttributesKeyArray(
            DefaultLanguageHighlighterColors.FUNCTION_DECLARATION, IET_BUILTIN_COLORS_STYLES);

    private static final TextAttributesKey[] COMMENTS = getTextAttributesKeyArray(
            DefaultLanguageHighlighterColors.LINE_COMMENT, IET_COMMENTS);

    private static final TextAttributesKey[] BAD_CHARS = getTextAttributesKeyArray(
            HighlighterColors.BAD_CHARACTER, IET_BAD_CHARACTER);

    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];


    @NotNull
    @Override
    public Lexer getHighlightingLexer()
    {
        return new AFLLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType)
    {
        if (Arrays.asList(IET_KEYWORDS).contains(tokenType))
        {
            return RESERVED_KEYWORDS;
        }
        else if (Arrays.asList(IET_STRING).contains(tokenType))
        {
            return STRING;
        }
        else if (Arrays.asList(IET_NUMBER).contains(tokenType))
        {
            return NUMBER;
        }
        else if (Arrays.asList(IET_BUILTIN_ARRAYS).contains(tokenType))
        {
            return BUILTIN_ARRAYS;
        }
        else if (Arrays.asList(IET_BUILTIN_FUNCTIONS).contains(tokenType))
        {
            return BUILTIN_FUNCTIONS;
        }
        else if (Arrays.asList(IET_BUILTIN_HEADERS).contains(tokenType))
        {
            return BUILTIN_HEADERS;
        }
        else if (Arrays.asList(IET_BUILTIN_COLORS_STYLES).contains(tokenType))
        {
            return BUILTIN_COLORS_STYLES;
        }
        else if (Arrays.asList(IET_COMMENTS).contains(tokenType))
        {
            return COMMENTS;
        }
        else if (Arrays.asList(IET_BAD_CHARACTER).contains(tokenType))
        {
            return BAD_CHARS;
        }
        else {
            return EMPTY_KEYS;
        }
    }


    public static TextAttributesKey[] getTextAttributesKeyArray(TextAttributesKey colors, IElementType[] elementTypes) {
        TextAttributesKey[] textAttributesKeys = new TextAttributesKey[elementTypes.length];
        int i = 0;
        for(IElementType elementType : elementTypes) {
            textAttributesKeys[i++] = createTextAttributesKey(elementType.toString(), colors);
        }
        return textAttributesKeys;
    }
}
