package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.Schedule;
import com.group7.swp391.drug_prevention.domain.request.ReqScheduleDTO;
import com.group7.swp391.drug_prevention.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/getschedules")
    public ResponseEntity<List<Schedule>> getSchedules() {
        List<Schedule> schedules = scheduleService.findAllResScheduleDTO();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @PostMapping("/createSchedule")
    public ResponseEntity<?> createSchedule(@RequestBody ReqScheduleDTO reqScheduleDTO) {
        Schedule savedSchedule = scheduleService.createSchedule(reqScheduleDTO);
        if(savedSchedule != null) {
            return new ResponseEntity<>(savedSchedule, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("The id is not consultant!!!",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSchedule(@RequestParam long id) {
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>("The schedule deleted!!!", HttpStatus.OK);
    }
}
