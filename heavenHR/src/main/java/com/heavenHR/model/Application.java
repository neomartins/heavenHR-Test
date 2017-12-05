/**
 * 
 */
package com.heavenHR.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.hibernate.validator.constraints.Email;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.heavenHR.common.ApplicationStatus;

/**
 * @author Gustavo
 *
 */
@Entity
@Table(name = "application", uniqueConstraints = @UniqueConstraint(columnNames = { "offer", "candidate_email" }))
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Application {

	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false, unique = true)
	private Long id;

	@JoinColumn(name = "offer", updatable = false)
	@ManyToOne
	private Offer relatedOffer;

	@Email
	@Column(name = "candidate_email")
	private String candidateEmail;

	@Column(name = "resume")
	private String resume;

	@Enumerated(EnumType.STRING)
	@Column(name = "application_status")
	private ApplicationStatus applicationStatus;

	public Application() {
	}

	public Application(Long id, Offer offer, String candidateEmail, String resume,
			ApplicationStatus applicationStatus) {
		this.id = id;
		this.relatedOffer = offer;
		this.candidateEmail = candidateEmail;
		this.resume = resume;
		this.applicationStatus = applicationStatus;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the offer
	 */
	public Offer getOffer() {
		return relatedOffer;
	}

	/**
	 * @param offer
	 *            the offer to set
	 */
	public void setOffer(Offer offers) {
		this.relatedOffer = offers;
	}

	/**
	 * @return the candidateEmail
	 */
	public String getCandidateEmail() {
		return candidateEmail;
	}

	/**
	 * @param candidateEmail
	 *            the candidateEmail to set
	 */
	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	/**
	 * @return the resume
	 */
	public String getResume() {
		return resume;
	}

	/**
	 * @param resume
	 *            the resume to set
	 */
	public void setResume(String resume) {
		this.resume = resume;
	}

	/**
	 * @return the applicationStatus
	 */
	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}

	/**
	 * @param applicationStatus
	 *            the applicationStatus to set
	 */
	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationStatus == null) ? 0 : applicationStatus.hashCode());
		result = prime * result + ((candidateEmail == null) ? 0 : candidateEmail.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((relatedOffer == null) ? 0 : relatedOffer.hashCode());
		result = prime * result + ((resume == null) ? 0 : resume.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Application other = (Application) obj;
		if (applicationStatus != other.applicationStatus)
			return false;
		if (candidateEmail == null) {
			if (other.candidateEmail != null)
				return false;
		} else if (!candidateEmail.equals(other.candidateEmail))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (relatedOffer == null) {
			if (other.relatedOffer != null)
				return false;
		} else if (!relatedOffer.equals(other.relatedOffer))
			return false;
		if (resume == null) {
			if (other.resume != null)
				return false;
		} else if (!resume.equals(other.resume))
			return false;
		return true;
	}

}
