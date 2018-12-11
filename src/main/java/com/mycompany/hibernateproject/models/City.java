package com.mycompany.hibernateproject.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private Set<Team> teams = new HashSet<Team>();

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private Set<Arena> arenas = new HashSet<Arena>();

    public City() {}

    public City(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public Set<Arena> getArenas() {
        return arenas;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public void setArenas(Set<Arena> arenas) {
        this.arenas = arenas;
    }
}
