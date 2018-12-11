package com.mycompany.hibernateproject;

import com.mycompany.hibernateproject.models.*;
import org.joda.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;

public class App  {
    public static void main(String[] args) {
        System.out.println("Start");

        EntityManager entityManager = null;
        EntityManagerFactory entityManagerFactory = null;

        try {
            //taka nazwa jak w persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");
            //utworz entityManagera
            entityManager = entityManagerFactory.createEntityManager();

            //rozpocznij transakcje
            entityManager.getTransaction().begin();

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
            HashSet<Team> teams = new HashSet<>();
            teams.add(team);


            coach.setTeam(team);

            arena.setCity(city);
            arena.setTeam(team);
            HashSet<Arena> arenas = new HashSet<>();
            arenas.add(arena);

            city.setArenas(arenas);
            city.setTeams(teams);


            entityManager.persist(player);
            entityManager.persist(team);
            entityManager.persist(city);
            entityManager.persist(arena);
            entityManager.persist(coach);

            Player playerDb = entityManager.find(Player.class, 1);
            Team teamDb = entityManager.find(Team.class, 1);
            City cityDb = entityManager.find(City.class, 1);
            Arena arenaDb = entityManager.find(Arena.class, 1);
            Coach coachDb = entityManager.find(Coach.class, 1);

            // Test

            System.out.println("***Player from DB***");
            System.out.println("Name: " + playerDb.getName() + "\n" + playerDb.getTeam());

            System.out.println("***Team from DB***");
            System.out.println("Team: " + teamDb.getName() + "\n" + teamDb.getCoach());

            System.out.println("***Coach from DB***");
            System.out.println("Coach: " + coachDb.getName() + "\n" + coachDb.getExperience());

            System.out.println("***Arena from DB***");
            System.out.println("Arena: " + arenaDb.getName() + "\n" + arenaDb.getDescription());

            System.out.println("***City from DB***");
            System.out.println("City: " + cityDb.getName() + "\n" + cityDb.getDescription());

            //zakoncz transakcje
            entityManager.getTransaction().commit();

            System.out.println("Done");

            entityManager.close();

        } catch (Throwable e) {
            System.err.println("Creation failed. " + e);
        } finally {
            entityManagerFactory.close();
        }
    }
}
