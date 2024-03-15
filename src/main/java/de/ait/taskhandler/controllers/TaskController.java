package de.ait.taskhandler.controllers;

import de.ait.taskhandler.models.Task;
import de.ait.taskhandler.services.TasksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
@Tags(value = {
        @Tag(name = "Courses")
})
public class TaskController {

    private final TasksService tasksService;

    @ResponseBody
    @PostMapping
    @Operation(summary = "Добавление задачи", description = "Доступно администратору системы")
    public Task addTask(@RequestBody Task newTask) {
        return tasksService.addTask(newTask);
    }

    @ResponseBody
    @GetMapping("/tasks")
    @Operation(summary = "Получение всех задач", description = "Доступно администратору системы")
    public List<Task> getAllTasks() {
        return tasksService.getAllTasks();
    }



}
