package com.heavenHR.controller;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heavenHR.Exceptions.ConstraintsException;
import com.heavenHR.applicationService.ApplicationService;
import com.heavenHR.common.ApplicationStatus;
import com.heavenHR.dto.ApplicationDto;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/application")
public class ApplicationController {

	private final Log logger = LogFactory.getLog(this.getClass());
	private final ApplicationService applicationService;

	@Autowired
	public ApplicationController(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@GetMapping(produces = { "application/json" })
	@ApiOperation(value = "Find all applications")
	public ResponseEntity<List<ApplicationDto>> getAll() {
		return ResponseEntity.ok(applicationService.getAll());
	}

	@GetMapping(value = "/{id}", produces = { "application/json" })
	@ApiOperation(value = "Find applications by offer id")
	public ResponseEntity<List<ApplicationDto>> getApplicationsByOfferId(@PathVariable Long id) {
		return ResponseEntity.ok(applicationService.getApplicationsByOfferId(id));
	}

	@GetMapping(value = "/{id}/application", produces = { "application/json" })
	@ApiOperation(value = "Find applications by id")
	public ResponseEntity<ApplicationDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(applicationService.getById(id));
	}

	@PostMapping
	@ApiOperation(value = "Create applicarion")
	public ResponseEntity<?> createApplication(@RequestBody ApplicationDto applicationDto) {
		ResponseEntity<?> responseEntity;
		try {
			responseEntity = new ResponseEntity<>(applicationService.createApplication(applicationDto), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		} catch (ConstraintsException c) {
			responseEntity = new ResponseEntity<>(c.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}

		return responseEntity;
	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Create applicarion")
	public ResponseEntity<?> updateApplication(@PathVariable Long id,
			@RequestParam ApplicationStatus applicationStatus) {
		ResponseEntity<?> responseEntity;
		try {
			responseEntity = new ResponseEntity<>(applicationService.updateApplication(id, applicationStatus),
					HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			logger.warn(e.getMessage());
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;
	}

}
