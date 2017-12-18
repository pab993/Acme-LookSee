
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CandidateService;
import domain.Candidate;
import forms.CandidateForm;

@Controller
@RequestMapping("/candidate")
public class CandidateController extends AbstractController {

	//Services
	// ============================================================================

	@Autowired
	private CandidateService	candidateService;


	//Constructors
	// ============================================================================

	public CandidateController() {
		super();
	}

	//Edition
	//=============================================================================

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;
		result = new ModelAndView("candidate/edit");

		result.addObject("candidateForm", new CandidateForm());

		return result;
	}

	// Save
	// ====================================================================

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CandidateForm candidateForm, final BindingResult binding) {
		ModelAndView result;
		Candidate candidate;

		try {
			candidate = candidateService.reconstruct(candidateForm, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(candidateForm, "candidate.save.error");
			else {
				result = new ModelAndView("redirect:/welcome/index.do");
				candidate = this.candidateService.save(candidate);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(candidateForm, "candidate.save.error");
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(CandidateForm candidateForm) {

		return createEditModelAndView(candidateForm, null);
	}

	private ModelAndView createEditModelAndView(CandidateForm candidateForm, String message) {

		ModelAndView resul = new ModelAndView("candidate/edit");

		resul.addObject("candidateForm", candidateForm);
		resul.addObject("message", message);
		return resul;
	}

}
