package com.skuymaen.features.player.entities;

import com.skuymaen.shared.classes.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Entity
@Table(name = "players")
public class Player extends BaseEntity {
    @Getter
    @Setter
    @Column(name = "player_name", nullable = false)
    private String playerName;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Getter
    @Setter
    @Column(name = "height", nullable = false)
    private Float height;

    @ToString.Exclude
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "nationality_code")
    private Nationality nationality;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "position_code")
    private Position position;

}