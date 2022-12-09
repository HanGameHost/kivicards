package ru.zenkov.kiviCards.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 1, message = "Название должена быть больше 1 символа")
    private String trackname;
    @NotNull(message = "Выберите файл")
    private String trackfile;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Genre genre;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Label label;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Producer producer;

    @ManyToMany
    @JoinTable (name="artist_track",
            joinColumns=@JoinColumn (name="track_id"),
            inverseJoinColumns=@JoinColumn(name="album_id"))
    private List<Album> albums;

    @ManyToMany
    @JoinTable (name="track_playlist",
            joinColumns=@JoinColumn (name="track_id"),
            inverseJoinColumns=@JoinColumn(name="playlist_id"))
    private List<Playlist> playlists;

    public Track() {
    }

    public Track(String trackname, String trackfile, Genre genre, Label label, Producer producer, List<Album> albums, List<Playlist> playlists) {
        this.trackname = trackname;
        this.trackfile = trackfile;
        this.genre = genre;
        this.label = label;
        this.producer = producer;
        this.albums = albums;
        this.playlists = playlists;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackname() {
        return trackname;
    }

    public void setTrackname(String trackname) {
        this.trackname = trackname;
    }

    public String getTrackfile() {
        return trackfile;
    }

    public void setTrackfile(String trackfile) {
        this.trackfile = trackfile;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}
