package com.heavenHR.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.heavenHR.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

	@Query("SELECT application FROM Application application WHERE application.relatedOffer.id=?1")
	List<Application> getApplicationsByOfferId(Long id);
}
