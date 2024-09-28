package ru.gb.timesheet.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Timesheet {
    private Long id;
    private Long projectName;
    private int minutes;
    private LocalDate createdAt;

}