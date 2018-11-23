package mandatory.two.controller;

import mandatory.two.model.Student;
import mandatory.two.helper.PasswordHasher;
import mandatory.two.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
public class StudentController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/student/create")
    public String createStudentView(Model model){

        model.addAttribute("user", new Student());

        return "createStudent";
    }

    @PostMapping("/student/create")
    public String createStudent(@ModelAttribute Student student){

        try {
            student.setPassword(PasswordHasher.generateStrongPasswordHash(student.getPassword()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        userRepository.save(student);

        return "redirect:/student/create";
    }
}
