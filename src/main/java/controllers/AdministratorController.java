/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.CandidateService;
import services.CompanyService;
import services.CurriculumService;
import services.OfferService;
import domain.Candidate;
import domain.Company;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	//Services ----------------------------------------------------------------

	@Autowired
	private CandidateService	candidateService;

	@Autowired
	private CompanyService		companyService;

	@Autowired
	private OfferService		offerService;

	@Autowired
	private CurriculumService	curriculumService;

	@Autowired
	private ApplicationService	applicationService;


	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;

		result = new ModelAndView("administrator/action-1");

		return result;
	}

	// Action-2 ---------------------------------------------------------------

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("administrator/action-2");

		return result;
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;

		Double avgNumberCurriculumPerCandidate;
		Double avgNumberOffersPerCompany;
		Integer minNumberOfPendingApplicationsPerCompany;
		Integer maxNumberOfPendingApplicationsPerCompany;
		Double avgNumberOfPendingApplicationsPerCompany;
		Integer minNumberOfAcceptedApplicationsPerCompany;
		Integer maxNumberOfAcceptedApplicationsPerCompany;
		Double avgNumberOfAcceptedApplicationsPerCompany;
		Integer minNumberOfRejectedApplicationsPerCompany;
		Integer maxNumberOfRejectedApplicationsPerCompany;
		Double avgNumberOfRejectedApplicationsPerCompany;
		Integer minNumberOfApplicationsPerCandidate;
		Integer maxNumberOfApplicationsPerCandidate;
		Double avgNumberOfApplicationsPerCandidate;
		Integer minNumberOfApplicationsPerOffer;
		Integer maxNumberOfApplicationsPerOffer;
		Double avgNumberOfApplicationsPerOffer;

		Collection<Candidate> findCandidatesByNumberOfCurricula = new ArrayList<Candidate>();
		Collection<Company> findCompaniesByNumberOfOffers = new ArrayList<Company>();
		Collection<Candidate> findCandidatesWithMoreCurricula = new ArrayList<Candidate>();
		Collection<Company> findCompaniesWithMoreOffers = new ArrayList<Company>();

		findCandidatesByNumberOfCurricula = this.candidateService.findCandidatesByNumberOfCurricula();
		findCompaniesByNumberOfOffers = companyService.findCompanyByNumberOfOffers();
		avgNumberCurriculumPerCandidate = curriculumService.avgNumberCurriculumPerCandidate();
		avgNumberOffersPerCompany = offerService.avgNumberOffersPerCompany();
		findCandidatesWithMoreCurricula = candidateService.findCandidatesWithMoreCurricula();
		findCompaniesWithMoreOffers = companyService.findCompaniesWithMoreOffers();
		minNumberOfApplicationsPerCandidate = applicationService.minNumberOfApplicationsPerCandidate();
		maxNumberOfApplicationsPerCandidate = applicationService.maxNumberOfApplicationsPerCandidate();
		avgNumberOfApplicationsPerCandidate = applicationService.avgNumberOfApplicationsPerCandidate();
		minNumberOfApplicationsPerOffer = applicationService.minNumberOfApplicationsPerOffer();
		maxNumberOfApplicationsPerOffer = applicationService.maxNumberOfApplicationsPerOffer();
		avgNumberOfApplicationsPerOffer = applicationService.avgNumberOfApplicationsPerOffer();
		minNumberOfPendingApplicationsPerCompany = applicationService.minNumberOfPendingApplicationsPerCompany();
		maxNumberOfPendingApplicationsPerCompany = applicationService.maxNumberOfPendingApplicationsPerCompany();
		avgNumberOfPendingApplicationsPerCompany = applicationService.avgNumberOfPendingApplicationsPerCompany();
		minNumberOfAcceptedApplicationsPerCompany = applicationService.minNumberOfAcceptedApplicationsPerCompany();
		maxNumberOfAcceptedApplicationsPerCompany = applicationService.maxNumberOfAcceptedApplicationsPerCompany();
		avgNumberOfAcceptedApplicationsPerCompany = applicationService.avgNumberOfAcceptedApplicationsPerCompany();
		minNumberOfRejectedApplicationsPerCompany = applicationService.minNumberOfRejectedApplicationsPerCompany();
		maxNumberOfRejectedApplicationsPerCompany = applicationService.maxNumberOfRejectedApplicationsPerCompany();
		avgNumberOfRejectedApplicationsPerCompany = applicationService.avgNumberOfRejectedApplicationsPerCompany();

		result = new ModelAndView("administrator/dashboard");
		result.addObject("findCandidatesByNumberOfCurricula", findCandidatesByNumberOfCurricula);
		result.addObject("findCompaniesByNumberOfOffers", findCompaniesByNumberOfOffers);
		result.addObject("avgNumberCurriculumPerCandidate", avgNumberCurriculumPerCandidate);
		result.addObject("avgNumberOffersPerCompany", avgNumberOffersPerCompany);
		result.addObject("findCandidatesWithMoreCurricula", findCandidatesWithMoreCurricula);
		result.addObject("findCompaniesWithMoreOffers", findCompaniesWithMoreOffers);
		result.addObject("minNumberOfApplicationsPerCandidate", minNumberOfApplicationsPerCandidate);
		result.addObject("maxNumberOfApplicationsPerCandidate", maxNumberOfApplicationsPerCandidate);
		result.addObject("avgNumberOfApplicationsPerCandidate", avgNumberOfApplicationsPerCandidate);
		result.addObject("minNumberOfApplicationsPerOffer", minNumberOfApplicationsPerOffer);
		result.addObject("maxNumberOfApplicationsPerOffer", maxNumberOfApplicationsPerOffer);
		result.addObject("avgNumberOfApplicationsPerOffer", avgNumberOfApplicationsPerOffer);
		result.addObject("minNumberOfPendingApplicationsPerCompany", minNumberOfPendingApplicationsPerCompany);
		result.addObject("maxNumberOfPendingApplicationsPerCompany", maxNumberOfPendingApplicationsPerCompany);
		result.addObject("avgNumberOfPendingApplicationsPerCompany", avgNumberOfPendingApplicationsPerCompany);
		result.addObject("minNumberOfAcceptedApplicationsPerCompany", minNumberOfAcceptedApplicationsPerCompany);
		result.addObject("maxNumberOfAcceptedApplicationsPerCompany", maxNumberOfAcceptedApplicationsPerCompany);
		result.addObject("avgNumberOfAcceptedApplicationsPerCompany", avgNumberOfAcceptedApplicationsPerCompany);
		result.addObject("minNumberOfRejectedApplicationsPerCompany", minNumberOfRejectedApplicationsPerCompany);
		result.addObject("maxNumberOfRejectedApplicationsPerCompany", maxNumberOfRejectedApplicationsPerCompany);
		result.addObject("avgNumberOfRejectedApplicationsPerCompany", avgNumberOfRejectedApplicationsPerCompany);

		result.addObject("requestURI", "administrator/dashboard.do");

		return result;
	}
}
