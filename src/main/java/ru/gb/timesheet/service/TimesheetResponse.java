package ru.gb.timesheet.service;

import ru.gb.timesheet.model.Timesheet;

import java.time.LocalDate;

public class TimesheetResponse {
    private Long id;
    private String projectName;
    private int minutes;
    private LocalDate createdAt;

    public TimesheetResponse(Timesheet timesheet, String projectName) {
        this.id = timesheet.getId();
        this.projectName = projectName;
        this.minutes = timesheet.getMinutes();
        this.createdAt = timesheet.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}