package com.mts.user.repository;

import com.mts.user.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий пользователя и круд методы
 */
public interface UserRepository extends CrudRepository<User, Long> {

}
