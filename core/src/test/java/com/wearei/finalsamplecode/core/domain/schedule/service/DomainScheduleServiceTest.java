package com.wearei.finalsamplecode.core.domain.schedule.service;

import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.core.domain.schedule.entity.Schedule;
import com.wearei.finalsamplecode.core.domain.schedule.repository.ScheduleRepository;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class DomainScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private DomainScheduleService domainScheduleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 일정_정상_생성_테스트() {
        Long userId = 1L;
        Long teamId = 1L;
        String title = "Game Schedule";
        String contents = "Team meeting and warm-up";
        String ground = "Main Ground";
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        User user = mock(User.class);
        Team team = mock(Team.class);
        Schedule schedule = new Schedule(team, title, contents, ground, date, time);

        when(userRepository.findByIdOrThrow(userId)).thenReturn(user);
        when(user.isNotSameRole(UserRole.ROLE_ADMIN)).thenReturn(false);
        when(teamRepository.findByIdOrThrow(teamId)).thenReturn(team);
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(schedule);

        Schedule createdSchedule = domainScheduleService.createSchedule(userId, teamId, title, contents, ground, date, time);

        assertNotNull(createdSchedule);
        assertEquals(title, createdSchedule.getTitle());
        assertEquals(contents, createdSchedule.getContents());
        verify(scheduleRepository, times(1)).save(any(Schedule.class));
    }

    @Test
    void 일정_정상_수정_테스트() {
        Long scheduleId = 1L;
        Long teamId = 1L;

        Team team = mock(Team.class);
        Schedule schedule = mock(Schedule.class);

        String title = "Updated Title";
        String contents = "Updated Contents";
        String ground = "Updated Ground";
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        when(teamRepository.findByIdOrThrow(teamId)).thenReturn(team);
        when(scheduleRepository.findByIdOrThrow(scheduleId)).thenReturn(schedule);

        when(schedule.getTitle()).thenReturn(title);
        when(schedule.getContents()).thenReturn(contents);
        when(schedule.getGround()).thenReturn(ground);
        when(schedule.getDate()).thenReturn(date);
        when(schedule.getTime()).thenReturn(time);

        Schedule updatedSchedule = domainScheduleService.updateSchedule(scheduleId, teamId);

        verify(schedule, times(1)).updateSchedule(
                eq(team),
                eq(title),
                eq(contents),
                eq(ground),
                eq(date),
                eq(time)
        );
        assertEquals(schedule, updatedSchedule);
    }

    @Test
    void 일정_정상_삭제_테스트() {
        Long scheduleId = 1L;
        Long teamId = 1L;

        Team team = mock(Team.class);
        Schedule schedule = mock(Schedule.class);

        when(teamRepository.findByIdOrThrow(teamId)).thenReturn(team);
        when(scheduleRepository.findByIdOrThrow(scheduleId)).thenReturn(schedule);

        domainScheduleService.deleteSchedule(scheduleId, teamId);

        verify(scheduleRepository, times(1)).delete(schedule);
    }
}
