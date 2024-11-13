package com.wearei.finalsamplecode.api.ground.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wearei.finalsamplecode.core.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.core.domain.ground.entity.QGround;
import com.wearei.finalsamplecode.core.domain.team.entity.QTeam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GroundQuery {
    private final JPAQueryFactory queryFactory;

    public Optional<Ground> searchGroundByTeamOrGroundName(String teamName, String groundName) {
         QGround ground = QGround.ground;
         QTeam team = QTeam.team;

         Ground result = queryFactory
                 .selectFrom(ground)
                 .leftJoin(ground.team, team).fetchJoin()
                 .where(
                         teamName != null && !teamName.isBlank() ? team.teamName.eq(teamName) : null,
                         groundName != null && !groundName.isBlank() ? ground.groundName.eq(groundName) : null
                 )
                 .fetchOne();

         if (result == null) {
             return Optional.empty();
         }

         return Optional.of(result);
    }

}