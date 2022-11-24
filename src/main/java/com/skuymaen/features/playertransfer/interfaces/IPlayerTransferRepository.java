package com.skuymaen.features.playertransfer.interfaces;

import com.skuymaen.features.player.entities.Player;
import com.skuymaen.features.playertransfer.entities.PlayerTransfer;
import com.skuymaen.features.team.entities.Team;
import com.skuymaen.shared.interfaces.IRepository;

import java.util.List;

public interface IPlayerTransferRepository extends IRepository<PlayerTransfer, Long> {
    PlayerTransfer getActiveTransfer(Player player);
    List<PlayerTransfer> getTeamTransfers(Team team, boolean activeTransfersOnly);
}
