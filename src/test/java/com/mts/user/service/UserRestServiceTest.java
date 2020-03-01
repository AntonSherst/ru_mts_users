package com.mts.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mts.user.model.User;
import com.mts.user.model.Users;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Класс тестирования обработки REST вызовов
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRestServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private void init() {
        this.restTemplate.postForEntity("/users", new User(null, "Иванов"), String.class);
    }

    @Test
    void create() {
        User user = new User(null, "Петров");
        ResponseEntity<String> entity = this.restTemplate.postForEntity("/users", user, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertTrue(entity.getHeaders().getLocation().toString().matches(".*users/\\d$"));
    }

    @Test
    void createError() {
        User user = new User(1L, "Петров2");
        ResponseEntity<String> entity = this.restTemplate.postForEntity("/users", user, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void getAll() throws JsonProcessingException {
        init();
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/users", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        ObjectMapper mapper = new ObjectMapper();
        Users requestContent = mapper.readValue(entity.getBody(), Users.class);
        assertThat(requestContent.getUsers()).hasSizeGreaterThan(0);
    }

    @Test
    void getUserById() {
        init();
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/users/2", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).contains("\"id\":2");
    }

    @Test
    void delete() {
        init();
        ResponseEntity<String> entity;
        entity = this.restTemplate.getForEntity("/users/1", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        this.restTemplate.delete("/users/1");
        entity = this.restTemplate.getForEntity("/users/1", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}