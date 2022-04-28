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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    //get mapping is not good for security
        //  getmapping is where we define which method we
        // use for the /map on url

    @GetMapping("/delete")
    //this will delete a patient by id
    public String delete(Long id,String keyword,int page){
        patientRepository.deleteById(id);
        //then redirecting to index
        return "redirect:/index?page="+page+"&keyword="+keyword;

    }
    @GetMapping("/")
    public String home(){

        //then refreshing the page
        return "redirect:/index";

    }

    //returns patient list as a json file
    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }

    @GetMapping("/formPatients")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }

    @PostMapping(path = "/save")
    public String save(Model model,Patient patient){
      //to save patient in database we use
        patientRepository.save(patient);


        return "formPatients";
    }

}
