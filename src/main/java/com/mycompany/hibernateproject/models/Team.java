package com.mycompany.hibernateproject.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private int championships;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private Set<Player> players = new HashSet<Player>();

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Coach_ID", referencedColumnName = "id")
    private Coach coach;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Arena_ID", referencedColumnName = "id")
    private Arena arena;

    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    public Team() {}

    public Team(String name, int championships) {
        this.name = name;
        this.championships = championships;
    }

    public String getName() {
        return name;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setPlayers(HashSet<Player> players) {
        this.players = players;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }
}