package ru.gb.timesheet.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimesheetService {

    private final TimesheetRepository repository;
    private final ProjectRepository projectRepository;

    public TimesheetService(TimesheetRepository repository, ProjectRepository projectRepository) {
        this.repository = repository;
        this.projectRepository = projectRepository;
    }

    public Optional<Timesheet> getById(Long id) {
        return repository.getById(id);
    }

    public List<Timesheet> getAll() {
        return repository.getAll();
    }

    public Timesheet create(Timesheet timesheet) {
        timesheet.setCreatedAt(LocalDate.now());
        if (!projectRepository.existsById(timesheet.getProjectId())) {
            throw new IllegalArgumentException("Проект с id " + timesheet.getProjectId() + " не найден");
        }
        return repository.create(timesheet);
    }

        public void delete (Long id){
            repository.delete(id);
        }

    public List<Timesheet> getTimesheetsByProjectId(Long projectId) {
        return repository.getAll().stream()
                .filter(timesheet -> Objects.equals(timesheet.getProjectId(), projectId))
                .collect(Collectors.toList());
    }
    }
