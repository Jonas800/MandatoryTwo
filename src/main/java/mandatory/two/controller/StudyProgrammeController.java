package mandatory.two.controller;

import mandatory.two.helper.SessionHelper;
import mandatory.two.model.StudyProgramme;
import mandatory.two.repository.StudyProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class StudyProgrammeController {

    @Autowired
    private StudyProgrammeRepository studyProgrammeRepository;

    @GetMapping("/studyprogramme/create")
    public String createStudyProgrammeView(Model model, HttpServletRequest request){

        model.addAttribute("studyProgramme", new StudyProgramme());

        return SessionHelper.redirectAdministrator(request, "administrator/createStudyProgramme");
    }

    @PostMapping("/studyprogramme/create")
    public String createStudyProgramme(@ModelAttribute StudyProgramme studyProgramme, HttpServletRequest request){

        if(SessionHelper.isAdministrator(request)) {
            studyProgrammeRepository.save(studyProgramme);
        }
        return "redirect:/studyprogramme";
    }

    @GetMapping("/studyprogramme")
    public String viewStudyProgramme(Model model, HttpServletRequest request){

        ArrayList<StudyProgramme> studyProgrammes = (ArrayList) studyProgrammeRepository.findAll();

        model.addAttribute("studyprogrammes", studyProgrammes);

        return SessionHelper.redirectAdministrator(request, "administrator/viewStudyProgramme");
    }

    @GetMapping("/studyprogramme/delete/{id}")
    public String deleteStudyProgramme(@PathVariable Long id){
        studyProgrammeRepository.deleteById(id);
        return "redirect:/studyprogramme";
    }
}
