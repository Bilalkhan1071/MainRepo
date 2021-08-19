package ui;

import exception.TeamFullException;
import model.League;
import model.Player;
import model.Team;
import persistance.JsonReader;
import persistance.JsonWriter;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Fantasy Application where there is a pre set league and list of players
//User can make new teams, add players to teams and add goals and assist for these players
//Players are instantiated in this class as database is not accessible at the moment
public class FantasyApp {
    private League myLeague;
    public static ArrayList<Team> existingTeams;
    private Scanner input;
    public static List<Player> existingPlayers;
    private Player messi = new Player("Messi", "Barcelona");
    private Player ronaldo = new Player("Ronaldo", "Juventus");
    private Player neymar = new Player("Neymar", "PSG");
    private Player mbappe = new Player("Mbappe", "PSG");
    private Player lewa = new Player("Lewandowski", "Bayern");
    private Player ramos = new Player("Ramos", "Madrid");
    private Player alphonso = new Player("Alphonso", "Bayern");
    private Player virgil = new Player("Virgil", "Liverpool");
    private Player trent = new Player("Trent", "Liverpool");
    private Player telles = new Player("Telles", "Man United");
    private Player kepa = new Player("Kepa", "Chelsea");
    private static final String JSON_STORE = "./data/fantasyApp.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;



    public FantasyApp() throws FileNotFoundException {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        myLeague = new League();
        runApp();
    }

    // displays the main menu of the app
    public void displayMain() {
        System.out.println("\n Welcome to Fantasy Football!");
        System.out.println("\n What do you want to see?");
        System.out.println("\t l ---> View League");
        System.out.println("\t t ---> Team Menu");
        System.out.println("\t p ---> View Players");
        System.out.println("\t s ---> Save League");
        System.out.println("\t y ---> Load League");
        System.out.println("\t q ---> Quit");
    }


    //The main part of the application that allows it to run
    public void runApp() {
        boolean keepGoing = true;
        String key = null;

        init();


        while (keepGoing) {
            displayMain();
            key = input.next();
            key = key.toLowerCase();

            if (key.equals("q")) {
                keepGoing = false;
            } else {
                selectKey(key);
            }
        }

        System.out.println("\nSee Ya!");
    }

    //Options for the main menu
    public void selectKey(String key) {
        if (key.equals("l")) {
            openLeague();
        } else if (key.equals("t")) {
            teamMenu();
        } else if (key.equals("p")) {
            showPlayers();
        } else if (key.equals("s")) {
            saveLeague();
        } else if (key.equals("y")) {
            loadLeague();
        } else {
            System.out.println("Invalid Selection!");
        }
    }

    // Initializes the base requirements for the FantasyApp such as a list of teams and players to add to
    // The actual players are made by myself for now since that will later be done using a data file
    public void init() {
        existingTeams = myLeague.teams;
        existingPlayers = myLeague.leaguePlayers;
        input = new Scanner(System.in);
        existingPlayers.add(messi);
        existingPlayers.add(ronaldo);
        existingPlayers.add(neymar);
        existingPlayers.add(mbappe);
        existingPlayers.add(lewa);
        existingPlayers.add(ramos);
        existingPlayers.add(alphonso);
        existingPlayers.add(virgil);
        existingPlayers.add(trent);
        existingPlayers.add(telles);
        existingPlayers.add(kepa);
    }



    //EFFECTS: shows a list of times if existing with the teams scores
    public void openLeague() {
        String leagueKey = "";
        while (!(leagueKey.equals("b"))) {
            if (!(existingTeams.isEmpty())) {
                for (Team tms : existingTeams) {
                    System.out.println("\n" + tms.getTeamName() + " " + "Score" + " " + tms.getTeamScore());
                }
            } else {
                System.out.println("No teams in The League");
            }
            System.out.println("\n b --->Back to main Menu");
            leagueKey = input.next();
            leagueKey = leagueKey.toLowerCase();
        }
    }


    //EFFECTS: presents the user with a menu with options to do things involving teams
    public void teamMenu() {
        String teamKey = "";
        System.out.println("\n What would you like to do?");
        System.out.println("\t n ---> Create New Team");
        System.out.println("\t v ---> View Teams");
        System.out.println("\t b ---> Back to Main Menu");

        while (!(teamKey.equals("b"))) {
            if (teamKey.equals("n")) {
                makeTeam();
            } else if (teamKey.equals("v")) {
                showTeams();
            }
            teamKey = input.next();
            teamKey = teamKey.toLowerCase();
        }
    }

    //REQUIRES: that team name is one string otherwise it will only print the first half
    //EFFECTS: creates a new team with the users input as a name
    public void makeTeam() {
        System.out.println("\n What is your team name?");
        String teamName = input.next();
        Team newTeam = new Team(teamName);
        existingTeams.add(newTeam);
        System.out.println("Your team has been added!");
        System.out.println("Enter b to return to main menu");

    }


    //EFFECTS: presents all the teams if present and gives an option to add a player to these teams
    public void showTeams() {
        String teamKey = "";
        if (!(existingTeams.isEmpty())) {
            for (Team tms : existingTeams) {
                System.out.println("\n" + tms.getTeamName());
            }
            System.out.println("\n a ---> Add Player");
            System.out.println("\n b ---> Back to Main Menu");
            System.out.println("\n p ---> Show Players");
            teamKey = input.next();
            teamKey = teamKey.toLowerCase();
            if (teamKey.equals("a")) {
                playerAdd();
            } else if (teamKey.equals("p")) {
                playersInTeam();
            }
        } else {
            System.out.println("No teams currently");
            System.out.println("\n b ---> Back to Main Menu");
        }
    }

