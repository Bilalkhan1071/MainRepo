package persistance;


import exception.TeamFullException;
import model.League;
import model.Player;
import model.Team;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

//Represents a reader that reads JSON data stored in a file
public class JsonReader {
    private String source;

    //EFFECTS: makes a JSON reader which reads a given source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads a league from file and returns it.
    //throws exception if error occurs
    public League read() throws IOException {
        String jsonData = readData(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeague(jsonObject);
    }


    //EFFECTS: reads a given source file and returns it
    private String readData(String source) throws IOException {
        StringBuilder strBuild = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> strBuild.append(s));
        }

        return strBuild.toString();
    }


    //EFFECTS: parses league from JSON object and returns it
    private League parseLeague(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        League lg = new League();
        addLeaguePlayers(lg, jsonObject);
        addTeams(lg, jsonObject);
        return lg;
    }

    //EFFECTS: parses teams from JSON object and adds them to the JSON file
    private void addTeams(League lg, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("teams");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            addTeam(lg, nextTeam);
        }
    }

    //MODIFIES: lg
    //EFFECTS: parses team from JSON object and adds them to the league
    private void addTeam(League lg, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Integer teamScore = jsonObject.getInt("teamScore");
        Team team = new Team(name);
        addPlayersForTeam(lg, team, jsonObject);
        team.teamScore = teamScore;
        lg.teams.add(team);
    }

    //MODIFIES: tm
    //EFFECTS: parses players from JSON object and adds them to the JSON file
    private void addPlayersForTeam(League lg, Team tm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        for (Object json : jsonArray) {
            String nextPlayer = (String) json;
            addPlayer(lg, tm, nextPlayer);
        }
    }

    //MODIFIES: tm
    //EFFECTS: parses player from JSON object and adds them to teams
    private void addPlayer(League lg, Team tm, String playerName) {
        for (Player plyr : lg.leaguePlayers) {
            if (plyr.getName().equals(playerName)) {
                try {
                    tm.addPlayer(plyr);
                } catch (TeamFullException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //MODIFIES: lg
    //EFFECTS: updates existing players with JSON player
    private void addLeaguePlayers(League lg, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("leaguePlayers");
        for (Object json : jsonArray) {
            JSONObject nextLeaguePlayer = (JSONObject) json;
            addLeaguePlayer(lg, nextLeaguePlayer);
        }
    }

    //MODIFIES: lg
    //EFFECTS: updates existing players with JSON player
    private void addLeaguePlayer(League lg, JSONObject jsonObject) {
        String name = jsonObject.getString("leaguePlayerName");
        Integer goals = jsonObject.getInt("leaguePlayerGoals");
        Integer assists = jsonObject.getInt("leaguePlayerAssists");
        Integer leaguePlayerScore = jsonObject.getInt("leaguePlayerScore");
        Player leaguePlayer = new Player(name, "Existing Players");
        leaguePlayer.points = leaguePlayerScore;
        leaguePlayer.setGoals(goals);
        leaguePlayer.setAssists(assists);
        lg.leaguePlayers.add(leaguePlayer);
    }


}
