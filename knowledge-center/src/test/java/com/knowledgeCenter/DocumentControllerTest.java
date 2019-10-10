package com.knowledgeCenter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.knowledgeCenter.controller.DocumentController;
import com.knowledgeCenter.model.Document;
import com.knowledgeCenter.service.DocumentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class DocumentControllerTest {

	private MockMvc mockmvc;

	private static final ObjectMapper mapper = new ObjectMapper();

	@InjectMocks
	private DocumentController documentController;

	@Mock
	private DocumentService documentService;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(documentController).build();
	}

	@Test
	public void verifyCreateDocumentSuccess() throws Exception {
		List<Document> documents = new ArrayList<Document>();
		Document document1 = new Document();
		document1.setId(1);
		document1.setCategoryId(0);
		document1.setType("FAQ");
		document1.setQuestion("What is your name?");
		document1.setAnswer("Sudha");
		document1.setLocale("English");

		Document document2 = new Document();
		document2.setId(2);
		document2.setCategoryId(0);
		document2.setType("Article");
		document2.setTitle("Bank Form Filling");
		document2.setContent("Please make sure you have the following documents");
		document2.setLocale("English");

		documents.add(document1);
		documents.add(document2);

		String json = mapper.writeValueAsString(documents);
		System.out.println(json);
		mockmvc.perform(post("/api/v1/document").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());

	}

	@Test
	public void verifyUpdateDocumentSuccess() throws Exception {
		Document document = new Document();
		document.setId(1);
		document.setCategoryId(0);
		document.setType("FAQ");
		document.setQuestion("What is your favorite Place?");
		document.setAnswer("Hyderabad");
		document.setLocale("English");
		String json = mapper.writeValueAsString(document);
		System.out.println(json);
		mockmvc.perform(put("/api/v1/document/1").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void verifyDeleteDocumentSuccess() throws Exception {
		mockmvc.perform(delete("/api/v1/document/2")).andExpect(status().isOk());
	}

}
