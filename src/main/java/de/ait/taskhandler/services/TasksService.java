package de.ait.taskhandler.services;

import de.ait.taskhandler.dto.NewTaskDto;
import de.ait.taskhandler.dto.TaskDto;
import de.ait.taskhandler.models.Task;

import java.util.List;

public interface TasksService {

    List<TaskDto> getAllTasks();

    TaskDto addTask(NewTaskDto newTask);

    TaskDto getTask(Long taskID);
}
