package spring.app.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.app.crud.dao.PersonDAO;
import spring.app.crud.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private PersonDAO personDAO;

    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String adminPage(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, Model model) {
        model.addAttribute("people", personDAO.index());
        return "adminPage";
    }

    @PatchMapping("/add")
    public String makeAdmin(@ModelAttribute("person") Person person) {
        System.out.println(person.getId());
        return "redirect:/people";
    }
}
