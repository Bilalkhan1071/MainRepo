package persistance;


import model.League;
import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Cite: Sample JSON Application
public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fileNotExisting.json");
        try {
            League lg = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //expected
        }
    }

    @Test
    void testReaderEmptyLeague() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLeague.json");
        try {
            League lg = reader.read();
            assertEquals("The League", lg.getLeagueName());
            assertEquals(0, lg.teams.size());
        } catch (IOException e) {
            fail("Couldn't read file");
        }
    }

    @Test
    void testReaderGeneralLeague() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLeague.json");
        try {
            League lg = reader.read();
            assertEquals("The League", lg.getLeagueName());
            assertEquals(1, lg.teams.size());
            List<Team> checkTeams = lg.teams;
            Team firstTeam = checkTeams.get(0);
            assertEquals("A Team", firstTeam.getTeamName());
            assertEquals(1, firstTeam.getPlayers().size());
            assertEquals(5,firstTeam.getTeamScore());
            Player p1 = firstTeam.players.get(0);
            assertEquals(p1.getName(), lg.leaguePlayers.get(0).getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
