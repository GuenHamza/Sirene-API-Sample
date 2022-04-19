package com.izicap.sirene.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.izicap.sirene.api.controller.CompanySiretControllerImpl;
import com.izicap.sirene.api.controller.client.CompanySiretController;
import com.izicap.sirene.api.service.client.CompanySiretService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ApiSireneApplicationTests {

	private MockMvc mockMvc;

	private static String ENDPOINT = "/api/companyInfo";

	private static String validSiret = "55203253400646";

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

	@Autowired
	private CompanySiretService configurationService;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		CompanySiretController configurationController = new CompanySiretControllerImpl();
		this.mockMvc = MockMvcBuilders.standaloneSetup(configurationController).build();
	}

	@Test
	void gievenValidSiretWhenSiretExistsThenOk() throws Exception {
		mockMvc.perform(post(ENDPOINT + "/" + validSiret)
				.contentType(APPLICATION_JSON_UTF8)).andExpect(status().isOk());
	}

}
