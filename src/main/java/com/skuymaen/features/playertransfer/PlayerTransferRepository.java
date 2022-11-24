package com.skuymaen.features.playertransfer;

import com.skuymaen.features.player.entities.Player;
import com.skuymaen.features.playertransfer.entities.PlayerTransfer;
import com.skuymaen.features.playertransfer.interfaces.IPlayerTransferRepository;
import com.skuymaen.features.team.entities.Team;
import com.skuymaen.shared.classes.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PlayerTransferRepository extends BaseRepository<PlayerTransfer, Long> implements IPlayerTransferRepository {
    public PlayerTransferRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<PlayerTransfer> findAll() {
        TypedQuery<PlayerTransfer> query = entityManager.createQuery("SELECT t FROM PlayerTransfer t", PlayerTransfer.class);

        return query.getResultList();
    }

    @Override
    public PlayerTransfer findById(Long id) {
        return entityManager.find(PlayerTransfer.class, id);
    }

    @Override
    public PlayerTransfer getActiveTransfer(Player player) {
        TypedQuery<PlayerTransfer> query =
                entityManager.createQuery(
                        "SELECT t FROM PlayerTransfer t WHERE player = :player AND isActive = true",
                        PlayerTransfer.class
                );
        query.setParameter("player", player);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<PlayerTransfer> getTeamTransfers(Team team, boolean activeTransfersOnly) {
        String queryString = "SELECT t FROM PlayerTransfer t ";
        if (activeTransfersOnly) {
            queryString = queryString + "WHERE recipientTeam = :recipientTeam ";
            queryString = queryString + "AND isActive = :isActive";
        } else {
            queryString = queryString + "WHERE recipientTeam = :recipientTeam OR sourceTeam = :sourceTeam";
        }
        TypedQuery<PlayerTransfer> query =
                entityManager.createQuery(queryString, PlayerTransfer.class);
        query.setParameter("recipientTeam", team);
        if (activeTransfersOnly) {
            query.setParameter("isActive", true);
        } else {
            query.setParameter("sourceTeam", team);
        }

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
