package com.heavenHR.converter;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.heavenHR.dto.ApplicationDto;
import com.heavenHR.model.Application;
import com.heavenHR.model.Offer;
import com.heavenHR.offerService.OfferService;

@Component
public class ApplicationConverter {

	private final Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private OfferService offerService;
	@Autowired
	private OfferConverter offerConvert;

	public Application applicationConvertFromDto(ApplicationDto applicationDto) throws EntityNotFoundException {
		Offer offer = null;
		offer = applicationDto.getOffer() != null ? offerService.getById(applicationDto.getOffer().getId()) : null;
		this.validateApplications(offer);
		return new Application(applicationDto.getId(), offer, applicationDto.getCandidateEmail(),
				applicationDto.getResume(), applicationDto.getApplicationStatus());
	}

	public ApplicationDto applicationConvertToDto(Application application) {

		return new ApplicationDto(application.getId(), offerConvert.applicationConvertToDto(application.getOffer()),
				application.getCandidateEmail(), application.getResume(), application.getApplicationStatus());
	}

	private void validateApplications(Offer offer) {
		if (offer == null) {
			logger.warn("Offer not found");
			throw new EntityNotFoundException("Offer not found.");
		}
	}
}
