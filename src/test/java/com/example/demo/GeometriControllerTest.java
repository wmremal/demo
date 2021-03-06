package com.example.demo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.example.demo.service.GeometriController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GeometriControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private GeometriController geometriController;
	
	@Before
	public void deleteAllaBefore() throws Exception {
		geometriController.deleteAll();
	}

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {

		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(
				jsonPath("$._links.geometri").exists());
	}
	@Test
	public void shouldCreateEntity() throws Exception {

		mockMvc.perform(post("/geometri").content(
				"{\"granslinje\": \"Gräns1\", \"yta\":0.5}")).andExpect(
						status().isCreated()).andExpect(
								header().string("Location", containsString("geometri/")));
	}
}
