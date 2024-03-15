package de.ait.taskhandler.services.impl;

import de.ait.taskhandler.dto.NewTaskDto;
import de.ait.taskhandler.dto.TaskDto;
import de.ait.taskhandler.exceptions.RestException;
import de.ait.taskhandler.models.Task;
import de.ait.taskhandler.repositories.TasksRepository;
import de.ait.taskhandler.services.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;

import static de.ait.taskhandler.dto.TaskDto.from;

@Service
@RequiredArgsConstructor
public class TasksServiceImpl implements TasksService {

    private final TasksRepository tasksRepository;

    @Override
    public TaskDto addTask(NewTaskDto newTask) {

        Task task =  Task.builder()
                .title(newTask.getTitle())
                .description(newTask.getDescription())
                .assignTo(newTask.getAssignTo())
                .status(Task.Status.DRAFT)
                .priority(Task.Priority.valueOf(newTask.getPriority()))
                .build();

        tasksRepository.save(task);
        return from(task);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = tasksRepository.findAll();
        return from(tasks);
    }

    @Override
    public TaskDto getTask(Long taskID) {
        Task task = tasksRepository.findById(taskID)
                .orElseThrow(()-> new RestException(HttpStatus.NOT_FOUND,"Task with  id <"+taskID+"> is not found"));
        return from(task);
    }
}
