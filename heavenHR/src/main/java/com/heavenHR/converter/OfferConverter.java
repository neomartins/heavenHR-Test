package com.heavenHR.converter;

import org.springframework.stereotype.Component;

import com.heavenHR.dto.OfferDto;
import com.heavenHR.model.Offer;

@Component
public class OfferConverter {

	public Offer applicationConvertFromDto(OfferDto offerDto) {
		return new Offer(offerDto.getId(), offerDto.getJobTitle(), offerDto.getStartDate(), 0L,
				offerDto.getOfferStatus());

	}

	public OfferDto applicationConvertToDto(Offer offer) {
		return new OfferDto(offer.getId(), offer.getJobTitle(), offer.getStartDate(), offer.getOfferStatus());
	}
}
