package model;


import org.json.JSONObject;
import persistance.Writable;

//Creates a player with a given name and club name
//Gives the player a starting score, goals and assists of 0
public class Player implements Writable {
    private String name;  // the name of the player
    private String club;  // the actual club the player plays for
    private int goals;    // the amount of goals the player scored
    private int assists;  // the amount of assist the player scored
    public int points;    // the score that the player has in the fantasy league
    private int goalPoints = 3;
    private int assistPoints = 1;
    private boolean partOfTeam;

    //REQUIRES: the name has a non-zero length
    //EFFECTS: creates a player with name, club
    //         with goals, assists and score set to 0
    //         not part of a team

    public Player(String name, String club) {
        this.name = name;
        this.club = club;
        goals = 0;
        assists = 0;
        points = 0;
        partOfTeam = false;
    }

    //EFFECTS: returns a player's name
    public String getName() {
        return name;
    }

    //EFFECTS: returns a player's club name
    public String getClub() {
        return club;
    }

    //EFFECTS: returns a player's goals scored
    public int getGoals() {
        return goals;
    }

    //EFFECTS: returns a player's assists given
    public int getAssists() {
        return assists;
    }

    //EFFECTS: returns a player's score
    public int getPoints() {
        return points;
    }

    // 1 goal will be worth 3 points
    //REQUIRES: int gol should be >0
    //MODIFIES: this
    //EFFECTS: adds a goal to a given players goals total
    //         adds the appropriate amount of points to players points total
    public void scoredGoal(int gol) {
        this.goals = this.goals + gol;
        this.points = this.points + (gol * goalPoints);
    }

    //same as above but for when player is in team
    public void scoredGoalForTeam(int gol, Team tm) {
        this.goals = this.goals + gol;
        this.points = this.points + (gol * goalPoints);
        tm.teamScore = tm.teamScore + (gol * goalPoints);
    }

    // 1 assist will be worth 1 point
    //REQUIRES: int ast should be >0
    //MODIFIES: this
    //EFFECTS: adds an assist to a given players goals total
    //         adds the appropriate amount of points to players points total
    public void madeAssist(int ast) {
        this.assists = this.assists + ast;
        this.points = this.points + (ast * assistPoints);
    }

    //same as above but for when a player is in a team
    public void madeAssistForTeam(int ast, Team tm) {
        this.assists = this.assists + ast;
        this.points = this.points + (ast * assistPoints);
        tm.teamScore = tm.teamScore + (ast * assistPoints);
    }

    // MODIFIES: this
    //EFFECTS: changes the players status to being a part of a team
    public void joinedTeam() {
        this.partOfTeam = true;
    }

    //EFFECTS: checks if a player is part of a team
    public boolean isPartOfTeam() {
        return partOfTeam;
    }

    //EFFECTS: checks if a player is part of a given team
    public boolean isInGivenTeam(Team team) {
        return team.isInTeam(this);
    }

    //EFFECTS: set a players name
    public void setName(String name) {
        this.name = name;
    }

    //EFFECTS: set a players goals
    public void setGoals(int goals) {
        this.scoredGoal(goals);

    }

    //EFFECTS: set a players assists
    public void setAssists(int assists) {
        this.madeAssist(assists);

    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("leaguePlayerName", name);
        json.put("leaguePlayerScore", points);
        json.put("leaguePlayerGoals", goals);
        json.put("leaguePlayerAssists", assists);
        return json;
    }

}
