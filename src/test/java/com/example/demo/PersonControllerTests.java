package com.example.demo;


import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.demo.service.PersonController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PersonController personController;
	
	@Before
	public void deleteAllaBefore() throws Exception {
		personController.deleteAll();
	}
	
	@Test
	public void shouldReturnControllerIndex() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(
				status().isOk()).andExpect(jsonPath("$._links.person").exists());
	}
	
	@Test
	public void shouldCreateEntity() throws Exception{
		this.mockMvc.perform(post("/person").content("{\"fornamn\":\"Reza\",\"efternamn\":\"Malekkhaiat\"}"))
		.andExpect(status().isCreated())
		.andExpect(header().string("Location", containsString("person/")));
	}

	@Test
	public void shouldRetrieveEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/person").content(
				"{\"fornamn\": \"Frodo\", \"efternamn\":\"Baggins\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.fornamn").value("Frodo")).andExpect(
						jsonPath("$.efternamn").value("Baggins"));
	}

	@Test
	public void shouldQueryEntity() throws Exception {

		mockMvc.perform(post("/person").content(
				"{ \"fornamn\": \"Frodo\", \"efternamn\":\"Baggins\"}")).andExpect(
						status().isCreated());

		mockMvc.perform(
				get("/person/search/findByEfternamn?efternamn={efternamn}", "Baggins")).andExpect(
						status().isOk()).andExpect(
								jsonPath("$._embedded.person[0].fornamn").value(
										"Frodo"));
	}

	@Test
	public void shouldUpdateEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/person").content(
				"{\"fornamn\": \"Frodo\", \"efternamn\":\"Baggins\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(put(location).content(
				"{\"fornamn\": \"Bilbo\", \"efternamn\":\"Baggins\"}")).andExpect(
						status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.fornamn").value("Bilbo")).andExpect(
						jsonPath("$.efternamn").value("Baggins"));
	}

	@Test
	public void shouldPartiallyUpdateEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/person").content(
				"{\"fornamn\": \"Frodo\", \"efternamn\":\"Baggins\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(
				patch(location).content("{\"fornamn\": \"Bilbo Jr.\"}")).andExpect(
						status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.fornamn").value("Bilbo Jr.")).andExpect(
						jsonPath("$.efternamn").value("Baggins"));
	}

	@Test
	public void shouldDeleteEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/person").content(
				"{ \"fornamn\": \"Bilbo\", \"efternamn\":\"Baggins\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(delete(location)).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}
}
