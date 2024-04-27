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
        Iterable<Study> studyList = studyRepository.findAllAscById();
        model.addAttribute("studyList", studyList);
        return "/study/index";
    }

    @PostMapping("/study/add")
    public String studyAdd(@Valid Study study, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/study";
        }

        studyRepository.save(study);
        return "redirect:/study";
    }

    @GetMapping("/study/update/{id}")
    @ResponseBody
    public Study studyUpdate (@PathVariable int id, Model model){
        return studyRepository.findById(id).orElseThrow(()->{throw new RuntimeException("Study not found!"+id);});
     }

     @PostMapping("/study/update")
    public String studyUpdate(@Valid Study study, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/study";
        }
        studyRepository.save(study);
        return "redirect:/study";
     }

     @GetMapping("/study/delete/{id}")
    public String studyDelete(@PathVariable int id, Model model){

        Study study = studyRepository.findById(id).orElseThrow(
                ()-> {throw new RuntimeException("Study not found!:"+id);}
        );
        model.addAttribute("study",study);
        return "redirect:/study";
     }

     @PostMapping("/study/delete/{id}")
    public String studyDelete(@PathVariable int id){
        studyRepository.deleteById(id);
        return "redirect:/study";
     }


}