    private void playersInTeam() {
        String teamPlayerKey = "";
        System.out.println("Which team would you like to see");
        teamPlayerKey = input.next();
        Boolean chckPlayer = false;

        for (Team tms : existingTeams) {
            if (teamPlayerKey.equals(tms.getTeamName())) {
                for (Player plyr : existingPlayers) {
                    if (tms.isInTeam(plyr)) {
                        System.out.println("\n" + plyr.getName() + " " + "Score" + " " + plyr.getPoints());
                        chckPlayer = true;
                    }
                }
                if (!chckPlayer) {
                    System.out.println("No players Found");
                }
            } else {
                System.out.println("No team with this name");
            }
        }
        System.out.println("b ---> Back to Main Menu");
    }

    //EFFECTS: presents a list of the pre made players with their score
    //         also gives the option to register goals and assists for given players
    public void showPlayers() {
        String playerKey = "";
        if (!(existingPlayers.isEmpty())) {
            for (Player plyr : existingPlayers) {
                System.out.println("\n" + plyr.getName() + " " + "Score" + " " + plyr.getPoints());
            }
        } else {
            System.out.println("No Players!");
        }
        System.out.println("\n g ---> Register Goals");
        System.out.println("\n a ---> Register Assist");
        System.out.println("\n b ---> Back to Main Menu");
        playerKey = input.next();
        playerKey = playerKey.toLowerCase();

        if (playerKey.equals("g")) {
            registerGoal();
        } else if (playerKey.equals("a")) {
            registerAssist();
        }
    }

    //REQUIRES: the player name coming under "Who Scored?" needs to be the exact same name as the player
    //          else goal will not be registered
    //          goal value has to be a positive integer
    //EFFECTS: scores a set amount of goals for a player and adds these points to the player and/or teams score
    public void registerGoal() {
        String goalKey = "";
        String playerKey = "";
        System.out.println("Who Scored?");
        playerKey = input.next();

        System.out.println("How many Goals?");
        goalKey = input.next();
        int gol = Integer.parseInt(goalKey);

        for (Player plyr : existingPlayers) {
            if (playerKey.equals(plyr.getName())) {
                if (plyr.isPartOfTeam()) {
                    for (Team tms : existingTeams) {
                        if (tms.isInTeam(plyr)) {
                            plyr.scoredGoalForTeam(gol, tms);
                        }
                    }
                } else {
                    plyr.scoredGoal(gol);
                }
            }
        }
        System.out.println("Goal has been Registered");
    }

    //REQUIRES: the player name coming under "Who Assisted?" needs to be the exact same name as the player
    //          else assist will not be registered
    //          assist value must be a positive integer
    //EFFECTS: registers a set amount of assists for a player and adds these points to the player and/or teams score
    public void registerAssist() {
        String assistKey = "";
        String playerKey = "";
        System.out.println("Who Assisted?");
        playerKey = input.next();

        System.out.println("How many assists?");
        assistKey = input.next();
        int ast = Integer.parseInt(assistKey);

        for (Player plyr : existingPlayers) {
            if (playerKey.equals(plyr.getName())) {
                if (plyr.isPartOfTeam()) {
                    for (Team tms : existingTeams) {
                        if (tms.isInTeam(plyr)) {
                            plyr.madeAssistForTeam(ast, tms);
                        }
                    }
                } else {
                    plyr.madeAssist(ast);
                }
            }
        }
        System.out.println("Assist has been Registered");
    }

    //REQUIRES: the player being added and the team being added to must be existing
    //EFFECTS: add a player to a team by entering the names.
    public void playerAdd() {
        String playerKey = "";
        String teamKey = "";
        System.out.println("Which Player would you like to add?");
        playerKey = input.next();

        System.out.println("Which Team would you like to add to?");
        teamKey = input.next();

        for (Player plyr : existingPlayers) {
            for (Team tms : existingTeams) {
                if ((playerKey.equals(plyr.getName())) && (teamKey.equals(tms.getTeamName()))) {
                    if (!plyr.isInGivenTeam(tms)) {
                        try {
                            tms.addPlayer(plyr);
                        } catch (TeamFullException e) {
                            System.out.println("This team is full!");
                        }
                        System.out.println("Player has been added!");
                    } else {
                        System.out.println("Player is already in given team");
                    }
                }
            }
        }
        System.out.println("\n b ---> Back to Main Menu");
    }

    //EFFECTS: saves the current league
    private void saveLeague() {
        try {
            jsonWriter.open();
            jsonWriter.write(myLeague);
            jsonWriter.close();
            System.out.println("Saved" + " " + myLeague.getLeagueName() + " " + "to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file: " + JSON_STORE);
        }
    }

    //EFFECTS: loads the league from the saved file
    private void loadLeague() {
        try {
            System.out.println("Starting Load");
            myLeague = jsonReader.read();
            existingTeams = myLeague.teams;
            existingPlayers = myLeague.leaguePlayers;
            System.out.println("Loaded" + " " + myLeague.getLeagueName() + " " + "From" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to load from file: " + JSON_STORE);
        }
    }



}


