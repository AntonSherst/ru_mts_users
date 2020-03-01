package com.mts.user.service;

import com.mts.user.model.User;
import com.mts.user.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;

/**
 * Класс тестирования сервиса
 */
@ExtendWith(MockitoExtension.class)
class UserRestServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserRestServiceImpl restService;

    @Test
    void create() {
        User user = new User(null, "Петров");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Response result = restService.create(user);
        Mockito.verify(userRepository).save(user);
        Assert.assertEquals(HttpStatus.CREATED.value(), result.getStatus());
    }

    @Test
    void update() {
        User dst = new User(1L, "пепе");
        User src = new User(1L, "Петров");
        Mockito.when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(dst));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(dst);
        Response result = restService.update(1L, src);
        Mockito.verify(userRepository).findById(1L);
        Mockito.verify(userRepository).save(Mockito.any(User.class));
        Assert.assertEquals(HttpStatus.OK.value(), result.getStatus());
    }

    @Test
    void getUserById() {
        Response result = restService.getUserById(1L);
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatus());
    }
}