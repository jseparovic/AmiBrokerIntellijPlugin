package com.jseppa.intellij.amibroker.language;

import com.intellij.lang.Language;

public class AFLLanguage extends Language {
    public static final AFLLanguage INSTANCE = new AFLLanguage();

    private AFLLanguage() {
        super("AFL");
    }
}