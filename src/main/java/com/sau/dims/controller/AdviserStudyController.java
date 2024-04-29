package com.sau.dims.controller;


import com.sau.dims.model.Adviser;
import com.sau.dims.model.AdviserStudy;
import com.sau.dims.model.Study;
import com.sau.dims.repository.AdviserRepository;
import com.sau.dims.repository.AdviserStudyRepository;
import com.sau.dims.repository.StudyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;




@Controller
public class AdviserStudyController {

    @Autowired
    private AdviserStudyRepository adviserStudyRepository;

    @Autowired
    private AdviserRepository adviserRepository;
    @Autowired
    private StudyRepository studyRepository;

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


    @PostMapping("/adviserstudy/update")
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

    @PostMapping("/adviser-study/supervise-for-adviser")
    public String superviseAdviser(@Valid @RequestParam int adviserId, @RequestParam int studyId, @RequestParam String involvedDate, @RequestParam int performance) {

            Adviser adviser = adviserRepository.findById(adviserId).orElseThrow(() -> new EntityNotFoundException("Adviser not found"));
            Study study = studyRepository.findById(studyId).orElseThrow(() -> new EntityNotFoundException("Study not found"));

            if (involvedDate == null || involvedDate.isBlank()){
                involvedDate = String.valueOf(LocalDate.now());
            }
            LocalDate date = LocalDate.parse(involvedDate);

            AdviserStudy adviserStudy = new AdviserStudy();
            adviserStudy.setAdviser(adviser);
            adviserStudy.setStudy(study);
            adviserStudy.setAdviserInvolvedDate(date);
            adviserStudy.setPerformance((long) performance);

            adviserStudyRepository.save(adviserStudy);

        return "redirect:/adviser";
    }
    @PostMapping("/adviser-study/supervise-for-study")
    public String superviseStudy(@Valid @RequestParam int adviserId, @RequestParam int studyId, @RequestParam String involvedDate, @RequestParam int performance) {

        Adviser adviser = adviserRepository.findById(adviserId).orElseThrow(() -> new EntityNotFoundException("Adviser not found"));
        Study study = studyRepository.findById(studyId).orElseThrow(() -> new EntityNotFoundException("Study not found"));

        if (involvedDate == null || involvedDate.isBlank()){
            involvedDate = String.valueOf(LocalDate.now());
        }
        LocalDate date = LocalDate.parse(involvedDate);

        AdviserStudy adviserStudy = new AdviserStudy();
        adviserStudy.setAdviser(adviser);
        adviserStudy.setStudy(study);
        adviserStudy.setAdviserInvolvedDate(date);
        adviserStudy.setPerformance((long) performance);

        adviserStudyRepository.save(adviserStudy);

        return "redirect:/study";
    }
    @PostMapping("/adviser-study/delete")
    public String deleteAdviserStudyForBridgeTable(@RequestParam int adviserStudyId){
        adviserStudyRepository.deleteById(adviserStudyId);
        return "redirect:/adviserstudy";
    }



}
