package com.heavenHR.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenHR.Exceptions.ConstraintsException;
import com.heavenHR.applicationService.ApplicationService;
import com.heavenHR.common.ApplicationStatus;
import com.heavenHR.common.OfferStatus;
import com.heavenHR.dto.ApplicationDto;
import com.heavenHR.dto.OfferDto;
import com.heavenHR.offerService.OfferService;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationController.class)
public class ApplicationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ApplicationService applicationService;

	@MockBean
	private OfferService offerService;

	private List<ApplicationDto> applicationList = new ArrayList<>();
	private ApplicationDto mockApplication1;
	private ApplicationDto mockApplication2;
	private OfferDto offerDto;

	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() throws EntityNotFoundException, ConstraintsException {
		Calendar c = new GregorianCalendar(2017, 12, 12);
		offerDto = new OfferDto(1L, "teste", c, OfferStatus.OPEN);
		offerService.createOffer(offerDto);
		mockApplication1 = new ApplicationDto(1L, offerDto, "neo@neo.com", "text", ApplicationStatus.APPLIED);
		mockApplication2 = new ApplicationDto(2L, offerDto, "neo@neo.com", "text", ApplicationStatus.APPLIED);
		applicationService.createApplication(mockApplication2);
	}

	@Test
	public void creatApplication() throws Exception {
		 when(applicationService.createApplication(mockApplication1)).thenReturn(mockApplication1);
		this.mockMvc.perform(post("/api/application").contentType("application/json")
				.content(mapper.writeValueAsString(mockApplication1))).andExpect(status().isOk());
	}

	@Test
	public void getAll() throws Exception {
		when(applicationService.getAll()).thenReturn(applicationList);
		this.mockMvc.perform(get("/api/application")).andExpect(status().isOk())
				.andExpect(content().json(mapper.writeValueAsString(applicationList)));
	}

	@Test
	public void getApplicationsByOfferId() throws Exception {
		when(applicationService.getApplicationsByOfferId(1L)).thenReturn(applicationList);
		this.mockMvc.perform(get("/api/application/1")).andExpect(status().isOk())
				.andExpect(content().json(mapper.writeValueAsString(applicationList)));
	}

	@Test
	public void updeteApplication() throws Exception {
		when(applicationService.updateApplication(1L, ApplicationStatus.HIRED)).thenReturn(mockApplication1);
		this.mockMvc
				.perform(put("/api/application/1").param("applicationStatus", ApplicationStatus.HIRED.toString())
						.contentType("application/json").content(mapper.writeValueAsString(mockApplication1)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void getById() throws JsonProcessingException, Exception{
		when(applicationService.getById(1L)).thenReturn(mockApplication1);
		this.mockMvc
				.perform(get("/api/application/1/application")
						.contentType("application/json").content(mapper.writeValueAsString(mockApplication1)))
				.andExpect(status().isOk());
	}
}