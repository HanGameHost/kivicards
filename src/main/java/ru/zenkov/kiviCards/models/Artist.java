package ru.zenkov.kiviCards.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 1, message = "Название должен быть больше 1 символа")
    private String artistname;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 5, message = "Описание должен быть больше 5 символов")
    private String description;
    @NotNull(message = "Выберите файл")
    private String avatar;

    @ManyToMany
    @JoinTable (name="artist_album",
            joinColumns=@JoinColumn (name="artist_id"),
            inverseJoinColumns=@JoinColumn(name="album_id"))
    private List<Album> albums;

    public Artist() {
    }

    public Artist(String artistname, String description, String avatar, List<Album> albums) {
        this.artistname = artistname;
        this.description = description;
        this.avatar = avatar;
        this.albums = albums;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
