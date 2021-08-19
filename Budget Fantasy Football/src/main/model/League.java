package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.List;

//Class will act as the main league to which users will add teams
public class League implements Writable {
    private String leagueName;
    public ArrayList<Team> teams;
    public List<Player> leaguePlayers;

    // creates a league with league name
    public League() {
        this.leagueName = "The League";
        this.teams = new ArrayList<Team>();
        this.leaguePlayers = new ArrayList<Player>();
    }

    //EFFECTS: returns the league name
    public String getLeagueName() {
        return this.leagueName;
    }

    public void addTeam(Team tm) {
        if (!teams.contains(tm)) {
            teams.add(tm);
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", leagueName);
        json.put("teams", teamsToJson());
        json.put("leaguePlayers",leaguePlayerToJson());
        return json;
    }

    //EFFECTS: returns the teams as a JSON array
    private JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team t : teams) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

    private JSONArray leaguePlayerToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : leaguePlayers) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

}
