package com.skuymaen.features.player;

import com.skuymaen.features.player.entities.Player;
import com.skuymaen.features.player.interfaces.IPlayerRepository;
import com.skuymaen.shared.classes.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PlayerRepository extends BaseRepository<Player, Long> implements IPlayerRepository {
    public PlayerRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Player> findAll() {
        TypedQuery<Player> query = entityManager.createQuery("SELECT t FROM Player t", Player.class);

        return query.getResultList();
    }

    @Override
    public Player findById(Long id) {
        return entityManager.find(Player.class, id);
    }
}
