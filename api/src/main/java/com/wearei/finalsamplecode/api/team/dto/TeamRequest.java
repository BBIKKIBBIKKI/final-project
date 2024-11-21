package com.wearei.finalsamplecode.api.team.dto;
import static com.wearei.finalsamplecode.api.team.dto.TeamRequest.*;
public sealed interface TeamRequest permits Create{
    record Create(
            String teamName,
            String themeSong
    ) implements TeamRequest {};
}
