package mandatory.two.controller;

import mandatory.two.helper.SearchSpecification;
import mandatory.two.helper.SessionHelper;
import mandatory.two.model.Course;
import mandatory.two.repository.CourseRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class SearchController {
    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/search")
    public ModelAndView search(@RequestParam String search, HttpServletRequest request) {

        ArrayList<Course> courses = (ArrayList<Course>) courseRepository.findAll(Specification
                .where(SearchSpecification.doesFieldContain(search, "nameInDanish"))
                .or(SearchSpecification.doesFieldContain(search, "nameInEnglish"))
        );

        ModelAndView mav = new ModelAndView(SessionHelper.redirectStudent(request, "searchResult"));
        mav.getModel().put("courses", courses);

        HttpSession session = request.getSession();
        session.setAttribute("lastView", mav);

        return mav;
    }

    @GetMapping("/search/advanced")
    public String searchAdvanced(Model model, HttpServletRequest request) {
        model.addAttribute("course", new Course());
        return SessionHelper.redirectStudent(request, "searchAdvanced");
    }

    @PostMapping("search/advanced")
    public ModelAndView searchAdvanced(@ModelAttribute Course course, HttpServletRequest request) {

        ArrayList<Course> courses = (ArrayList<Course>) courseRepository.findAll(Specification
                .where(SearchSpecification.doesFieldContain(course.getNameInDanish(), "nameInDanish"))
                .and(SearchSpecification.doesFieldContain(course.getNameInEnglish(), "nameInEnglish"))
                .and(SearchSpecification.doesFieldContain(course.getExamForm(), "examForm"))
                .and(SearchSpecification.doesFieldContain(course.getLanguage(), "language"))
                .and(SearchSpecification.doesFieldContain(course.getClassCode(), "classCode"))
                .and(SearchSpecification.doesForeignKeyContain(course.getStudyProgramme().getName(), "name", "studyProgramme"))
        );

        ModelAndView mav = new ModelAndView(SessionHelper.redirectStudent(request,"searchResult"));
        mav.getModel().put("courses", courses);

        HttpSession session = request.getSession();
        session.setAttribute("lastView", mav);

        return mav;
    }
}
