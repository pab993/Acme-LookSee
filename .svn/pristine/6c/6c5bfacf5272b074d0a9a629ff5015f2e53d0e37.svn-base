
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Miscellaneous;

@Component
@Transactional
public class MiscellaneousToStringConverter implements Converter<Miscellaneous, String> {

	@Override
	public String convert(final Miscellaneous miscellaneous) {
		String result;

		if (miscellaneous == null)
			result = null;
		else
			result = String.valueOf(miscellaneous.getId());

		return result;
	}

}
