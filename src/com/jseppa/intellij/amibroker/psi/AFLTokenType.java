package com.jseppa.intellij.amibroker.psi;

import com.intellij.psi.tree.IElementType;
import com.jseppa.intellij.amibroker.language.AFLLanguage;
import org.jetbrains.annotations.*;

public class AFLTokenType extends IElementType {
    public AFLTokenType(@NotNull @NonNls String debugName) {
        super(debugName, AFLLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "AFLTokenType." + super.toString();
    }
}