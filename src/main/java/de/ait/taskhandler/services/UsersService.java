package de.ait.taskhandler.services;

import de.ait.taskhandler.dto.NewUserDto;
import de.ait.taskhandler.dto.UserDto;

public interface UsersService {
    UserDto register(NewUserDto newUser);
}
