package mandatory.two.controller;

import mandatory.two.model.Course;
import mandatory.two.model.Student;
import mandatory.two.repository.CourseRepository;
import mandatory.two.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class WaitingListController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/administrator/waitinglist")
    public String waitingListView(Model model){
        ArrayList<Course> courses = (ArrayList<Course>) courseRepository.findAll();
        model.addAttribute("courses", courses);

        return "waitingList";
    }

    @GetMapping("/administrator/waitinglist/course/{id}")
    public String waitingListCourseView(@PathVariable Long id, Model model){

        Optional<Course> optionalCourse = courseRepository.findById(id);
        Course course = optionalCourse.get();

        model.addAttribute("course", course);

        return "waitingListCourse";
    }

    @GetMapping("/administrator/waitinglist/course/{courseId}/approve/{studentId}")
    public String approve(@PathVariable Long courseId, @PathVariable Long studentId){
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        Course course = optionalCourse.get();

        Student student = studentRepository.findById(studentId).get();

        course.addStudent(student);
        course.removeStudentFromWaitingList(student);

        courseRepository.save(course);

        return "";
    }
}
