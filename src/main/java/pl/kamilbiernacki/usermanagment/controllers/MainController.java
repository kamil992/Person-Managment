package pl.kamilbiernacki.usermanagment.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kamilbiernacki.usermanagment.models.PersonModel;
import pl.kamilbiernacki.usermanagment.models.forms.PersonForm;
import pl.kamilbiernacki.usermanagment.models.repositories.PersonRepository;
import pl.kamilbiernacki.usermanagment.models.services.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MainController {

    final
    PersonRepository personRepository;

    final
    PersonService personService;

    @Autowired
    public MainController(PersonRepository personRepository, PersonService personService) {
        this.personRepository = personRepository;
        this.personService = personService;
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
                            @RequestParam("password") String password,
                            Model model) {

        if (login.equals("user") && password.equals("user123")) {
            personService.setLogin(true);
            return "redirect:/";
        }

        model.addAttribute("isValid", "Wrong Login or Password!");
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

    @GetMapping("/update/{id}")
    public String updatePerson(@PathVariable("id") int id,
                               Model model){
        model.addAttribute("person", personRepository.findById(id));
        return "update";
    }

    @PostMapping("/update/{id}")
    public String postUpdate(@PathVariable("id") int id,
                             @ModelAttribute("person") PersonModel personModel,
                             Model model){


        model.addAttribute("person", personRepository.findById(id));
        personRepository.save(personModel);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personRepository.delete(id);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest requestHandler){
        personService.setLogin(false);
        requestHandler.changeSessionId();

        return "redirect:/login";
    }

}
