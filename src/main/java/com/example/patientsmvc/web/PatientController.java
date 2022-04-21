package com.example.patientsmvc.web;

import com.example.patientsmvc.entities.Patient;
import com.example.patientsmvc.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    // @Autowired  not recommended (better use a constructor with parameters )

    private PatientRepository patientRepository;
    @GetMapping(path="/index")
    //if i go to http://localhost:8082/index this view will be triggered
    public String patients(Model model,
                           @RequestParam(name="page",defaultValue = "0") int page ,
                           @RequestParam(name="size",defaultValue = "5") int size,
                           @RequestParam(name="keyword",defaultValue = "") String keyword
                           ){
        // to return  values to the view
        Page<Patient> Pagepatients = patientRepository.findByNomContains(keyword,PageRequest .of(page, size));
        model.addAttribute("listPatients",Pagepatients.getContent());
        model.addAttribute("pages",new int[Pagepatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }

}
