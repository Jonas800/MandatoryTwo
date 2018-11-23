package mandatory.two.controller;

import mandatory.two.helper.SearchSpecification;
import mandatory.two.model.Course;
import mandatory.two.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class SearchController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/index")
    public String indexStudent(){
        return "indexStudent";
    }

    @PostMapping("/search")
    public String search(@RequestParam String search){
        ArrayList<Course> course = (ArrayList<Course>) courseRepository.findAll(SearchSpecification.doesNameInDanishContain("Konstruktion"));

        System.out.println("SE MIG : " + course.get(0).getNameInDanish());

        return "searchResult";
    }
}
