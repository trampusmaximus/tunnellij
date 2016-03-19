package net.boruvka.idea.tunnellij.action;

import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JOptionPane;

import com.intellij.openapi.ui.Messages;
import net.boruvka.idea.tunnellij.TunnelBundle;
import net.boruvka.idea.tunnellij.ui.Icons;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * @author boruvka
 * @since
 */
public class AboutAction extends AnAction {

    private static final String MESSAGE = TunnelBundle.getBundle().getString("TunnelliJ.message");

    public AboutAction() {
        super("Show About dialog", "Show About dialog", Icons.ICON_HELP);
    }

    public void actionPerformed(AnActionEvent event) {

        if (MESSAGE != null) {
            TextArea area = new TextArea(20, 80);
            area.setEditable(false);
            area.append(MESSAGE);
            JOptionPane.showMessageDialog(null, area);
        } else {
            System.out.println("stream is null!");
        }
    }
}