package com.collabera.poc.product.service.impl;

import com.collabera.poc.product.dto.UserRequestDto;
import com.collabera.poc.product.entity.User;
import com.collabera.poc.product.exception.InvalidRequestParameterException;
import com.collabera.poc.product.repository.UserRepository;
import com.collabera.poc.product.service.UserService;
import com.collabera.poc.product.service.UserValidationService;
import com.collabera.poc.product.util.ErrorMessageUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplUTest {
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserService userService;

    @Mock
    private UserValidationService userValidationService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void addSuccess() {
        final UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("Lorem");
        userRequestDto.setAddress("Metro Manila");
        userRequestDto.setEmail("lorem.ipsum@gmail.com");

        final User expected = User.builder()
            .name("Lorem")
            .email("lorem.ipsum@gmail.com")
            .address("Metro Manila")
            .build();

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(expected);
        final User actual = userServiceImpl.add(userRequestDto);

        Assert.assertEquals(expected, actual);

        Mockito.verify(userValidationService, Mockito.times(1))
            .validateRequestBody(userRequestDto);
    }

    @Test
    public void convertDtoToUser() {
        final User user = User.builder()
            .name("Lorem")
            .email("lorem.ipsum@gmail.com")
            .address("Metro Manila")
            .build();

        final UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("Lorem");
        userRequestDto.setAddress("Metro Manila");
        userRequestDto.setEmail("lorem.ipsum@gmail.com");

        final User actual = userServiceImpl.convertDtoToUser(userRequestDto);

        Assert.assertEquals(user, actual);
    }

    @Test
    public void addFailEmptyName() {
        final UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("");
        userRequestDto.setAddress("Metro Manila");
        userRequestDto.setEmail("lorem.ipsum@gmail.com");

        final User user = User.builder()
            .name("")
            .email("lorem.ipsum@gmail.com")
            .address("Metro Manila")
            .build();

        final InvalidRequestParameterException exception = new InvalidRequestParameterException(
            ErrorMessageUtil.ERROR_400_U_INVALID_NAME);

        Mockito.doNothing().when(userValidationService).validateRequestBody(userRequestDto);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        userServiceImpl.add(userRequestDto);

        Assert.assertEquals(
            exception.getErrorMessage().getErrorCode(),
            ErrorMessageUtil.ERROR_400_U_INVALID_NAME.getErrorCode());
    }

    @Test
    public void addFailNullName() {
        final UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName(null);
        userRequestDto.setAddress("Metro Manila");
        userRequestDto.setEmail("lorem.ipsum@gmail.com");

        final User user = User.builder()
            .name("")
            .email("lorem.ipsum@gmail.com")
            .address("Metro Manila")
            .build();

        final InvalidRequestParameterException exception = new InvalidRequestParameterException(
            ErrorMessageUtil.ERROR_400_U_INVALID_NAME);

        Mockito.doNothing().when(userValidationService).validateRequestBody(userRequestDto);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        userServiceImpl.add(userRequestDto);

        Assert.assertEquals(
            exception.getErrorMessage().getErrorCode(),
            ErrorMessageUtil.ERROR_400_U_INVALID_NAME.getErrorCode());
    }
}