package com.wearei.finalsamplecode.domain.ground.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wearei.finalsamplecode.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.domain.ground.entity.QGround;
import com.wearei.finalsamplecode.domain.team.entity.QTeam;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class GroundRepositoryQueryImpl implements GroundRepositoryQuery {
    private final JPAQueryFactory queryFactory;

    @Override
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