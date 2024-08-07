package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        //User savedUser = userRepository.save(UserMapper.maptoUser(userDto));
//        User savedUser = userRepository.save(modelMapper.map(userDto, User.class));
        User savedUser = userRepository.save(AutoUserMapper.MAPPER.mapToUser(userDto));
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                //.map((user) -> UserMapper.mapToUserDto(user))
                .map((user) -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(int id) {
        Optional<User> savedUser = userRepository.findById(id);
//        return UserMapper.mapToUserDto(savedUser.get());
        return modelMapper.map(savedUser.get(), UserDto.class);
    }



}
