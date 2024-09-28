package ru.gb.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class TimesheetApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);

		ProjectRepository projectRepo = ctx.getBean(ProjectRepository.class);
		for (int i = 1; i <= 5; i++) {
			Project project = new Project();
			project.setId((long)i);
			project.setName("Project #" + i);
			projectRepo.create(project);
		}

		TimesheetRepository timesheetRepository = ctx.getBean(TimesheetRepository.class);

		LocalDate cratedAt = LocalDate.now();
		for(int i = 1; i <= 10; i++){
			cratedAt = cratedAt.plusDays(1);
			Timesheet timesheet = new Timesheet();
			timesheet.setId((long)i);
			timesheet.setProjectName(ThreadLocalRandom.current().nextLong(1,6));
			timesheet.setCreatedAt(cratedAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));
			timesheetRepository.create(timesheet);
		}
	}

}
