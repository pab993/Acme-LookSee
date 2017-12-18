package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.CandidateRepository;
import domain.Candidate;

@Component
@Transactional
public class StringToCandidateConverter implements Converter<String, Candidate> {

	@Autowired
	private CandidateRepository candidateRepository;

	@Override
	public Candidate convert(String text) {
		Candidate result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = candidateRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
