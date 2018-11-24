package mandatory.two.controller;

import mandatory.two.helper.SearchSpecification;
import mandatory.two.model.Course;
import mandatory.two.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLOutput;
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
        //ArrayList<Course> course = (ArrayList<Course>) courseRepository.findAll(SearchSpecification.doesNameInDanishContain("Kon"));

        //System.out.println("SE MIG : " + course.get(0).getNameInDanish());

        ArrayList<Course> course2 = (ArrayList<Course>) courseRepository.findAll(Specification
                .where(SearchSpecification.doesFieldContain("Kon", "nameInDanish"))
                .and(SearchSpecification.doesFieldContain("", "nameInEnglish"))
        );


        System.out.println("SE MIG : " + course2.get(0).getNameInEnglish());


        return "searchResult";
    }

    @GetMapping("/search/advanced")
    public String searchAdvanced(Model model){
        model.addAttribute("course", new Course());
        return "searchAdvanced";
    }
    @PostMapping("search/advanced")
    public String searchAdvanced(@ModelAttribute Course course){

        ArrayList<Course> courses = (ArrayList<Course>) courseRepository.findAll(Specification
                .where(SearchSpecification.doesFieldContain(course.getNameInDanish(), "nameInDanish"))
                .and(SearchSpecification.doesFieldContain(course.getNameInEnglish(), "nameInEnglish"))
                .and(SearchSpecification.doesFieldContain(course.getExamForm(), "examForm"))
                .and(SearchSpecification.doesFieldContain(course.getLanguage(), "language"))
                .and(SearchSpecification.doesFieldContain(course.getClassCode(), "classCode"))
                .and(SearchSpecification.doesForeignKeyContain(course.getStudyProgramme().getName(), "name", "studyProgramme"))
        );

        System.out.println("SE MIG : " + courses.get(0).getNameInEnglish());

        return "searchResult";
    }
}
