package com.skuymaen.features.team;

import com.skuymaen.features.team.entities.Team;
import com.skuymaen.features.team.interfaces.ITeamRepository;
import com.skuymaen.shared.classes.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TeamRepository extends BaseRepository<Team, Long> implements ITeamRepository {
    public TeamRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Team> findAll() {
        TypedQuery<Team> query = entityManager.createQuery("SELECT t FROM Team t", Team.class);

        return query.getResultList();
    }

    @Override
    public Team findById(Long id) {
        return entityManager.find(Team.class, id);
    }
}
