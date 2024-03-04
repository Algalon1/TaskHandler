package de.ait.taskhandler.services.impl;

import de.ait.taskhandler.models.Task;
import de.ait.taskhandler.repositories.TasksRepositories;
import de.ait.taskhandler.services.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksServiceImpl implements TasksService {

    private final TasksRepositories tasksRepositories;

    @Override
    public List<Task> getAllTasks() {
        return tasksRepositories.findAll();
    }

    @Override
    public Task addTask(Task newTask) {

        Task task =  Task.builder()
                .title(newTask.getTitle())
                .description(newTask.getDescription())
                .assignTo(newTask.getAssignTo()).build();

        tasksRepositories.save(task);

        return task;
    }
}
