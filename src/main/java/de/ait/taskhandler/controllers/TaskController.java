package de.ait.taskhandler.controllers;

import de.ait.taskhandler.dto.NewTaskDto;
import de.ait.taskhandler.dto.TaskDto;
import de.ait.taskhandler.models.Task;
import de.ait.taskhandler.services.TasksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    @Operation(summary = "Добавление задачи", description = "Доступно администратору системы")
    public TaskDto addTask(@RequestBody NewTaskDto newTask) {
        return tasksService.addTask(newTask);
    }

    @ResponseBody
    @GetMapping("/tasks")
    @Operation(summary = "Получение всех задач", description = "Доступно администратору системы")
    public List<TaskDto> getAllTasks() {
        return tasksService.getAllTasks();
    }




    @Operation(summary = "Получение конкретной задачи", description = "Доступно всем пользователям")
    @GetMapping("/{task-id}")
    public ResponseEntity<TaskDto> getTask(@Parameter(description = "id задачи", example = "1")
                                           @PathVariable("task-id") Long taskID) {
        return ResponseEntity.ok(tasksService.getTask(taskID));
    }

}
