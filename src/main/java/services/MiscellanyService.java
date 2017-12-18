
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MiscellaneousRepository;
import domain.Candidate;
import domain.Miscellaneous;

@Service
@Transactional
public class MiscellanyService {

	// Managed Repository
	// =============================================================================

	@Autowired
	private MiscellaneousRepository	miscellanyRepository;
	
	@Autowired
	private CandidateService candidateService;


	// Constructor methods
	// ============================================================================

	// Simple CRUD methods
	// ============================================================================

	public Miscellaneous findOne(final int miscellanyId) {
		Miscellaneous result;

		result = this.miscellanyRepository.findOne(miscellanyId);

		return result;
	}

	public Collection<Miscellaneous> findAll() {
		Collection<Miscellaneous> result;

		result = this.miscellanyRepository.findAll();

		return result;
	}

	public Miscellaneous save(final Miscellaneous miscellany) {
		Assert.notNull(miscellany);
		
		Candidate principal = candidateService.findByPrincipal();
		Assert.isTrue(principal.equals(miscellany.getCurriculum().getCandidate()));
		
		Miscellaneous result;

		result = this.miscellanyRepository.save(miscellany);

		return result;
	}

	public void delete(final Miscellaneous miscellany) {
		Assert.notNull(miscellany);

		Candidate principal = candidateService.findByPrincipal();
		Assert.isTrue(principal.equals(miscellany.getCurriculum().getCandidate()));
		
		this.miscellanyRepository.delete(miscellany);

	}

	// Other Business Methods
	// =========================================================================

	public Collection<Miscellaneous> findAllByCurriculumId(final int curriculumId) {
		Collection<Miscellaneous> result;

		result = this.miscellanyRepository.findAllByCurriculumId(curriculumId);

		return result;
	}

	public Miscellaneous createCopy(final Miscellaneous miscellaneous) {
		Assert.notNull(miscellaneous);

		final Miscellaneous result = new Miscellaneous();

		result.setAttachment(miscellaneous.getAttachment());
		result.setComments(miscellaneous.getComments());
		result.setTitle(miscellaneous.getTitle());

		return result;
	}

}
