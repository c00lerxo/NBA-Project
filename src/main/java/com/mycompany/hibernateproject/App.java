package com.mycompany.hibernateproject;

import com.mycompany.hibernateproject.db.DatabaseManager;
import com.mycompany.hibernateproject.jsonxml.JSONXMLHandler;
import com.mycompany.hibernateproject.models.*;
import org.hibernate.metamodel.relational.Database;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.util.HashSet;

public class App  {

    public static void main(String[] args) throws IOException {
        System.out.println("Start");

            // test data
            Coach coach = new Coach("Phil Jackson", "lol", 3);
            Team team = new Team("Chicago Bulls", 4);
            Player player = new Player("Michael Jordan", LocalDate.now(), "American", 20, 20, 20);
            Arena arena = new Arena("Chicago Arena", "Worst one");
            City city = new City("Chicago", "Best one");

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

            DatabaseManager.addToDatabase(team);
            DatabaseManager.addToDatabase(player);
            DatabaseManager.addToDatabase(coach);
            DatabaseManager.addToDatabase(arena);
            DatabaseManager.addToDatabase(city);

            JSONXMLHandler handler = new JSONXMLHandler();

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

        /*    Player player3 = new Player("Seweryn Zachwieja", LocalDate.now(), "Polish", 40, 40, 40);
            DatabaseManager.addToDatabase(player3);

            Player player3Db = DatabaseManager.entityManager.find(Player.class, player3.getId());

            handler.serialize(player3Db, "json");*/
/*
            Player player = new Player("Michael Jordan", LocalDate.now(), "American", 20, 20, 20);
            DatabaseManager.addToDatabase(player);

            Player examplePlayer = DatabaseManager.entityManager.find(Player.class, 1);
            JSONXMLHandler handler = new JSONXMLHandler();
            handler.serialize(examplePlayer, "json");*/


            DatabaseManager.close();

    }
}
