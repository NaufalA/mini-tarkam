package com.skuymaen.features.match;

import com.skuymaen.features.match.entities.Match;
import com.skuymaen.features.match.entities.Standing;
import com.skuymaen.features.match.interfaces.IMatchService;
import com.skuymaen.features.team.entities.Team;
import com.skuymaen.shared.utils.InputHelper;
import com.skuymaen.shared.utils.StringHelper;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MatchPresenter {
    private final IMatchService matchService;

    public MatchPresenter(IMatchService matchService) {
        this.matchService = matchService;
    }

    public void entry() {
        boolean stop = false;
        do {
            StringHelper.printHeader("Standings");
            System.out.println(
                    "1. View Standings\n" +
                            "2. View Matches History\n" +
                            "0. EXIT"
            );
            StringHelper.printInputPrompt("Choose menu");
            int choice = InputHelper.inputInt();
            switch (choice) {
                case 1:
                    standingsMenu();
                    break;
                case 2:
                    matchesMenu();
                    break;
                case 0:
                    stop = InputHelper.confirmation("Are you sure?");
                    break;
                default:
                    break;
            }
        } while (!stop);
    }

    private void standingsMenu() {
        StringHelper.printHeader("Standings");
        Map<Team, Standing> standings = matchService.getStandings();
        System.out.println("|| \t\t | " +
                "Team\t\t | " +
                "MP\t | " +
                "W\t | " +
                "D\t | " +
                "L\t | " +
                "GF\t | " +
                "GA\t | " +
                "GD\t | " +
                "Pts\t ||"
        );
        int i = 0;
        for (Map.Entry<Team, Standing> standing : standings.entrySet()) {
            Team t = standing.getKey();
            Standing s = standing.getValue();
            System.out.println("|| " + ++i + "\t | " +
                    t.getTeamName() + "\t | " +
                    s.getMatchesPlayed() + "\t | " +
                    s.getWins() + "\t | " +
                    s.getDraws() + "\t | " +
                    s.getLosses() + "\t | " +
                    s.getGoalsFor() + "\t | " +
                    s.getGoalsAgainst() + "\t | " +
                    s.getGoalDifference() + "\t | " +
                    s.getPoints() + "\t ||"
            );
        }
    }

    private void matchesMenu() {
        StringHelper.printHeader("Matches History");
        List<Match> matches = matchService.getAll().stream()
                .sorted(Comparator.comparingLong(t -> t.getMatchDate().getTime()))
                .collect(Collectors.toList());
        System.out.println("No.\t" +
                "Match Date\t\t\t\t\t" +
                "Home\t\t" +
                "Score\t\t" +
                "Away "
        );
        int i = 0;
        for (Match m : matches) {
            String output =
                    ++i + ".\t" +
                            m.getMatchDate() + "\t\t" +
                            m.getHomeTeam().getTeamName() + "\t\t" +
                            m.getHomeScore() + " - " + m.getAwayScore() +
                            "\t\t" + m.getAwayTeam().getTeamName();

            System.out.println(output);
        }
    }
}
