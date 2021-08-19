package ui;

import model.League;
import model.Player;
import model.Team;


import javax.swing.table.AbstractTableModel;

public class PlayerTableModel extends AbstractTableModel {

//    private List<Player> playerList;
    private League league;
    private final String[] columnNames = new String[] {
            "Name", "Goals", "Assists", "Points"
    };

    private final Class[] columnClass = new Class[] {
            String.class, Integer.class, Integer.class, Integer.class
    };

    //EFFECTS: constructs a table model
    public PlayerTableModel(League league) {
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
        return league.leaguePlayers.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Player row = league.leaguePlayers.get(rowIndex);
        if (0 == columnIndex) {
            return row.getName();
        } else if (1 == columnIndex) {
            return row.getGoals();
        } else if (2 == columnIndex) {
            return row.getAssists();
        } else if (3 == columnIndex) {
            return ((row.getGoals() * 3) + (row.getAssists() * 1));
        }
        return null;
    }

    //EFFECTS: allows user to set the value of goals and assist in the player table
    @Override
    public void setValueAt(Object val, int rowIndex, int columnIndex) {
        Player row = league.leaguePlayers.get(rowIndex);
        if (1 == columnIndex) {
            if (!row.isPartOfTeam()) {
                row.scoredGoal((Integer) val);
            } else {
                for (Team tm: league.teams) {
                    if (tm.isInTeam(row)) {
                        row.scoredGoalForTeam((Integer) val, tm);
                    }
                }
            }
        } else if (2 == columnIndex) {
            if (!row.isPartOfTeam()) {
                row.madeAssist((Integer) val);
            } else {
                for (Team tm: league.teams) {
                    if (tm.isInTeam(row)) {
                        row.madeAssistForTeam((Integer) val, tm);
                    }
                }
            }
        }
    }

    //EFFECTS: allows the editing of the goals and assists for players
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
