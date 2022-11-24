package com.skuymaen.features.team;

import com.skuymaen.features.team.entities.Team;
import com.skuymaen.features.team.interfaces.ITeamService;
import com.skuymaen.shared.classes.BaseService;
import com.skuymaen.shared.interfaces.IRepository;

public class TeamService extends BaseService<Team, Long> implements ITeamService {
    public TeamService(IRepository<Team, Long> repository) {
        super(repository);
    }
}
