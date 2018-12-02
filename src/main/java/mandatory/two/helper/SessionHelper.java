package mandatory.two.helper;

import mandatory.two.model.Administrator;
import mandatory.two.model.Student;
import mandatory.two.model.Teacher;
import mandatory.two.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionHelper {
    public static boolean isSessionValid(HttpServletRequest request) {
        HttpSession session = request.getSession();
        //System.out.println(session.getAttribute("user"));
        return session.getAttribute("user") != null;
    }

    public static boolean isAdministrator(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            if (session.getAttribute("user") instanceof Administrator) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isTeacher(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            if (session.getAttribute("user") instanceof Teacher) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isStudent(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            if (session.getAttribute("user") instanceof Student) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String loginRedirect(User user){
        if(user instanceof Student){
            return "redirect:/student/index";
        } else if(user instanceof Teacher){
            return "redirect:/teacher/index";
        } else if(user instanceof Administrator){
            return "redirect:/administrator/index";
        } else{
            return "redirect:/login";
        }
    }

    public static String redirectAdministrator(HttpServletRequest request, String view) {
        if (isSessionValid(request)) {
            if (isAdministrator(request)) {
                return "administrator/"+ view;
            } else {
                return "all/forbidden";
            }
        } else{
            return "redirect:/login";
        }
    }

    public static String redirectTeacher(HttpServletRequest request, String view) {
        if (isSessionValid(request)) {
            if (isTeacher(request)) {
                return "teacher/"+ view;
            } else {
                return "all/forbidden";
            }
        } else{
            return "redirect:/login";
        }
    }
    public static String redirectStudent(HttpServletRequest request, String view) {
        if (isSessionValid(request)) {
            if (isStudent(request)) {
                return "student/"+ view;
            } else {
                return "all/forbidden";
            }
        } else{
            return "redirect:/login";
        }
    }

    public static void logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
    }
}
