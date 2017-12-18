
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "candidate_id"), @Index(columnList = "copy")
})
public class Curriculum extends DomainEntity {

	//Attributes 
	// =================================================================

	private String	ticker;
	private String	name;
	private String	picture;
	private String	email;
	private String	phoneNumber;
	private String	linkToLinkedIn;
	private Boolean	copy;


	@Pattern(regexp = "^[0-9]{4}[-][0-9]{2}[-][0-9]{2}[-][a-zA-Z0-9]{5}$")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getTicker() {
		return this.ticker;
	}
	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	@URL
	public String getPicture() {
		return this.picture;
	}
	public void setPicture(final String picture) {
		this.picture = picture;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Email
	@NotBlank
	public String getEmail() {
		return this.email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}

	@Pattern(regexp = "^[+][a-zA-Z]{2}([(][0-9]{1,3}[)])?[0-9]{4,25}$")
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	@URL
	public String getLinkToLinkedIn() {
		return this.linkToLinkedIn;
	}
	public void setLinkToLinkedIn(final String linkToLinkedIn) {
		this.linkToLinkedIn = linkToLinkedIn;
	}

	public Boolean getCopy() {
		return this.copy;
	}
	public void setCopy(final Boolean copy) {
		this.copy = copy;
	}


	//Relationships
	// =================================================================

	private Candidate						candidate;
	private Collection<ProfessionalRecord>	professionalRecords;
	private Collection<Miscellaneous>		miscellaneouss;
	private Collection<EducationRecord>		educationRecords;
	private Collection<Endorser>			endorsers;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Candidate getCandidate() {
		return this.candidate;
	}
	public void setCandidate(final Candidate candidate) {
		this.candidate = candidate;
	}

	@Valid
	@OneToMany(mappedBy = "curriculum")
	public Collection<ProfessionalRecord> getProfessionalRecords() {
		return this.professionalRecords;
	}
	public void setProfessionalRecords(final Collection<ProfessionalRecord> professionalRecords) {
		this.professionalRecords = professionalRecords;
	}

	@Valid
	@OneToMany(mappedBy = "curriculum")
	public Collection<Miscellaneous> getMiscellaneouss() {
		return this.miscellaneouss;
	}
	public void setMiscellaneouss(final Collection<Miscellaneous> miscellaneouss) {
		this.miscellaneouss = miscellaneouss;
	}

	@Valid
	@OneToMany(mappedBy = "curriculum")
	public Collection<EducationRecord> getEducationRecords() {
		return this.educationRecords;
	}
	public void setEducationRecords(final Collection<EducationRecord> educationRecords) {
		this.educationRecords = educationRecords;
	}

	@Valid
	@OneToMany(mappedBy = "curriculum")
	public Collection<Endorser> getEndorsers() {
		return this.endorsers;
	}
	public void setEndorsers(final Collection<Endorser> endorsers) {
		this.endorsers = endorsers;
	}

}
