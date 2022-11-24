package com.skuymaen.features.playertransfer.entities;

import com.skuymaen.features.player.entities.Player;
import com.skuymaen.features.team.entities.Team;
import com.skuymaen.shared.classes.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Entity
@Table(name = "player_transfers")
public class PlayerTransfer extends BaseEntity {
    @Getter
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_team_id")
    private Team sourceTeam;

    @Getter
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_team_id")
    private Team recipientTeam;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "transfer_date", nullable = false)
    private Date transferDate = new Date(System.currentTimeMillis());

    @Getter
    @Setter
    @Column(name = "is_active", nullable = false, columnDefinition = "boolean DEFAULT true")
    private Boolean isActive = true;
}