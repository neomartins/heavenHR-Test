package com.heavenHR.offerService;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.heavenHR.Exceptions.ConstraintsException;
import com.heavenHR.dto.OfferDto;
import com.heavenHR.model.Offer;

public interface OfferService {

	Offer createOffer(OfferDto offerDto) throws ConstraintsException;

	List<Offer> getAll();
	
	Offer getById(Long offerId)throws EntityNotFoundException;
	
	Offer numberOfApplication(Offer offer) throws EntityNotFoundException;
}
