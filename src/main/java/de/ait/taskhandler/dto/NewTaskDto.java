package de.ait.taskhandler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "New Task", description = "Описание задачи")
public class NewTaskDto {

    @Schema(description = "Название задачи", example = "Создать форму регистрации")
    @NotNull  //Поле точно должно присутствовать в Http запросе
    @NotBlank  // Значение поля не может быть представлено набором пробелов
    @NotEmpty // Значение поля не может быть пустой строкой
    private String title;

    @Schema(description = "Описание задачи", example = "Добавить поля для ввода, кнопки, валидации")
    @Size(min = 5, max = 1000)
    @NotNull
    @NotBlank
    private String description;

    @Schema(description = "Кому назначена задача", example = "Алексу")
    @NotNull
    @NotBlank
    private String assignTo;

    @Schema(description = "Приоритет задачи", example = "High")
    @Pattern(regexp = "^(HIGHEST|HIGHT|MEDIUM|LOW|LOWEST)$")
    private String priority;

}
