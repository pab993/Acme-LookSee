
package forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

import domain.Curriculum;

public class EndorserForm {

	public EndorserForm() {
		super();
	}


	private int			endorserId;
	private int			endorserVersion;
	private String		name;
	private String		email;
	private String		phoneNumber;
	private String		linkToLinkedIn;
	private String		comments;
	private Curriculum	curriculum;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Email
	@NotBlank
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp = "^[+][a-zA-Z]{2}([(][0-9]{1,3}[)])?[0-9]{4,25}$")
	@NotBlank
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@URL
	@NotBlank
	public String getLinkToLinkedIn() {
		return linkToLinkedIn;
	}
	public void setLinkToLinkedIn(String linkToLinkedIn) {
		this.linkToLinkedIn = linkToLinkedIn;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getEndorserId() {
		return endorserId;
	}
	public void setEndorserId(int endorserId) {
		this.endorserId = endorserId;
	}

	public int getEndorserVersion() {
		return endorserVersion;
	}
	public void setEndorserVersion(int endorserVersion) {
		this.endorserVersion = endorserVersion;
	}

	@Valid
	@NotNull
	public Curriculum getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

}
