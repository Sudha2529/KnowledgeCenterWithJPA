package com.knowledgeCenter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowledgeCenter.config.AppConfig;
import com.knowledgeCenter.controller.KnowledgeBaseController;
import com.knowledgeCenter.model.Category;
import com.knowledgeCenter.model.KnowledgeBase;
import com.knowledgeCenter.service.KnowledgeBaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class KnowledgeControllerTest {

	private MockMvc mockmvc;

	private static final ObjectMapper mapper = new ObjectMapper();

	@InjectMocks
	private KnowledgeBaseController knowledgeBaseController;

	@Mock
	private KnowledgeBaseService knowledgeBaseService;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(knowledgeBaseController).build();
	}

	@Test
	public void verifySaveKnowledgeSuccess() throws Exception {
		final KnowledgeBase knowledge = new KnowledgeBase();
		knowledge.setKnowledgeDescription("Banking");
		knowledge.setSupportedLanguage("English");
		String json = mapper.writeValueAsString(knowledge);
		System.out.println(json);
		mockmvc.perform(post("/api/v1/knowledge").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
	}

	@Test
	public void verifyUpdateKnowledgeSuccess() throws Exception {
		final KnowledgeBase knowledge = new KnowledgeBase();
		knowledge.setKnowledgeDescription("Airlines");
		knowledge.setSupportedLanguage("Spanish");
		String json = mapper.writeValueAsString(knowledge);
		System.out.println(json);
		mockmvc.perform(put("/api/v1/knowledge/0").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void verifyDeleteKnowledgeSuccess() throws Exception {
		mockmvc.perform(delete("/api/v1/knowledge/0")).andExpect(status().isOk());
	}

	@Test
	public void verifySaveCategorySuccess() throws Exception {
		final Category category = new Category();
		category.setCategoryName("Accounts Management");
		String json = mapper.writeValueAsString(category);
		System.out.println(json);
		mockmvc.perform(post("/api/v1/knowledge/0/category").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
	}

	@Test
	public void verifyUpdateCategorySuccess() throws Exception {
		final Category category = new Category();
		category.setCategoryName("Credit And Loan");
		String json = mapper.writeValueAsString(category);
		System.out.println(json);
		mockmvc.perform(put("/api/v1/knowledge/0/category/0").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void verifyDeleteCategorySuccess() throws Exception {
		mockmvc.perform(delete("/api/v1/knowledge/category/0")).andExpect(status().isOk());
	}

}
