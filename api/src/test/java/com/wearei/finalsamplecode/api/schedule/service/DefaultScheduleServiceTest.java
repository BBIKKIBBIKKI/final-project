//package com.wearei.finalsamplecode.api.schedule.service;
//
//import com.wearei.finalsamplecode.api.schedule.DefaultScheduleService;
//import com.wearei.finalsamplecode.core.domain.schedule.entity.Schedule;
//import com.wearei.finalsamplecode.core.domain.schedule.repository.ScheduleRepository;
//import com.wearei.finalsamplecode.core.domain.team.entity.Team;
//import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//
//@Transactional
//@SpringBootTest
//class DefaultScheduleServiceTest {
//
//    @Autowired
//    private DefaultScheduleService defaultScheduleService;
//
//    @Autowired
//    private TeamRepository teamRepository;
//
//    @Autowired
//    private ScheduleRepository scheduleRepository;
//
//    private Team team;
//    private Schedule schedule;
//
//    @BeforeEach
//    void setUp() {
//        team = teamRepository.save(new Team("두산베어스", "url1", "url2", "url3", "url4"));
//        schedule = scheduleRepository.save(new Schedule(team, "빅매치", "잠실야구장에서 함", "잠실주경기장", LocalDate.parse("2024-11-12"), LocalTime.parse("18:30")));
//    }
//
//    @Test
//    void 일정_다건조회_정상_테스트() {
//        List<Schedule> response = defaultScheduleService.getSchedules(team.getId());
//
//        assertEquals(schedule.getTeam(), response.get(0).getTeam());
//        assertEquals(schedule.getTitle(), response.get(0).getTitle());
//        assertEquals(schedule.getContents(), response.get(0).getContents());
//        assertEquals(schedule.getGround(), response.get(0).getGround());
//        assertEquals(schedule.getDate(), response.get(0).getDate());
//        assertEquals(schedule.getTime(), response.get(0).getTime());
//    }
//
//    @Test
//    void 일정_단건조회_정상_테스트() {
//        Schedule response = defaultScheduleService.getSchedule(team.getId(), schedule.getId());
//
//        assertEquals(schedule.getTeam(), response.getTeam());
//        assertEquals(schedule.getTitle(), response.getTitle());
//        assertEquals(schedule.getContents(), response.getContents());
//        assertEquals(schedule.getGround(), response.getGround());
//        assertEquals(schedule.getDate(), response.getDate());
//        assertEquals(schedule.getTime(), response.getTime());
//    }
//}