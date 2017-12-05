/**
 * 
 */
package com.heavenHR.dto;

import java.util.Calendar;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.heavenHR.common.OfferStatus;

/**
 * @author Gustavo
 *
 */

public class OfferDto {
	private static final String DATE_PATTERN = "yyyy-MM-dd";

	private Long id;

	private String jobTitle;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
	@JsonSerialize(using = CalendarSerializer.class)
	private Calendar startDate;

	private OfferStatus offerStatus;

	public OfferDto(){}
	/**
	 * @param id
	 * @param jobTitle
	 * @param startDate
	 * @param finishDate
	 * @param offerStatus
	 */
	public OfferDto(Long id, String jobTitle, Calendar startDate, OfferStatus offerStatus) {
		this.id = id;
		this.jobTitle = jobTitle;
		this.startDate = startDate;
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

}
