package com.skuymaen.features.team.entities;

import com.skuymaen.shared.classes.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Entity
@Table(name = "teams")
public class Team extends BaseEntity {
    @Getter
    @Setter
    @Column(name = "team_code", nullable = false, unique = true, length = 5)
    private String teamCode;

    @Getter
    @Setter
    @Column(name = "team_name", nullable = false, unique = true)
    private String teamName;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "establish_date", nullable = false)
    private Date establishDate;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;
}