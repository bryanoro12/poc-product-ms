package com.collabera.poc.product.service;

import com.collabera.poc.product.dto.UserRequestDto;
import com.collabera.poc.product.entity.User;

public interface UserService {
    User add(UserRequestDto userRequestDto);

    User convertDtoToUser(UserRequestDto userRequestDto);
}
