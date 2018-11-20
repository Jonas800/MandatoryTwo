package mandatory.two.controller;

import mandatory.two.model.Course;
import mandatory.two.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by Matthias Skou 20/11/2018
 */

@Controller
public class CourseController {
    @Autowired
    private CourseRepository courseRepo;

    @GetMapping("")
    public String courses(Model model){
        model.addAttribute("course", new Course());
        return "courses";
    }

}
