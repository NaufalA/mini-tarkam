package com.skuymaen.features.team;

import com.skuymaen.features.playertransfer.interfaces.IPlayerTransferService;
import com.skuymaen.features.team.entities.Team;
import com.skuymaen.features.team.interfaces.ITeamService;
import com.skuymaen.shared.utils.InputHelper;
import com.skuymaen.shared.utils.StringHelper;

import java.util.List;

public class TeamPresenter {
    private final ITeamService teamService;
    private final IPlayerTransferService playerTransferService;

    public TeamPresenter(ITeamService teamService, IPlayerTransferService playerTransferService) {
        this.teamService = teamService;
        this.playerTransferService = playerTransferService;
    }

    public void run() {
        boolean stop = false;
        do {
            StringHelper.printHeader("Manage Teams");
            System.out.println(
                    "1. Register New Team\n" +
                            "2. View All Team\n" +
                            "3. Update Team\n" +
                            "4. Manage Transfers\n" +
                            "0. EXIT"
            );
            StringHelper.printInputPrompt("Choose menu");
            int choice = InputHelper.inputInt();
            switch (choice) {
                case 1:
                    StringHelper.printHeader("Register New Team");
                    System.out.println("Unimplemented");
                    break;
                case 2:
                    StringHelper.printHeader("View All Team");
                    printTeamList();
                    break;
                case 3:
                    StringHelper.printHeader("Update Team");
                    System.out.println("Unimplemented");
                    break;
                case 4:
                    StringHelper.printHeader("Manage Transfers");
                    System.out.println("Unimplemented");
                    break;
                case 0:
                    stop = InputHelper.confirmation("Are you sure?");
                    break;
                default:
                    break;
            }
        } while (!stop);
    }


    private List<Team> printTeamList() {
        List<Team> teams = teamService.getAll();
        System.out.println("|| No.\t| " +
                "Code" + "\t\t| " +
                "Name" + "\t\t\t| " +
                "City" + "\t\t\t| " +
                "Established Date\t ||"
        );
        int i = 0;
        for (Team team : teams) {
            System.out.println("|| " + ++i + ".\t| " +
                    team.getTeamCode() + "\t\t| " +
                    team.getTeamName() + "\t\t| " +
                    team.getCity().getCityName() + "\t\t| " +
                    team.getEstablishDate() + "\t\t ||"
            );
        }

        return teams;
    }
}
