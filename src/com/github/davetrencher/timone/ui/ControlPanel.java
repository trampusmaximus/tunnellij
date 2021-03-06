package com.github.davetrencher.timone.ui;

import com.github.davetrencher.timone.TunnelPlugin;
import com.github.davetrencher.timone.net.Call;
import com.github.davetrencher.timone.net.TunnelListener;
import org.apache.commons.lang.math.NumberUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by dave on 21/03/16.
 */
public class ControlPanel extends JPanel implements TunnelListener {

    private JTextField srcPort;

    private JTextField destHost;

    private JTextField destPort;

    private PortNumberVerifier portNumberVerifier;

    private boolean isRunning = false;

    public ControlPanel() {
        super();
        portNumberVerifier = new PortNumberVerifier();
        initComponents();

    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel subPanelAddress = new JPanel();
        subPanelAddress.setBorder(new TitledBorder("properties"));

        srcPort = new JTextField(TunnelPlugin.TunnelConfig.getSourcePort());
        srcPort.setInputVerifier(portNumberVerifier);
        srcPort.setHorizontalAlignment(JTextField.RIGHT);
        srcPort.setColumns(5);

        destPort = new JTextField(TunnelPlugin.TunnelConfig
                .getDestinationPort());
        destPort.setInputVerifier(portNumberVerifier);
        destPort.setHorizontalAlignment(JTextField.RIGHT);
        destPort.setColumns(5);

        destHost = new JTextField(TunnelPlugin.TunnelConfig
                .getDestinationString());
        destHost.setHorizontalAlignment(JTextField.RIGHT);
        destHost.setColumns(24);

        subPanelAddress.add(new JLabel("tunnel from localhost:"));
        subPanelAddress.add(srcPort);
        subPanelAddress.add(new JLabel("to "));
        subPanelAddress.add(destHost);
        subPanelAddress.add(new JLabel(":"));
        subPanelAddress.add(destPort);

        add(subPanelAddress, BorderLayout.SOUTH);

        subPanelAddress.revalidate();
        subPanelAddress.repaint();
    }

    private void setControlPanelEditable(boolean b) {

        TunnelPlugin.PROPERTIES.put(TunnelPlugin.TunnelConfig.DST_HOST,
                destHost.getText());
        TunnelPlugin.PROPERTIES.put(TunnelPlugin.TunnelConfig.DST_PORT,
                destPort.getText());
        TunnelPlugin.PROPERTIES.put(TunnelPlugin.TunnelConfig.SRC_PORT,
                srcPort.getText());

        SwingUtilities.invokeLater(() -> {
                srcPort.setEditable(b);
                destHost.setEditable(b);
                destPort.setEditable(b);

                srcPort.setEnabled(b);
                destHost.setEnabled(b);
                destPort.setEnabled(b);
        });

    }

    public int getSrcPort() {
        return Integer.parseInt(srcPort.getText());
    }

    public int getDestPort() {
        return Integer.parseInt(destPort.getText());
    }

    public String getDestHost() {
        return destHost.getText();
    }

    public void newCall(Call call) {
        //
    }

    public void endCall(Call call) {
        //
    }

    public void tunnelStarted() {
        isRunning = true;
        setControlPanelEditable(false);
    }

    public void tunnelStopped() {
        isRunning = false;
        setControlPanelEditable(true);
    }

    protected boolean isRunning() {
        return isRunning;
    }


    private class PortNumberVerifier extends InputVerifier {

        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText();

            return NumberUtils.isDigits(text);

        }

    }


}

