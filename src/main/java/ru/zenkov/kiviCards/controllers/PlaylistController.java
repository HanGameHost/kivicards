package ru.zenkov.kiviCards.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zenkov.kiviCards.models.Playlist;
import ru.zenkov.kiviCards.models.Track;
import ru.zenkov.kiviCards.repository.PlaylistRepository;
import ru.zenkov.kiviCards.repository.TrackRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private TrackRepository trackRepository;

    @GetMapping("/")
    public String playlistMain(Model model){
        Iterable<Playlist> playlists = playlistRepository.findAll();
        model.addAttribute("playlist", playlists);
        return "playlist/playlist-main";
    }

    @GetMapping("/add")
    public String addPlaylist(Model model, Playlist playlist){
        return "playlist/playlist-add";
    }

    @PostMapping("/add")
    public String PostaddPlaylist(@Valid Playlist playlist, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "playlist/playlist-add";
        }
        playlistRepository.save(playlist);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String playlistDetails(@PathVariable(value = "id") long id, Model model){
        Playlist playlist = playlistRepository.findById(id).orElseThrow();
        model.addAttribute("playlist", playlist);
        if (!playlistRepository.existsById(id)){
            return "redirect:/";
        }
        return "playlist/playlist-details";
    }

    @GetMapping("/{id}/edit")
    public String playlistEdit(@PathVariable(value = "id") long id, Model model, Playlist playlist){
        if (!playlistRepository.existsById(id)){
            return "redirect:/";
        }
        playlist = playlistRepository.findById(id).orElseThrow();
        model.addAttribute("playlist", playlist);
        return "playlist/playlist-upd";
    }

    @PostMapping("/{id}/edit")
    public String playlistUpd(@PathVariable(value = "id") long id, Model model,
                           @Valid Playlist playlist, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "playlist/playlist-upd";
        }
        playlistRepository.save(playlist);
        return "redirect:/";
    }
    @PostMapping("/{id}/remove")
    public String labelDelete(@PathVariable(value = "id") long id, Model model){
        Playlist playlist = playlistRepository.findById(id).orElseThrow();
        playlistRepository.delete(playlist);
        return "redirect:/";
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/con")
    public String albumCon(Model model){
        Iterable<Playlist> playlists = playlistRepository.findAll();
        Iterable<Track> tracks = trackRepository.findAll();
        model.addAttribute("playlist", playlists);
        model.addAttribute("tracks", tracks);
        return "playlist/playlist-con";
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @PostMapping("/con")
    public String albumConAdd(@RequestParam Long playlists, @RequestParam Long tracks,
                              Model model){
        Track track = trackRepository.findById(tracks).orElseThrow();
        Playlist playlist = playlistRepository.findById(playlists).orElseThrow();
        playlist.getTracks().add(track);
        playlistRepository.save(playlist);
        return "redirect:/playlist/con";
    }

}
