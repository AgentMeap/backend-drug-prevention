package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.Schedule;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqScheduleDTO;
import com.group7.swp391.drug_prevention.domain.response.ResConsultantDTO;
import com.group7.swp391.drug_prevention.domain.response.ResScheduleDTO;
import com.group7.swp391.drug_prevention.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/v1")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<Schedule>> getSchedules() {
        List<Schedule> schedules = scheduleService.findAllResScheduleDTO();
        return ResponseEntity.ok(schedules);
    }

    @PutMapping("/schedules")
    public ResponseEntity<Schedule> updateSchedule(@Valid @RequestBody Schedule reqSchedule) {
        Schedule updateSchedule = this.scheduleService.handleUpdateSchedule(reqSchedule);
        return ResponseEntity.ok(updateSchedule);
    }

    @PostMapping("/schedules")
    @PreAuthorize("hasRole('CONSULTANT')")
    public ResponseEntity<?> createSchedule(@Valid @RequestBody ReqScheduleDTO reqScheduleDTO) {
        try {
            Schedule savedSchedule = scheduleService.createSchedule(reqScheduleDTO);
            if (savedSchedule != null) {
                return new ResponseEntity<>(savedSchedule, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Invalid consultant id.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable("id") long id) {
        this.scheduleService.deleteSchedule(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/getScheduleByConsultantId/{consultantId}")
    public List<ResScheduleDTO>  getScheduleByConsultantId(@PathVariable long consultantId) {
        return scheduleService.getScheduleByConsultantId(consultantId);
    }

    @GetMapping("/getConsultantByDay/{day}")
    public List<ResConsultantDTO> getScheduleByDay(@PathVariable LocalDate day) {
        return scheduleService.getConsultantByDay(day);
    }

}
