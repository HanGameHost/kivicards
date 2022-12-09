package ru.zenkov.kiviCards.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zenkov.kiviCards.models.Genre;
import ru.zenkov.kiviCards.models.Label;
import ru.zenkov.kiviCards.models.Producer;
import ru.zenkov.kiviCards.models.Track;
import ru.zenkov.kiviCards.repository.GenreRepository;
import ru.zenkov.kiviCards.repository.LabelRepository;
import ru.zenkov.kiviCards.repository.ProducerRepository;
import ru.zenkov.kiviCards.repository.TrackRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/track")
public class TrackController {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private LabelRepository labelRepository;

    @GetMapping("/")
    public String trackMain(Model model){
        Iterable<Track> tracks = trackRepository.findAll();
        model.addAttribute("track", tracks);
        return "track/track-main";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
    @GetMapping("/add")
    public String addTrack(Model model, Track track){
        Iterable<Genre> genres = genreRepository.findAll();
        Iterable<Producer> producers = producerRepository.findAll();
        Iterable<Label> labels = labelRepository.findAll();

        model.addAttribute("genre", genres);
        model.addAttribute("producer", producers);
        model.addAttribute("label", labels);

        return "track/track-add";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
    @PostMapping("/add")
    public String PostaddTrack(@Valid Track track, BindingResult bindingResult, Model model,
                               @RequestParam Long genres, @RequestParam Long producers,
                               @RequestParam Long labels){
        if (bindingResult.hasErrors()){
            Iterable<Genre> genre = genreRepository.findAll();
            Iterable<Producer> producer = producerRepository.findAll();
            Iterable<Label> label = labelRepository.findAll();

            model.addAttribute("genre", genre);
            model.addAttribute("producer", producer);
            model.addAttribute("label", label);
            return "track/track-add";
        }

        Genre genre = genreRepository.findById(genres).orElseThrow();
        Producer producer = producerRepository.findById(producers).orElseThrow();
        Label label = labelRepository.findById(labels).orElseThrow( );

        track.setGenre(genre);
        track.setProducer(producer);
        track.setLabel(label);

        trackRepository.save(track);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String trackDetails(@PathVariable(value = "id") long id, Model model){
        Track track = trackRepository.findById(id).orElseThrow();
        model.addAttribute("track", track);
        Iterable<Genre> genres = genreRepository.findAll();
        Iterable<Producer> producers = producerRepository.findAll();
        Iterable<Label> labels = labelRepository.findAll();

        model.addAttribute("genre", genres);
        model.addAttribute("producer", producers);
        model.addAttribute("label", labels);
        if (!trackRepository.existsById(id)){
            return "redirect:/";
        }
        return "track/track-details";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
    @GetMapping("/{id}/edit")
    public String trackEdit(@PathVariable(value = "id") long id, Model model, Track track){
        if (!trackRepository.existsById(id)){
            return "redirect:/";
        }
        Iterable<Genre> genres = genreRepository.findAll();
        Iterable<Producer> producers = producerRepository.findAll();
        Iterable<Label> labels = labelRepository.findAll();

        model.addAttribute("genre", genres);
        model.addAttribute("producer", producers);
        model.addAttribute("label", labels);
        track = trackRepository.findById(id).orElseThrow();
        model.addAttribute("track", track);
        return "track/track-upd";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
    @PostMapping("/{id}/edit")
    public String trackUpd(@PathVariable(value = "id") long id, Model model,
                              @Valid Track track, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "track/track-upd";
        }
        trackRepository.save(track);
        return "redirect:/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/{id}/remove")
    public String labelDelete(@PathVariable(value = "id") long id, Model model){
        Track track = trackRepository.findById(id).orElseThrow();
        trackRepository.delete(track);
        return "redirect:/";
    }

}
