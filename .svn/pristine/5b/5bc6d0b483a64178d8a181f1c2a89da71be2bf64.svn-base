
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "curriculum_id")
})
public class Endorser extends DomainEntity {

	//Attributes 
	// =================================================================

	private String	name;
	private String	email;
	private String	phoneNumber;
	private String	linkToLinkedIn;
	private String	comments;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
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

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp = "^[+][a-zA-Z]{2}([(][0-9]{1,3}[)])?[0-9]{4,25}$")
	@NotBlank
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@URL
	@NotBlank
	public String getLinkToLinkedIn() {
		return this.linkToLinkedIn;
	}
	public void setLinkToLinkedIn(final String linkToLinkedIn) {
		this.linkToLinkedIn = linkToLinkedIn;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getComments() {
		return this.comments;
	}
	public void setComments(final String comments) {
		this.comments = comments;
	}


	//Relationships
	// =================================================================

	private Curriculum	curriculum;


	@Valid
	@ManyToOne(optional = false)
	public Curriculum getCurriculum() {
		return this.curriculum;
	}
	public void setCurriculum(final Curriculum curriculum) {
		this.curriculum = curriculum;
	}

}
