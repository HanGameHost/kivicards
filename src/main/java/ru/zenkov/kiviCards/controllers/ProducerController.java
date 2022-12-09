package ru.zenkov.kiviCards.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zenkov.kiviCards.models.Producer;
import ru.zenkov.kiviCards.repository.ProducerRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/producer")
public class ProducerController {
    @Autowired
    private ProducerRepository producerRepository;

    @GetMapping("/")
    public String producerMain(Model model){
        Iterable<Producer> producers = producerRepository.findAll();
        model.addAttribute("producer", producers);
        return "producer/producer-main";
    }

    @GetMapping("/add")
    public String addProducer(Model model, Producer producer){
        return "producer/producer-add";
    }

    @PostMapping("/add")
    public String PostaddProducer(@Valid Producer producer, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "producer/producer-add";
        }
        producerRepository.save(producer);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String producerDetails(@PathVariable(value = "id") long id, Model model){
        Producer producer = producerRepository.findById(id).orElseThrow();
        model.addAttribute("producer", producer);
        if (!producerRepository.existsById(id)){
            return "redirect:/";
        }
        return "producer/producer-details";
    }

    @GetMapping("/{id}/edit")
    public String producerEdit(@PathVariable(value = "id") long id, Model model, Producer producer){
        if (!producerRepository.existsById(id)){
            return "redirect:/";
        }
        producer = producerRepository.findById(id).orElseThrow();
        model.addAttribute("producer", producer);
        return "producer/producer-upd";
    }

    @PostMapping("/{id}/edit")
    public String producerUpd(@PathVariable(value = "id") long id, Model model,
                              @Valid Producer producer, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "producer/producer-upd";
        }
        producerRepository.save(producer);
        return "redirect:/";
    }
    @PostMapping("/{id}/remove")
    public String labelDelete(@PathVariable(value = "id") long id, Model model){
        Producer producer = producerRepository.findById(id).orElseThrow();
        producerRepository.delete(producer);
        return "redirect:/";
    }
}
