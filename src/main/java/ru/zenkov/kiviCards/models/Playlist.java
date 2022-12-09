package ru.zenkov.kiviCards.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 1, message = "Название должен быть больше 1 символа")
    private String playlistname;
    @NotNull(message = "Выберите файл")
    private String  photo;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Playlist playlist;

    @ManyToMany
    @JoinTable (name="track_playlist",
            joinColumns=@JoinColumn (name="playlist_id"),
            inverseJoinColumns=@JoinColumn(name="track_id"))
    private List<Track> tracks;

    public Playlist() {
    }

    public Playlist(String playlistname, String photo, Playlist playlist, List<Track> tracks) {
        this.playlistname = playlistname;
        this.photo = photo;
        this.playlist = playlist;
        this.tracks = tracks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaylistname() {
        return playlistname;
    }

    public void setPlaylistname(String playlistname) {
        this.playlistname = playlistname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
