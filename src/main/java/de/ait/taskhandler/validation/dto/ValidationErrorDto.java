package de.ait.taskhandler.validation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "ValidationError", description = "Описание ошибки валидации")
public class ValidationErrorDto {

    @Schema(description = "название поля, в котором возникла ошибка", example = "title")
    private String field;
    @Schema(description = "значение, которое ввёл пользователь и которое было не принято сервером", example = "Lorem ipsum dolor sit amet," +
            " consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis nat\n")
    private String rejectedValue;
    @Schema(description = "сообщение, которое мы должны показать пользователю", example = "must be less than or equal 100")
    private String message;
}
