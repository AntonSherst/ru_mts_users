package com.mts.user.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс обертка для списка пользователей rest
 */
public class Users {

    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
