package de.ait.taskhandler.services;

import de.ait.taskhandler.models.Task;

import java.util.List;

public interface TasksService {

    List<Task> getAllTasks();

    Task addTask(Task newTask);
}
