package com.mycompany.hibernateproject.models;

import javax.persistence.*;

@Entity
public class Arena {

    @Id @GeneratedValue
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Team_ID", referencedColumnName = "id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    public Arena() {}

    public Arena(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Team getTeam() {
        return team;
    }

    public City getCity() {
        return city;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
