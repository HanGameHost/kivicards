package ru.zenkov.kiviCards.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 1, message = "Название должен быть больше 1 символа")
    private String labelname;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 1, message = "Название должен быть больше 1 символа")
    private String legaladdress;

    @OneToMany(mappedBy = "label", fetch = FetchType.EAGER)
    private Collection<Track> tracks;

    public Label() {
    }

    public Label(String labelname, String legaladdress, Collection<Track> tracks) {
        this.labelname = labelname;
        this.legaladdress = legaladdress;
        this.tracks = tracks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getLegaladdress() {
        return legaladdress;
    }

    public void setLegaladdress(String legaladdress) {
        this.legaladdress = legaladdress;
    }

    public Collection<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Collection<Track> tracks) {
        this.tracks = tracks;
    }
}
