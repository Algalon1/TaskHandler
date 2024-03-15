package de.ait.taskhandler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Message", description = "Какое-либо сообщение с сервера")
public class StandardResponseDto {
    @Schema(description = "Возможное сообщение об ошибке, изменении состояния и т.д.")
    private String message;
}
