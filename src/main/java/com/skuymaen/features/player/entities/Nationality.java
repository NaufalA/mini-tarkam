package com.skuymaen.features.player.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Table(name = "nationalities")
public class Nationality {
    @Id
    @Getter
    @Setter
    @Column(name = "nation_code", nullable = false, length = 5)
    private String nationCode;

    @Getter
    @Setter
    @Column(name = "nation_name", nullable = false, unique = true)
    private String nationName;

    public Nationality() {
    }

    public Nationality(String nationCode, String nationName) {
        this.nationCode = nationCode;
        this.nationName = nationName;
    }
}