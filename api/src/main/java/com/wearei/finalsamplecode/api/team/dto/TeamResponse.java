package com.wearei.finalsamplecode.api.team.dto;

import com.wearei.finalsamplecode.core.domain.team.entity.Team;

import static com.wearei.finalsamplecode.api.team.dto.TeamResponse.*;

public sealed interface TeamResponse permits Create, Search {
    record Create(
            String teamName,
            String uniformImg,
            String mascotImg,
            String equipmentImg,
            String themeSong
    ) implements TeamResponse{
        public Create(Team team) {
            this(
                    team.getTeamName(),
                    team.getUniformImg(),
                    team.getMascotImg(),
                    team.getEquipmentImg(),
                    team.getThemeSong()
            );
        }
    }

    record Search(
            String teamName,
            String uniformImg,
            String mascotImg,
            String equipmentImg,
            String themeSong
    ) implements TeamResponse{
        public Search(Team team) {
            this(
                    team.getTeamName(),
                    team.getUniformImg(),
                    team.getMascotImg(),
                    team.getEquipmentImg(),
                    team.getThemeSong()
            );
        }
    }
}