package ru.zenkov.kiviCards.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zenkov.kiviCards.models.Album;
import ru.zenkov.kiviCards.models.Artist;
import ru.zenkov.kiviCards.models.Track;
import ru.zenkov.kiviCards.repository.AlbumRepository;
import ru.zenkov.kiviCards.repository.ArtistRepository;
import ru.zenkov.kiviCards.repository.TrackRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/")
    public String albumMain(Model model){
        Iterable<Album> albums = albumRepository.findAll();
        model.addAttribute("album", albums);
        return "album/album-main";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
    @GetMapping("/add")
    public String addAlbum(Model model, Album album){
        Iterable<Artist> artists = artistRepository.findAll();
        model.addAttribute("artist", artists);

        return "album/album-add";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
    @PostMapping("/add")
    public String PostaddAlbum(@Valid Album albums, BindingResult bindingResult, Model model,
                               @RequestParam Long artist){
        if (bindingResult.hasErrors()){
            return "album/album-add";
        }
        Artist artists = artistRepository.findById(artist).orElseThrow();
        List<Artist> listArtist = new ArrayList<>();
        listArtist.add(artists);
        albums.setArtists(listArtist);
        albumRepository.save(albums);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String albumDetails(@PathVariable(value = "id") long id, Model model){
        Album album = albumRepository.findById(id).orElseThrow();
        model.addAttribute("album", album);
        if (!albumRepository.existsById(id)){
            return "redirect:/";
        }
        return "album/album-details";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
    @GetMapping("/{id}/edit")
    public String albumEdit(@PathVariable(value = "id") long id, Model model, Album album){
        if (!albumRepository.existsById(id)){
            return "redirect:/";
        }
        album = albumRepository.findById(id).orElseThrow();
        model.addAttribute("album", album);
        model.addAttribute("artist", artistRepository.findAll());
        return "album/album-upd";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
    @PostMapping("/{id}/edit")
    public String albumUpd(@PathVariable(value = "id") long id, Model model,
                           @Valid Album album, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "album/album-upd";
        }


        albumRepository.save(album);
        return "redirect:/";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
    @PostMapping("/{id}/remove")
    public String albumDelete(@PathVariable(value = "id") long id, Model model){
        Album album = albumRepository.findById(id).orElseThrow();
        albumRepository.delete(album);
        return "redirect:/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
    @GetMapping("/con")
    public String albumCon(Model model){
        Iterable<Album> albums = albumRepository.findAll();
        Iterable<Track> tracks = trackRepository.findAll();
        model.addAttribute("albums", albums);
        model.addAttribute("tracks", tracks);
        return "album/album-con";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
    @PostMapping("/con")
    public String albumConAdd(@RequestParam Long tracks, @RequestParam Long albums,
            Model model){
        Track track = trackRepository.findById(tracks).orElseThrow();
        Album album = albumRepository.findById(albums).orElseThrow();
        album.getTracks().add(track);
        albumRepository.save(album);
        return "redirect:/album/con";
    }

}
