package de.ait.taskhandler.services.impl;

import de.ait.taskhandler.dto.NewUserDto;
import de.ait.taskhandler.dto.UserDto;
import de.ait.taskhandler.exceptions.RestException;
import de.ait.taskhandler.models.User;
import de.ait.taskhandler.repositories.UsersRepository;
import de.ait.taskhandler.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDto register(NewUserDto newUser) {
        if (usersRepository.existsByEmail(newUser.getEmail())) {
            throw new RestException(HttpStatus.CONFLICT, "User with the email <" + newUser.getEmail() + "> already exists");
        }

        User user = User.builder()
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .role(User.Role.USER)
                .build();
        usersRepository.save(user);

        return UserDto.from(user);
    }
}
