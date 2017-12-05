/**
 * 
 */
package com.heavenHR.model;

import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.heavenHR.common.OfferStatus;
import static javax.persistence.CascadeType.ALL;

/**
 * @author Gustavo
 *
 */

@Entity
@Table(name = "offer")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Offer {

	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false, unique = true)
	private Long id;

	@Column(name = "job_title", unique = true, nullable = false)
	private String jobTitle;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Calendar startDate;

	@OneToMany(mappedBy = "relatedOffer", cascade = { ALL }, fetch = FetchType.LAZY)
	private List<Application> applicationList;

	@Column(name = "number_of_applications")
	private long numberOfApplications;

	@Enumerated(EnumType.STRING)
	@Column(name = "offer_status")
	private OfferStatus offerStatus;

	public Offer() {

	}

	public Offer(Long id, String jobTitle, Calendar startDate, long numberOfApplications, OfferStatus offerStatus) {
		this.id = id;
		this.jobTitle = jobTitle;
		this.startDate = startDate;
		this.numberOfApplications = numberOfApplications;
		this.offerStatus = offerStatus;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle
	 *            the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @return the startDate
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the numberOfApplications
	 */
	public long getNumberOfApplications() {
		return numberOfApplications;
	}

	/**
	 * @param numberOfApplications
	 *            the numberOfApplications to set
	 */
	public void setNumberOfApplications(long numberOfApplications) {
		this.numberOfApplications = numberOfApplications;
	}

	/**
	 * @return the offerStatus
	 */
	public OfferStatus getOfferStatus() {
		return offerStatus;
	}

	/**
	 * @param offerStatus
	 *            the offerStatus to set
	 */
	public void setOfferStatus(OfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}

	/**
	 * @return the applicationList
	 */
	public List<Application> getApplicationList() {
		return applicationList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
		result = prime * result + (int) (numberOfApplications ^ (numberOfApplications >>> 32));
		result = prime * result + ((offerStatus == null) ? 0 : offerStatus.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		Offer other = (Offer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (jobTitle == null) {
			if (other.jobTitle != null)
				return false;
		} else if (!jobTitle.equals(other.jobTitle))
			return false;
		if (numberOfApplications != other.numberOfApplications)
			return false;
		if (offerStatus != other.offerStatus)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

}
