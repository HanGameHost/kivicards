package ru.zenkov.kiviCards.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zenkov.kiviCards.models.Track;

import java.util.List;

public interface TrackRepository extends CrudRepository<Track, Long> {
    List<Track> findByTracknameContaining(String Track_Name);
}
