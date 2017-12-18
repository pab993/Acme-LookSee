
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Miscellaneous;

@Repository
public interface MiscellaneousRepository extends JpaRepository<Miscellaneous, Integer> {

	@Query("select m from Miscellaneous m where m.curriculum.id = ?1")
	Collection<Miscellaneous> findAllByCurriculumId(int curriculumId);

}
