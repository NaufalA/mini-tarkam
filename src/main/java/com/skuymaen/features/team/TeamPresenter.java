package com.skuymaen.features.team;

import com.skuymaen.features.player.entities.Player;
import com.skuymaen.features.player.interfaces.IPlayerService;
import com.skuymaen.features.playertransfer.entities.PlayerTransfer;
import com.skuymaen.features.playertransfer.interfaces.IPlayerTransferService;
import com.skuymaen.features.team.entities.City;
import com.skuymaen.features.team.entities.Team;
import com.skuymaen.features.team.interfaces.ITeamService;
import com.skuymaen.shared.utils.InputHelper;
import com.skuymaen.shared.utils.StringHelper;

import java.util.List;
import java.util.stream.Collectors;

public class TeamPresenter {
    private final ITeamService teamService;
    private final IPlayerService playerService;
    private final IPlayerTransferService playerTransferService;

    public TeamPresenter(ITeamService teamService, IPlayerService playerService, IPlayerTransferService playerTransferService) {
        this.teamService = teamService;
        this.playerService = playerService;
        this.playerTransferService = playerTransferService;
    }

    public void run() {
        boolean stop = false;
        do {
            StringHelper.printHeader("Manage Teams");
            System.out.println(
                    "1. Register New Team\n" +
                            "2. View Team\n" +
                            "0. EXIT"
            );
            StringHelper.printInputPrompt("Choose menu");
            int choice = InputHelper.inputInt();
            switch (choice) {
                case 1:
                    StringHelper.printHeader("Register New Team");
                    registerTeamMenu();
                    break;
                case 2:
                    StringHelper.printHeader("View Team");
                    viewTeamMenu();
                    break;
                case 0:
                    stop = InputHelper.confirmation("Are you sure?");
                    break;
                default:
                    break;
            }
        } while (!stop);
    }

    private void registerTeamMenu() {
        Team team = inputTeamData(null);

        try {
            teamService.create(team);
            System.out.println("Successfully Registered New Team");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateTeamMenu() {
        List<Team> teams = printTeamList();
        StringHelper.printInputPrompt("Select Team To Update");
        int teamChoice = InputHelper.inputInt(1, teams.size()) - 1;
        Team team = inputTeamData(teams.get(teamChoice));
        try {
            teamService.update(team);
            System.out.println("Successfully Registered New Team");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Team inputTeamData(Team team) {
        boolean newTeam = team == null;
        if (newTeam) {
            team = new Team();
        }
        StringHelper.printInputPrompt("Team Code");
        team.setTeamCode(InputHelper.inputString(!newTeam));
        StringHelper.printInputPrompt("Team Name");
        team.setTeamName(InputHelper.inputString(!newTeam));
        StringHelper.printInputPrompt("City");
        City city = new City();
        city.setCityName(InputHelper.inputString(!newTeam));
        team.setCity(city);
        StringHelper.printInputPrompt("Establish Date");
        team.setEstablishDate(InputHelper.inputDate());

        return team;
    }

    private void viewTeamMenu() {
        List<Team> teams = printTeamList();
        StringHelper.printInputPrompt("Select Team index");
        int teamChoice = InputHelper.inputInt(1, teams.size()) - 1;

        teamDetailMenu(teams.get(teamChoice));
    }

    private void teamDetailMenu(Team team) {
        boolean stop = false;
        do {
            StringHelper.printHeader("Team Detail");
            System.out.println(
                    "ID\t\t\t\t\t " + team.getId() + "\n" +
                            "Code\t\t\t\t " + team.getTeamCode() + "\n" +
                            "Name\t\t\t\t " + team.getTeamName() + "\n" +
                            "City\t\t\t\t " + team.getCity().getCityName() + "\n" +
                            "Established Date\t " + team.getEstablishDate() + "\n"
            );
            System.out.println(
                    "1. View Team Players\n" +
                            "2. View Transfers History\n" +
                            "3. Update Team\n" +
                            "0. EXIT"
            );
            StringHelper.printInputPrompt("Choose menu");
            int choice = InputHelper.inputInt();
            switch (choice) {
                case 1:
                    StringHelper.printHeader("View Team Players");
                    printPlayerList(
                            playerTransferService.getTeamTransfers(team, true)
                                    .stream().map(PlayerTransfer::getPlayer).collect(Collectors.toList()),
                            false
                    );
                    break;
                case 2:
                    StringHelper.printHeader("View Team");
                    printPlayerList(
                            playerTransferService.getTeamTransfers(team, false)
                                    .stream().map(PlayerTransfer::getPlayer).collect(Collectors.toList()),
                            true
                    );
                    break;
                case 3:
                    StringHelper.printHeader("Update Team");
                    updateTeamMenu();
                    break;
                case 0:
                    stop = true;
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

    private void printPlayerList(List<Player> players, boolean withTransfer) {
        if (players == null || players.size() == 0) {
            System.out.println("No Players");
            return;
        }
        int i = 0;
        for (Player player : players) {
            StringBuilder output = new StringBuilder(++i + ".\t");
            output.append(player.getPlayerName());
            output.append("\t| ").append(player.getPosition().getPositionCode());

            if (withTransfer) {
                PlayerTransfer playerTransfer =playerTransferService.getActiveTransfer(player);
                if (playerTransfer.getSourceTeam() == null) {
                    output.append("\t| ").append(playerTransfer.getRecipientTeam().getTeamName());
                } else {
                    output.append("\t| ").append(playerTransfer.getSourceTeam().getTeamName());
                    output.append(" -> ").append(playerTransfer.getRecipientTeam().getTeamName());
                }
            }
            System.out.println(output);
        }
    }
}
