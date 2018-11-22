package mandatory.two.controller;

import mandatory.two.model.StudyProgramme;
import mandatory.two.repository.StudyProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class StudyProgrammeController {

    @Autowired
    private StudyProgrammeRepository studyProgrammeRepository;

    @GetMapping("/studyprogramme/create")
    public String createStudyProgrammeView(Model model){

        model.addAttribute("studyProgramme", new StudyProgramme());

        return "createStudyProgramme";
    }

    @PostMapping("/studyprogramme/create")
    public String createStudyProgramme(@ModelAttribute StudyProgramme studyProgramme){

        studyProgrammeRepository.save(studyProgramme);

        return "redirect:/studyprogramme";
    }

    @GetMapping("/studyprogramme")
    public String viewStudyProgramme(Model model){

        ArrayList<StudyProgramme> studyProgrammes = (ArrayList) studyProgrammeRepository.findAll();

        model.addAttribute("studyprogrammes", studyProgrammes);

        return "viewStudyProgramme";
    }

    @GetMapping("/studyprogramme/delete/{id}")
    public String deleteStudyProgramme(@PathVariable Long id){
        studyProgrammeRepository.deleteById(id);
        return "redirect:/studyprogramme";
    }
}
