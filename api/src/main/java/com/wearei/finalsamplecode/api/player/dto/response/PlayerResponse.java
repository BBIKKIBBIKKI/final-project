package com.wearei.finalsamplecode.api.player.dto.response;

import com.wearei.finalsamplecode.core.domain.player.entity.Player;
import static com.wearei.finalsamplecode.api.player.dto.response.PlayerResponse.*;

public sealed interface PlayerResponse permits Search {

    record Search (
            Long playerId,
            Long playerAge,
            Long follow,
            String playerName,
            String position,
            String playerSong,
            String playerBodyCheck
    ) implements PlayerResponse {
        public Search(Player player){
            this(
                    player.getId(),
                    player.getPlayerAge(),
                    player.getFollow(),
                    player.getPlayerName(),
                    player.getPosition(),
                    player.getPlayerSong(),
                    player.getPlayerBodyCheck()
            );
        }
    }
}
