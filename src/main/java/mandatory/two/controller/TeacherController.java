package mandatory.two.controller;

import mandatory.two.helper.SessionHelper;
import mandatory.two.model.Teacher;
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
public class TeacherController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/teacher/create")
    public String createTeacherView(Model model, HttpServletRequest request) {

        model.addAttribute("user", new Teacher());

        return SessionHelper.redirectAdministrator(request, "/createTeacher");
    }

    @PostMapping("/teacher/create")
    public String createTeacher(@ModelAttribute Teacher teacher, HttpServletRequest request) {

        if (SessionHelper.isAdministrator(request)) {
            try {
                teacher.setPassword(PasswordHasher.generateStrongPasswordHash(teacher.getPassword()));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }

            userRepository.save(teacher);
        }
        return "redirect:/teacher/create";
    }
}
