
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Candidate extends Actor {

	//Relationships
	// =======================================================

	private Collection<Application>	applications;
	private Collection<Curriculum>	curriculums;


	@Valid
	@OneToMany(mappedBy = "candidate")
	public Collection<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(final Collection<Application> offers) {
		this.applications = offers;
	}

	@Valid
	@OneToMany(mappedBy = "candidate")
	public Collection<Curriculum> getCurriculums() {
		return curriculums;
	}

	public void setCurriculums(Collection<Curriculum> curriculums) {
		this.curriculums = curriculums;
	}
}
