package mandatory.two.controller;

import mandatory.two.model.Course;
import mandatory.two.model.StudyProgramme;
import mandatory.two.repository.CourseRepository;
import mandatory.two.repository.StudyProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthias Skou 20/11/2018
 */

@Controller
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudyProgrammeRepository studyRepo;

    @GetMapping("/course")
    public String courses(Model model){
        //ArrayList<Course> courseList = (ArrayList<Course>) courseRepository.findAll();
        //System.out.println(courseList);
        ArrayList<StudyProgramme> studyList = (ArrayList<StudyProgramme>) studyRepo.findAll();
        System.out.println(studyList);
        // System.out.println(courseList.get(0).getLanguage());

        model.addAttribute("courses", studyRepo);
        return "courses";
    }

}
