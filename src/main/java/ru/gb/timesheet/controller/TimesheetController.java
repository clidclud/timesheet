package ru.gb.timesheet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.ProjectService;
import ru.gb.timesheet.service.TimesheetResponse;
import ru.gb.timesheet.service.TimesheetService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/timesheets")
public class TimesheetController {

    // GET - получить - Не содержит тела
    // POST - добавить
    // PUT - изменить
    // PATCH - изменение
    // DELETE - удалить

    //@GetMapping("/timesheet/{id}") // Получить одну запись
    //@DeleteMapping("/timesheet/{id}") // Удалить конкретную запись
    //@PutMapping("/timesheet/{id}") // Обновить конкретную запись

    private final TimesheetService timesheetService;
    private final ProjectService projectService;

    public TimesheetController(TimesheetService timesheetService, ProjectService projectService){
        this.timesheetService = timesheetService;
        this.projectService = projectService;
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Timesheet>> getTimesheetsByProjectId(@PathVariable Long projectId) {
        List<Timesheet> timesheets = timesheetService.getTimesheetsByProjectId(projectId);
        return ResponseEntity.ok(timesheets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimesheetResponse> get(@PathVariable Long id) {
        Optional<Timesheet> timesheetOpt = timesheetService.getById(id);
        if (timesheetOpt.isPresent()) {
            Timesheet timesheet = timesheetOpt.get();
            Optional<Project> projectOpt = projectService.getById(timesheet.getProjectId());
            if (projectOpt.isPresent()) {
                String projectName = projectOpt.get().getName();
                TimesheetResponse response = new TimesheetResponse(timesheet, projectName);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Если проект не найден
            }
        }
        return ResponseEntity.notFound().build();
    }

//    // /timesheets/{id}
//    @GetMapping("/{id}") // Получить все
//    public ResponseEntity <Timesheet> get(@PathVariable Long id){
//        Optional<Timesheet> ts = service.getById(id);
//        if(ts.isPresent()){
//            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
////            return ResponseEntity.ok().body(ts.get());
//        } return ResponseEntity.notFound().build();
//    }

    @GetMapping
    public ResponseEntity<List<Timesheet>> getAll(
            @RequestParam(required = false) LocalDate createdAtAfter,
            @RequestParam(required = false) LocalDate createdAtBefore) {

        List<Timesheet> timesheets = timesheetService.getAll();

        if (createdAtAfter != null) {
            timesheets = timesheets.stream()
                    .filter(ts -> ts.getCreatedAt().isAfter(createdAtAfter))
                    .collect(Collectors.toList());
        }

        if (createdAtBefore != null) {
            timesheets = timesheets.stream()
                    .filter(ts -> ts.getCreatedAt().isBefore(createdAtBefore))
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(timesheets);
    }


    @PostMapping // создание нового ресурса
    public ResponseEntity <Timesheet> create(@RequestBody Timesheet timesheet){
        timesheet = timesheetService.create(timesheet);
        // 201 created
        return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        timesheetService.delete(id);
        // 204 no content
        return ResponseEntity.noContent().build();
    }
}