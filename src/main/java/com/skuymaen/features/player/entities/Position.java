package com.skuymaen.features.player.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Entity
@Table(name = "positions")
public class Position {
    @Id
    @Getter
    @Setter
    @Column(name = "position_code", nullable = false, length = 5)
    private String positionCode = "";

    @Getter
    @Setter
    @Column(name = "position_title", nullable = false, unique = true)
    private String positionTitle = "";

    @Getter
    @Setter
    @ToString.Exclude
    @OneToMany(mappedBy = "position")
    private List<Player> players;

    public Position() {}

    public Position(String positionCode, String positionTitle) {
        this.positionCode = positionCode;
        this.positionTitle = positionTitle;
    }
}