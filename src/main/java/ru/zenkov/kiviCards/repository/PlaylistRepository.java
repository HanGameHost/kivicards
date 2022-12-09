package ru.zenkov.kiviCards.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zenkov.kiviCards.models.Playlist;

public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
    Playlist findByPlaylistname(String Playlist_Name);
}
