package de.ait.taskhandler.controllers;

import de.ait.taskhandler.models.Task;
import de.ait.taskhandler.services.TasksService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TasksService tasksService;

    @ResponseBody
    @GetMapping("/tasks")
    @Operation(summary = "Получение всех задач", description = "Доступно администратору системы")
    public List<Task> getAllTasks() {
        return tasksService.getAllTasks();
    }

    @ResponseBody
    @PostMapping("/tasks")
    @Operation(summary = "Добавление задачи", description = "Доступно администратору системы")
    public Task addTask(@RequestBody Task newTask) {
        return tasksService.addTask(newTask);
    }
}
