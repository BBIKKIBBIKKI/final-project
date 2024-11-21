package com.wearei.finalsamplecode.api.schedule.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import static com.wearei.finalsamplecode.api.schedule.dto.request.ScheduleRequest.*;

public sealed interface ScheduleRequest permits Create, Update {

    record Create (
            Long teamId,
            String title,
            String contents,
            String ground,
            LocalDate date,
            LocalTime time
    ) implements ScheduleRequest {}

    record Update (
            Long teamId,
            String title,
            String contents,
            String ground,
            LocalDate date,
            LocalTime time
    ) implements ScheduleRequest {}
}
