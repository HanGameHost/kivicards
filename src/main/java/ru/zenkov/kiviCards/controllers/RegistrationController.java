package ru.zenkov.kiviCards.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.zenkov.kiviCards.models.Role;
import ru.zenkov.kiviCards.models.User;
import ru.zenkov.kiviCards.repository.UserRepository;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping("/registration")
    public String regView(Model model){
        return "registration";
    }
    @PostMapping("/registration")
    public String regAction(Model model, User user){
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null){
            model.addAttribute("error", "Пользователь с таким именем существует");
            return "registration";
        }
        user.setActive(Boolean.TRUE);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }
}
