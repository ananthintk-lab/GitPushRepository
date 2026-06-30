package com.githubexample.githubdemo.task;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    private final TaskRepository taskRepository;

    public DataInitializer(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (taskRepository.count() == 0) {
            Task t1 = new Task("Fix critical production bug");
            t1.setDescription("Database connection pool exhaustion on high traffic");
            t1.setPriority(Priority.HIGH);

            Task t2 = new Task("Write unit tests for auth module");
            t2.setDescription("Cover edge cases for token expiry");
            t2.setPriority(Priority.MEDIUM);

            Task t3 = new Task("Update README documentation");
            t3.setDescription("Add setup instructions for new contributors");
            t3.setPriority(Priority.LOW);

            Task t4 = new Task("Deploy hotfix to staging");
            t4.setDescription("Urgently needed before tomorrow's release");
            t4.setPriority(Priority.HIGH);

            taskRepository.save(t1);
            taskRepository.save(t2);
            taskRepository.save(t3);
            taskRepository.save(t4);
        }
    }
}
