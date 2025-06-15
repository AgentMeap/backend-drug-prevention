package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.Schedule;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqScheduleDTO;
import com.group7.swp391.drug_prevention.repository.ScheduleRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    public List<Schedule> findAllResScheduleDTO() {
        return scheduleRepository.findAll();
    }

    public Schedule createSchedule(ReqScheduleDTO dto) {
        User consultant = userRepository.findById(dto.getConsultantId()).orElse(null);
        if (consultant != null && consultant.getRole().equalsIgnoreCase("consultant")) {
            Schedule schedule = new Schedule();
            schedule.setConsultant(consultant);
            schedule.setStartTime(dto.getStartTime());
            schedule.setEndTime(dto.getEndTime());
            schedule.setDayOfWeek(dto.getDayOfWeek());
            return scheduleRepository.save(schedule);
        }
        return null;
    }

    public void deleteSchedule(long id) {
        scheduleRepository.deleteById(id);
    }

}
