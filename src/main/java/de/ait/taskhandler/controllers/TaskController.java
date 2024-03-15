package de.ait.taskhandler.controllers;

import de.ait.taskhandler.dto.NewTaskDto;
import de.ait.taskhandler.dto.StandardResponseDto;
import de.ait.taskhandler.dto.TaskDto;
import de.ait.taskhandler.models.Task;
import de.ait.taskhandler.services.TasksService;
import de.ait.taskhandler.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
@Tags(value = {
        @Tag(name = "Tasks")
})
public class TaskController {

    private final TasksService tasksService;

    @PostMapping
    @Operation(summary = "Добавление задачи", description = "Доступно администратору системы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Задача была успешно создана",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Ошибка валидации, создать задачу не удалось",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorsDto.class)))
    }
    )
    public ResponseEntity<TaskDto> addTask(@RequestBody @Valid NewTaskDto newTask) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tasksService.addTask(newTask));
    }


    @GetMapping
    @Operation(summary = "Получение всех задач", description = "Доступно администратору системы")
    public List<TaskDto> getAllTasks() {
        return tasksService.getAllTasks();
    }


    @Operation(summary = "Получение конкретной задачи", description = "Доступно всем пользователям")
    @GetMapping("/{task-id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Запрос обработан успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Задача не найдена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))})
    public ResponseEntity<TaskDto> getTask(@Parameter(description = "id задачи", example = "1")
                                           @PathVariable("task-id") Long taskID) {
        return ResponseEntity.ok(tasksService.getTask(taskID));
    }
}
