package mandatory.two.controller;

import mandatory.two.helper.SessionHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @GetMapping("/forbidden")
    public String forbidden(){
        return "forbidden";
    }

    @GetMapping("/teacher/index")
    public String teacherIndex(HttpServletRequest request){

        return SessionHelper.redirectTeacher(request, "index");
    }
    @GetMapping("/administrator/index")
    public String administratorIndex(HttpServletRequest request){

        return SessionHelper.redirectAdministrator(request, "index");
    }
    @GetMapping("/student/index")
    public String studentIndex(HttpServletRequest request){

        return SessionHelper.redirectStudent(request, "index");
    }
}
