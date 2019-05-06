package com.jseppa.intellij.amibroker.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.jseppa.intellij.amibroker.file.AFLFileType;
import com.jseppa.intellij.amibroker.language.AFLLanguage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AFLFile extends PsiFileBase {
    public AFLFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, AFLLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return AFLFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "AFL File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}
