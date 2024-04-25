package com.sau.dims.controller;


import com.sau.dims.model.AdviserStudy;
import com.sau.dims.repository.AdviserStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;




@Controller
public class AdviserStudyController {

    @Autowired
    private AdviserStudyRepository adviserStudyRepository;

    //retrive all the data in adviser_study table

    @GetMapping("/adviserstudy")
    public String showAdviserStudy(Model model){
        List<AdviserStudy> adviserStudies = adviserStudyRepository.findAll();
        model.addAttribute("adviserStudies", adviserStudies);
        return "adviserstudy/index";
    }

    @GetMapping("/adviserstudy/add")
    public String addAdviserStudy(Model model){
        AdviserStudy adviserStudy = new AdviserStudy();
        model.addAttribute("adviserStudy",adviserStudy);
        return "adviserstudy/add";
    }

    @PostMapping("/adviserstudy/add")
    public String addAdviserStudy( @ModelAttribute AdviserStudy adviserStudy){
        adviserStudyRepository.save(adviserStudy);
        return "redirect:/adviserstudy";
    }


    @GetMapping("/adviserstudy/update/{id}")
    public String updateAdviser(@PathVariable int id, Model model){
       AdviserStudy  adviserStudy = adviserStudyRepository.findById(id).orElseThrow(
                ()-> {throw new RuntimeException("studis not found not found!:"+id);}
        );
        model.addAttribute("adviserStudy",adviserStudy);
        return "/adviserstudy/update";
    }


    @PostMapping("/adviser/update")
    public String updateAdviser(@ModelAttribute AdviserStudy adviserStudy){
        adviserStudyRepository.save(adviserStudy);
        return "redirect:/adviserstudy";
    }

    @GetMapping("/adviserstudy/delete/{id}")
    public String deleteAdviser(@PathVariable int id, Model model){
        AdviserStudy adviserStudy = adviserStudyRepository.findById(id).orElseThrow(
                ()-> {throw new RuntimeException("Adviser not found!:"+id);}
        );
        model.addAttribute("adviserStudy", adviserStudy);
        return "redirect:/adviserstudy";
    }


    @PostMapping("/adviserstudy/delete/{id}")
    public String deleteAdviserStudy(@PathVariable int id){
        adviserStudyRepository.deleteById(id);
        return "redirect:/adviserstudy";
    }


}
