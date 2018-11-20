package mandatory.two.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    private String nameInDanish;
    private String nameInEnglish;
    private Boolean isMandatory;
    private Integer ECTS;
    private Integer semester;
    private String classCode;
    private String language;
    private Integer minimumNumberOfStudents;
    private Integer maximumNumberOfStudents;
    private Integer expectedNumberOfStudents;
    private String prerequisites;
    private String learningOutcome;
    private String content;
    private String learningActivities;
    private String examForm;
    @OneToOne
    private StudyProgramme studyProgramme;
    @ManyToMany
    private List<Teacher> teachers;

}
