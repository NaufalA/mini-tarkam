package com.skuymaen.features.match;

import com.skuymaen.features.match.entities.Match;
import com.skuymaen.features.match.entities.Standing;
import com.skuymaen.features.match.interfaces.IMatchService;
import com.skuymaen.features.team.entities.Team;
import com.skuymaen.shared.classes.BaseService;
import com.skuymaen.shared.interfaces.IRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MatchService extends BaseService<Match, Long> implements IMatchService {
    public MatchService(IRepository<Match, Long> repository) {
        super(repository);
    }

    @Override
    public Map<Team, Standing> getStandings() {
        List<Match> matches = getAll();

        Map<Team, Standing> standings = new HashMap<>();

        matches.forEach(m -> {
            Standing homeStanding = standings.get(m);
            if (homeStanding == null) homeStanding = new Standing();
            Standing awayStanding = standings.get(m);
            if (awayStanding == null) awayStanding = new Standing();

            boolean homeWin = m.getHomeScore() > m.getAwayScore();
            boolean draw = m.getHomeScore() > m.getAwayScore();
            homeStanding.setMatchesPlayed(homeStanding.getMatchesPlayed() + 1);
            if (draw) {
                homeStanding.setDraws(homeStanding.getDraws() + 1);
                awayStanding.setDraws(awayStanding.getDraws() + 1);
            } else if (homeWin) {
                homeStanding.setWins(homeStanding.getWins() + 1);
                awayStanding.setLosses(awayStanding.getLosses() + 1);
            } else {
                awayStanding.setWins(awayStanding.getWins() + 1);
                homeStanding.setLosses(homeStanding.getLosses() + 1);
            }
            homeStanding.setGoalsFor(homeStanding.getGoalsFor() + m.getHomeScore());
            homeStanding.setGoalsAgainst(homeStanding.getGoalsAgainst() + m.getAwayScore());
            awayStanding.setGoalsFor(awayStanding.getGoalsFor() + m.getAwayScore());
            awayStanding.setGoalsAgainst(awayStanding.getGoalsAgainst() + m.getHomeScore());

            homeStanding.setGoalDifference(homeStanding.getGoalsFor() + homeStanding.getGoalsAgainst());
            awayStanding.setGoalDifference(awayStanding.getGoalsFor() + awayStanding.getGoalsAgainst());

            homeStanding.setPoints(countPoints(homeStanding));
            awayStanding.setPoints(countPoints(awayStanding));

            standings.put(m.getHomeTeam(), homeStanding);
            standings.put(m.getAwayTeam(), awayStanding);
        });
        return standings.entrySet().stream()
                .sorted(Comparator.comparing(ts -> ts.getValue().getPoints()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new));
    }

    private Integer countPoints(Standing standing) {
        return standing.getWins() * 3 + standing.getDraws();
    }
}
