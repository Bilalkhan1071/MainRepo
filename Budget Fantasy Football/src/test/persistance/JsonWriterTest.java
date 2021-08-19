package persistance;

import model.League;
import model.Team;
import org.junit.jupiter.api.Test;


import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            League lg = new League();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLeague() {
        try {
            League lg = new League();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLeague.json");
            writer.open();
            writer.write(lg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLeague.json");
            lg = reader.read();
            assertEquals("The League", lg.getLeagueName());
            assertEquals(0, lg.teams.size());
        } catch (IOException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testWriterGeneralLeague() {
        try {
            League lg = new League();
            lg.addTeam(new Team("Team 1"));
            lg.addTeam(new Team("Team 2"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLeague.json");
            writer.open();
            writer.write(lg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLeague.json");
            lg = reader.read();
            assertEquals("The League", lg.getLeagueName());
            assertEquals(2, lg.teams.size());
        } catch (IOException e) {
            fail("Should not have been thrown");
        }
    }
}
