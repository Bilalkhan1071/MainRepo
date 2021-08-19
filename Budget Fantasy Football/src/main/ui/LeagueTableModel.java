package ui;


import model.Team;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LeagueTableModel extends AbstractTableModel {
    private final List<Team> teamList;
    private final String [] columnNames = new String[] {
            "Team Name", "Team Score"
    };

    private final Class[] columnClass = new Class[] {
            String.class, Integer.class
    };

    //EFFECTS: creates a league table model
    public LeagueTableModel(List<Team> teamList) {
        this.teamList = teamList;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public int getRowCount() {
        return teamList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Team row = teamList.get(rowIndex);
        if (0 == columnIndex) {
            return row.getTeamName();
        } else if (1 == columnIndex) {
            return row.getTeamScore();
        }
        return null;
    }
}
