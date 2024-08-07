package net.javaguides.springboot.controller;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
         return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> usersDto = userService.getUsers();
        return ResponseEntity.ok().body(usersDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

}
