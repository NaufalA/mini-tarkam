package com.skuymaen.features.match.entities;

import com.skuymaen.features.team.entities.Team;
import com.skuymaen.shared.classes.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Entity
@Table(name = "match")
public class Match extends BaseEntity {
    @Getter
    @Setter
    @Column(name = "match_date", nullable = false)
    private Date matchDate;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    @Getter
    @Setter
    @Column(name = "home_score", nullable = false)
    private Integer homeScore = 0;

    @Getter
    @Setter
    @Column(name = "away_score", nullable = false)
    private Integer awayScore = 0;

}