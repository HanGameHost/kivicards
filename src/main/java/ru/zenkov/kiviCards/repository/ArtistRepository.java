package ru.zenkov.kiviCards.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zenkov.kiviCards.models.Artist;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
    Artist findByArtistname(String Artist_Name);
}
