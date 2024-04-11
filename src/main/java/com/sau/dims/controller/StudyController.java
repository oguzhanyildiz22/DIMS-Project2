package com.sau.dims.controller;

import com.sau.dims.model.Study;
import com.sau.dims.repository.StudyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class StudyController {
    @Autowired
    private StudyRepository studyRepository;

    @GetMapping("/study")
    public String getStudy(Model model){
        Iterable<Study> studyList = studyRepository.findAll();
        model.addAttribute("studyList", studyList);
        return "/study/index";
    }

    @GetMapping("/study/add")
    public String studyAdd(Model model){
        Study study = new Study();
        model.addAttribute("study", study);
        return "/study/addstudy";
    }

    @PostMapping("/study/add")
    public String studyAdd(@Valid Study study, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/study";
        }

        studyRepository.save(study);
        return "redirect:/study";
    }

    @GetMapping("/study/update")
    public String studyUpdate (@RequestParam("id") int id, Model model){
        Optional<Study> study = studyRepository.findById(id);
        model.addAttribute("study", study);
        return "/study/updatestudy";
     }

     @PostMapping("/study/update")
    public String studyUpdate(@Valid Study study, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/study";
        }
        studyRepository.save(study);
        return "redirect:/study";
     }

     @GetMapping("/study/delete")
    public String studyDelete(@RequestParam("id") int id, Model model){
        Optional<Study> study = studyRepository.findById(id);
        if(study.isEmpty()){
            throw new RuntimeException("id is not found = " + id);
        }
        model.addAttribute("study", study);
        return "/study/deletestudy";
     }

     @PostMapping("/study/delete")
    public String studyDelete(@RequestParam("id") int id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/study";
        }
        studyRepository.deleteById(id);
        return "redirect:/study";
     }


}
