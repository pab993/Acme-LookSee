
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import repositories.OfferRepository;
import domain.Application;
import domain.Company;
import domain.Offer;
import domain.Salary;
import forms.OfferForm;

@Service
@Transactional
public class OfferService {

	//Managed Repository =============================================================================

	@Autowired
	private OfferRepository	offerRepository;

	@Autowired
	private CompanyService	companyService;


	//Constructor methods ============================================================================

	//Simple CRUD methods ============================================================================

	public Collection<Offer> findAll() {
		Collection<Offer> result;

		result = this.offerRepository.findAll();

		return result;
	}

	public Offer create() {
		Offer result;
		Company principal;
		Collection<Application> applications;

		principal = this.companyService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Company.class, principal);

		applications = new HashSet<Application>();

		result = new Offer();
		result.setCompany(principal);
		result.setApplications(applications);

		return result;
	}

	public Offer save(final Offer offer) {
		Assert.notNull(offer);
		Offer result;
		Company principal;

		principal = this.companyService.findByPrincipal();
		Assert.notNull(principal);

		final long l = 10;
		final Date actual = new Date(System.currentTimeMillis() - l);
		final Calendar futureDate = Calendar.getInstance();
		futureDate.setTime(actual);
		futureDate.add(Calendar.DAY_OF_YEAR, 7);

		Assert.isTrue(!offer.getTitle().isEmpty());
		Assert.isTrue(!offer.getDescription().isEmpty());
		Assert.isTrue((offer.getDeadline().getTime() >= futureDate.getTimeInMillis()) && (offer.getCreateMoment().getTime() <= actual.getTime()));
		Assert.isTrue(offer.getCompany().equals(principal));
		Assert.isTrue((offer.getSalary().getMaxSalary() >= offer.getSalary().getMinSalary()) && (offer.getSalary().getMaxSalary() >= 1) && (offer.getSalary().getMinSalary() >= 1));

		result = this.offerRepository.save(offer);

		return result;
	}

	public Offer findOne(final int offerID) {
		Assert.notNull(offerID);
		Offer result;
		result = this.offerRepository.findOne(offerID);
		return result;

	}

	//Other Business Methods =========================================================================

	public Collection<Offer> findAllByCompanyId(final int companyId) {
		Collection<Offer> result;

		result = this.offerRepository.findAllByCompanyId(companyId);

		return result;
	}

	public Collection<Offer> findAllMyOffersByCompanyId(final int companyId) {
		Collection<Offer> result;

		result = this.offerRepository.findAllMyOffersByCompanyId(companyId);

		return result;
	}

	public Collection<Offer> findAllNotDraft() {
		Collection<Offer> result;

		result = this.offerRepository.findAllNotDraft();

		return result;
	}

	public Collection<Offer> getOfferByKeyWord(final String keyWord, final String currency) {
		Assert.notNull(keyWord);
		Collection<Offer> result;

		result = this.offerRepository.searchOfferByWords(keyWord, currency);

		return result;
	}

	public OfferForm reconstructToForm(final Offer offer) {
		final OfferForm offerForm = new OfferForm();

		offerForm.setOfferId(offer.getId());
		offerForm.setCreateMoment(offer.getCreateMoment());
		offerForm.setTitle(offer.getTitle());
		offerForm.setDescription(offer.getDescription());
		offerForm.setDraft(offer.getDraft());
		offerForm.setDeadline(offer.getDeadline());
		offerForm.setCurrency(offer.getSalary().getCurrency());
		offerForm.setMaxSalary(offer.getSalary().getMaxSalary());
		offerForm.setMinSalary(offer.getSalary().getMinSalary());

		return offerForm;

	}

	public Offer reconstructOffer(final OfferForm offerForm, final BindingResult binding) {
		Offer result;
		if (offerForm.getOfferId() == null)
			result = this.create();
		else
			result = this.findOne(offerForm.getOfferId());

		final Salary salary = new Salary();

		final long l = 10;
		final Date actual = new Date(System.currentTimeMillis() - l);

		final Company company = this.companyService.findByPrincipal();

		result.setCreateMoment(actual);
		result.setCompany(company);
		result.setDeadline(offerForm.getDeadline());
		result.setDescription(offerForm.getDescription());
		result.setDraft(offerForm.getDraft());
		result.setTitle(offerForm.getTitle());
		salary.setCurrency(offerForm.getCurrency());
		salary.setMaxSalary(offerForm.getMaxSalary());
		salary.setMinSalary(offerForm.getMinSalary());
		result.setSalary(salary);

		this.comprobarDeadline(offerForm.getDeadline(), binding);
		this.comprobarMinAndMaxSalary(offerForm.getMinSalary(), offerForm.getMaxSalary(), binding);

		return result;
	}

	private boolean comprobarDeadline(final Date deadline, final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (deadline == null)
			result = true;
		else
			result = false;

		if (!result) {

			final long l = 10;
			final Date actual = new Date(System.currentTimeMillis() - l);
			final Calendar futureDate = Calendar.getInstance();
			futureDate.setTime(actual);
			futureDate.add(Calendar.DAY_OF_YEAR, 7);

			final Calendar deadlineCalendar = Calendar.getInstance();
			deadlineCalendar.setTime(deadline);

			if (deadlineCalendar.getTimeInMillis() >= futureDate.getTimeInMillis())
				result = true;
			else {

				codigos = new String[1];
				codigos[0] = "offer.deadline.error";
				error = new FieldError("offerForm", "deadline", deadline, false, codigos, null, "");
				binding.addError(error);

			}
		}

		return result;
	}

	private boolean comprobarMinAndMaxSalary(final Double minSalary, final Double maxSalary, final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (minSalary == null && maxSalary == null)
			result = true;
		else
			result = false;

		if (!result)
			if (minSalary <= maxSalary)
				result = true;
			else {

				codigos = new String[1];
				codigos[0] = "offer.salary.error";
				error = new FieldError("offerForm", "maxSalary", maxSalary, false, codigos, null, "");
				binding.addError(error);

			}

		return result;
	}

	public Double avgNumberOffersPerCompany() {
		Double avg;

		avg = this.offerRepository.avgNumberOffersPerCompany();
		return avg;
	}

}
