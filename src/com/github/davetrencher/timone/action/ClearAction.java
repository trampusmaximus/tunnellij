package com.github.davetrencher.timone.action;

import com.github.davetrencher.timone.ui.Icons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * @author boruvka
 */
public class ClearAction extends AnAction {

    public ClearAction() {
        super("Remove all calls from list", "Remove all calls from list",
                Icons.ICON_CLEAR);
    }

    public void actionPerformed(AnActionEvent event) {

    }
}
