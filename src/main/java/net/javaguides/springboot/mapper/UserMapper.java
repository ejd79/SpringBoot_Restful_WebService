package net.javaguides.springboot.mapper;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getDateCreated(),
                user.getLastUpdated());
    }

    public static User maptoUser(UserDto userDto) {
       return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getDateCreated(),
                userDto.getLastUpdated());
    }
}
