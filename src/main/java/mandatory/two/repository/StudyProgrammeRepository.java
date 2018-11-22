package mandatory.two.repository;

import mandatory.two.model.StudyProgramme;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Matthias Skou 22/11/2018
 */

public interface StudyProgrammeRepository extends CrudRepository<StudyProgramme, Long> {
    List<StudyProgramme> findAll();
}
