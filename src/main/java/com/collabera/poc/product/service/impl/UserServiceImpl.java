package com.collabera.poc.product.service.impl;

import com.collabera.poc.product.dto.UserRequestDto;
import com.collabera.poc.product.entity.User;
import com.collabera.poc.product.repository.UserRepository;
import com.collabera.poc.product.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    /**
     * Add User
     *
     * @param userRequestDto
     * @return
     */
    @Override
    public User add(final UserRequestDto userRequestDto) {
        log.info("Request: {}", userRequestDto.toString());
        log.info("Adding User...");
        return userRepository.save(this.convertDtoToUser(userRequestDto));
    }

    /**
     * Convert UserDTO to User
     *
     * @param userRequestDto
     * @return
     */
    @Override
    public User convertDtoToUser(final UserRequestDto userRequestDto) {
        log.info("Converting DTO to User...");
        final User user = User.builder()
            .name(userRequestDto.getName())
            .email(userRequestDto.getName())
            .address(userRequestDto.getAddress())
            .build();

        log.info("User save successfully.");
        log.info("User: {}", user.toString());
        return user;
    }
}
