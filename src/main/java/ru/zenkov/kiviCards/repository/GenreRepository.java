package ru.zenkov.kiviCards.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zenkov.kiviCards.models.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findByGenrename(String Genre_Name);
}
