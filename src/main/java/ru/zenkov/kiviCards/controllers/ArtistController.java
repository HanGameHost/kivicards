package ru.zenkov.kiviCards.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zenkov.kiviCards.models.Artist;
import ru.zenkov.kiviCards.repository.ArtistRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/")
    public String artistMain(Model model){
        Iterable<Artist> artists = artistRepository.findAll();
        model.addAttribute("artist", artists);
        return "artist/artist-main";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
    @GetMapping("/add")
    public String addArtist(Model model, Artist artist){
        return "artist/artist-add";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
    @PostMapping("/add")
    public String PostaddArtist(@Valid Artist artist, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "artist/artist-add";
        }
        artistRepository.save(artist);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String artistDetails(@PathVariable(value = "id") long id, Model model){
        Artist artist = artistRepository.findById(id).orElseThrow();
        model.addAttribute("artist", artist);
        if (!artistRepository.existsById(id)){
            return "redirect:/";
        }
        return "artist/artist-details";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
    @GetMapping("/{id}/edit")
    public String artistEdit(@PathVariable(value = "id") long id, Model model, Artist artist){
        if (!artistRepository.existsById(id)){
            return "redirect:/";
        }
        artist = artistRepository.findById(id).orElseThrow();
        model.addAttribute("artist", artist);
        return "artist/artist-upd";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
    @PostMapping("/{id}/edit")
    public String artistUpd(@PathVariable(value = "id") long id, Model model,
                           @Valid Artist artist, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "artist/artist-upd";
        }
        artistRepository.save(artist);
        return "redirect:/";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/{id}/remove")
    public String artistDelete(@PathVariable(value = "id") long id, Model model){
        Artist artist = artistRepository.findById(id).orElseThrow();
        artistRepository.delete(artist);
        return "redirect:/";
    }

}
