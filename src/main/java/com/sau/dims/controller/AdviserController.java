package com.sau.dims.controller;

import com.sau.dims.model.Adviser;
import com.sau.dims.repository.AdviserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.regex.Pattern;

/*
 GET adviser page
 Add adviser
 Update adviser
 Delete adviser
 */

@Controller
public class AdviserController {
    @Autowired
    private AdviserRepository adviserRepository;

    @GetMapping("/adviser")
    public String getAdviser(Model model){
        List<Adviser> advisers = adviserRepository.findAll();
        model.addAttribute("advisers", advisers);
        return "adviser/index";
    }
    @GetMapping("/adviser/add")
    public String addAdviser(Model model){
        Adviser adviser = new Adviser();
        model.addAttribute("adviser",adviser);
        return "adviser/add";
    }
    @PostMapping("/adviser/add")
    public String addAdviser(@Valid Adviser adviser, BindingResult result){
        if (result.hasErrors()){
            return "adviser/add";
        }
        adviser.setName(convertFirstLetterToUpperCase(adviser.getName()));
        adviser.setDepartment(convertFirstLetterToUpperCase(adviser.getDepartment()));

        adviserRepository.save(adviser);
        return "redirect:/adviser";
    }
    @GetMapping("/adviser/update/{id}")
    public String updateAdviser(@PathVariable int id,Model model){
        Adviser adviser = adviserRepository.findById(id).orElseThrow(
                ()-> {throw new RuntimeException("Adviser not found!:"+id);}
        );
        model.addAttribute("adviser",adviser);
        return "/adviser/update";
    }
    @PostMapping("/adviser/update")
    public String updateAdviser(@Valid Adviser adviser, BindingResult result){
        if (result.hasErrors()){
            return "/adviser/update";
        }
        adviser.setName(convertFirstLetterToUpperCase(adviser.getName()));
        adviser.setDepartment(convertFirstLetterToUpperCase(adviser.getDepartment()));

        adviserRepository.save(adviser);
        return "redirect:/adviser";
    }
    @GetMapping("/adviser/delete/{id}")
    public String deleteAdviser(@PathVariable int id, Model model){
        Adviser adviser = adviserRepository.findById(id).orElseThrow(
                ()-> {throw new RuntimeException("Adviser not found!:"+id);}
        );
        model.addAttribute("adviser", adviser);
        return "redirect:/adviser";
    }
    @PostMapping("/adviser/delete/{id}")
    public String deleteAdviser(@PathVariable int id){
        adviserRepository.deleteById(id);
        return "redirect:/adviser";
    }

    private String convertFirstLetterToUpperCase(String input){
        return Pattern.compile("\\b(\\w)").matcher(input).replaceAll(m -> m.group().toUpperCase());
    }
}
