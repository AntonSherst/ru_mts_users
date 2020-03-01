package com.mts.user.service;

import com.mts.user.model.User;
import com.mts.user.model.Users;
import com.mts.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Класс сервис обработки rest вызова
 */
public class UserRestServiceImpl implements UserRestService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response create(User user) {
        if (user.getId() != null || user.getName() == null) {
            return Response.status(400).build();
        }
        try {
            User result = userRepository.save(user);
            return Response.created(new URI("/users/" + result.getId())).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }

    @Override
    public Response update(long id, User src) {
        if (src == null) {
            return Response.status(400).build();
        }
        try {
            User dst = userRepository.findById(id).orElse(null);
            if (dst == null) {
                return Response.status(404).build();
            }
            src.setId(id);
            BeanUtils.copyProperties(src, dst);
            dst = userRepository.save(dst);
            return Response.ok(dst).build();
        } catch (Exception e) {
            return Response.status(404).build();
        }
    }

    @Override
    public Users getAll() {
        Users users = new Users();
        Iterable<User> iterable = userRepository.findAll();
        iterable.forEach(it -> users.getUsers().add(it));
        return users;
    }

    @Override
    public Response delete(long id) {
        try {
            userRepository.deleteById(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(404).build();
        }
    }

    @Override
    public Response getUserById(long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return Response.status(404).build();
        }
        return Response.ok(user).build();
    }

    @Override
    public Response getUserLogin(long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return Response.status(404).build();
        }
        return Response.ok(user.getLogin()).build();
    }

}
