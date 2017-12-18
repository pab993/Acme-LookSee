
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CompanyService;
import domain.Company;
import forms.CompanyForm;

@Controller
@RequestMapping("/company")
public class CompanyController extends AbstractController {

	//Services
	// ============================================================================

	@Autowired
	private CompanyService	companyService;


	//Constructors
	// ============================================================================

	public CompanyController() {
		super();
	}

	//Listing
	//=============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Company> companies;

		companies = this.companyService.findAll();

		result = new ModelAndView("company/list");
		result.addObject("companies", companies);
		result.addObject("requestURI", "company/list.do");

		return result;
	}

	//Listing by Offer
	//=============================================================================

	@RequestMapping(value = "/listByOffer", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int offerId) {
		ModelAndView result;
		Collection<Company> companies;

		companies = this.companyService.findAllByOfferId(offerId);

		result = new ModelAndView("company/list");
		result.addObject("companies", companies);
		result.addObject("requestURI", "company/listByOffer.do?offerId=" + offerId);

		return result;
	}

	//Edition
	//=============================================================================

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;
		result = new ModelAndView("company/edit");

		result.addObject("companyForm", new CompanyForm());

		return result;
	}

	// Save
	// ============================================================================

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CompanyForm companyForm, final BindingResult binding) {
		ModelAndView result;
		Company company;

		try {
			company = this.companyService.reconstruct(companyForm, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(companyForm, "company.save.error");
			else {
				result = new ModelAndView("redirect:/welcome/index.do");
				company = this.companyService.save(company);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(companyForm, "company.save.error");
		}

		return result;
	}

	@RequestMapping(value = "/administrator/ban", method = RequestMethod.GET)
	public ModelAndView register(@RequestParam final int companyId) {
		ModelAndView result;

		try {
			this.companyService.ban(companyId);
			result = this.list();
			result.addObject("message", "company.commit.ban.ok");
		} catch (final Throwable oops) {
			result = this.list();
			result.addObject("message", "company.commit.ban.error");
		}

		return result;
	}

	@RequestMapping(value = "/administrator/unBan", method = RequestMethod.GET)
	public ModelAndView unregister(@RequestParam final int companyId) {
		ModelAndView result;

		try {
			this.companyService.unBan(companyId);
			result = this.list();
			result.addObject("message", "company.commit.unban.ok");
		} catch (final Throwable oops) {
			result = this.list();
			result.addObject("message", "company.commit.unban.error");
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final CompanyForm companyForm) {

		return this.createEditModelAndView(companyForm, null);
	}

	private ModelAndView createEditModelAndView(final CompanyForm companyForm, final String message) {

		final ModelAndView resul = new ModelAndView("company/edit");

		resul.addObject("companyForm", companyForm);
		resul.addObject("message", message);
		return resul;
	}

}
