package de.ait.taskhandler.dto;

import de.ait.taskhandler.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "User", description = "Данные пользователя")
public class UserDto {

    @Schema(description = "идентификатор пользователя", example = "1")
    private Long id;

    @Schema(description = "имя пользователя", example = "Marsel")
    private String firstName;
    @Schema(description = "фамилия пользователя", example = "Sidikov")
    private String lastName;
    @Schema(description = "Email пользователя", example = "user@mail.com")
    private String email;
    @Schema(description = "Роль пользователя", example = "USER")
    private String role;

    public static UserDto from(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();
    }
}
