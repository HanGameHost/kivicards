package ru.zenkov.kiviCards.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zenkov.kiviCards.models.Producer;

public interface ProducerRepository extends CrudRepository<Producer, Long> {
    Producer findByLastname(String Last_Name);
}
