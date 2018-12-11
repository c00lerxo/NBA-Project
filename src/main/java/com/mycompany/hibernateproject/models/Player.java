package com.mycompany.hibernateproject.models;

import org.joda.time.LocalDate;

import javax.persistence.*;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @Column
    private String nationality;

    @Column
    private float ppg;

    @Column
    private float apg;

    @Column
    private float rpg;

    public Player() {}

    public Player(String name, LocalDate birthday, String nationality, float ppg, float apg, float rpg) {
        this.name = name;
        this.birthday = birthday;
        this.nationality = nationality;
        this.ppg = ppg;
        this.apg = apg;
        this.rpg = rpg;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Team getTeam() {
        return team;
    }

    public String getNationality() {
        return nationality;
    }

    public float getPpg() {
        return ppg;
    }

    public float getApg() {
        return apg;
    }

    public float getRpg() {
        return rpg;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
