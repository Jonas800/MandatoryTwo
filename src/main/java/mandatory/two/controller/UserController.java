package mandatory.two.controller;

import mandatory.two.model.Teacher;
import mandatory.two.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/teacher/create")
    public String createTeacherView(Model model){

        model.addAttribute("teacher", new Teacher());

        return "createTeacher";
    }

    @PostMapping("/teacher/create")
    public String createTeacher(@ModelAttribute Teacher teacher){
        userRepository.save(teacher);
        return "redirect:/teacher/create";
    }
}
