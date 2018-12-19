package com.mycompany.hibernateproject.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Player.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public float getPpg() {
        return ppg;
    }

    public void setPpg(float ppg) {
        this.ppg = ppg;
    }

    public float getApg() {
        return apg;
    }

    public void setApg(float apg) {
        this.apg = apg;
    }

    public float getRpg() {
        return rpg;
    }

    public void setRpg(float rpg) {
        this.rpg = rpg;
    }
}
