package com.jseppa.intellij.amibroker.actions;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.jseppa.intellij.amibroker.psi.AFLTypes;

public class AFLFunctionReference extends AnAction
{

    public static String AFL_REF_URL = "https://www.amibroker.com/guide/afl/%s.html";

    public AFLFunctionReference()
    {
        super("AFLLanguage Function Reference");
    }

    public void actionPerformed(AnActionEvent e)
    {
        PsiFile file = e.getData(CommonDataKeys.PSI_FILE);
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        int caret = editor.getCaretModel().getOffset();
        PsiElement element = file.findElementAt(caret);

        if(element != null && element instanceof LeafPsiElement)
        {
            LeafPsiElement leafPsiElement = (LeafPsiElement) element;
            if(leafPsiElement.getElementType().equals(AFLTypes.BUILTIN_FUNCTIONS))
            {
                String text = leafPsiElement.getText();
                BrowserUtil.browse(String.format(AFL_REF_URL, text.toLowerCase()));
            }
        }

    }
}