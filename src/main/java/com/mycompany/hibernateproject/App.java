package com.mycompany.hibernateproject;

import com.mycompany.hibernateproject.models.Coach;
import com.mycompany.hibernateproject.models.Player;
import com.mycompany.hibernateproject.models.Team;
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

            Coach coach = new Coach("Phil Jackson", "lol", 3);
            Team team = new Team("Chicago Bulls", 4);
            Player player = new Player("Michael Jordan", LocalDate.now(), "American", 20, 20, 20);

            player.setTeam(team);
            HashSet<Player> players = new HashSet<Player>();
            players.add(player);
            team.setPlayers(players);
            team.setCoach(coach);
            coach.setTeam(team);

            entityManager.persist(player);

            Player player1 = entityManager.find(Player.class, player.getId());

            entityManager.remove(player);

            System.out.println(player.getApg());
            System.out.println("Employee " + player1.getId() + " " + player1.getName() + player1.getTeam().getName());


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
