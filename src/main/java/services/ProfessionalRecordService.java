
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProfessionalRecordRepository;
import domain.Candidate;
import domain.ProfessionalRecord;

@Service
@Transactional
public class ProfessionalRecordService {

	// Managed Repository
	// =============================================================================

	@Autowired
	private ProfessionalRecordRepository	professionalRecordRepository;

	@Autowired
	private CandidateService				candidateService;


	// Constructor methods
	// ============================================================================

	// Simple CRUD methods
	// ============================================================================

	public ProfessionalRecord findOne(final int professionalRecordId) {
		ProfessionalRecord result;

		result = this.professionalRecordRepository.findOne(professionalRecordId);

		return result;
	}

	public Collection<ProfessionalRecord> findAll() {
		Collection<ProfessionalRecord> result;

		result = this.professionalRecordRepository.findAll();

		return result;
	}

	public ProfessionalRecord save(final ProfessionalRecord professionalRecord) {
		Assert.notNull(professionalRecord);
		ProfessionalRecord result;

		final Candidate principal = this.candidateService.findByPrincipal();
		Assert.isTrue(principal.equals(professionalRecord.getCurriculum().getCandidate()));

		result = this.professionalRecordRepository.save(professionalRecord);

		return result;
	}

	// Other Business Methods
	// =========================================================================

	public Collection<ProfessionalRecord> findAllByCurriculumId(final int curriculumId) {
		Collection<ProfessionalRecord> result;

		result = this.professionalRecordRepository.findAllByCurriculumId(curriculumId);

		return result;
	}

	public ProfessionalRecord createCopy(final ProfessionalRecord professionalRecord) {
		Assert.notNull(professionalRecord);

		final ProfessionalRecord result = new ProfessionalRecord();

		result.setAttachment(professionalRecord.getAttachment());
		result.setComments(professionalRecord.getComments());
		result.setCompanyName(professionalRecord.getCompanyName());
		result.setPeriod(professionalRecord.getPeriod());
		result.setRole(professionalRecord.getRole());

		return result;
	}

}
