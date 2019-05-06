package com.jseppa.intellij.amibroker.file;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.jseppa.intellij.amibroker.language.AFLLanguage;
import org.jetbrains.annotations.*;

import javax.swing.*;

public class AFLFileType extends LanguageFileType {
    public static final AFLFileType INSTANCE = new AFLFileType();

    private AFLFileType() {
        super(AFLLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "AFL file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "AFL language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "afl";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return AFLIcons.FILE;
    }
}