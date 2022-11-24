package com.skuymaen.features.match;

import com.skuymaen.features.match.entities.Match;
import com.skuymaen.features.match.interfaces.IMatchRepository;
import com.skuymaen.shared.classes.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class MatchRepository extends BaseRepository<Match, Long> implements IMatchRepository {

    public MatchRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Match> findAll() {
        TypedQuery<Match> query = entityManager.createQuery("SELECT t FROM Match t", Match.class);

        return query.getResultList();
    }

    @Override
    public Match findById(Long id) {
        return entityManager.find(Match.class, id);
    }
}
