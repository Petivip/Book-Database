package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/")
    public String homepage(Model model){
        model.addAttribute("books",bookRepository.findAll());
        return "index";
    }
    @GetMapping("/form")
    public String formpage(Model model){
        model.addAttribute("book",new book());
        return "form";
    }
    @PostMapping("/form")
    public String process(@Valid book book, BindingResult result){
        if(result.hasErrors()){
            return "form";
        }

        bookRepository.save(book);
        return "redirect:/";
    }

    @PostMapping("/searchlist")
    public String SearchPage(Model model,@RequestParam("search") String search) {
        model.addAttribute("books",bookRepository.findBySKUContainingIgnoreCaseOrAuthorContainingIgnoreCase(search,search));

        return "searchIndex";
    }

}
