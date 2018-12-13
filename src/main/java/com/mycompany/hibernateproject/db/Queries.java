package com.mycompany.hibernateproject.db;

import com.mycompany.hibernateproject.db.DatabaseManager;
import com.mycompany.hibernateproject.models.Player;
import com.mycompany.hibernateproject.models.Team;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class Queries {

    static EntityManager entityManager = DatabaseManager.entityManagerFactory.createEntityManager();

    public static List<Player> getPlayersByPage(int pagenr) {
        //calculate total number
        Query queryTotal = entityManager.createQuery
                ("SELECT count(p) from Player p");
        long countResult = (long) queryTotal.getSingleResult();

        //create query
        Query query = entityManager.createQuery("SELECT p FROM Player p");
        //set pageSize
        int pageSize = 10;
        //calculate number of pages
        int pageNumber = (int) ((countResult / pageSize) + 1);

        if (pagenr > pageNumber) pagenr = pageNumber;
        query.setFirstResult((pagenr-1) * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public static List<Team> getTeamsByChampionships(int championships) {
        Query query = entityManager.createQuery("SELECT t FROM Team t WHERE t.championships = :championships");
        return query.setParameter("championships", championships).getResultList();
    }

    public static List<Player> getPlayersByTeam(int teamID) {
        Query query = entityManager.createQuery("SELECT p FROM Player p WHERE p.team.id = :teamID");
        return query.setParameter("teamID", teamID).getResultList();
    }

    public static List<Player> getPlayersByTeam(String teamName) {
        Query teamQuery = entityManager.createQuery("SELECT t.id FROM Team t WHERE t.name = :teamName");
        int teamID = teamQuery.setParameter("teamName", teamName).getFirstResult();
        Query playersQuery = entityManager.createQuery("SELECT p from Player p where p.team = :teamID");
        return playersQuery.setParameter("teamID", teamID).getResultList();
    }

    public static List<Team> getTeamsByCity(int cityID) {
        Query query = entityManager.createQuery("SELECT c FROM City c WHERE c.id = :cityID");
        return query.setParameter("cityID", cityID).getResultList();
    }

    public static List<Team> getTeamsByCity(String cityName) {
        Query teamQuery = entityManager.createQuery("SELECT c.id FROM City c WHERE c.name = :cityName");
        int cityID = teamQuery.setParameter("cityName", cityName).getFirstResult();
        Query playersQuery = entityManager.createQuery("SELECT t from Team t where t.city = :cityID");
        return playersQuery.setParameter("cityID", cityID).getResultList();
    }

    public static List<Player> getPlayersByPpg(double ppg) {
        Query query = entityManager.createQuery("SELECT p FROM Player p WHERE p.ppg >= :ppg");
        return query.setParameter("ppg", ppg).getResultList();
    }
}