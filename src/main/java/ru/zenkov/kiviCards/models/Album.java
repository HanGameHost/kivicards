package ru.zenkov.kiviCards.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 1, message = "Название должен быть больше 1 символа")
    private String albumname;

    @NotNull(message = "Поле не должно быть пустым")
    private String releasedate;
    @NotNull(message = "Выберите файл")
    private String photo;

    @ManyToMany
    @JoinTable (name="artist_album",
            joinColumns=@JoinColumn (name="album_id"),
            inverseJoinColumns=@JoinColumn(name="artist_id"))
    private List<Artist> artists;

    @ManyToMany
    @JoinTable (name="artist_track",
            joinColumns=@JoinColumn (name="album_id"),
            inverseJoinColumns=@JoinColumn(name="track_id"))
    private List<Track> tracks;


    public Album() {
    }

    public Album(String albumname, String releasedate, String photo, List<Artist> artists, List<Track> tracks) {
        this.albumname = albumname;
        this.releasedate = releasedate;
        this.photo = photo;
        this.artists = artists;
        this.tracks = tracks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
