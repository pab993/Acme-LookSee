
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "candidate_id"), @Index(columnList = "offer_id"), @Index(columnList = "status")
})
public class Application extends DomainEntity {

	//Attributes 
	// =================================================================

	private Date	createMoment;
	private String	status;


	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getCreateMoment() {
		return this.createMoment;
	}
	public void setCreateMoment(final Date createMoment) {
		this.createMoment = createMoment;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}


	//Relationships
	// =================================================================

	private Candidate	candidate;
	private Offer		offer;
	private Curriculum	curriculum;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(final Candidate candidate) {
		this.candidate = candidate;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Offer getOffer() {
		return this.offer;
	}

	public void setOffer(final Offer offer) {
		this.offer = offer;
	}

	@NotNull
	@Valid
	@OneToOne(optional = false)
	public Curriculum getCurriculum() {
		return this.curriculum;
	}
	public void setCurriculum(final Curriculum curriculum) {
		this.curriculum = curriculum;
	}

}
