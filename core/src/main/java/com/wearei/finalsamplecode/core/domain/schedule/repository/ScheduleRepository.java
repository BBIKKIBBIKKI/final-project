package com.wearei.finalsamplecode.core.domain.schedule.repository;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule findByTeamIdAndId(Long teamId, Long scheduleId);

    @Query("SELECT s FROM Schedule s WHERE s.team.id = :teamId")
    List<Schedule> findSchedulesByTeamId(@Param("teamId") Long teamId);

    default Schedule findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_SCHEDULE));
    }
}

