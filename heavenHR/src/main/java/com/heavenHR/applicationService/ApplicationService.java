package com.heavenHR.applicationService;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.heavenHR.Exceptions.ConstraintsException;
import com.heavenHR.common.ApplicationStatus;
import com.heavenHR.dto.ApplicationDto;

public interface ApplicationService {

	List<ApplicationDto> getAll();

	List<ApplicationDto> getApplicationsByOfferId(Long id);

	ApplicationDto getById(Long id);

	ApplicationDto createApplication(ApplicationDto applicationDto)
			throws ConstraintsException, EntityNotFoundException;

	ApplicationDto updateApplication(Long applicationId, ApplicationStatus applicationStatus)
			throws EntityNotFoundException;
}
