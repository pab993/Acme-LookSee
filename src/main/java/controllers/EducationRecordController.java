
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
import services.EducationRecordService;
import domain.Candidate;
import domain.Curriculum;
import domain.EducationRecord;

@Controller
@RequestMapping("/educationRecord")
public class EducationRecordController extends AbstractController {

	// Services
	// ============================================================================

	@Autowired
	private EducationRecordService	educationRecordService;

	@Autowired
	private CurriculumService		curriculumService;

	@Autowired
	private CandidateService		candidateService;


	// Constructors
	// ============================================================================

	public EducationRecordController() {
		super();
	}

	// Edition
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int educationRecordId) {
		ModelAndView result;
		try {
			final EducationRecord educationRecord = this.educationRecordService.findOne(educationRecordId);

			Candidate principal = candidateService.findByPrincipal();
			Assert.isTrue(educationRecord.getCurriculum().getCandidate().getId() == principal.getId());

			result = this.createEditModelAndView(educationRecord);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final EducationRecord educationRecord, final BindingResult binding) {
		ModelAndView result;

		try {

			if (binding.hasErrors())
				result = this.createEditModelAndView(educationRecord, "educationRecord.save.error");
			else {
				result = new ModelAndView("redirect:/curriculum/display.do?curriculumId=" + educationRecord.getCurriculum().getId());

				this.educationRecordService.save(educationRecord);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(educationRecord, "educationRecord.save.error");
		}

		return result;
	}

	//Creating
	// ===========================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int curriculumId) {
		ModelAndView result;
		final EducationRecord educationRecord = new EducationRecord();

		final Curriculum curriculum = this.curriculumService.findOne(curriculumId);

		educationRecord.setCurriculum(curriculum);

		result = this.createEditModelAndView(educationRecord);

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final EducationRecord educationRecord) {

		return this.createEditModelAndView(educationRecord, null);
	}

	private ModelAndView createEditModelAndView(final EducationRecord educationRecord, final String message) {

		final ModelAndView resul = new ModelAndView("educationRecord/edit");

		resul.addObject("educationRecord", educationRecord);
		resul.addObject("message", message);
		return resul;
	}

}
