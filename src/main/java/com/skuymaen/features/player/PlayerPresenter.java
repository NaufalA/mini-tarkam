package com.skuymaen.features.player;

import com.skuymaen.features.player.entities.Nationality;
import com.skuymaen.features.player.entities.Player;
import com.skuymaen.features.player.entities.Position;
import com.skuymaen.features.player.entities.Skill;
import com.skuymaen.features.player.interfaces.IPlayerService;
import com.skuymaen.features.playertransfer.entities.PlayerTransfer;
import com.skuymaen.features.playertransfer.interfaces.IPlayerTransferService;
import com.skuymaen.features.team.entities.Team;
import com.skuymaen.features.team.interfaces.ITeamService;
import com.skuymaen.shared.utils.InputHelper;
import com.skuymaen.shared.utils.StringHelper;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerPresenter {
    private final IPlayerService playerService;
    private final IPlayerTransferService playerTransferService;
    private final ITeamService teamService;

    public PlayerPresenter(IPlayerService playerService, IPlayerTransferService playerTransferService, ITeamService teamService) {
        this.playerService = playerService;
        this.playerTransferService = playerTransferService;
        this.teamService = teamService;
    }

    public void entry() {
        boolean stop = false;
        do {
            StringHelper.printHeader("Manage Players");
            System.out.println(
                    "1. Register New Player\n" +
                            "2. View All Player\n" +
                            "3. Update Player\n" +
                            "4. Manage Transfers\n" +
                            "0. EXIT"
            );
            StringHelper.printInputPrompt("Choose menu");
            int choice = InputHelper.inputInt();
            switch (choice) {
                case 1:
                    registerPlayerMenu();
                    break;
                case 2:
                    printPlayerList(true);
                    break;
                case 3:
                    System.out.println("Unimplemented");
                    break;
                case 4:
                    transfersMenu();
                    break;
                case 0:
                    stop = InputHelper.confirmation("Are you sure?");
                    break;
                default:
                    break;
            }
        } while (!stop);
    }

    public void registerPlayerMenu() {
        Player player = new Player();
        StringHelper.printHeader("Register New Player");
        System.out.println("Player Profile");
        StringHelper.printSeparator(".", 30);
        StringHelper.printInputPrompt("Name");
        player.setPlayerName(InputHelper.inputString(false));
        StringHelper.printInputPrompt("Birth Date");
        player.setBirthDate(InputHelper.inputDate());
        StringHelper.printInputPrompt("Height");
        player.setHeight(InputHelper.inputFloat(153, 208));
        StringHelper.printInputPrompt("Nationality");
        Nationality nationality = new Nationality();
        nationality.setNationCode(InputHelper.inputString(false));
        player.setNationality(nationality);
        StringHelper.printInputPrompt("Position");
        Position position = new Position();
        position.setPositionCode(InputHelper.inputString(false));
        player.setPosition(position);
        System.out.println();
        Skill skill = new Skill();
        System.out.println("Player Skills");
        StringHelper.printSeparator(".", 30);
        StringHelper.printInputPrompt("Speed");
        skill.setSpeed(InputHelper.inputInt(0, 10));
        StringHelper.printInputPrompt("Shooting");
        skill.setShooting(InputHelper.inputInt(0, 10));
        StringHelper.printInputPrompt("Strength");
        skill.setStrength(InputHelper.inputInt(0, 10));
        StringHelper.printInputPrompt("Agility");
        skill.setAgility(InputHelper.inputInt(0, 10));
        StringHelper.printInputPrompt("Dribble");
        skill.setDribble(InputHelper.inputInt(0, 10));
        StringHelper.printInputPrompt("Stamina");
        skill.setStamina(InputHelper.inputInt(0, 10));
        StringHelper.printInputPrompt("Determination");
        skill.setDetermination(InputHelper.inputInt(0, 10));

        player.setSkill(skill);

        try {
            playerService.create(player);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void transfersMenu() {
        boolean stop = false;
        do {
            StringHelper.printHeader("Manage Transfers");
            System.out.println(
                    "1. View Transfer History\n" +
                            "2. Transfer Player\n" +
                            "0. BACK"
            );
            StringHelper.printInputPrompt("Choose menu");
            int choice = InputHelper.inputInt();
            switch (choice) {
                case 1:
                    transferHistoryMenu();
                    break;
                case 2:
                    transferPlayerMenu();
                    break;
                case 0:
                    stop = true;
                    break;
                default:
                    break;
            }
        } while (!stop);
    }

    private void transferHistoryMenu() {
        StringHelper.printHeader("Player Transfers History");
        List<PlayerTransfer> transfers =
                playerTransferService.getAll().stream()
                        .sorted(Comparator.comparingLong(t -> t.getTransferDate().getTime()))
                        .collect(Collectors.toList());
        System.out.println(
                "Transfer Date\t\t | " +
                        "Summary"
        );
        transfers.forEach(pt -> {
            StringBuilder output = new StringBuilder();
            output.append(pt.getTransferDate()).append("\t | ");
            output.append(pt.getPlayer().getPlayerName());
            if (pt.getSourceTeam() != null) {
                output.append(", ").append(pt.getSourceTeam().getTeamName());
            }
            output.append(" -> ").append(pt.getRecipientTeam().getTeamName());

            System.out.println(output);
        });
    }

    private void transferPlayerMenu() {
        StringHelper.printHeader("Transfer Player");
        List<Player> players = printPlayerList(true);
        StringHelper.printInputPrompt("Choose player to transfer");
        int playerChoice = InputHelper.inputInt(1, players.size()) - 1;
        Player player = players.get(playerChoice);
        List<Team> teams = printTeamList();
        StringHelper.printInputPrompt("Choose player to transfer");
        Team team;
        boolean sameTeam;
        do {
            int teamChoice = InputHelper.inputInt(1, players.size()) - 1;
            team = teams.get(teamChoice);
            sameTeam = team == playerTransferService.getActiveTransfer(player).getRecipientTeam();
            if (sameTeam) {
                System.out.println("Recipient team cannot be same with Current Team");
            }
        } while (sameTeam);

        try {
            PlayerTransfer pt = playerTransferService.transferPlayer(player, team, new Date(System.currentTimeMillis()));

            StringBuilder output = new StringBuilder("Transferred " + pt.getPlayer().getPlayerName());
            if (pt.getSourceTeam() != null) {
                output.append(" From ").append(pt.getSourceTeam().getTeamName());
            }
            output.append(" To ").append(pt.getRecipientTeam().getTeamName());

            System.out.println(output);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private List<Player> printPlayerList(boolean withTransfer) {
        List<Player> players = playerService.getAll();

        int i = 0;
        for (Player player : players) {
            StringBuilder output = new StringBuilder(++i + ".\t");
            output.append(player.getPlayerName());
            output.append("\t| ").append(player.getPosition().getPositionCode());
            if (withTransfer) {
                output.append("\t| ").append(playerTransferService.getActiveTransfer(player).getRecipientTeam().getTeamName());
            }
            System.out.println(output);
        }
        return players;
    }

    private List<Team> printTeamList() {
        List<Team> teams = teamService.getAll();

        int i = 0;
        for (Team team : teams) {
            System.out.println(++i + ".\t" + team.getTeamName());
        }

        return teams;
    }
}
