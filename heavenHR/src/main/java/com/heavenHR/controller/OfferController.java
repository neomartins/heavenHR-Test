package com.heavenHR.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.heavenHR.Exceptions.ConstraintsException;
import com.heavenHR.dto.OfferDto;
import com.heavenHR.model.Offer;
import com.heavenHR.offerService.OfferService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/offer")
public class OfferController {

	private final Log logger = LogFactory.getLog(this.getClass());
	private final OfferService offerService;

	@Autowired
	public OfferController(OfferService offerService) {
		this.offerService = offerService;
	}

	@GetMapping(produces = { "application/json" })
	@ApiOperation(value = "Find all offers")
	public @ResponseBody List<Offer> getAll() {
		return offerService.getAll();
	}

	@GetMapping(value = "/{id}", produces = { "application/json" })
	@ApiOperation(value = "Find offer by id")
	public @ResponseBody ResponseEntity<Offer> getById(@PathVariable Long id) {
		return ResponseEntity.ok(offerService.getById(id));
	}

	@GetMapping(value = "/{id}/applications", produces = { "application/json" })
	@ApiOperation(value = "Track number of applications by offer id")
	public ResponseEntity<?> trackNumberOfApplicationsById(@PathVariable Long id) {
		Offer offer = offerService.getById(id);
		return new ResponseEntity<>(offer != null ? offer.getApplicationList().size() : offer, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Create offer")
	public ResponseEntity<?> createOffer(@RequestBody OfferDto offerDto) {
		ResponseEntity<?> responseEntity;
		try {
			logger.warn("Offer created");
			responseEntity = new ResponseEntity<>(offerService.createOffer(offerDto), HttpStatus.OK);
		} catch (ConstraintsException e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;
	}
}
