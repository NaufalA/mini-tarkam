package com.skuymaen;

import com.skuymaen.features.player.PlayerService;
import com.skuymaen.features.player.entities.Nationality;
import com.skuymaen.features.player.entities.Player;
import com.skuymaen.features.player.entities.Position;
import com.skuymaen.features.player.entities.Skill;
import com.skuymaen.features.player.PlayerRepository;
import com.skuymaen.features.team.TeamRepository;
import com.skuymaen.features.team.TeamService;
import com.skuymaen.features.team.entities.City;
import com.skuymaen.features.team.entities.Team;
import com.skuymaen.shared.utils.JPAUtility;
import jakarta.persistence.EntityManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Seeder {
    public static void main(String[] args) {
        EntityManager em = JPAUtility.getEntityManager();

        PlayerRepository playerRepository = new PlayerRepository(em);
        PlayerService playerService = new PlayerService(playerRepository);

        TeamRepository teamRepository = new TeamRepository(em);
        TeamService teamService = new TeamService(teamRepository);

//        List<Nationality> nationalities = new ArrayList<>(Arrays.asList(
//                new Nationality("INA", "Indonesia"),
//                new Nationality("PHI", "Philippines"),
//                new Nationality("MYA", "Myanmar"),
//                new Nationality("MAS", "Malaysia"),
//                new Nationality("SGP", "Singapore"),
//                new Nationality("THA", "Thailand"),
//                new Nationality("CAM", "Cambodia"),
//                new Nationality("VIE", "Vietnam"),
//                new Nationality("LAO", "Laos"),
//                new Nationality("BRU", "Brunei")
//        ));
//
//        List<Position> positions = new ArrayList<>(Arrays.asList(
//                new Position("GK", "Goalkeeper"),
//                new Position("S", "Striker"),
//                new Position("CB", "Center Back"),
//                new Position("CM", "Center Midfielder")
//        ));
//        createPlayers(playerService, nationalities, positions, 20);

//        playerService.getAll().forEach(p -> {
//            System.out.println(p);
//            System.out.println(p.getSkill());
//        });

        List<City> cities = new ArrayList<>(Arrays.asList(
                new City("Jakarta"),
                new City("Bandung"),
                new City("Malang"),
                new City("Surabaya")
        ));
        createTeams(teamService, cities, 3);

        teamService.getAll().forEach(System.out::println);

        JPAUtility.close();
    }

    private static void createPlayers(PlayerService playerService, List<Nationality> nationalities, List<Position> positions, Integer size) {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            Player player = new Player();
            player.setPlayerName("Player " + (i + 1));
            player.setBirthDate(new Date(Date.valueOf("1994-04-23").getTime() + TimeUnit.DAYS.toMillis((i + 1) * 3L)));
            player.setHeight((float) (random.nextInt(208 + 1 - 153) + 153));
            player.setNationality(nationalities.get(random.nextInt(nationalities.size())));
            player.setPosition(positions.get(random.nextInt(positions.size())));
            player.setSkill(new Skill(
                    random.nextInt(11),
                    random.nextInt(11),
                    random.nextInt(11),
                    random.nextInt(11),
                    random.nextInt(11),
                    random.nextInt(11),
                    random.nextInt(11)
            ));

            playerService.create(player);
        }
    }

    private static void createTeams(TeamService teamService, List<City> cities, Integer size) {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            Team team = new Team();
            team.setTeamCode("TIM" + (i + 1));
            team.setTeamName("Team " + (i + 1));
            team.setEstablishDate(new Date(Date.valueOf("1990-02-11").getTime() + TimeUnit.DAYS.toMillis((i + 1) * 14L)));
            team.setCity(cities.get(random.nextInt(cities.size())));

            teamService.create(team);
        }
    }
}
