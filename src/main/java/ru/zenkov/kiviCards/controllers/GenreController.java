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
import ru.zenkov.kiviCards.models.Genre;
import ru.zenkov.kiviCards.repository.GenreRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/genre")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/")
    public String genreMain(Model model){
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genre", genres);
        return "genre/genre-main";
    }

    @GetMapping("/add")
    public String addGenre(Model model, Genre genre){
        return "genre/genre-add";
    }

    @PostMapping("/add")
    public String PostaddGenre(@Valid Genre genre, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "genre/genre-add";
        }
        genreRepository.save(genre);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String genreDetails(@PathVariable(value = "id") long id, Model model){
        Genre genre = genreRepository.findById(id).orElseThrow();
        model.addAttribute("genre", genre);
        if (!genreRepository.existsById(id)){
            return "redirect:/";
        }
        return "genre/genre-details";
    }

    @GetMapping("/{id}/edit")
    public String genreEdit(@PathVariable(value = "id") long id, Model model, Genre genre){
        if (!genreRepository.existsById(id)){
            return "redirect:/";
        }
        genre = genreRepository.findById(id).orElseThrow();
        model.addAttribute("genre", genre);
        return "genre/genre-upd";
    }

    @PostMapping("/{id}/edit")
    public String genreUpd(@PathVariable(value = "id") long id, Model model,
                            @Valid Genre genre, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "genre/genre-upd";
        }
        genreRepository.save(genre);
        return "redirect:/";
    }
    @PostMapping("/{id}/remove")
    public String genreDelete(@PathVariable(value = "id") long id, Model model){
        Genre genre = genreRepository.findById(id).orElseThrow();
        genreRepository.delete(genre);
        return "redirect:/";
    }
}
