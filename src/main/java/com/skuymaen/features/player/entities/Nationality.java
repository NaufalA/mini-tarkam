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
    private String nation_code;

    @Getter
    @Setter
    @Column(name = "nation_name", nullable = false, unique = true)
    private String nationName;

    public Nationality() {
    }

    public Nationality(String nation_code, String nationName) {
        this.nation_code = nation_code;
        this.nationName = nationName;
    }
}