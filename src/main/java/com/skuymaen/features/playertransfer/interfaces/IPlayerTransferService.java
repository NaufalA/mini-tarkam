package com.skuymaen.features.playertransfer.interfaces;

import com.skuymaen.features.player.entities.Player;
import com.skuymaen.features.playertransfer.entities.PlayerTransfer;
import com.skuymaen.features.team.entities.Team;
import com.skuymaen.shared.interfaces.IService;

import java.util.Date;
import java.util.List;

public interface IPlayerTransferService extends IService<PlayerTransfer, Long> {
    PlayerTransfer transferPlayer(Player player, Team recipientTeam, Date transferDate);
    PlayerTransfer getActiveTransfer(Player player);
    List<PlayerTransfer> getTeamTransfers(Team team, boolean activeTransfersOnly);
}
