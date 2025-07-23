package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.Schedule;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqScheduleDTO;
import com.group7.swp391.drug_prevention.domain.response.ResConsultantDTO;
import com.group7.swp391.drug_prevention.domain.response.ResScheduleDTO;
import com.group7.swp391.drug_prevention.repository.ScheduleRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import com.group7.swp391.drug_prevention.util.error.IdInvalidException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Schedule createSchedule(ReqScheduleDTO dto) throws IdInvalidException {
        User consultant = userRepository.findById(dto.getConsultantId())
                .orElseThrow(() -> new IdInvalidException("Không tìm thấy tư vấn viên với ID: " + dto.getConsultantId()));

        Schedule schedule = new Schedule();
        schedule.setConsultant(consultant);
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());
        schedule.setDay(dto.getDay());
        return scheduleRepository.save(schedule);
    }


    public void deleteSchedule(long id) {
        this.scheduleRepository.deleteById(id);
    }

    public Schedule handleUpdateSchedule(Schedule reqSchedule) {
        Optional<Schedule> scheduleOptional = this.scheduleRepository.findById(reqSchedule.getId());
        if(scheduleOptional.isPresent()) {
            Schedule schedule = scheduleOptional.get();
            schedule.setStartTime(reqSchedule.getStartTime());
            schedule.setEndTime(reqSchedule.getEndTime());
            return this.scheduleRepository.save(schedule);
        }
        return null;
    }

    public List<ResScheduleDTO> getScheduleByConsultantId(Long consultantId) {
        List<Schedule> lists = scheduleRepository.getSchedulesByConsultantId(consultantId);
        List<ResScheduleDTO> dtos = lists.stream().map(schedule -> new ResScheduleDTO(schedule.getStartTime(),
                schedule.getEndTime(),schedule.getDay())).toList();
        return  dtos;
    }

    public List<ResConsultantDTO> getConsultantByDay(LocalDate day){
        List<Schedule> schedule = scheduleRepository.getConsultantByDay(day);

        List<User> lists = schedule.stream().map(Schedule::getConsultant).toList();


        List<ResConsultantDTO> resConsultantDTOs = lists.stream().map(user -> new ResConsultantDTO(user.getFirstName(),
                user.getLastName(),
                user.getAvatar(),
                user.getId())).toList();

        return resConsultantDTOs;
    }

}
