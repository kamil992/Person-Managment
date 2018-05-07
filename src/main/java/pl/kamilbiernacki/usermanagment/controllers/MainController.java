package pl.kamilbiernacki.usermanagment.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kamilbiernacki.usermanagment.models.PersonModel;
import pl.kamilbiernacki.usermanagment.models.forms.PersonForm;
import pl.kamilbiernacki.usermanagment.models.repositories.PersonRepository;

import javax.validation.Valid;

@Controller
public class MainController {

    final
    PersonRepository personRepository;

    @Autowired
    public MainController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("persons", personRepository.findAllByOrderByIdAsc());
        return "index";
    }

    @GetMapping("/login")
    public String Login() {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam("login") String login,
                            @RequestParam("password") String password) {

        if (login.equals("user") && password.equals("user123")) {
            return "redirect:/";
        }

        return "login";
    }

    @GetMapping("/newperson")
    public String getPerson(Model model) {
        model.addAttribute("personForm", new PersonForm());
            return "newperson";
    }

    @PostMapping("/newperson")
    public String postPerson(@Valid @ModelAttribute("personForm") PersonForm personForm,
                             BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "newperson";
        }
        PersonModel personModel = new PersonModel(personForm);
        personRepository.save(personModel);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") int id){
        personRepository.delete(id);
        return "redirect:/";
    }

}
