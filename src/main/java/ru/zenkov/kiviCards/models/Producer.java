package ru.zenkov.kiviCards.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 1, message = "Фамилия должена быть больше 2 символов")
    private String lastname;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 1, message = "Имя должено быть больше 2 символов")
    private String firstname;

    private String middlename;
    @NotNull(message = "Выберите файл")
    private String avatar;

    @OneToMany(mappedBy = "producer", fetch = FetchType.EAGER)
    private Collection<Track> tracks;

    public Producer() {
    }

    public Producer(String lastname, String firstname, String middlename, String avatar, Collection<Track> tracks) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.avatar = avatar;
        this.tracks = tracks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Collection<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Collection<Track> tracks) {
        this.tracks = tracks;
    }
}
