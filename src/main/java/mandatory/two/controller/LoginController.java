package mandatory.two.controller;

import mandatory.two.model.User;
import mandatory.two.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    String error = "";

    @GetMapping("/login")
    public String login(Model model){

        model.addAttribute("error", error);

        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(defaultValue = "") String email,
                        @RequestParam(defaultValue = "") String password){

        User user = userRepository.findByEmail(email);
        System.out.println(user.getEmail());

        return "redirect:/forside";
    }

}
