package com.wearei.finalsamplecode.api.team;

import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DefaultTeamServiceTest {
    @Autowired
    private TeamRepository teamRepository;

    @Test
    void 팀_정상_검색() {
        // given
        teamRepository.save(new Team("Seoul Tigers", "uniformImgUrl", "mascotImgUrl", "equipmentImgUrl", "Eye of the Tiger"));

        // when
        TeamSearchResponse response = teamService.searchTeam("Seoul Tigers");

        // then
        assertNotNull(response);
        assertEquals("Seoul Tigers", response.getTeamName());
        assertEquals("uniformImgUrl", response.getUniformImg());
        assertEquals("mascotImgUrl", response.getMascotImg());
        assertEquals("equipmentImgUrl", response.getEquipmentImg());
    }

    @Test
    void 존재하지_않는_팀_검색_예외() {
        // given
        String nonExistentTeamName = "NonExistentTeam";

        // when & then
        assertThrows(ApiException.class, () -> teamService.searchTeam(nonExistentTeamName));
    }
}
