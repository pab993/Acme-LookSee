
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

import services.ActorService;
import services.CandidateService;
import services.CurriculumService;
import domain.Actor;
import domain.Curriculum;
import domain.EducationRecord;
import domain.Endorser;
import domain.Miscellaneous;
import domain.ProfessionalRecord;

@Controller
@RequestMapping("/curriculum")
public class CurriculumController extends AbstractController {

	// Services
	// ============================================================================

	@Autowired
	private CurriculumService	curriculumService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CandidateService	candidateService;


	// Constructors
	// ============================================================================

	public CurriculumController() {
		super();
	}

	// List
	// ============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Curriculum> curricula;

		curricula = this.curriculumService.findAllByCandidateId(this.actorService.findByPrincipal().getId());

		result = new ModelAndView("curriculum/list");
		result.addObject("curricula", curricula);
		result.addObject("requestURI", "curriculum/list.do");

		return result;
	}

	// Display
	// ============================================================================

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final Integer curriculumId) {

		ModelAndView result;
		Curriculum curriculum;
		Collection<Endorser> endorsers;
		Collection<EducationRecord> educationRecords;
		Collection<ProfessionalRecord> professionalRecords;
		Collection<Miscellaneous> miscellaneous;

		try {
			Actor principal = actorService.findByPrincipal();

			curriculum = this.curriculumService.findOne(curriculumId);
			endorsers = curriculum.getEndorsers();
			educationRecords = curriculum.getEducationRecords();
			professionalRecords = curriculum.getProfessionalRecords();
			miscellaneous = curriculum.getMiscellaneouss();

			result = new ModelAndView("curriculum/display");
			result.addObject("curriculum", curriculum);
			result.addObject("endorsers", endorsers);
			result.addObject("educationRecords", educationRecords);
			result.addObject("professionalRecords", professionalRecords);
			result.addObject("miscellaneous", miscellaneous);
			result.addObject("principal", principal);
			result.addObject("requestURI", "curriculum/display.do");
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}

	@RequestMapping(value = "/displayCopy", method = RequestMethod.GET)
	public ModelAndView displayCopy(@RequestParam final Integer curriculumId) {

		ModelAndView result;
		Curriculum curriculum;
		Collection<Endorser> endorsers;
		Collection<EducationRecord> educationRecords;
		Collection<ProfessionalRecord> professionalRecords;
		Collection<Miscellaneous> miscellaneous;

		try {

			curriculum = this.curriculumService.findOne(curriculumId);
			endorsers = curriculum.getEndorsers();
			educationRecords = curriculum.getEducationRecords();
			professionalRecords = curriculum.getProfessionalRecords();
			miscellaneous = curriculum.getMiscellaneouss();

			result = new ModelAndView("curriculum/display");
			result.addObject("curriculum", curriculum);
			result.addObject("endorsers", endorsers);
			result.addObject("educationRecords", educationRecords);
			result.addObject("professionalRecords", professionalRecords);
			result.addObject("miscellaneous", miscellaneous);
			result.addObject("requestURI", "curriculum/displayCopy.do");
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}

	// SearchForm
	// ==============================================================================

	@RequestMapping(value = "/searchForm", method = RequestMethod.GET)
	public ModelAndView listSearch() {
		ModelAndView result;
		Collection<Curriculum> curriculums;
		Boolean firstTime;

		curriculums = this.curriculumService.findAll();
		firstTime = true;

		result = new ModelAndView("curriculum/search");
		result.addObject("curriculums", curriculums);
		result.addObject("requestURI", "curriculum/searchForm.do");
		result.addObject("firstTime", firstTime);

		return result;
	}

	// Search
	// ==============================================================================

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam("keyword") final String keyword) {
		ModelAndView result;
		Collection<Curriculum> curriculums;

		curriculums = this.curriculumService.getCurriculumByKeyWord(keyword);

		result = new ModelAndView("curriculum/search");
		result.addObject("curriculums", curriculums);

		return result;
	}

	// Edition
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int curriculumId) {
		ModelAndView result;
		final Curriculum curriculum = this.curriculumService.findOne(curriculumId);

		result = this.createEditModelAndView(curriculum);
		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Curriculum curriculum, final BindingResult binding) {
		ModelAndView result;

		try {

			if (binding.hasErrors())
				result = this.createEditModelAndView(curriculum, "curriculum.save.error");
			else {
				result = new ModelAndView("redirect:/curriculum/list.do");
				this.curriculumService.save(curriculum);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(curriculum, "curriculum.save.error");
		}

		return result;
	}

	// Create
	// =================================================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Curriculum curriculum = this.curriculumService.create();

		result = this.createEditModelAndView(curriculum);

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final Curriculum curriculum) {

		return this.createEditModelAndView(curriculum, null);
	}

	private ModelAndView createEditModelAndView(final Curriculum curriculum, final String message) {

		final ModelAndView resul = new ModelAndView("curriculum/edit");

		resul.addObject("curriculum", curriculum);
		resul.addObject("message", message);
		return resul;
	}

}
