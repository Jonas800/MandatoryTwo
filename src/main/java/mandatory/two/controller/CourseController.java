package mandatory.two.controller;

import mandatory.two.model.*;
import mandatory.two.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    public String courses(Model model){
        List<Course> courseList = courseRepo.findAll();
        //ArrayList<StudyProgramme> studyList = (ArrayList<StudyProgramme>) studyRepo.findAll();
        //System.out.println(studyList);
        // System.out.println(courseList.get(0).getLanguage());
        //Course c = courseRepository.findByEcts(2);
        //System.out.println(c);
        model.addAttribute("courses", courseList);
        return "courses";
    }

    @GetMapping("/course/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseRepo.deleteById(id);
        return "redirect:/course";
    }
    @GetMapping("/course/edit/{id}")
    public String editCourseView(Model model, @PathVariable Long id){
        Optional<Course> courseOptional = courseRepo.findById(id);
        Course course = courseOptional.get();
        editId = course.getId();
        model.addAttribute("course", course);
        model.addAttribute("teacher", teacherRepo.findAll());
        model.addAttribute("studyprogramme", studyRepo.findAll());
        return "editCourse";
    }
    @PostMapping("/course/edit")
    public String editCourse(@ModelAttribute Course course) {
        course.setId(editId);
        editId = null;
        courseRepo.save(course);

        return "redirect:/course";
    }

    @GetMapping("/course/create")
    public String createCourse(Model model){
        model.addAttribute("teacher", teacherRepo.findAll());
        model.addAttribute("studyprogramme", studyRepo.findAll());
        model.addAttribute("course", new Course());
        return "createCourse";
    }
    @PostMapping("course/create")
    public String createCourse(@ModelAttribute Course course){
        //System.out.println(course.toString());
        courseRepo.save(course);
        return "redirect:/course";
    }

    @GetMapping("/course/join/{id}")
    public ModelAndView joinCourse(@PathVariable Long id, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("user");

        Optional<Course> optionalCourse = courseRepo.findById(id);
        Course course = optionalCourse.get();

        course.addStudentToWaitingList(student);

        courseRepo.save(course);

        ModelAndView mav = (ModelAndView) session.getAttribute("lastView");

        return mav;
    }

    @GetMapping("/course/showmore")
    public String showMore(@RequestParam Long id, Model model){
        Optional<Course> optionalCourse = courseRepo.findById(id);
        Course course = optionalCourse.get();
        model.addAttribute("course", course);
        return "courseInfo";
    }

    @GetMapping("/course/students/{id}")
    public String courseStudents(@PathVariable Long id, Model model){
        Optional<Course> optionalCourse = courseRepo.findById(id);
        Course course = optionalCourse.get();
        model.addAttribute("student", studentRepo.findAll());
        model.addAttribute("course", course);
        return "courseStudents";
    }

}
