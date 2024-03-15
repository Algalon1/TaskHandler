package de.ait.taskhandler.dto;

import de.ait.taskhandler.models.Task;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Task", description = "Описание курса")
public class TaskDTO {
    @Schema(description = "id задачи", example = "1")
    private Long id;
    @Schema(description = "Заголовок задачи", example = "Сделать форму регистрации")
    private String title;
    @Schema(description = "Описание задачи", example = "Добавить поля для ввода, кнопки, валидации")
    private String description;
    @Schema(description = "Кому назначена задача", example = "Алексу")
    private String assignTo;
    @Schema(description = "Статус задачи - DRAFT, TODO, IN_PROGRESS, TESTING, DONE", example = "TODO")
    private String status;


    public static TaskDTO from(Task task){
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .assignTo(task.getAssignTo())
                .status(task.getStatus())
                .build();
    }


    public static List<TaskDTO> from(List<Task> tasks){
        return tasks.stream()
                .map(TaskDTO::from)
                .toList();
    }
}
