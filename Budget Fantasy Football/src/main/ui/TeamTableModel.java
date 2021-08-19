package ui;

import model.League;

import model.Team;

import javax.swing.table.AbstractTableModel;


public class TeamTableModel extends AbstractTableModel {
    private League league;
    private final String [] columnNames = new String[] {
            "Team Name", "Team Score"
    };

    private final Class[] columnClass = new Class[] {
            String.class, Integer.class
    };

    //EFFECTS: creates team table model
    public TeamTableModel(League league) {
        this.league = league;
    }

    //EFFECTS: updates every cell in table model
    public void updateLeague(League league) {
        this.league = league;
        this.fireTableDataChanged();
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
        return league.teams.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Team row = league.teams.get(rowIndex);
        if (0 == columnIndex) {
            return row.getTeamName();
        } else if (1 == columnIndex) {
            return row.getTeamScore();
        }
        return null;
    }
}
