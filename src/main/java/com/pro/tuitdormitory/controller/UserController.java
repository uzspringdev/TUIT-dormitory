package com.pro.tuitdormitory.controller;

import com.pro.tuitdormitory.domain.User;
import com.pro.tuitdormitory.dto.request.UserDto;
import com.pro.tuitdormitory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        User user = userService.save(userDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {

        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/findByEmail/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
        User user = userService.findByEmail(email);

        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/getCurrentUser")
    public ResponseEntity<?> findByEmail() {
        Object currentUser = userService.getProfile();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }


    @PostMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        User user = userService.update(id, userDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok("User is deleted");
    }
}
