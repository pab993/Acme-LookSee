
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
import services.CompanyService;
import services.OfferService;
import domain.Actor;
import domain.Company;
import domain.Offer;
import forms.OfferForm;
import forms.SearchTemplateForm;

@Controller
@RequestMapping("/offer")
public class OfferController extends AbstractController {

	//Services
	// ============================================================================

	@Autowired
	private OfferService	offerService;

	@Autowired
	private CompanyService	companyService;

	@Autowired
	private ActorService	actorService;


	//Constructors
	// ============================================================================

	public OfferController() {
		super();
	}

	//List
	//=============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listOffer() {
		ModelAndView result;
		Collection<Offer> offers;

		offers = this.offerService.findAllNotDraft();
		final long l = 10;
		final Date actual = new Date(System.currentTimeMillis() - l);

		result = new ModelAndView("offer/list");
		result.addObject("offers", offers);
		result.addObject("actual", actual);
		result.addObject("requestURI", "offer/list.do");

		return result;
	}

	//List by company 
	//=============================================================================

	@RequestMapping("/listByCompany")
	public ModelAndView listByCompany(@RequestParam final int companyId) {
		ModelAndView result;

		try {
			Company company = companyService.findOne(companyId);
			Assert.notNull(company);
			final Collection<Offer> offers = this.offerService.findAllByCompanyId(companyId);
			//		final Actor principal = this.actorService.findByPrincipal();
			result = new ModelAndView("offer/list");

			result.addObject("offers", offers);
			result.addObject("requestURI", "offer/listByCompany.do");
			//		result.addObject("principal", principal);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}

	//List by company
	//=============================================================================

	@RequestMapping("/company/list")
	public ModelAndView list() {
		ModelAndView result;

		final Company company = this.companyService.findByPrincipal();

		final Actor principal = this.actorService.findByPrincipal();

		final Collection<Offer> offers = this.offerService.findAllMyOffersByCompanyId(company.getId());

		result = new ModelAndView("offer/list");

		result.addObject("offers", offers);
		result.addObject("requestURI", "offer/company/list.do");
		result.addObject("principal", principal);

		return result;
	}

	//Search ==============================================================================

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView listSearch() {
		ModelAndView result;
		Collection<Offer> offers;

		offers = new ArrayList<Offer>();

		result = new ModelAndView("offer/search");
		result.addObject("searchTemplateForm", new SearchTemplateForm());
		result.addObject("offers", offers);
		result.addObject("requestURI", "offer/search.do");

		return result;
	}

	//Search ==============================================================================	

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "search")
	public ModelAndView search(final SearchTemplateForm searchTemplateForm, final BindingResult binding) {
		ModelAndView result;
		Collection<Offer> offers;

		if (binding.hasErrors())
			result = this.createEditModelAndView(searchTemplateForm);
		else
			try {
				offers = this.offerService.getOfferByKeyWord(searchTemplateForm.getKeyword(), searchTemplateForm.getCurrency());
				result = new ModelAndView("offer/search");

				result.addObject("searchTemplateForm", searchTemplateForm);
				result.addObject("offers", offers);
				result.addObject("requestURI", "offer/search.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(searchTemplateForm, "searchTemplateForm.commit.error");
			}
		return result;
	}

	//Edition
	//=============================================================================

	@RequestMapping(value = "/company/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int offerId) {
		ModelAndView result;
		final Offer offer = this.offerService.findOne(offerId);
		Assert.isTrue(offer.getDraft());
		final OfferForm offerForm = this.offerService.reconstructToForm(offer);
		result = this.createEditModelAndView(offerForm);
		return result;
	}

	//Creating
	// ===========================================================================

	@RequestMapping(value = "/company/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final OfferForm offerForm = new OfferForm();
		final long l = 10;
		final Date actual = new Date(System.currentTimeMillis() - l);
		offerForm.setCreateMoment(actual);

		result = this.createEditModelAndView(offerForm);

		return result;
	}

	//Save
	// =============================================================================

	@RequestMapping(value = "/company/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final OfferForm offerForm, final BindingResult binding) {
		ModelAndView result;
		Offer offer;

		try {
			offer = this.offerService.reconstructOffer(offerForm, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(offerForm, "offer.save.error");
			else {
				result = new ModelAndView("redirect:/offer/company/list.do");
				offer = this.offerService.save(offer);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(offerForm, "offer.save.error");
		}

		return result;
	}

	// Ancilliary methods
	// =============================================================================

	private ModelAndView createEditModelAndView(final OfferForm offerForm) {

		return this.createEditModelAndView(offerForm, null);
	}

	private ModelAndView createEditModelAndView(final OfferForm offerForm, final String message) {

		final ModelAndView resul = new ModelAndView("offer/edit");

		resul.addObject("offerForm", offerForm);
		resul.addObject("message", message);
		return resul;
	}

	protected ModelAndView createEditModelAndView(final SearchTemplateForm searchTemplateForm) {
		ModelAndView result;

		result = this.createEditModelAndView(searchTemplateForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final SearchTemplateForm searchTemplateForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("offer/search");

		result.addObject("searchTemplateForm", searchTemplateForm);
		result.addObject("message", message);

		return result;
	}
}
