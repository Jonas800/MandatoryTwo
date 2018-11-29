package mandatory.two.controller;

import mandatory.two.helper.SessionHelper;
import mandatory.two.model.*;
import mandatory.two.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * Created by Matthias Skou 20/11/2018
 */

@Controller
public class CourseController {
    @Autowired
    private CourseRepository courseRepo;
    // JPA Test
    @Autowired
    private StudyProgrammeRepository studyRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private TeacherRepository teacherRepo;
    @Autowired
    private StudentRepository studentRepo;

    private Long editId;

    @GetMapping("/course")
    public String courses(Model model, HttpServletRequest request) {
        List<Course> courseList = courseRepo.findAll();
        //ArrayList<StudyProgramme> studyList = (ArrayList<StudyProgramme>) studyRepo.findAll();
        //System.out.println(studyList);
        // System.out.println(courseList.get(0).getLanguage());
        //Course c = courseRepository.findByEcts(2);
        //System.out.println(c);
        model.addAttribute("courses", courseList);
        return SessionHelper.redirectTeacher(request, "courses");
    }

    @GetMapping("/course/delete/{id}")
    public String deleteCourse(@PathVariable Long id, HttpServletRequest request) {
        if (SessionHelper.isTeacher(request)) {
            courseRepo.deleteById(id);
        }
        return "redirect:/course";
    }

    @GetMapping("/course/edit/{id}")
    public String editCourseView(Model model, @PathVariable Long id, HttpServletRequest request) {
        Optional<Course> courseOptional = courseRepo.findById(id);
        Course course = courseOptional.get();
        editId = course.getId();
        model.addAttribute("course", course);
        model.addAttribute("teacher", teacherRepo.findAll());
        model.addAttribute("studyprogramme", studyRepo.findAll());
        return SessionHelper.redirectTeacher(request, "editCourse");
    }

    @PostMapping("/course/edit")
    public String editCourse(@ModelAttribute Course course, HttpServletRequest request) {
        if (SessionHelper.isTeacher(request)) {
            course.setId(editId);
            editId = null;
            courseRepo.save(course);
        }
        return "redirect:/course";
    }

    @GetMapping("/course/create")
    public String createCourse(Model model, HttpServletRequest request) {
        model.addAttribute("teacher", teacherRepo.findAll());
        model.addAttribute("studyprogramme", studyRepo.findAll());
        model.addAttribute("course", new Course());
        return SessionHelper.redirectTeacher(request, "createCourse");
    }

    @PostMapping("course/create")
    public String createCourse(@ModelAttribute Course course, HttpServletRequest request) {
        //System.out.println(course.toString());
        if (SessionHelper.isTeacher(request)) {
            courseRepo.save(course);
        }
        return "redirect:/course";
    }

    @GetMapping("/course/join/{id}")
    public ModelAndView joinCourse(@PathVariable Long id, HttpServletRequest request) {
        if (SessionHelper.isStudent(request)) {

            HttpSession session = request.getSession();
            Student student = (Student) session.getAttribute("user");

            Optional<Course> optionalCourse = courseRepo.findById(id);
            Course course = optionalCourse.get();

            course.addStudentToWaitingList(student);

            courseRepo.save(course);

            ModelAndView mav = (ModelAndView) session.getAttribute("lastView");

            return mav;
        } else {
            return new ModelAndView("all/forbidden");
        }
    }

    @GetMapping("/course/showmore")
    public String showMore(@RequestParam Long id, Model model, HttpServletRequest request) {
        if (SessionHelper.isSessionValid(request)) {
            Optional<Course> optionalCourse = courseRepo.findById(id);
            Course course = optionalCourse.get();
            model.addAttribute("course", course);
            model.addAttribute("userType", request.getSession().getAttribute("user").getClass().getSimpleName());
            return "all/courseInfo";
        } else {
            return "all/forbidden";
        }
    }

    @GetMapping("/course/students/{id}")
    public String courseStudents(@PathVariable Long id, Model model, HttpServletRequest request) {
        Optional<Course> optionalCourse = courseRepo.findById(id);
        Course course = optionalCourse.get();
        model.addAttribute("student", studentRepo.findAll());
        model.addAttribute("course", course);
        return SessionHelper.redirectTeacher(request, "courseStudents");
    }

}
