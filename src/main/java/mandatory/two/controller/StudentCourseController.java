package mandatory.two.controller;

import mandatory.two.repository.CourseRepository;
import mandatory.two.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentCourseController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

}
