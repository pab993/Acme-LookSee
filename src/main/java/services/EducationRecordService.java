
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EducationRecordRepository;
import domain.Candidate;
import domain.EducationRecord;

@Service
@Transactional
public class EducationRecordService {

	// Managed Repository
	// =============================================================================

	@Autowired
	private EducationRecordRepository	educationRecordRepository;

	@Autowired
	private CandidateService			candidateService;


	// Constructor methods
	// ============================================================================

	// Simple CRUD methods
	// ============================================================================

	public EducationRecord findOne(final int educationRecordId) {
		EducationRecord result;

		result = this.educationRecordRepository.findOne(educationRecordId);

		return result;
	}

	public Collection<EducationRecord> findAll() {
		Collection<EducationRecord> result;

		result = this.educationRecordRepository.findAll();

		return result;
	}

	public EducationRecord save(final EducationRecord educationRecord) {
		Assert.notNull(educationRecord);
		EducationRecord result;

		final Candidate principal = this.candidateService.findByPrincipal();
		Assert.isTrue(principal.equals(educationRecord.getCurriculum().getCandidate()));

		result = this.educationRecordRepository.save(educationRecord);

		return result;
	}

	// Other Business Methods
	// =========================================================================

	public Collection<EducationRecord> findAllByCurriculumId(final int curriculumId) {
		Collection<EducationRecord> result;

		result = this.educationRecordRepository.findAllByCurriculumId(curriculumId);

		return result;
	}

	public EducationRecord createCopy(final EducationRecord educationRecord) {
		Assert.notNull(educationRecord);

		final EducationRecord result = new EducationRecord();

		result.setAttachment(educationRecord.getAttachment());
		result.setComments(educationRecord.getComments());
		result.setDiplomaTitle(educationRecord.getDiplomaTitle());
		result.setPeriod(educationRecord.getPeriod());

		return result;
	}

}
