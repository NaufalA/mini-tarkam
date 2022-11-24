package com.skuymaen.features.player.entities;

import com.skuymaen.shared.classes.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Table(name = "skills")
public class Skill extends BaseEntity {
    @Getter
    @Setter
    @Column(name = "speed", nullable = false)
    private Integer speed = 0;
    @Getter
    @Setter
    @Column(name = "shooting", nullable = false)
    private Integer shooting = 0;
    @Getter
    @Setter
    @Column(name = "strength", nullable = false)
    private Integer strength = 0;
    @Getter
    @Setter
    @Column(name = "agility", nullable = false)
    private Integer agility = 0;
    @Getter
    @Setter
    @Column(name = "dribble", nullable = false)
    private Integer dribble = 0;
    @Getter
    @Setter
    @Column(name = "stamina", nullable = false)
    private Integer stamina = 0;
    @Getter
    @Setter
    @Column(name = "determination", nullable = false)
    private Integer determination = 0;

    public Skill() {}

    public Skill(Integer speed, Integer shooting, Integer strength, Integer agility, Integer dribble, Integer stamina, Integer determination) {
        this.speed = speed;
        this.shooting = shooting;
        this.strength = strength;
        this.agility = agility;
        this.dribble = dribble;
        this.stamina = stamina;
        this.determination = determination;
    }
}