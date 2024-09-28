package ru.gb.timesheet.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class TimesheetPageDto {

    private String id;
    private String projectName;
    private String minutes;
    private String createdAt;
}
