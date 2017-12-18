
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.MiscellaneousRepository;
import domain.Miscellaneous;

@Component
@Transactional
public class StringToMiscellaneousConverter implements Converter<String, Miscellaneous> {

	@Autowired
	private MiscellaneousRepository	miscellaneousRepository;


	@Override
	public Miscellaneous convert(String text) {
		Miscellaneous result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = miscellaneousRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
