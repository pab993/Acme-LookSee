
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.EndorserRepository;
import domain.Candidate;
import domain.Curriculum;
import domain.Endorser;
import forms.EndorserForm;

@Service
@Transactional
public class EndorserService {

	// Managed Repository
	// =============================================================================

	@Autowired
	private EndorserRepository	endorserRepository;

	//Managed Services
	// ============================================================================

	@Autowired
	private CandidateService	candidateService;


	// Constructor methods
	// ============================================================================

	// Simple CRUD methods
	// ============================================================================

	public Endorser findOne(final int endorserId) {
		Endorser result;

		result = this.endorserRepository.findOne(endorserId);

		return result;
	}

	public Collection<Endorser> findAll() {
		Collection<Endorser> result;

		result = this.endorserRepository.findAll();

		return result;
	}

	public Endorser save(final Endorser endorser) {
		Assert.notNull(endorser);
		Endorser result;

		final Candidate principal = this.candidateService.findByPrincipal();
		Assert.isTrue(principal.equals(endorser.getCurriculum().getCandidate()));

		result = this.endorserRepository.save(endorser);

		return result;
	}

	public Endorser create(final Curriculum curriculum) {
		Endorser result;
		Candidate principal;

		principal = this.candidateService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Candidate.class, principal);
		;

		result = new Endorser();
		result.setCurriculum(curriculum);

		return result;
	}

	// Other Business Methods
	// =========================================================================

	public Collection<Endorser> findAllByCurriculumId(final int curriculumId) {
		Collection<Endorser> result;

		result = this.endorserRepository.findAllByCurriculumId(curriculumId);

		return result;
	}

	public Endorser reconstructEndorser(final EndorserForm endorserForm, final BindingResult binding) {
		Endorser result;
		result = this.create(endorserForm.getCurriculum());
		result.setId(endorserForm.getEndorserId());
		result.setVersion(endorserForm.getEndorserVersion());

		result.setComments(endorserForm.getComments());
		result.setEmail(endorserForm.getEmail());
		result.setName(endorserForm.getName());
		result.setPhoneNumber(endorserForm.getPhoneNumber());
		result.setLinkToLinkedIn(endorserForm.getLinkToLinkedIn());

		return result;
	}

	public EndorserForm reconstructToForm(final Endorser endorser) {
		final EndorserForm endorserForm = new EndorserForm();

		endorserForm.setEndorserId(endorser.getId());
		endorserForm.setEndorserVersion(endorser.getVersion());
		endorserForm.setComments(endorser.getComments());
		endorserForm.setEmail(endorser.getEmail());
		endorserForm.setLinkToLinkedIn(endorser.getLinkToLinkedIn());
		endorserForm.setName(endorser.getName());
		endorserForm.setPhoneNumber(endorser.getPhoneNumber());
		endorserForm.setCurriculum(endorser.getCurriculum());

		return endorserForm;

	}

	public Endorser createCopy(final Endorser endorser) {
		Assert.notNull(endorser);

		final Endorser result = new Endorser();

		result.setEmail(endorser.getEmail());
		result.setComments(endorser.getComments());
		result.setLinkToLinkedIn(endorser.getLinkToLinkedIn());
		result.setName(endorser.getName());
		result.setPhoneNumber(endorser.getPhoneNumber());

		return result;
	}

}
