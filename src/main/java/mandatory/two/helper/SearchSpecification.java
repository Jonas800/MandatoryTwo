package mandatory.two.helper;

import mandatory.two.model.Course;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SearchSpecification {

    public static Specification<Course> doesNameInDanishContain(String nameInDanish){
        return new Specification<Course>() {
            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("nameInDanish"), nameInDanish);
            }
        };
    }

    //public static Specification<Course> doesNameIn
}
