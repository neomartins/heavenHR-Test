package com.heavenHR.offerService;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.heavenHR.Exceptions.ConstraintsException;
import com.heavenHR.converter.OfferConverter;
import com.heavenHR.dto.OfferDto;
import com.heavenHR.model.Offer;
import com.heavenHR.repositories.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService {

	private final Log logger = LogFactory.getLog(this.getClass());
	private final OfferRepository offerRepository;
	private final OfferConverter offerConverter;

	@Autowired
	public OfferServiceImpl(OfferRepository offerRepository, OfferConverter offerConverter) {
		this.offerRepository = offerRepository;
		this.offerConverter = offerConverter;
	}

	@Override
	public Offer createOffer(OfferDto offerDto) throws ConstraintsException {
		try {
			return offerRepository.save(offerConverter.applicationConvertFromDto(offerDto));
		} catch (DataIntegrityViolationException e) {
			logger.warn("Error inserting record", e);
			throw new ConstraintsException("Error inserting record constraint violation.");
		}
	}

	@Override
	public List<Offer> getAll() {
		return offerRepository.findAll();
	}

	@Override
	public Offer getById(Long offerId) {
		return offerRepository.findOne(offerId);
	}

	@Override
	public Offer numberOfApplication(Offer offer) throws EntityNotFoundException {
		this.offerExist(offer);
		offer.setNumberOfApplications(offer.getApplicationList().size());
		return offerRepository.save(offer);
	}

	private Offer offerExist(Offer offer) throws EntityNotFoundException {
		if (offer != null) {
			return offer;
		} else {
			logger.warn("Offer not found!");
			throw new EntityNotFoundException("Offer not found!");
		}
	}
}
