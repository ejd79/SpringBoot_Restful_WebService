package net.javaguides.springboot.service;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;

import java.util.List;


public interface UserService {
    UserDto createUser(UserDto user);
    List<UserDto> getUsers();
    UserDto getUserById(int id);
}
