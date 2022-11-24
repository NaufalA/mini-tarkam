package com.skuymaen.features.playertransfer.interfaces;

import com.skuymaen.features.player.entities.Player;
import com.skuymaen.features.playertransfer.entities.PlayerTransfer;
import com.skuymaen.shared.interfaces.IRepository;

public interface IPlayerTransferRepository extends IRepository<PlayerTransfer, Long> {
    PlayerTransfer getActiveTransfer(Player player);
}
