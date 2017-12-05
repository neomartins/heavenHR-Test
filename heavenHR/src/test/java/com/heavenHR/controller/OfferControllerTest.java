package com.heavenHR.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenHR.Exceptions.ConstraintsException;
import com.heavenHR.applicationService.ApplicationService;
import com.heavenHR.common.ApplicationStatus;
import com.heavenHR.common.OfferStatus;
import com.heavenHR.dto.ApplicationDto;
import com.heavenHR.dto.OfferDto;
import com.heavenHR.model.Offer;
import com.heavenHR.offerService.OfferService;

@RunWith(SpringRunner.class)
@WebMvcTest(OfferController.class)
public class OfferControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ApplicationService applicationService;

	@MockBean
	private OfferService offerService;

	private ApplicationDto mockApplication2;
	private OfferDto offerDto;
	private OfferDto offerDto2;
	private Offer offer;
	private List<Offer> offerList = new ArrayList<>();
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() throws EntityNotFoundException, ConstraintsException {
		Calendar c = new GregorianCalendar(2017, 12, 12);
		offerDto = new OfferDto(1L, "teste", c, OfferStatus.OPEN);
		offerDto2 = new OfferDto(2L, "teste", c, OfferStatus.OPEN);
		offer = offerService.createOffer(offerDto);
		mockApplication2 = new ApplicationDto(2L, offerDto, "neo@neo.com", "text", ApplicationStatus.APPLIED);
		applicationService.createApplication(mockApplication2);
	}

	@Test
	public void createOffer() throws Exception {
		when(offerService.createOffer(offerDto2)).thenReturn(offer);
		this.mockMvc
				.perform(post("/api/offer").contentType("application/json").content(mapper.writeValueAsString(offerDto2)))
				.andExpect(status().isOk());
	}

	@Test
	public void getAll() throws Exception {
		when(offerService.getAll()).thenReturn(offerList);
		this.mockMvc.perform(get("/api/offer")).andExpect(status().isOk())
				.andExpect(content().json(mapper.writeValueAsString(offerList)));
	}

	@Test
	public void getById() throws Exception {
		when(offerService.getById(1L)).thenReturn(offer);
		this.mockMvc.perform(get("/api/offer/1").contentType("application/json")
				.content(mapper.writeValueAsString(offer))).andExpect(status().isOk());
	}

	@Test
	public void numberOfApplication() throws Exception {
		when(offerService.numberOfApplication(offer)).thenReturn(offer);
		this.mockMvc.perform(get("/api/offer/1/applications").contentType("application/json")
				.content(mapper.writeValueAsString(offer))).andExpect(status().isOk());
	}
}