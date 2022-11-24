package com.skuymaen.features.player;

import com.skuymaen.features.player.entities.Player;
import com.skuymaen.features.player.interfaces.IPlayerService;
import com.skuymaen.shared.classes.BaseService;
import com.skuymaen.shared.interfaces.IRepository;

public class PlayerService extends BaseService<Player, Long> implements IPlayerService {
    public PlayerService(IRepository<Player, Long> repository) {
        super(repository);
    }
}
