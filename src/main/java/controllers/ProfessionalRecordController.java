
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CandidateService;
import services.CurriculumService;
import services.ProfessionalRecordService;
import domain.Candidate;
import domain.Curriculum;
import domain.ProfessionalRecord;

@Controller
@RequestMapping("/professionalRecord")
public class ProfessionalRecordController extends AbstractController {

	// Services
	// ============================================================================

	@Autowired
	private ProfessionalRecordService	professionalRecordService;

	@Autowired
	private CurriculumService			curriculumService;

	@Autowired
	private CandidateService			candidateService;


	// Constructors
	// ============================================================================

	public ProfessionalRecordController() {
		super();
	}

	// Edition
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int professionalRecordId) {
		ModelAndView result;
		try {
			final ProfessionalRecord professionalRecord = this.professionalRecordService.findOne(professionalRecordId);

			Candidate principal = candidateService.findByPrincipal();
			Assert.isTrue(professionalRecord.getCurriculum().getCandidate().getId() == principal.getId());

			result = this.createEditModelAndView(professionalRecord);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ProfessionalRecord professionalRecord, final BindingResult binding) {
		ModelAndView result;

		try {

			if (binding.hasErrors())
				result = this.createEditModelAndView(professionalRecord, "professionalRecord.save.error");
			else {
				result = new ModelAndView("redirect:/curriculum/display.do?curriculumId=" + professionalRecord.getCurriculum().getId());

				this.professionalRecordService.save(professionalRecord);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(professionalRecord, "professionalRecord.save.error");
		}

		return result;
	}

	//Creating
	// ===========================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int curriculumId) {
		ModelAndView result;
		final ProfessionalRecord professionalRecord = new ProfessionalRecord();

		final Curriculum curriculum = this.curriculumService.findOne(curriculumId);

		professionalRecord.setCurriculum(curriculum);

		result = this.createEditModelAndView(professionalRecord);

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final ProfessionalRecord professionalRecord) {

		return this.createEditModelAndView(professionalRecord, null);
	}

	private ModelAndView createEditModelAndView(final ProfessionalRecord professionalRecord, final String message) {

		final ModelAndView resul = new ModelAndView("professionalRecord/edit");

		resul.addObject("professionalRecord", professionalRecord);
		resul.addObject("message", message);
		return resul;
	}

}
