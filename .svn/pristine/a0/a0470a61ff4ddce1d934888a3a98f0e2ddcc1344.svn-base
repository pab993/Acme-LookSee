
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Candidate;

@Component
@Transactional
public class CandidateToStringConverter implements Converter<Candidate, String> {

	@Override
	public String convert(final Candidate candidate) {
		String result;

		if (candidate == null)
			result = null;
		else
			result = String.valueOf(candidate.getId());

		return result;
	}

}
