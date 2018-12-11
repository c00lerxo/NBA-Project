package com.mycompany.hibernateproject.models;

import javax.persistence.*;

@Entity
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String experience;

    @Column
    private int championships;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Team_ID", referencedColumnName = "id")
    private Team team;

    public Coach() {}

    public Coach(String name, String experience, int championships) {
        this.name = name;
        this.experience = experience;
        this.championships = championships;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExperience() {
        return experience;
    }

    public int getChampionships() {
        return championships;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
