package com.jseppa.intellij.amibroker;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class AFLLexerAdapter extends FlexAdapter {
    public AFLLexerAdapter() {
        super(new AFLLexer((Reader) null));
    }
}
