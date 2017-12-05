package com.heavenHR.dto;

import com.heavenHR.common.ApplicationStatus;

/**
 * @author Gustavo
 *
 */
public class ApplicationDto {

	private Long id;

	private OfferDto offer;

	private String candidateEmail;

	private String resume;

	private ApplicationStatus applicationStatus;
	
	public ApplicationDto(){}

	/**
	 * @param id
	 * @param offer
	 * @param candidateEmail
	 * @param resume
	 * @param applicationStatus
	 */
	public ApplicationDto(Long id, OfferDto offer, String candidateEmail, String resume,
			ApplicationStatus applicationStatus) {
		this.id = id;
		this.offer = offer;
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
	public OfferDto getOffer() {
		return offer;
	}

	/**
	 * @param offer
	 *            the offer to set
	 */
	public void setOffer(OfferDto offer) {
		this.offer = offer;
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

}
