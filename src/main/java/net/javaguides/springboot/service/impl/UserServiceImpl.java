package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
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

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email already exists for User");
        }

        User savedUser = userRepository.save(modelMapper.map(userDto, User.class));

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
    public UserDto getUserById(Long userId) {
        User savedUser = userRepository.findById(Math.toIntExact(userId)).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
//        return UserMapper.mapToUserDto(savedUser.get());
        return modelMapper.map(savedUser, UserDto.class);
//        return AutoUserMapper.MAPPER.mapToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId().intValue()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDto.getId())
        );
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }


}
