
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ApplicationService;
import services.CompanyService;
import services.CurriculumService;
import services.OfferService;
import domain.Application;
import domain.Company;
import domain.Curriculum;
import domain.Offer;

@Controller
@RequestMapping("/application")
public class ApplicationController extends AbstractController {

	// Services
	// ============================================================================

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private OfferService		offerService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CurriculumService	curriculumService;

	@Autowired
	private CompanyService		companyService;


	// Constructors
	// ============================================================================

	public ApplicationController() {
		super();
	}

	// List
	// ============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Application> applications;

		applications = this.applicationService.findAllByCandidateId(this.actorService.findByPrincipal().getId());
		this.applicationService.automaticReject(applications);

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/list.do");

		return result;
	}

	// List
	// ============================================================================

	@RequestMapping(value = "/listByOffer", method = RequestMethod.GET)
	public ModelAndView listByOffer(@RequestParam final int offerId) {

		ModelAndView result;
		Collection<Application> applications;

		final Offer offer = this.offerService.findOne(offerId);

		applications = this.applicationService.findAllByOfferId(offerId);
		this.applicationService.automaticReject(applications);

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("offer", offer);
		result.addObject("requestURI", "application/listByOffer.do");

		return result;
	}

	// List order
	// ============================================================================

	@RequestMapping(value = "/listOrderByCreateMomentAsc", method = RequestMethod.GET)
	public ModelAndView listOrderByCreateMomentAsc() {

		ModelAndView result;
		Collection<Application> applications;

		applications = this.applicationService.findAllByCandidateIdOrderByCreateMomentAsc(this.actorService.findByPrincipal().getId());
		this.applicationService.automaticReject(applications);

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/listOrderByCreateMomentAsc.do");

		return result;
	}

	@RequestMapping(value = "/listOrderByCreateMomentDesc", method = RequestMethod.GET)
	public ModelAndView listOrderByCreateMomentDesc() {

		ModelAndView result;
		Collection<Application> applications;

		applications = this.applicationService.findAllByCandidateIdOrderByCreateMomentDesc(this.actorService.findByPrincipal().getId());
		this.applicationService.automaticReject(applications);

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/listOrderByCreateMomentDesc.do");

		return result;
	}

	@RequestMapping(value = "/listOrderByStatus", method = RequestMethod.GET)
	public ModelAndView listOrderByCreateMomentStatus() {

		ModelAndView result;
		Collection<Application> applications;

		applications = this.applicationService.findAllByCandidateIdOrderByStatus(this.actorService.findByPrincipal().getId());
		this.applicationService.automaticReject(applications);

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/listOrderByStatus.do");

		return result;
	}

	@RequestMapping(value = "/listOrderByDeadline", method = RequestMethod.GET)
	public ModelAndView listOrderByCreateMomentDeadline() {

		ModelAndView result;
		Collection<Application> applications;

		applications = this.applicationService.findAllByCandidateIdOrderByDeadline(this.actorService.findByPrincipal().getId());
		this.applicationService.automaticReject(applications);

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/listOrderByDeadline.do");

		return result;
	}

	// Edition
	// =============================================================================

	@RequestMapping(value = "/editStatus", method = RequestMethod.GET)
	public ModelAndView editStatus(@RequestParam final int applicationId) {
		ModelAndView result;
		try {
			final Application application = this.applicationService.findOne(applicationId);

			Company company = companyService.findByPrincipal();
			Assert.isTrue(application.getOffer().getCompany().getId() == company.getId());

			result = this.createEditModelAndViewStatus(application);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:/panic/misc");
		}
		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/editStatus", method = RequestMethod.POST, params = "save")
	public ModelAndView saveStatus(final Application application, final BindingResult binding) {
		ModelAndView result;
		Application applicationRec;

		try {
			applicationRec = this.applicationService.reconstructStatus(application, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndViewStatus(application, "application.save.error");
			else {
				result = new ModelAndView("redirect:/application/listByOffer.do?offerId=" + applicationRec.getOffer().getId());
				applicationRec = this.applicationService.saveStatus(applicationRec);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewStatus(application, "application.save.error");
		}

		return result;
	}

	//Creating
	// ===========================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int offerId) {
		ModelAndView result;
		try {
			final Offer offer = this.offerService.findOne(offerId);

			final Application application = this.applicationService.create(offer);

			result = this.createEditModelAndView(application);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Application application, final BindingResult binding) {
		ModelAndView result;

		try {

			if (binding.hasErrors())
				result = this.createEditModelAndView(application, "application.save.error");
			else {
				result = new ModelAndView("redirect:/application/list.do");

				//				Curriculum curriculumCopy = curriculumService.createCopy(application.getCurriculum());
				//				//				Curriculum saved = curriculumService.save(curriculumCopy);
				//				application.setCurriculum(curriculumCopy);
				this.applicationService.save(application);

			}

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(application, "application.save.error");
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final Application application) {

		return this.createEditModelAndView(application, null);
	}

	private ModelAndView createEditModelAndView(final Application application, final String message) {

		final ModelAndView result = new ModelAndView("application/edit");

		Collection<Curriculum> curriculums = new ArrayList<Curriculum>();
		curriculums = this.curriculumService.findAllByCandidateId(application.getCandidate().getId());

		result.addObject("application", application);
		result.addObject("curriculums", curriculums);
		result.addObject("message", message);

		return result;
	}

	private ModelAndView createEditModelAndViewStatus(final Application application) {

		return this.createEditModelAndViewStatus(application, null);
	}

	private ModelAndView createEditModelAndViewStatus(final Application application, final String message) {

		final ModelAndView result = new ModelAndView("application/editStatus");

		result.addObject("application", application);
		result.addObject("message", message);

		return result;
	}

}
