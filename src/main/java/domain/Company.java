
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Company extends Actor {

	private String	vat;
	private Boolean	isBan;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp = "^[A-Z]{2}?[0-9]{0,12}$")
	public String getVat() {
		return this.vat;
	}

	public void setVat(final String vat) {
		this.vat = vat;
	}

	public Boolean getIsBan() {
		return this.isBan;
	}
	public void setIsBan(final Boolean isBan) {
		this.isBan = isBan;
	}


	//Relationships
	// =======================================================

	private Collection<Offer>	offers;


	@Valid
	@OneToMany(mappedBy = "company")
	public Collection<Offer> getOffers() {
		return this.offers;
	}

	public void setOffers(final Collection<Offer> offers) {
		this.offers = offers;
	}

}
