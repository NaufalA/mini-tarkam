package com.skuymaen;

import com.skuymaen.features.player.PlayerService;
import com.skuymaen.features.player.entities.Nationality;
import com.skuymaen.features.player.entities.Player;
import com.skuymaen.features.player.entities.Position;
import com.skuymaen.features.player.entities.Skill;
import com.skuymaen.features.player.PlayerRepository;
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

        List<Nationality> nationalities = new ArrayList<>(Arrays.asList(
                new Nationality("INA", "Indonesia"),
                new Nationality("PHI", "Philippines"),
                new Nationality("MYA", "Myanmar"),
                new Nationality("MAS", "Malaysia"),
                new Nationality("SGP", "Singapore"),
                new Nationality("THA", "Thailand"),
                new Nationality("CAM", "Cambodia"),
                new Nationality("VIE", "Vietnam"),
                new Nationality("LAO", "Laos"),
                new Nationality("BRU", "Brunei")
        ));

        List<Position> positions = new ArrayList<>(Arrays.asList(
                new Position("GK", "Goalkeeper"),
                new Position("S", "Striker"),
                new Position("CB", "Center Back"),
                new Position("CM", "Center Midfielder")
        ));

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Player player = new Player();
            player.setPlayerName("Player " + (i + 1));
            player.setBirthDate(new Date(Date.valueOf("1994-04-23").getTime() + TimeUnit.DAYS.toMillis((i + 1) * 3)));
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

        playerService.getAll().forEach(p -> {
            System.out.println(p);
            System.out.println(p.getSkill());
        });

        JPAUtility.close();
    }
}
