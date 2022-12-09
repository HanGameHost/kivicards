package ru.zenkov.kiviCards.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zenkov.kiviCards.models.Album;

import java.util.List;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    List<Album> findByAlbumname(String Album_Name);
}
