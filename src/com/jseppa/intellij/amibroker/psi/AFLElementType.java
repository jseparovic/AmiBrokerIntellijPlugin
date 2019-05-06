package com.jseppa.intellij.amibroker.psi;

import com.intellij.psi.tree.IElementType;
import com.jseppa.intellij.amibroker.language.AFLLanguage;
import org.jetbrains.annotations.*;

public class AFLElementType extends IElementType {
    public AFLElementType(@NotNull @NonNls String debugName) {
        super(debugName, AFLLanguage.INSTANCE);
    }
}
