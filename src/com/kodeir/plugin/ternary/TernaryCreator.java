package com.kodeir.plugin.ternary;

import com.intellij.codeInsight.daemon.impl.quickfix.CreateMethodQuickFix;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;

/**
 * Created by Sergei "Kodeir" Riabinin on 12/03/17.
 *
 */
public class TernaryCreator extends AnAction {

    private Project project;
    private Editor editor;
    private Document document;
    private CaretModel caretModel;

    @Override
    public void actionPerformed(AnActionEvent actionEvent) {
        getProjectData(actionEvent);
        WriteCommandAction.runWriteCommandAction(project, run());

        final PsiElement element =
                PsiUtilBase.getPsiFileInEditor(editor, project).findElementAt(caretModel.getOffset());
        PsiClass psiClass = PsiTreeUtil.getParentOfType(element, PsiClass.class);

        CreateMethodQuickFix.createFix(psiClass, "sig", "bod");
    }

    /**
     * get project & editor, get document & caretModel from editor
     * @param actionEvent AnActionEvent
     */
    private void getProjectData(AnActionEvent actionEvent){
        //PlatformDataKeys or CommonDataKeys ?? TBD
        project = actionEvent.getData(PlatformDataKeys.PROJECT);
         editor = actionEvent.getRequiredData(CommonDataKeys.EDITOR);
        document = editor.getDocument();
        caretModel = editor.getCaretModel();
    }

    /**
     * create new runnable instance to make changes in current editor
     * @return Runnable
     */
    private Runnable run(){
        return () -> {
            //get the end of the current line
            int txtLineEnd = caretModel.getOffset();
            //insert ternary to the end of the line
            document.insertString(txtLineEnd, createTernaryText());
        };
    }

    private String createTernaryText() {
        return ("checkCondition() ? doIfTrue() : doIfFalse();");
    }
}
