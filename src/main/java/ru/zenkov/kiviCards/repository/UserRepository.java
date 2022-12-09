package ru.zenkov.kiviCards.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zenkov.kiviCards.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
