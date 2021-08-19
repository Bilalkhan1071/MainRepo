package model;

import exception.TeamFullException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyModelTest {
    private Team testTeam;

    @BeforeEach
    void runBefore() {
        testTeam = new Team("My Team");
    }

    @Test
    void testSetters() {
        Player p1 = new Player("BK","IFJ");
        assertEquals("BK", p1.getName());
        p1.setAssists(2);
        assertEquals(2,p1.getAssists());
        p1.setGoals(3);
        assertEquals(3,p1.getGoals());
        p1.setName("Bilal");
        assertEquals("Bilal", p1.getName());
    }

    @Test
    void toJsonPlayer() {
        Player jsonPlayer = new Player("JSON", "J");
        assertTrue(jsonPlayer.toJson() instanceof JSONObject);
    }

    @Test
    void toJsonTeam() {
        try {
            testTeam.addPlayer(new Player("Json", "J"));
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        assertTrue(testTeam.toJson() instanceof JSONObject);
    }

    @Test
    void toJsonLeague() {
        League jsonLeague = new League();
        try {
            testTeam.addPlayer(new Player("json", "j"));
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        jsonLeague.leaguePlayers.add(new Player("Player", "League"));
        jsonLeague.addTeam(testTeam);
        assertTrue(jsonLeague.toJson() instanceof JSONObject);
    }

    @Test
    void testAddOnePlayer() {
        assertEquals(0,testTeam.getTeamSize());
        assertEquals("My Team", testTeam.getTeamName());

        Player p1 = new Player("Auba","Arsenal");
        assertEquals("Auba", p1.getName());
        assertEquals("Arsenal", p1.getClub());

        try {
            assertTrue(testTeam.addPlayer(p1));
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        assertEquals(1, testTeam.getTeamSize());
    }

    @Test
    void testAddMultiplePlayers() {
        assertEquals(0, testTeam.getTeamSize());

        Player p1 = new Player("Messi", "Barca");
        Player p2 = new Player("Neymar", "PSG");
        assertEquals("Messi", p1.getName());
        assertEquals("PSG", p2.getClub());

        try {
            assertTrue(testTeam.addPlayer(p1));
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        assertEquals(1, testTeam.getTeamSize());
        assertTrue(p1.isPartOfTeam());

        try {
            assertTrue(testTeam.addPlayer(p2));
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        assertEquals(2, testTeam.getTeamSize());
        assertTrue(p2.isInGivenTeam(testTeam));
    }

    @Test
    void testAddPlayerFullTeam() {
        assertEquals(0, testTeam.getTeamSize());

        Player p1 = new Player("P1", "Team 1");
        Player p2 = new Player("P2", "Team 2");
        Player p3 = new Player("P3", "Team 3");
        Player p4 = new Player("P4", "Team 4");
        Player p5 = new Player("P5", "Team 5");
        Player p6 = new Player("P6", "Team 6");
        Player p7 = new Player("P7", "Team 7");
        Player p8 = new Player("P8", "Team 8");
        Player p9 = new Player("P9", "Team 9");
        Player p10 = new Player("P10", "Team 10");
        Player p11 = new Player("P11", "Team 11");

        try {
            testTeam.addPlayer(p1);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        try {
            testTeam.addPlayer(p2);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        try {
            testTeam.addPlayer(p3);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        try {
            testTeam.addPlayer(p4);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        try {
            testTeam.addPlayer(p5);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        try {
            testTeam.addPlayer(p6);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        try {
            testTeam.addPlayer(p7);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        try {
            testTeam.addPlayer(p8);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        try {
            testTeam.addPlayer(p9);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        try {
            testTeam.addPlayer(p10);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        try {
            testTeam.addPlayer(p11);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }

        assertEquals(11, testTeam.getTeamSize());

        Player p12 = new Player("P12", "Won't Enter");
        try {
            assertFalse(testTeam.addPlayer(p12));
            fail("Exception expected");
        } catch (TeamFullException e) {
            System.out.println("This error should be printed");
        }

    }

    @Test
    void testRemoveOnePlayer() {
        assertEquals(0,testTeam.getTeamSize());

        Player p1 = new Player("Auba","Arsenal");

        try {
            testTeam.addPlayer(p1);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        assertEquals(1, testTeam.getTeamSize());

        testTeam.removePlayer(p1);
        assertEquals(0, testTeam.getTeamSize());
    }

    @Test
    void testRemoveMultiplePlayers() {
        assertEquals(0, testTeam.getTeamSize());

        Player p1 = new Player("Messi", "Barca");
        Player p2 = new Player("Neymar", "PSG");
        Player p3 = new Player("Ronaldo", "Juve");
        assertEquals("Messi", p1.getName());
        assertEquals("PSG", p2.getClub());

        try {
            testTeam.addPlayer(p1);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        assertEquals(1, testTeam.getTeamSize());

        try {
            testTeam.addPlayer(p2);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        assertEquals(2, testTeam.getTeamSize());

        try {
            testTeam.addPlayer(p3);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        assertEquals(3,testTeam.getTeamSize());

        testTeam.removePlayer(p2);
        assertEquals(2, testTeam.getTeamSize());
        assertTrue(testTeam.isInTeam(p1));
        assertTrue(testTeam.isInTeam(p3));
        assertFalse(testTeam.isInTeam(p2));
    }

    @Test
    void testScoreOneGoal() {
        Player p1 = new Player("Mbappe","PSG");
        p1.scoredGoal(1);
        assertEquals(1, p1.getGoals());
        assertEquals(3, p1.getPoints());
    }

    @Test
    void testScoreMultipleGoals() {
        Player p1 = new Player("Ibra", "Milan");
        p1.scoredGoal(2);
        assertEquals(2, p1.getGoals());
        assertEquals(6, p1.getPoints());

        p1.scoredGoal(3);
        assertEquals(5, p1.getGoals());
        assertEquals(15, p1.getPoints());
    }

    @Test
    void testMakeOneAssist() {
        Player p1 = new Player("Frenkie", "Barca");
        p1.madeAssist(1);
        assertEquals(1,p1.getAssists());
        assertEquals(1, p1.getPoints());
    }

    @Test
    void testMakeMultipleAssists() {
        Player p1 = new Player("Bruno", "Man U");
        p1.madeAssist(2);
        assertEquals(2, p1.getAssists());
        assertEquals(2, p1.getPoints());

        p1.madeAssist(3);
        assertEquals(5, p1.getAssists());
        assertEquals(5, p1.getPoints());
    }

    @Test
    void testMultipleGoalsAndAssists() {
        Player p1 = new Player("Messi", "Barca");
        p1.scoredGoal(3);
        assertEquals(3, p1.getGoals());
        assertEquals(9, p1.getPoints());

        p1.madeAssist(2);
        assertEquals(2,p1.getAssists());
        assertEquals(11, p1.getPoints());

        p1.scoredGoal(2);
        p1.madeAssist(3);
        assertEquals(5, p1.getGoals());
        assertEquals(5,p1.getAssists());
        assertEquals(20,p1.getPoints());
    }

    @Test
    void testGetTeamScore() {
        assertEquals(0, testTeam.getTeamSize());

        Player p1 = new Player("Messi", "Barca");
        Player p2 = new Player("Neymar", "PSG");
        Player p3 = new Player("Ronaldo", "Juve");

        try {
            testTeam.addPlayer(p1);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        try {
            testTeam.addPlayer(p2);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        try {
            testTeam.addPlayer(p3);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }

        p1.scoredGoalForTeam(2, testTeam);
        p1.madeAssistForTeam(3, testTeam);
        assertEquals(9, p1.getPoints());

        p2.scoredGoalForTeam(1, testTeam);
        p2.madeAssistForTeam(2, testTeam);
        assertEquals(5, p2.getPoints());

        p3.scoredGoalForTeam(3,testTeam);
        p3.madeAssistForTeam(1, testTeam);
        assertEquals(10, p3.getPoints());

        assertEquals(24, testTeam.getTeamScore());
    }

    @Test
    void testGetPlayerNamesInTeam() {
        assertEquals(0, testTeam.getTeamSize());

        Player p1 = new Player("Fekir", "Betis");
        Player p2 = new Player("Griezi", "Barca");
        Player p3 = new Player("Riqui", "Barca");

        try {
            testTeam.addPlayer(p1);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        try {
            testTeam.addPlayer(p2);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }
        try {
            testTeam.addPlayer(p3);
        } catch (TeamFullException e) {
            fail("Exception is not expected");
        }

        List<String> names = new ArrayList<>();
        String s1 = "Fekir";
        String s2 = "Griezi";
        String s3 = "Riqui";

        names.add(s1);
        names.add(s2);
        names.add(s3);

        assertEquals(names, testTeam.getPlayers());
    }

    @Test
    void testLeagueName() {
        League myLeague = new League();
        assertEquals("The League", myLeague.getLeagueName());
        Team t1 = new Team("MyTeam");
        myLeague.addTeam(t1);
        assertEquals(1,myLeague.teams.size());
    }

    @Test
    void addTeamToLeagueTwice() {
        League myLeague = new League();
        assertEquals(0,myLeague.teams.size());
        myLeague.addTeam(testTeam);
        assertEquals(1,myLeague.teams.size());

        myLeague.addTeam(testTeam);
        assertEquals(1,myLeague.teams.size());
    }

}