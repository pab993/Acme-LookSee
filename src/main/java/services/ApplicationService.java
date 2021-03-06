
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.ApplicationRepository;
import domain.Application;
import domain.Candidate;
import domain.Company;
import domain.Curriculum;
import domain.Offer;

@Service
@Transactional
public class ApplicationService {

	//Managed Repository =============================================================================

	@Autowired
	private ApplicationRepository	applicationRepository;

	@Autowired
	private CandidateService		candidateService;

	@Autowired
	private CompanyService			companyService;

	@Autowired
	private CurriculumService		curriculumService;


	//Constructor methods ============================================================================

	//Simple CRUD methods ============================================================================

	public Application create(final Offer offer) {
		Application result;
		Candidate principal;
		Date moment;

		principal = this.candidateService.findByPrincipal();
		Assert.notNull(principal);
		moment = new Date(System.currentTimeMillis());
		Assert.isTrue(moment.before(offer.getDeadline()));

		result = new Application();
		result.setCandidate(principal);
		result.setOffer(offer);
		result.setCreateMoment(moment);
		result.setStatus("PENDING");

		return result;
	}

	public Application save(final Application application) {
		Assert.notNull(application);
		Application result;
		Candidate principal;
		Date moment;
		Curriculum copy;

		moment = new Date(System.currentTimeMillis());
		Assert.isTrue(moment.before(application.getOffer().getDeadline()));

		principal = this.candidateService.findByPrincipal();
		Assert.notNull(principal);
		Assert.notNull(application.getCurriculum());

		for (final Application a : principal.getApplications())
			if (a.getOffer().equals(application.getOffer()))
				Assert.isTrue(a.getStatus().equals("REJECTED"));

		copy = this.curriculumService.createCopy(application.getCurriculum());
		application.setCurriculum(copy);

		result = this.applicationRepository.save(application);

		return result;
	}

	public Application saveStatus(final Application application) {
		Assert.notNull(application);

		Application result;
		Company principal;

		principal = this.companyService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.getOffers().contains(application.getOffer()));

		result = this.applicationRepository.save(application);

		return result;
	}

	public Application automaticSave(final Application application) {
		Assert.notNull(application);

		Application result;

		result = this.applicationRepository.save(application);

		return result;
	}

	public Application findOne(final int applicationId) {
		Application result;

		result = this.applicationRepository.findOne(applicationId);
		Assert.notNull(result);

		return result;
	}

	//Other Business Methods =========================================================================

	public Collection<Application> findAllByCandidateId(final int candidateId) {
		Collection<Application> result;

		result = this.applicationRepository.findAllByCandidateId(candidateId);

		return result;
	}

	public Collection<Application> findAllByOfferId(final int offerId) {
		Collection<Application> result;

		result = this.applicationRepository.findAllByOfferId(offerId);

		return result;
	}

	public Integer minNumberOfApplicationsPerCandidate() {
		Integer result;

		result = this.applicationRepository.minNumberOfApplicationsPerCandidate();

		return result;
	}

	public Integer maxNumberOfApplicationsPerCandidate() {
		Integer result;

		result = this.applicationRepository.maxNumberOfApplicationsPerCandidate();

		return result;
	}

	public Double avgNumberOfApplicationsPerCandidate() {
		Double result;

		result = this.applicationRepository.avgNumberOfApplicationsPerCandidate();

		return result;
	}

	public Integer minNumberOfApplicationsPerOffer() {
		Integer result;

		result = this.applicationRepository.minNumberOfApplicationsPerOffer();

		return result;
	}

	public Integer maxNumberOfApplicationsPerOffer() {
		Integer result;

		result = this.applicationRepository.maxNumberOfApplicationsPerOffer();

		return result;
	}

	public Double avgNumberOfApplicationsPerOffer() {
		Double result;

		result = this.applicationRepository.avgNumberOfApplicationsPerOffer();

		return result;
	}

	public Integer minNumberOfPendingApplicationsPerCompany() {
		final Integer result = this.applicationRepository.minNumberOfPendingApplicationsPerCompany();
		return result;
	}

	public Integer maxNumberOfPendingApplicationsPerCompany() {
		final Integer result = this.applicationRepository.maxNumberOfPendingApplicationsPerCompany();
		return result;
	}

	public Double avgNumberOfPendingApplicationsPerCompany() {
		final Double result = this.applicationRepository.avgNumberOfPendingApplicationsPerCompany();
		return result;
	}

	public Integer minNumberOfAcceptedApplicationsPerCompany() {
		final Integer result = this.applicationRepository.minNumberOfAcceptedApplicationsPerCompany();
		return result;
	}

	public Integer maxNumberOfAcceptedApplicationsPerCompany() {
		final Integer result = this.applicationRepository.maxNumberOfAcceptedApplicationsPerCompany();
		return result;
	}

	public Double avgNumberOfAcceptedApplicationsPerCompany() {
		final Double result = this.applicationRepository.avgNumberOfAcceptedApplicationsPerCompany();
		return result;
	}

	public Integer minNumberOfRejectedApplicationsPerCompany() {
		final Integer result = this.applicationRepository.minNumberOfRejectedApplicationsPerCompany();
		return result;
	}

	public Integer maxNumberOfRejectedApplicationsPerCompany() {
		final Integer result = this.applicationRepository.maxNumberOfRejectedApplicationsPerCompany();
		return result;
	}

	public Double avgNumberOfRejectedApplicationsPerCompany() {
		final Double result = this.applicationRepository.avgNumberOfRejectedApplicationsPerCompany();
		return result;
	}

	public Application reconstructStatus(final Application application, final BindingResult binding) {
		Application result;

		result = this.findOne(application.getId());
		result.setStatus(application.getStatus());

		return result;
	}

	public Collection<Application> findAllByCandidateIdOrderByCreateMomentAsc(final int candidateId) {
		Collection<Application> result;

		result = this.applicationRepository.findAllByCandidateIdOrderByCreateMomentAsc(candidateId);

		return result;
	}

	public Collection<Application> findAllByCandidateIdOrderByCreateMomentDesc(final int candidateId) {
		Collection<Application> result;

		result = this.applicationRepository.findAllByCandidateIdOrderByCreateMomentDesc(candidateId);

		return result;
	}

	public Collection<Application> findAllByCandidateIdOrderByStatus(final int candidateId) {
		Collection<Application> result;

		result = this.applicationRepository.findAllByCandidateIdOrderByStatus(candidateId);

		return result;
	}

	public Collection<Application> findAllByCandidateIdOrderByDeadline(final int candidateId) {
		Collection<Application> result;

		result = this.applicationRepository.findAllByCandidateIdOrderByDeadline(candidateId);

		return result;
	}

	public void automaticReject(final Collection<Application> applications) {

		for (final Application a : applications) {
			final Calendar calendarDeadline = Calendar.getInstance();
			calendarDeadline.setTime(a.getOffer().getDeadline());
			calendarDeadline.add(Calendar.DAY_OF_YEAR, 7);

			final Calendar calendarCurrent = Calendar.getInstance();

			if ((calendarDeadline.before(calendarCurrent)) && (a.getStatus().equals("PENDING"))) {
				a.setStatus("REJECTED");
				this.automaticSave(a);
			}
		}
	}
}
