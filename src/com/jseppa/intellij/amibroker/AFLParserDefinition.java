package com.jseppa.intellij.amibroker;

import com.intellij.lang.*;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.tree.*;
import com.jseppa.intellij.amibroker.language.AFLLanguage;
import com.jseppa.intellij.amibroker.psi.AFLFile;
import com.jseppa.intellij.amibroker.psi.AFLTypes;
import org.jetbrains.annotations.NotNull;

public class AFLParserDefinition implements ParserDefinition {
    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(AFLTypes.LINE_COMMENT);

    public static final IFileElementType FILE = new IFileElementType(AFLLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new AFLLexerAdapter();
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new AFLParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new AFLFile(viewProvider);
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return AFLTypes.Factory.createElement(node);
    }
}
