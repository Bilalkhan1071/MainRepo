package ui;

import exception.TeamFullException;
import model.League;
import model.Player;
import model.Team;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class MainGui implements ActionListener {
    //All panels will be linked here
    // this panel will be called in the FantasyApp
    //Panel/Frame Section:
    private JSplitPane upperContentPane = new JSplitPane();
    private JSplitPane mainSplitPane = new JSplitPane();
    private JPanel teamPanel = new JPanel();
    private JPanel playerPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JFrame frame = new JFrame();
    //Button Section:
    private JButton saveButton;
    private JButton loadButton;
    private JButton createTeamButton;
    private JButton showLeagueButton;
    private JButton finishTeamCreateButton;
    private JButton addPlayerButton;
    private JButton followButton;
    private JButton finishAddPlayerButton;
    //GUI Lists:
    public static List<Player> guiPlayers;
    public static List<Team> guiTeams;
    //Table Models:
    private TeamTableModel teamTableModel;
    private JTable teamTable;
    private PlayerTableModel playerTableModel;
    private JTable playerTable;
    //Text Fields/Labels :
    private JLabel teamNameLabel;
    private JTextField teamNameText;
    private JLabel teamAdded;
    private JLabel playerAdded;
    private JTextField enterPlayerNameText;
    private JTextField enterTeamNameText;
    //Initialization;
    private League myLeague = new League();
    private static final String JSON_STORE = "./data/fantasyApp.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    //Forwards
    private Player messi = new Player("Messi", "Barcelona");
    private Player ronaldo = new Player("Ronaldo", "Juventus");
    private Player neymar = new Player("Neymar", "PSG");
    private Player mbappe = new Player("Mbappe", "PSG");
    private Player lewa = new Player("Lewandowski", "Bayern");
    private Player salah = new Player("Salah", "Liverpool");
    private Player suarez = new Player("Suarez", "Atletico");
    private Player sterling = new Player("Sterling", "Man City");
    //Midfielders
    private Player frenkie = new Player("Frenkie", "Barcelona");
    private Player deBruyne = new Player("De Bruyne", "Man City");
    private Player arthur = new Player("Arthur", "Juventus");
    private Player kimmich = new Player("Kimmich", "Bayern");
    private Player grealish = new Player("Grealish", "Aston Villa");
    //Defenders
    private Player ramos = new Player("Ramos", "Madrid");
    private Player alphonso = new Player("Alphonso", "Bayern");
    private Player virgil = new Player("Virgil", "Liverpool");
    private Player trent = new Player("Trent", "Liverpool");
    private Player telles = new Player("Telles", "Man United");
    private Player lenglet = new Player("Lenglet", "Barcelona");
    private Player upamecano = new Player("Upamecano", "Liepzig");
    private Player walker = new Player("Walker", "Man City");
    //Keepers
    private Player mendy = new Player("Mendy", "Chelsea");
    private Player aayush = new Player("Aayush", "Chelsea");
    private Player mats = new Player("Ter Stegan", "Barcelona");
    private Player neuer = new Player("Neuer", "Bayern");


    public MainGui() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                init();
                createAndShowGUI();
            }
        });
    }

    //EFFECTS: initializes the information needed for the GUI
    public void init() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        guiPlayers = myLeague.leaguePlayers;
        guiTeams = myLeague.teams;
        addForwards();
        addMidfielders();
        addDefenders();
        addKeepers();
    }

    //EFFECTS: adds forwards to the list of players
    public void addForwards() {
        guiPlayers.add(messi);
        guiPlayers.add(ronaldo);
        guiPlayers.add(neymar);
        guiPlayers.add(mbappe);
        guiPlayers.add(lewa);
        guiPlayers.add(salah);
        guiPlayers.add(suarez);
        guiPlayers.add(sterling);
    }

    //EFFECTS: adds midfielders to the list of players
    public void addMidfielders() {
        guiPlayers.add(frenkie);
        guiPlayers.add(deBruyne);
        guiPlayers.add(arthur);
        guiPlayers.add(kimmich);
        guiPlayers.add(grealish);
    }

    //EFFECTS: adds defenders to the list of players
    public void addDefenders() {
        guiPlayers.add(ramos);
        guiPlayers.add(alphonso);
        guiPlayers.add(virgil);
        guiPlayers.add(trent);
        guiPlayers.add(telles);
        guiPlayers.add(lenglet);
        guiPlayers.add(upamecano);
        guiPlayers.add(walker);
    }

    //EFFECTS: adds goalkeepers to the list of players
    public void addKeepers() {
        guiPlayers.add(mendy);
        guiPlayers.add(aayush);
        guiPlayers.add(mats);
        guiPlayers.add(neuer);
    }

    //EFFECTS: sets general frame of GUI
    public void createAndShowGUI() {
        frame.setPreferredSize(new Dimension(600, 600));
        setMainSplitPane();
        frame.add(mainSplitPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Fantasy Football");
        frame.pack();
        upperContentPane.setDividerLocation(0.5);
        frame.setVisible(true);
    }

    //MODIFIES: GUI
    //EFFECTS: Splits the main panel into top and bottom sections
    public void setMainSplitPane() {
        mainSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        mainSplitPane.setOneTouchExpandable(false);
        mainSplitPane.setDividerLocation(400);
        setUpperContentPane();
        setButtonPanel();
        mainSplitPane.setTopComponent(upperContentPane);
        mainSplitPane.setBottomComponent(buttonPanel);
    }

    //MODIFIES: GUI
    //EFFECTS: sets button panel including all the buttons
    public void setButtonPanel() {
        buttonPanel.setBackground(Color.pink);
        buttonPanel.setLayout(new GridLayout(0, 2));
        saveButton = new JButton("Save League");
        saveButton.addActionListener(this::actionPerformed);
        loadButton = new JButton("Load League");
        loadButton.addActionListener(this::actionPerformed);
        createTeamButton = new JButton("Create Team");
        createTeamButton.addActionListener(this::actionPerformed);
        showLeagueButton = new JButton("Show League");
        showLeagueButton.addActionListener(this::actionPerformed);
        addPlayerButton = new JButton("Add Player to Team");
        addPlayerButton.addActionListener(this::actionPerformed);
        followButton = new JButton("Link Up Fam");
        followButton.addActionListener(this::actionPerformed);
        buttonPanel.add(createTeamButton);
        buttonPanel.add(showLeagueButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(addPlayerButton);
        buttonPanel.add(followButton);
    }

    //MODIFIES: GUI
    //EFFECTS: sets upper half of content pane
    public void setUpperContentPane() {
        upperContentPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        upperContentPane.setOneTouchExpandable(false);
        setTeamPanel();
        setPlayerPanel();
        upperContentPane.setLeftComponent(teamPanel);
        upperContentPane.setRightComponent(playerPanel);
    }

    //MODIFIES: GUI
    //EFFECTS: panel where team information will be
    public void setTeamPanel() {
        teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.Y_AXIS));
        teamPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
        teamTableModel = new TeamTableModel(myLeague);
        teamTable = new JTable(teamTableModel);
        teamTable.setBackground(Color.pink);
        teamTable.setFillsViewportHeight(true);
        teamPanel.add(new JScrollPane(teamTable));
    }

    //MODIFIES: GUI
    //EFFECTS: panel where player information will be
    public void setPlayerPanel() {
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
        playerTableModel = new PlayerTableModel(myLeague);
        playerTable = new JTable(playerTableModel);
        playerTable.setBackground(Color.PINK);
        playerTable.setFillsViewportHeight(true);
        playerPanel.add(new JScrollPane(playerTable));
    }

    //MODIFIES: GUI
    //EFFECTS: shows a league table on button click
    public void pressShowLeagueButton() {
        JDialog dialog = new JDialog();
        dialog.setTitle("League Standings");
        JPanel leaguePanel = new JPanel();
        leaguePanel.setBackground(Color.green);
        LeagueTableModel leagueTableModel = new LeagueTableModel(guiTeams);
        JTable leagueTable = new JTable(leagueTableModel);
        leagueTable.setFillsViewportHeight(true);
        leaguePanel.add(new JScrollPane(leagueTable));
        dialog.add(leaguePanel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        dialog.pack();
    }

    //REQUIRES: team to not already be in the league
    //MODIFIES: teamPanel
    //EFFECTS: creates pop up where user can create team
    public void pressCreateTeamButton() {
        JFrame createTeamDialog = new JFrame();
        createTeamDialog.setPreferredSize(new Dimension(300, 200));
        createTeamDialog.setTitle("Create Team");
        JPanel createTeamPanel = new JPanel();
        teamNameLabel = new JLabel("Enter Team Name:");
        teamNameLabel.setBounds(20, 40, 80, 30);
        createTeamPanel.add(teamNameLabel);
        teamNameText = new JTextField(20);
        teamNameText.setBounds(100, 40, 165, 25);
        finishTeamCreateButton = new JButton("Create Team");
        finishTeamCreateButton.setBounds(60, 100, 80, 25);
        teamAdded = new JLabel("");
        finishTeamCreateButton.addActionListener(this::actionPerformed);
        createTeamPanel.add(teamNameText);
        createTeamPanel.add(finishTeamCreateButton);
        createTeamPanel.add(teamAdded);
        createTeamDialog.add(createTeamPanel);
        createTeamDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        createTeamDialog.setVisible(true);
        createTeamDialog.pack();
    }

    //REQUIRES: team to exist and player to not already be in team
    //MODIFIES: teamPanel
    //EFFECTS: creates pop up where user can add player to team
    public void pressAddPlayerButton() {
        JFrame addPlayerDialog = new JFrame("Add Player to Team");
        addPlayerDialog.setPreferredSize(new Dimension(300, 200));
        JPanel addPlayerPanel = new JPanel();
        JLabel enterPlayerNameLabel = new JLabel("Enter Player Name:");
        addPlayerPanel.add(enterPlayerNameLabel);
        enterPlayerNameLabel.setBounds(20, 40, 80, 30);
        JLabel enterTeamNameLabel = new JLabel("Enter Team Name:");
        addPlayerPanel.add(enterTeamNameLabel);
        enterTeamNameLabel.setBounds(20, 60, 80, 30);
        setAddPlayerTextFields();
        addLabelsAndText(addPlayerPanel, enterPlayerNameLabel, enterTeamNameLabel);
        finishAddPlayerButton = new JButton("Add Player");
        finishAddPlayerButton.addActionListener(this::actionPerformed);
        finishAddPlayerButton.setBounds(20, 100, 165, 25);
        addPlayerPanel.add(finishAddPlayerButton);
        playerAdded = new JLabel("");
        addPlayerPanel.add(playerAdded);
        addPlayerDialog.add(addPlayerPanel);
        addPlayerDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addPlayerDialog.setVisible(true);
        addPlayerDialog.pack();
    }

    //EFFECTS: helper for pressAddPlayerButton()
    private void addLabelsAndText(JPanel addPlayerPanel, JLabel enterPlayerNameLabel, JLabel enterTeamNameLabel) {
        addPlayerPanel.add(enterPlayerNameLabel);
        addPlayerPanel.add(enterPlayerNameText);
        addPlayerPanel.add(enterTeamNameLabel);
        addPlayerPanel.add(enterTeamNameText);
    }

    //EFFECTS: helper for pressAddPlayerButton()
    private void setAddPlayerTextFields() {
        enterPlayerNameText = new JTextField(20);
        enterTeamNameText = new JTextField(20);
    }

    //EFFECTS: saves the GUI as is
    public void saveGui() {
        try {
            jsonWriter.open();
            jsonWriter.write(myLeague);
            jsonWriter.close();
            System.out.println("Saved" + " " + myLeague.getLeagueName() + " " + "to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file: " + JSON_STORE);
        }
    }

    //EFFECTS: loads a previously saved GUI
    public void loadGui() {
        try {
            System.out.println("Starting Load");
            myLeague = jsonReader.read();
            guiTeams = myLeague.teams;
            guiPlayers = myLeague.leaguePlayers;
            teamTableModel.updateLeague(myLeague);
            playerTableModel.updateLeague(myLeague);
            System.out.println("Loaded" + " " + myLeague.getLeagueName() + " " + "From" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to load from file: " + JSON_STORE);
        }
    }

    //EFFECTS: creates a pop up showing my insta link
    public void pressFollowButton() {
        JFrame followDialog = new JFrame();
        followDialog.setPreferredSize(new Dimension(600,100));
        followDialog.setTitle("Follow me if you want, if not its cool idc :(");
        JPanel followPanel = new JPanel();
        JLabel followLabel = new JLabel("Instagram: bilalkhan1071");
        followPanel.add(followLabel);
        followDialog.add(followPanel);
        followDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        followDialog.setVisible(true);
        followDialog.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        buttonOptions(source);
    }

    private void buttonOptions(Object source) {
        if (source == showLeagueButton) {
            pressShowLeagueButton();
            playSound("leagueButton.wav");
        } else if (source == createTeamButton) {
            pressCreateTeamButton();
            playSound("createTeamButton.wav");
        } else if (source == finishTeamCreateButton) {
            pressFinishTeamCreateButton();
        } else if (source == addPlayerButton) {
            pressAddPlayerButton();
            playSound("addPlayerButton.wav");
        } else if (source == finishAddPlayerButton) {
            pressFinishAddPlayerButton();
            playerAdded.setText("Player Added!");
        } else if (source == saveButton) {
            saveGui();
            playSound("saveLeagueButton.wav");
        } else if (source == loadButton) {
            loadGui();
            playSound("loadLeagueButton.wav");
        } else if (source == followButton) {
            pressFollowButton();
        }
    }

    //EFFECTS: finalizes adding player to team
    private void pressFinishAddPlayerButton() {
        String playerText = enterPlayerNameText.getText();
        String teamText = enterTeamNameText.getText();
        for (Player plyr : guiPlayers) {
            for (Team tms : guiTeams) {
                if ((playerText.equals(plyr.getName())) && (teamText.equals(tms.getTeamName()))) {
                    if (!plyr.isInGivenTeam(tms)) {
                        try {
                            tms.addPlayer(plyr);
                        } catch (TeamFullException e) {
                            System.out.println("Team is already full");
                        }
                        tms.teamScore += plyr.getPoints();
                    }
                }
            }
        }
    }

    //EFFECTS: finalizes creation of team
    private void pressFinishTeamCreateButton() {
        String userText = teamNameText.getText();
        guiTeams.add(new Team(userText));
        teamAdded.setText("Team Created!");
    }

    //EFFECTS: sound function for button clicks
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
