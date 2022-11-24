package com.skuymaen.features.playertransfer;

import com.skuymaen.features.player.entities.Player;
import com.skuymaen.features.playertransfer.entities.PlayerTransfer;
import com.skuymaen.features.playertransfer.interfaces.IPlayerTransferRepository;
import com.skuymaen.features.playertransfer.interfaces.IPlayerTransferService;
import com.skuymaen.features.team.entities.Team;
import com.skuymaen.shared.classes.BaseService;

import java.util.Date;

public class PlayerTransferService extends BaseService<PlayerTransfer, Long> implements IPlayerTransferService {
    private final IPlayerTransferRepository playerTransferRepository;

    public PlayerTransferService(IPlayerTransferRepository playerTransferRepository) {
        super(playerTransferRepository);
        this.playerTransferRepository = playerTransferRepository;
    }

    @Override
    public PlayerTransfer transferPlayer(Player player, Team recipientTeam, Date transferDate) {
        PlayerTransfer activeTransfer = getActiveTransfer(player);

        if (activeTransfer != null && activeTransfer.getRecipientTeam() == recipientTeam) {
            throw new RuntimeException("Cannot transfer to the same team");
        }

        PlayerTransfer newTransfer = new PlayerTransfer();
        newTransfer.setPlayer(player);
        newTransfer.setSourceTeam(activeTransfer != null ? activeTransfer.getRecipientTeam() : null);
        newTransfer.setRecipientTeam(recipientTeam);
        newTransfer.setTransferDate(transferDate);

        if (activeTransfer != null) {
            activeTransfer.setIsActive(false);
            repository.update(activeTransfer);
        }

        repository.insert(newTransfer);
        newTransfer.setId(newTransfer.getId());

        return newTransfer;
    }

    @Override
    public PlayerTransfer getActiveTransfer(Player player) {
        return playerTransferRepository.getActiveTransfer(player);
    }
}
