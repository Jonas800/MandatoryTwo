package mandatory.two.controller;

import mandatory.two.helper.SessionHelper;
import mandatory.two.model.Student;
import mandatory.two.helper.PasswordHasher;
import mandatory.two.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
public class StudentController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/student/create")
    public String createStudentView(Model model, HttpServletRequest request){
        model.addAttribute("user", new Student());

        return SessionHelper.redirectAdministrator(request, "administrator/createStudent");
    }

    @PostMapping("/student/create")
    public String createStudent(@ModelAttribute Student student, HttpServletRequest request){

        if(SessionHelper.isAdministrator(request)) {
            try {
                student.setPassword(PasswordHasher.generateStrongPasswordHash(student.getPassword()));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }

            userRepository.save(student);
        }
        return "redirect:/student/create";
    }

}
