package com.skuymaen.features.match;

import com.skuymaen.features.match.entities.Match;
import com.skuymaen.features.match.entities.Standing;
import com.skuymaen.features.match.interfaces.IMatchService;
import com.skuymaen.features.team.entities.Team;
import com.skuymaen.shared.classes.BaseService;
import com.skuymaen.shared.interfaces.IRepository;

import java.util.*;
import java.util.stream.Collectors;

public class MatchService extends BaseService<Match, Long> implements IMatchService {
    public MatchService(IRepository<Match, Long> repository) {
        super(repository);
    }

    @Override
    public Map<Team, Standing> getStandings() {
        List<Match> matches = getAll();

        Map<Team, Standing> standings = new LinkedHashMap<>();

        matches.forEach(m -> {
            boolean newHome = false;
            Standing homeStanding = standings.get(m.getHomeTeam());
            if (homeStanding == null) {
                newHome = true;
                homeStanding = new Standing();
            }
            boolean newAway = false;
            Standing awayStanding = standings.get(m.getAwayTeam());
            if (awayStanding == null) {
                newAway = true;
                awayStanding = new Standing();
            }

            boolean draw = Objects.equals(m.getHomeScore(), m.getAwayScore());
            boolean homeWin = m.getHomeScore() > m.getAwayScore();
            homeStanding.setMatchesPlayed(homeStanding.getMatchesPlayed() + 1);
            awayStanding.setMatchesPlayed(awayStanding.getMatchesPlayed() + 1);
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

            homeStanding.setGoalDifference(homeStanding.getGoalsFor() - homeStanding.getGoalsAgainst());
            awayStanding.setGoalDifference(awayStanding.getGoalsFor() - awayStanding.getGoalsAgainst());

            homeStanding.setPoints(countPoints(homeStanding));
            awayStanding.setPoints(countPoints(awayStanding));

            if (newHome) standings.put(m.getHomeTeam(), homeStanding);
            if (newAway) standings.put(m.getAwayTeam(), awayStanding);
        });

        Comparator<Map.Entry<Team, Standing>> sortPoints = Comparator.comparingInt(ts -> ts.getValue().getPoints());
        return standings.entrySet().stream()
                .sorted(sortPoints.reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private Integer countPoints(Standing standing) {
        return standing.getWins() * 3 + standing.getDraws();
    }
}
