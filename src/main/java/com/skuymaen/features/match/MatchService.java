package com.skuymaen.features.match;

import com.skuymaen.features.match.entities.Match;
import com.skuymaen.features.match.interfaces.IMatchService;
import com.skuymaen.shared.classes.BaseService;
import com.skuymaen.shared.interfaces.IRepository;

public class MatchService extends BaseService<Match, Long> implements IMatchService {
    public MatchService(IRepository<Match, Long> repository) {
        super(repository);
    }
}
