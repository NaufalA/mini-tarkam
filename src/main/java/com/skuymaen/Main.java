package com.skuymaen;

import com.skuymaen.features.match.MatchPresenter;
import com.skuymaen.features.match.MatchRepository;
import com.skuymaen.features.match.MatchService;
import com.skuymaen.features.match.interfaces.IMatchRepository;
import com.skuymaen.features.match.interfaces.IMatchService;
import com.skuymaen.features.player.PlayerRepository;
import com.skuymaen.features.player.PlayerService;
import com.skuymaen.features.player.interfaces.IPlayerRepository;
import com.skuymaen.features.player.interfaces.IPlayerService;
import com.skuymaen.features.playertransfer.PlayerTransferRepository;
import com.skuymaen.features.playertransfer.PlayerTransferService;
import com.skuymaen.features.playertransfer.interfaces.IPlayerTransferRepository;
import com.skuymaen.features.playertransfer.interfaces.IPlayerTransferService;
import com.skuymaen.features.team.TeamRepository;
import com.skuymaen.features.team.TeamService;
import com.skuymaen.features.team.interfaces.ITeamRepository;
import com.skuymaen.features.team.interfaces.ITeamService;
import com.skuymaen.shared.utils.InputHelper;
import com.skuymaen.shared.utils.JPAUtility;
import com.skuymaen.shared.utils.StringHelper;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtility.getEntityManager();

        IPlayerRepository playerRepository = new PlayerRepository(em);
        IPlayerService playerService = new PlayerService(playerRepository);

        ITeamRepository teamRepository = new TeamRepository(em);
        ITeamService teamService = new TeamService(teamRepository);

        IPlayerTransferRepository playerTransferRepository = new PlayerTransferRepository(em);
        IPlayerTransferService playerTransferService = new PlayerTransferService(playerTransferRepository);

        IMatchRepository matchRepository = new MatchRepository(em);
        IMatchService matchService = new MatchService(matchRepository);

        MatchPresenter matchPresenter = new MatchPresenter(matchService, playerTransferService);

        boolean stop = false;
        do {
            StringHelper.printHeader("Mini Tarkam");
            System.out.println(
                    "1. Manage Players\n" +
                            "2. Manage Teams\n" +
                            "3. Standings\n" +
                            "0. EXIT"
            );
            StringHelper.printInputPrompt("Choose menu");
            int choice = InputHelper.inputInt();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    matchPresenter.entry();
                    break;
                case 0:
                    stop = InputHelper.confirmation("Are you sure?");
                    break;
                default:
                    break;
            }
        } while (!stop);

        JPAUtility.close();
    }
}