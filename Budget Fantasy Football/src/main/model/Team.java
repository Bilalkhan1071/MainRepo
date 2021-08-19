package model;



import java.util.ArrayList;
import java.util.List;

import exception.TeamFullException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;


//Creates a Team that the user will make
//Allows user to add players to the team and have a score as a fantasy app would
public class Team implements Writable {
    private String teamName; //Name of the team
    public ArrayList<Player> players; //List of the players in the team
    public int teamScore; //Total team score which is sum of players scores. change to sum of player scores
    private int playerLimit = 11;

    // create a team which is a list of players which is initially empty
    //REQUIRES: team name to be a non zero string
    //EFFECTS: create a team with a teamName, list of players and teamScore
    //         teamScore starts at 0 until players are added.
    public Team(String tn) {
        players = new ArrayList<Player>();
        this.teamName = tn;
        teamScore = 0;
    }

    //EFFECTS: return the team name;
    public String getTeamName() {
        return teamName;
    }

    //EFFECTS: return the teams score
    public int getTeamScore() {
        return teamScore;
    }

    //EFFECTS: returns a list of players names in a team
    public List<String> getPlayers() {
        List<String> playerNames = new ArrayList<>();
        for (Player p : players) {
            playerNames.add(p.getName());
        }
        return playerNames;

    }

    //EFFECTS: return the size of the team
    public int getTeamSize() {
        return players.size();
    }

    //REQUIRES: that a player is not already in the team
    //MODIFIES: this
    //EFFECTS: adds a player to the team and updates the teams score
    public boolean addPlayer(Player plyr) throws TeamFullException {
        if (players.size() >= playerLimit) {
            throw new TeamFullException();
        } else {
            players.add(plyr);
            plyr.joinedTeam();
            return true;
        }
    }

    //REQUIRES: a player is in already in the team
    //MODIFIES: this
    //EFFECTS: removes a player from the team
    //IMPORTANT: removing a player will not remove the score that the player contributed
    //           to the team since this was done while the player was in the team
    public void removePlayer(Player plyr) {
        players.remove(plyr);
    }

    //EFFECTS: checks if a given player is part of the time being acted upon.
    public boolean isInTeam(Player plyr) {
        return players.contains(plyr);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", teamName);
        json.put("players", playersToJson());
        json.put("teamScore",teamScore);
        return json;
    }


    //EFFECTS: returns the players as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : players) {
            jsonArray.put(p.getName());
        }
        return jsonArray;
    }


}
