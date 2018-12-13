package com.mycompany.hibernateproject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mycompany.hibernateproject.db.DatabaseManager;
import com.mycompany.hibernateproject.jsonxml.JSONXMLHandler;
import com.mycompany.hibernateproject.models.*;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class ReadFromDBTest {
    private JSONXMLHandler handler = new JSONXMLHandler();
    private static final double DELTA = 1e-15;
    private final String xmlPath = "src/main/resources/xml";
    private final String jsonPath = "src/main/resources/json";
    private final String serialized = "/serialized/";

    @Before
    public void setup() throws IOException {
        Coach coach = new Coach("Phil Jackson", "lol", 3);
        Team team = new Team("Chicago Bulls", 4);
        Player player = new Player("Michael Jordan", LocalDate.now(), "American", 20, 20, 20);
        Arena arena = new Arena("Chicago Arena", "Worst one");
        City city = new City("Chicago", "Best one");
        Player player2 = new Player("Seweryn Zachwieja", LocalDate.now(), "Polish", 40, 40, 40);

        player.setTeam(team);
        HashSet<Player> players = new HashSet<Player>();
        players.add(player);

        team.setPlayers(players);
        team.setCoach(coach);
        team.setCity(city);
        team.setArena(arena);
        HashSet<Team> teams = new HashSet<>();
        teams.add(team);

        coach.setTeam(team);

        arena.setCity(city);
        arena.setTeam(team);
        HashSet<Arena> arenas = new HashSet<>();
        arenas.add(arena);

        city.setArenas(arenas);
        city.setTeams(teams);

        DatabaseManager.addToDatabase(player);
        DatabaseManager.addToDatabase(player2);
        DatabaseManager.addToDatabase(team);
        DatabaseManager.addToDatabase(coach);
        DatabaseManager.addToDatabase(arena);
        DatabaseManager.addToDatabase(city);

        Player player3Db = DatabaseManager.entityManager.find(Player.class, player2.getId());


        handler.serialize(player, "xml");
        handler.serialize(player, "json");
        handler.serialize(team, "xml");
        handler.serialize(team, "json");
        handler.serialize(city, "xml");
        handler.serialize(city, "json");
        handler.serialize(arena, "xml");
        handler.serialize(arena, "json");
        handler.serialize(coach, "json");
        handler.serialize(coach, "xml");



        handler.serialize(player3Db, "json");
        handler.serialize(player3Db, "xml");
    }


    @Test
    public void objectShouldBeSerializedToXml() throws IOException {
        assertTrue(isDirectoryValid(xmlPath + serialized, "xml"));
    }

    @Test
    public void objectShouldBeSerializedToJson() throws IOException {
        assertTrue(isDirectoryValid(jsonPath + serialized, "json"));
    }

    private static boolean isValid(final String content, final String type) throws IOException {
        boolean valid = true;
        try{
            ObjectMapper mapper = new ObjectMapper();
            XmlMapper xMapper = new XmlMapper();
            if (type.equals("json"))
                mapper.readTree(content);
            else
                xMapper.readTree(content);
        } catch(JsonProcessingException e){
            valid = false;
        }
        return valid;
    }

    private static boolean isDirectoryValid(final String myDirectoryPath, final String type) throws IOException {
        File dir = new File(myDirectoryPath);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                Scanner sc = new Scanner(child);
                String content = "";
                while(sc.hasNextLine()) {
                    content += sc.nextLine();
                }
                sc.close();
                return isValid(content, type);
            }
        }
        return false;
    }
}
