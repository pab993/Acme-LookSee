
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
@Access(AccessType.PROPERTY)
public class Period {

	//Attributes 
	// =================================================================

	private Date	startPeriod;
	private Date	endPeriod;


	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getStartPeriod() {
		return this.startPeriod;
	}

	public void setStartPeriod(final Date startPeriod) {
		this.startPeriod = startPeriod;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getEndPeriod() {
		return this.endPeriod;
	}

	public void setEndPeriod(final Date endPeriod) {
		this.endPeriod = endPeriod;
	}

}
