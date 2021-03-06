
package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Candidate;
import domain.Curriculum;
import domain.EducationRecord;
import domain.Endorser;
import domain.Miscellaneous;
import domain.ProfessionalRecord;

@Service
@Transactional
public class CurriculumService {

	// Managed Repository
	// =============================================================================

	@Autowired
	private CurriculumRepository		curriculumRepository;

	@Autowired
	private CandidateService			candidateService;

	@Autowired
	private MiscellanyService			miscellaneousService;

	@Autowired
	private EndorserService				endorserService;

	@Autowired
	private EducationRecordService		educationRecordService;

	@Autowired
	private ProfessionalRecordService	professionalRecordService;


	// Constructor methods
	// ============================================================================

	// Simple CRUD methods
	// ============================================================================

	public Curriculum create() {
		Curriculum result;
		Candidate principal;
		String ticker;

		result = new Curriculum();
		principal = this.candidateService.findByPrincipal();
		ticker = this.generateTicker();

		result.setCandidate(principal);
		result.setCopy(false);
		result.setTicker(ticker);

		return result;
	}

	public Curriculum createCopy(final Curriculum curriculum) {
		final Curriculum result = new Curriculum();
		Curriculum saved;

		result.setCandidate(curriculum.getCandidate());
		result.setEmail(curriculum.getEmail());
		result.setLinkToLinkedIn(curriculum.getLinkToLinkedIn());
		result.setName(curriculum.getName());
		result.setPhoneNumber(curriculum.getPhoneNumber());
		result.setPicture(curriculum.getPicture());
		result.setTicker(curriculum.getTicker());
		result.setCopy(true);

		saved = this.save(result);

		//		Collection<Miscellaneous> miscellaneouss = new ArrayList<Miscellaneous>();
		for (final Miscellaneous m : curriculum.getMiscellaneouss()) {
			final Miscellaneous savedMisc = this.miscellaneousService.createCopy(m);
			savedMisc.setCurriculum(saved);
			this.miscellaneousService.save(savedMisc);
		}

		//		Collection<Endorser> endorsers = new ArrayList<Endorser>();
		for (final Endorser e : curriculum.getEndorsers()) {
			final Endorser savedEnd = this.endorserService.createCopy(e);
			savedEnd.setCurriculum(saved);
			this.endorserService.save(savedEnd);
		}

		//		Collection<EducationRecord> educationRecords = new ArrayList<EducationRecord>();
		for (final EducationRecord er : curriculum.getEducationRecords()) {
			final EducationRecord savedEdRec = this.educationRecordService.createCopy(er);
			savedEdRec.setCurriculum(saved);
			this.educationRecordService.save(savedEdRec);
		}

		//		Collection<ProfessionalRecord> professionalRecords = new ArrayList<ProfessionalRecord>();
		for (final ProfessionalRecord pr : curriculum.getProfessionalRecords()) {
			final ProfessionalRecord savedProfRec = this.professionalRecordService.createCopy(pr);
			savedProfRec.setCurriculum(saved);
			this.professionalRecordService.save(savedProfRec);
		}

		return saved;
	}

	public Curriculum findOne(final int curriculumId) {
		Curriculum result;

		result = this.curriculumRepository.findOne(curriculumId);

		return result;
	}

	public Collection<Curriculum> findAll() {
		Collection<Curriculum> result;

		result = this.curriculumRepository.findAll();

		return result;
	}

	// Other Business Methods
	// =========================================================================

	public Collection<Curriculum> findAllByCandidateId(final int candidateId) {
		Collection<Curriculum> result;

		result = this.curriculumRepository.findAllByCandidateId(candidateId);

		return result;
	}

	public Collection<Curriculum> findAllNotCopy() {
		Collection<Curriculum> result;

		result = this.curriculumRepository.findAllNotCopy();

		return result;
	}

	public Collection<Curriculum> getCurriculumByKeyWord(final String keyWord) {
		Assert.notNull(keyWord);
		Collection<Curriculum> result;
		Candidate principal;

		principal = this.candidateService.findByPrincipal();
		Assert.isInstanceOf(Candidate.class, principal);

		result = this.curriculumRepository.searchCurriculumByWords(keyWord);

		return result;
	}

	public Double avgNumberCurriculumPerCandidate() {
		Double avg;

		avg = this.curriculumRepository.avgNumberCurriculumPerCandidate();
		return avg;
	}

	public Curriculum save(final Curriculum curriculum) {
		Assert.notNull(curriculum);
		Curriculum result;

		final Candidate principal = this.candidateService.findByPrincipal();
		Assert.isTrue(principal.equals(curriculum.getCandidate()));

		result = this.curriculumRepository.save(curriculum);
		System.out.print(result.getId());

		return result;
	}

	public String generateTicker() {
		String result = "";
		Date moment;
		Collection<Curriculum> curriculums = new HashSet<Curriculum>();
		curriculums = this.findAllNotCopy();

		final DateFormat formatoMoment = new SimpleDateFormat("yyyy-MM-dd");

		final String characters = "abcdefghijklmn�opqrstuvwxyzABCDEFGHIJKLMN�OPQRSTUVWXYZ0123456789";

		moment = new Date(System.currentTimeMillis());
		result = formatoMoment.format(moment) + "-";

		for (int i = 0; i < 5; i++)
			result += characters.charAt((int) (Math.random() * 64));

		for (final Curriculum c : curriculums)
			if (c.getTicker().equals(result))
				result = this.generateTicker();

		return result;
	}

}
