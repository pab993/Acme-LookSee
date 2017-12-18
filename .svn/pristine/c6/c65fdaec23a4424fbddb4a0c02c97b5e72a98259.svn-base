
package forms;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

public class SearchTemplateForm {

	private String	keyword;
	private String	currency;


	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getKeyword() {
		return this.keyword;
	}
	public void setKeyword(final String keyword) {
		this.keyword = keyword;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getCurrency() {
		return this.currency;
	}
	public void setCurrency(final String currency) {
		this.currency = currency;
	}

}
