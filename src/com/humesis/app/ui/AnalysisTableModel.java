/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humesis.app.ui;

import com.humesis.app.entity.DataStats;
import com.humesis.app.entity.DataStatsTable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vishwaka
 */
public class AnalysisTableModel extends AbstractTableModel {

    //Two arrays used for the table data
    String[] columnNames = {"Category", "Exts", "Count", "Size", "Properties", "Update Time"};
    private List<DataStatsTable> stats;

    public AnalysisTableModel() {
        stats = new ArrayList<>();
    }

    public AnalysisTableModel(List<DataStatsTable> stats) {
        this.stats = stats;
    }

    @Override
    public int getRowCount() {
        return stats.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    public void add(DataStatsTable stat) {
        int size = getRowCount();
        stats.add(stat);
        fireTableRowsInserted(size, size);
    }

    public void remove(DataStatsTable stat) {
        if (stats.contains(stat)) {
            int index = stats.indexOf(stat);
            stats.remove(index);
            fireTableRowsDeleted(index, getRowCount());
        }
    }
    public void removeAll() {
        if (stats.size()>0) {
            stats.clear();
            fireTableRowsDeleted(0, 0);
        }
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DataStatsTable stat = stats.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stat.dataCategory;
            case 1:
                return stat.dataType;
            case 2:
                return stat.dataCount;
            case 3:
                return stat.dataSize;
            case 4:
                return stat.dataStatisticsMap;
            case 5:
                return stat.dataUpdateTime;
        }
        return "";
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

        DataStatsTable stat = stats.get(rowIndex);
        switch (columnIndex) {
            case 0:
                stat.dataCategory = ((String) value);
                break;
            case 1:
                stat.dataType = ((String) value);
                break;
            case 2:
                stat.dataCount = ((int) value);
                break;
            case 3:
                stat.dataSize = ((int) value);
                break;
            case 4:
                stat.dataStatisticsMap = ((HashMap<DataStats.DataStatIdentifier, String>) value);
                break;
            case 5:
                stat.dataUpdateTime = ((Timestamp) value);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    //Used by the JTable object to set the column names
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    //Used by the JTable object to render different
    //functionality based on the data type

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 0 || column == 1) {
            return false;
        } else {
            return true;
        }
    }
}
