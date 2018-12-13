package com.mycompany.hibernateproject;

import static org.junit.Assert.assertEquals;

import com.mycompany.hibernateproject.db.DatabaseManager;
import com.mycompany.hibernateproject.jsonxml.JSONXMLHandler;
import com.mycompany.hibernateproject.models.*;
import org.junit.Test;


import java.io.File;
import java.io.IOException;

public class SaveToDBTest
{
    private JSONXMLHandler handler = new JSONXMLHandler();
    private static final double DELTA = 1e-15;
    private final String xmlPath = "src/main/resources/xml";
    private final String jsonPath = "src/main/resources/json";
    private final String tbs = "/tobesaved/";
    private final String serialized = "/serialized/";

    @Test
    public void xmlFileShouldBeSavedToDb() throws IOException {
        DatabaseManager.entityManager.getTransaction().begin();

        Player player = handler.deserializePlayerXml(new File(xmlPath + tbs + "0.xml"));
        DatabaseManager.entityManager.persist(player);

        Team team = handler.deserializeTeamXml(new File(xmlPath + tbs + "1.xml"));
        DatabaseManager.entityManager.persist(team);

        Coach coach = handler.deserializeCoachXml(new File(xmlPath + tbs + "2.xml"));
        DatabaseManager.entityManager.persist(coach);

        City city = handler.deserializeCityXml(new File(xmlPath + tbs + "3.xml"));
        DatabaseManager.entityManager.persist(city);

       // Arena arena = handler.deserializeArenaXml(new File(xmlPath + tbs + "4.xml"));
       // DatabaseManager.entityManager.persist(arena);

        DatabaseManager.entityManager.getTransaction().commit();

        Player playerDb = DatabaseManager.entityManager.find(Player.class, player.getId());
        Team teamDb = DatabaseManager.entityManager.find(Team.class, team.getId());
        Coach coachDb = DatabaseManager.entityManager.find(Coach.class, coach.getId());
        City cityDb = DatabaseManager.entityManager.find(City.class, city.getId());
        //Arena arenaDb = DatabaseManager.entityManager.find(Arena.class, arena.getId());

        assertEquals(player.getId(), playerDb.getId());
        assertEquals(player.getName(), playerDb.getName());
        assertEquals(player.getBirthday(), playerDb.getBirthday());
        assertEquals(player.getTeam(), playerDb.getTeam());
        assertEquals(player.getNationality(), playerDb.getNationality());
        assertEquals(player.getPpg(), playerDb.getPpg(), DELTA);
        assertEquals(player.getApg(), playerDb.getApg(), DELTA);
        assertEquals(player.getRpg(), playerDb.getRpg(), DELTA);

        assertEquals(team.getId(), teamDb.getId());
        assertEquals(team.getName(), teamDb.getName());
        assertEquals(team.getChampionships(), teamDb.getChampionships());
        assertEquals(team.getPlayers(), teamDb.getPlayers());
        assertEquals(team.getCoach(), teamDb.getCoach());
        assertEquals(team.getArena(), teamDb.getArena());
        assertEquals(team.getCity(), teamDb.getCity());

        assertEquals(coach.getId(), coachDb.getId());
        assertEquals(coach.getName(), coachDb.getName());
        assertEquals(coach.getChampionships(), coachDb.getChampionships());
        assertEquals(coach.getExperience(), coachDb.getExperience());
        assertEquals(coach.getChampionships(), coachDb.getChampionships());
        assertEquals(coach.getTeam(), coachDb.getTeam());

        assertEquals(city.getId(), cityDb.getId());
        assertEquals(city.getName(), cityDb.getName());
        assertEquals(city.getArenas(), cityDb.getArenas());
        assertEquals(city.getTeams(), cityDb.getTeams());
        assertEquals(city.getDescription(), cityDb.getDescription());

       /* assertEquals(arena.getId(), arenaDb.getId());
        assertEquals(arena.getName(), arenaDb.getName());
        assertEquals(arena.getDescription(), arenaDb.getDescription());
        assertEquals(arena.getTeam(), arenaDb.getTeam());
        assertEquals(arena.getCity(), arenaDb.getCity());
*/    }

    @Test
    public void jsonFileShouldBeSavedToDb() throws IOException {
        Player player = handler.deserializePlayerJson(new File(jsonPath + tbs + "0.json"));
        DatabaseManager.addToDatabase(player);
        Player playerDb = DatabaseManager.entityManager.find(Player.class, player.getId());

        Team team = handler.deserializeTeamJson(new File(jsonPath + tbs + "1.json"));
        DatabaseManager.addToDatabase(team);
        Team teamDb = DatabaseManager.entityManager.find(Team.class, team.getId());

        Coach coach = handler.deserializeCoachJson(new File(jsonPath + tbs + "2.json"));
        DatabaseManager.addToDatabase(coach);
        Coach coachDb = DatabaseManager.entityManager.find(Coach.class, coach.getId());

        City city = handler.deserializeCityJson(new File(jsonPath + tbs + "3.json"));
        DatabaseManager.addToDatabase(city);
        City cityDb = DatabaseManager.entityManager.find(City.class, city.getId());

       /* Arena arena = handler.deserializeArenaJson(new File(jsonPath + tbs + "4.json"));
        DatabaseManager.addToDatabase(arena);
        Arena arenaDb = DatabaseManager.entityManager.find(Arena.class, arena.getId());*/


        assertEquals(player.getId(), playerDb.getId());
        assertEquals(player.getName(), playerDb.getName());
        assertEquals(player.getBirthday(), playerDb.getBirthday());
        assertEquals(player.getTeam(), playerDb.getTeam());
        assertEquals(player.getNationality(), playerDb.getNationality());
        assertEquals(player.getPpg(), playerDb.getPpg(), DELTA);
        assertEquals(player.getApg(), playerDb.getApg(), DELTA);
        assertEquals(player.getRpg(), playerDb.getRpg(), DELTA);

        assertEquals(team.getId(), teamDb.getId());
        assertEquals(team.getName(), teamDb.getName());
        assertEquals(team.getChampionships(), teamDb.getChampionships());
        assertEquals(team.getPlayers(), teamDb.getPlayers());
        assertEquals(team.getCoach(), teamDb.getCoach());
        assertEquals(team.getArena(), teamDb.getArena());
        assertEquals(team.getCity(), teamDb.getCity());

        assertEquals(coach.getId(), coachDb.getId());
        assertEquals(coach.getName(), coachDb.getName());
        assertEquals(coach.getChampionships(), coachDb.getChampionships());
        assertEquals(coach.getExperience(), coachDb.getExperience());
        assertEquals(coach.getChampionships(), coachDb.getChampionships());
        assertEquals(coach.getTeam(), coachDb.getTeam());

        assertEquals(city.getId(), cityDb.getId());
        assertEquals(city.getName(), cityDb.getName());
        assertEquals(city.getArenas(), cityDb.getArenas());
        assertEquals(city.getTeams(), cityDb.getTeams());
        assertEquals(city.getDescription(), cityDb.getDescription());

        /*assertEquals(arena.getId(), arenaDb.getId());
        assertEquals(arena.getName(), arenaDb.getName());
        assertEquals(arena.getDescription(), arenaDb.getDescription());
        assertEquals(arena.getTeam(), arenaDb.getTeam());
        assertEquals(arena.getCity(), arenaDb.getCity());*/
    }
}
