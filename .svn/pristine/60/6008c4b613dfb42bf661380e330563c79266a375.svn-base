
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

	@Query("select c from Candidate c where c.userAccount.id = ?1")
	Candidate findByUserAccountId(int userAccountId);

	@Query("select c from Candidate c join c.curriculums cu where cu.copy = false group by c order by count(cu) DESC")
	Collection<Candidate> findCandidatesByNumberOfCurricula();

	@Query("select c from Candidate c join c.curriculums cu where cu.copy = false group by c having count(cu) >= ALL (select count(cu1) from Candidate c1 join c1.curriculums cu1 where cu1.copy = false group by c1)")
	Collection<Candidate> findCandidatesWithMoreCurricula();

}
