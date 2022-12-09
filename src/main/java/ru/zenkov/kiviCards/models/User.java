package ru.zenkov.kiviCards.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
public class User {
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
    @NotBlank
    @Email
    private String email;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 5, message = "Имя пользователя должено быть больше 5 символов")
    private String username;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 8, message = "Пароль должен быть больше 8 символов")
    private String password;
    @NotNull(message = "Выберите файл")
    private String avatar;
    private Boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "playlist", fetch = FetchType.EAGER)
    private Collection<Playlist> playlists;

    public User() {
    }

    public User(String lastname, String firstname, String middlename, String email, String username, String password, String avatar, Boolean active, Set<Role> roles, Collection<Playlist> playlists) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.email = email;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.active = active;
        this.roles = roles;
        this.playlists = playlists;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Collection<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Collection<Playlist> playlists) {
        this.playlists = playlists;
    }
}
