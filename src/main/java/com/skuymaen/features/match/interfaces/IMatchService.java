package com.skuymaen.features.match.interfaces;

import com.skuymaen.features.match.entities.Match;
import com.skuymaen.features.match.entities.Standing;
import com.skuymaen.features.team.entities.Team;
import com.skuymaen.shared.interfaces.IService;

import java.util.Map;

public interface IMatchService extends IService<Match, Long> {
    Map<Team, Standing> getStandings();
}
