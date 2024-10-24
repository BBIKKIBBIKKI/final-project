package com.wearei.finalsamplecode;

import com.wearei.finalsamplecode.domain.schedule.dto.request.ScheduleCreateRequestDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleCreateResponseDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleSearchResponseDto;
import com.wearei.finalsamplecode.domain.schedule.entity.Schedule;
import com.wearei.finalsamplecode.domain.schedule.repository.ScheduleRepository;
import com.wearei.finalsamplecode.domain.schedule.service.ScheduleService;
import com.wearei.finalsamplecode.domain.team.dto.request.TeamRequest;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
class ScheduleServiceTest {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private TeamRepository teamRepository;
    private Team team;
    private Schedule schedule;

    @BeforeEach
    public void setUp() {
        TeamRequest teamRequest = new TeamRequest(
                "한화이글스",
                null,
                null,
                null,
                null
        );
        team = new Team(teamRequest);
        teamRepository.save(team);

        schedule = new Schedule(team,
                "제목",
                "내용",
                "운동장",
                LocalDate.of(2024, 10, 24),
                LocalTime.of(14,0));
        // 추가 필드 설정
    }

    @Test
    public void testCreateSchedule() {
       ScheduleCreateRequestDto scheduleCreateRequestDto = new ScheduleCreateRequestDto(
               1L,
               "제목",
               "내용",
               "운동장",
               LocalDate.of(2024, 10, 24),
               LocalTime.of(14,0)
       );

       ScheduleCreateResponseDto createdSchedule = scheduleService.createSchedule(scheduleCreateRequestDto);

       assertThat(createdSchedule).isNotNull();
       assertThat(createdSchedule.getId()).isNotNull();
    }

//    @Test
//    public void testGetSchedule() {
//        ScheduleCreateRequestDto scheduleCreateRequestDto = new ScheduleCreateRequestDto(
//                1L,
//                "제목",
//                "내용",
//                "운동장",
//                LocalDate.of(2024, 10, 24),
//                LocalTime.of(14,0)
//        );
//        scheduleService.createSchedule(scheduleCreateRequestDto);
//
//        ScheduleSearchResponseDto getSchedule = scheduleService.getSchedule(1L, 1L);
//        assertThat(getSchedule).isNotNull();
//        assertThat(getSchedule.getId()).isEqualTo(1L);
//    }

//    @Test
//    public void testGetAllSchedules() {
//        scheduleService.createSchedule(schedule);
//        List<Schedule> schedules = scheduleService.getAllSchedules();
//        assertThat(schedules).isNotEmpty();
//    }
//
//    @Test
//    public void testDeleteSchedule() {
//        scheduleService.createSchedule(schedule);
//        scheduleService.deleteSchedule(1L);
//        Optional<Schedule> foundSchedule = scheduleService.getSchedule(1L);
//        assertThat(foundSchedule).isNotPresent();
//    }
}
