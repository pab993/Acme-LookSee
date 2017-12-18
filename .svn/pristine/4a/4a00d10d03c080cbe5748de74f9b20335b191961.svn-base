
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Embeddable
@Access(AccessType.PROPERTY)
public class Salary {

	//Attributes 
	// =================================================================

	private Double	minSalary;
	private Double	maxSalary;
	private String	currency;


	@NotNull
	@Min(1)
	public Double getMinSalary() {
		return this.minSalary;
	}

	public void setMinSalary(final Double minSalary) {
		this.minSalary = minSalary;
	}

	@NotNull
	@Min(1)
	public Double getMaxSalary() {
		return this.maxSalary;
	}

	public void setMaxSalary(final Double maxSalary) {
		this.maxSalary = maxSalary;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(final String currency) {
		this.currency = currency;
	}

}
