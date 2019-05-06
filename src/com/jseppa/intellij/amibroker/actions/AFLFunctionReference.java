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
        super("AFL Function Reference");
    }

    private LeafPsiElement getLeafPsiElement(AnActionEvent e)
    {
        PsiFile file = e.getData(CommonDataKeys.PSI_FILE);
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        int caret = editor.getCaretModel().getOffset();
        PsiElement element = file.findElementAt(caret);

        if (element != null && element instanceof LeafPsiElement)
        {
            LeafPsiElement leafPsiElement = (LeafPsiElement) element;
            return leafPsiElement;
        }
        return null;
    }

    private boolean isBuiltInFunction(LeafPsiElement leafPsiElement)
    {
        return leafPsiElement != null && leafPsiElement.getElementType().equals(AFLTypes.BUILTIN_FUNCTIONS);
    }

    public void actionPerformed(AnActionEvent e)
    {
        LeafPsiElement leafPsiElement = getLeafPsiElement(e);
        if(isBuiltInFunction(leafPsiElement))
        {
            String text = leafPsiElement.getText();
            BrowserUtil.browse(String.format(AFL_REF_URL, text.toLowerCase()));
        }
    }

    public void update(AnActionEvent e) {
        e.getPresentation().setEnabled(isBuiltInFunction(getLeafPsiElement(e)));
    }

}