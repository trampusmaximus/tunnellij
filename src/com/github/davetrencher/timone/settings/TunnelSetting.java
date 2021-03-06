package com.github.davetrencher.timone.settings;

import com.github.davetrencher.timone.net.TunnelManager;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dave on 22/03/16.
 */
public class TunnelSetting implements Serializable {

    private int srcPort;

    private String destHost;

    private int destPort;

    public TunnelSetting() {
    }

    public TunnelSetting(int srcPort, String destHost, int destPort) {
        this.srcPort = srcPort;
        this.destHost = destHost;
        this.destPort = destPort;
    }

    public TunnelSetting(Object[] arr) {
        if (arr[0] != null && arr[0] instanceof Integer) {
            this.srcPort = (Integer)arr[0];
        }
        if (arr[1] != null && arr[1] instanceof String) {
            this.destHost = (String)arr[1];
        }
        if (arr[2] != null && arr[2] instanceof Integer) {
            this.destPort = (Integer)arr[2];
        }
    }


    public int getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(int srcPort) {
        this.srcPort = srcPort;
    }

    public String getDestHost() {
        return destHost;
    }

    public void setDestHost(String destHost) {
        this.destHost = destHost;
    }

    public int getDestPort() {
        return destPort;
    }

    public void setDestPort(int destPort) {
        this.destPort = destPort;
    }

    public static class SettingsTableModel extends AbstractTableModel {

        public static final String NAME = "SettingsTable";

        public static final String SOURCE_PORT = "Source Port";
        public static final String DEST_HOST = "Dest Host";
        public static final String DEST_PORT = "Dest Port";
        public static final String RUNNING = "Running";

        private final List<TunnelSetting> settings;


        public SettingsTableModel(List<TunnelSetting> settings) {
            this.settings = settings;
        }

        public void addRow(Object[] arr) {
            TunnelSetting setting = new TunnelSetting(arr);
            settings.add(setting);
        }

        public void removeRow(int row) {
            if (getRowCount() >= row) {
                settings.remove(row);
            }
        }


        @Override
        public int getRowCount() {
            if (settings == null) {
                return 0;
            }
            return settings.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public boolean isCellEditable(int row, int col) {

            switch (col) {
                case 3: return false;
                default:
                    return true;
            }

        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            TunnelSetting setting = settings.get(row);
            switch (col) {
                case 0: setting.setSrcPort(Integer.parseInt((String)value));
                    break;
                case 1: setting.setDestHost((String)value);
                    break;
                case 2: setting.setDestPort(Integer.parseInt((String)value));
                    break;
                default:
                    //probably should do something here.
            }

            fireTableCellUpdated(row, col);
        }

        @Override
        public String getColumnName(int column) {

            switch (column) {
                case 0:
                    return SOURCE_PORT;
                case 1:
                    return DEST_HOST;
                case 2:
                    return DEST_PORT;
                case 3:
                    return RUNNING;
                default:

                    return null;
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            TunnelSetting setting = settings.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return setting.getSrcPort();
                case 1:
                    return setting.getDestHost();
                case 2:
                    return setting.getDestPort();
                case 3:
                    return TunnelManager.isTunnelRunningOnPort(setting.getSrcPort());
                default:
                    return null;
            }
        }
        
    }
}
