package mandatory.two.controller;

import mandatory.two.model.Administrator;
import mandatory.two.model.Student;
import mandatory.two.passwordHandlers.PasswordHasher;
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
public class AdministratorController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/administrator/create")
    public String createAdministratorView(Model model){

        model.addAttribute("user", new Administrator());

        return "createAdministrator";
    }

    @PostMapping("/administrator/create")
    public String createAdministrator(@ModelAttribute Administrator administrator){

        try {
            administrator.setPassword(PasswordHasher.generateStrongPasswordHash(administrator.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        userRepository.save(administrator);
        return "redirect:/administrator/create";
    }
}
