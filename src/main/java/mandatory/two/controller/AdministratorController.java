package mandatory.two.controller;

import mandatory.two.helper.SessionHelper;
import mandatory.two.model.Administrator;
import mandatory.two.helper.PasswordHasher;
import mandatory.two.repository.AdministratorRepository;
import mandatory.two.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
public class AdministratorController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @GetMapping("/administrator/view")
    public String viewStudents(Model model, HttpServletRequest request){

        model.addAttribute("administrators", administratorRepository.findAll());

        return SessionHelper.redirectAdministrator(request, "viewAdministrator");
    }

    @GetMapping("/administrator/create")
    public String createAdministratorView(Model model, HttpServletRequest request) {
        model.addAttribute("user", new Administrator());

        //swap the commenting on the lines below to create a user if someone purges the db
        //and comment the session check in the next method
        return SessionHelper.redirectAdministrator(request, "createAdministrator");
        //return "administrator/createAdministrator";
    }

    @PostMapping("/administrator/create")
    public String createAdministrator(@ModelAttribute Administrator administrator, HttpServletRequest request) {

        if(SessionHelper.isAdministrator(request)) {
            try {
                administrator.setPassword(PasswordHasher.generateStrongPasswordHash(administrator.getPassword()));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }

            userRepository.save(administrator);
        }
        return "redirect:/administrator/create";
    }

    @GetMapping("/administrator/delete/{id}")
    public String deleteAdministrator(@PathVariable Long id, HttpServletRequest request){
        if(SessionHelper.isAdministrator(request)) {
            administratorRepository.deleteById(id);
        }
        return "redirect:/administrator/view";
    }
}
