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
import ru.zenkov.kiviCards.models.Label;
import ru.zenkov.kiviCards.repository.LabelRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/label")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class LabelController {

    @Autowired
    private LabelRepository labelRepository;

    @GetMapping("/")
    public String labelMain(Model model){
        Iterable<Label> labels = labelRepository.findAll();
        model.addAttribute("label", labels);
        return "label/label-main";
    }

    @GetMapping("/add")
    public String addLabel(Model model, Label label){
        return "label/label-add";
    }

    @PostMapping("/add")
    public String PostaddLabel(@Valid Label label, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "label/label-add";
        }
        labelRepository.save(label);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String genreDetails(@PathVariable(value = "id") long id, Model model){
        Label label = labelRepository.findById(id).orElseThrow();
        model.addAttribute("label", label);
        if (!labelRepository.existsById(id)){
            return "redirect:/";
        }
        return "label/label-details";
    }

    @GetMapping("/{id}/edit")
    public String genreEdit(@PathVariable(value = "id") long id, Model model, Label label){
        if (!labelRepository.existsById(id)){
            return "redirect:/";
        }
        label = labelRepository.findById(id).orElseThrow();
        model.addAttribute("label", label);
        return "label/label-upd";
    }

    @PostMapping("/{id}/edit")
    public String labelUpd(@PathVariable(value = "id") long id, Model model,
                           @Valid Label label, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "label/label-upd";
        }
        labelRepository.save(label);
        return "redirect:/";
    }
    @PostMapping("/{id}/remove")
    public String labelDelete(@PathVariable(value = "id") long id, Model model){
        Label label = labelRepository.findById(id).orElseThrow();
        labelRepository.delete(label);
        return "redirect:/";
    }

}
