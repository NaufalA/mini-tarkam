package com.skuymaen.features.match.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Standing {
    private Integer matchesPlayed = 0;
    private Integer wins = 0;
    private Integer losses = 0;
    private Integer draws = 0;
    private Integer points = 0;
    private Integer GoalsFor = 0;
    private Integer GoalsAgainst = 0;
    private Integer GoalDifference = 0;
}
