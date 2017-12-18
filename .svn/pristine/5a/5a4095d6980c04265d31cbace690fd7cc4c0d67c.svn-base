
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "curriculum_id")
})
public class ProfessionalRecord extends DomainEntity {

	//Attributes 
	// =================================================================

	private String	companyName;
	private Period	period;
	private String	role;
	private String	attachment;
	private String	comments;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getCompanyName() {
		return this.companyName;
	}
	public void setCompanyName(final String companyName) {
		this.companyName = companyName;
	}

	@Valid
	@NotNull
	public Period getPeriod() {
		return this.period;
	}
	public void setPeriod(final Period period) {
		this.period = period;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getRole() {
		return this.role;
	}
	public void setRole(final String role) {
		this.role = role;
	}

	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getAttachment() {
		return this.attachment;
	}
	public void setAttachment(final String attachment) {
		this.attachment = attachment;
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
