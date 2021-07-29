package com.collabera.poc.product.controller;

import com.collabera.poc.product.dto.UserRequestDto;
import com.collabera.poc.product.entity.User;
import com.collabera.poc.product.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Create User
     *
     * @param userRequestDto
     * @return
     */
    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody final UserRequestDto userRequestDto) {
        return new ResponseEntity<>(
            userService.add(userRequestDto),
            HttpStatus.CREATED);
    }
}
