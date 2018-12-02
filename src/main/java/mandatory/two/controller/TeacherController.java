package mandatory.two.controller;

import mandatory.two.helper.SessionHelper;
import mandatory.two.model.Teacher;
import mandatory.two.helper.PasswordHasher;
import mandatory.two.repository.TeacherRepository;
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
import java.util.ArrayList;

@Controller
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/teacher/view")
    public String teacherView(Model model, HttpServletRequest request){

        ArrayList<Teacher> teachers = (ArrayList) teacherRepository.findAll();

        model.addAttribute("teachers", teachers);

        return SessionHelper.redirectAdministrator(request, "viewTeacher");
    }

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

            teacherRepository.save(teacher);
        }
        return "redirect:/teacher/create";
    }

    @GetMapping("/teacher/delete/{id}")
    public String deleteTeacher(@PathVariable Long id, HttpServletRequest request){
        if(SessionHelper.isAdministrator(request)) {
            teacherRepository.deleteById(id);
        }
        return "redirect:/teacher/view";
    }
}
