package mandatory.two.controller;

import mandatory.two.helper.SessionHelper;
import mandatory.two.model.Student;
import mandatory.two.helper.PasswordHasher;
import mandatory.two.repository.StudentRepository;
import mandatory.two.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
public class StudentController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/student/view")
    public String viewStudents(Model model, HttpServletRequest request){

        model.addAttribute("students", studentRepository.findAll());

        return SessionHelper.redirectAdministrator(request, "viewStudent");
    }

    @GetMapping("/student/create")
    public String createStudentView(Model model, HttpServletRequest request){
        model.addAttribute("user", new Student());

        return SessionHelper.redirectAdministrator(request, "createStudent");
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
    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable Long id, HttpServletRequest request){
        if(SessionHelper.isAdministrator(request)) {
            studentRepository.deleteById(id);
        }
        return "redirect:/student/view";
    }
}
