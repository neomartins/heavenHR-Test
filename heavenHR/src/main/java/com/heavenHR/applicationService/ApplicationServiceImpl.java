package com.heavenHR.applicationService;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.heavenHR.Exceptions.ConstraintsException;
import com.heavenHR.common.ApplicationStatus;
import com.heavenHR.converter.ApplicationConverter;
import com.heavenHR.dto.ApplicationDto;
import com.heavenHR.model.Application;
import com.heavenHR.offerService.OfferService;
import com.heavenHR.repositories.ApplicationRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	private final Log logger = LogFactory.getLog(this.getClass());
	private final ApplicationRepository applicationRepository;
	private final ApplicationConverter applicationConvert;
	private final OfferService offerService;

	@Autowired
	public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationConverter applicationConvert,
			OfferService offerService) {
		this.applicationRepository = applicationRepository;
		this.applicationConvert = applicationConvert;
		this.offerService = offerService;
	}

	@Override
	public List<ApplicationDto> getAll() {
		return applicationRepository.findAll().stream().map(applicationConvert::applicationConvertToDto)
				.sorted((a, b) -> {
					return a.getApplicationStatus().compareTo(b.getApplicationStatus());
				}).collect(Collectors.toList());
	}

	@Override
	public ApplicationDto createApplication(ApplicationDto applicationDto)
			throws ConstraintsException, EntityNotFoundException {
		try {
			applicationDto.setApplicationStatus(ApplicationStatus.APPLIED);
			Application application = applicationRepository
					.save(applicationConvert.applicationConvertFromDto(applicationDto));
			offerService.numberOfApplication(application.getOffer());
			return applicationConvert.applicationConvertToDto(application);
		} catch (DataIntegrityViolationException e) {
			logger.warn("Error inserting record", e);
			throw new ConstraintsException("Error inserting record constraint violation.");
		} catch (ConstraintViolationException c) {
			throw new ConstraintsException("Not valid email");
		}

	}

	@Override
	public List<ApplicationDto> getApplicationsByOfferId(Long id) {
		return applicationRepository.getApplicationsByOfferId(id).stream()
				.map(applicationConvert::applicationConvertToDto).sorted((a, b) -> {
					return a.getApplicationStatus().compareTo(b.getApplicationStatus());
				}).collect(Collectors.toList());
	}

	@Override
	public ApplicationDto updateApplication(Long applicationId, ApplicationStatus applicationStatus)
			throws EntityNotFoundException {
		Application application = this.verifyApplication(applicationId);
		application.setApplicationStatus(applicationStatus);
		application = applicationRepository.save(application);
		this.notifyApplicationStatusChanged(application, applicationStatus);
		return applicationConvert.applicationConvertToDto(application);
	}

	private Application verifyApplication(Long id) throws EntityNotFoundException {
		Application applicarion = applicationRepository.findOne(id);
		if (applicarion == null) {
			throw new EntityNotFoundException("Application not found");
		}
		return applicarion;
	}

	private void notifyApplicationStatusChanged(Application application, ApplicationStatus applicationStatus) {
		for (ApplicationStatus status : ApplicationStatus.values()) {
			if (status.equals(applicationStatus)) {
				logger.debug("Applicant " + application.getCandidateEmail() + " has been" + status + "for this offer: "
						+ application.getOffer().getJobTitle() + ".");
			}
		}
	}

	@Override
	public ApplicationDto getById(Long id) {
		return applicationConvert.applicationConvertToDto(this.verifyApplication(id));
	}

}
