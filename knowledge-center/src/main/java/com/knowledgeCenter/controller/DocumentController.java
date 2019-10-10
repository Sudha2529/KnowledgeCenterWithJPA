package com.knowledgeCenter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.knowledgeCenter.model.Document;
import com.knowledgeCenter.service.DocumentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/v1")
@Api(value = "Document Creation", description = "Operations related to creation of  Document")
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	/**
	 * This API is used to create document of type FAQ.
	 * 
	 * @param document which contains the fields like question ,answer ,category.
	 * @return If the document is uploaded successfully, it returns the HttpStatus
	 *         code and Message.
	 */
	@ApiOperation(value = "Creating a Document of Type FAQ")
	@PostMapping("/document/faq")
	public ResponseEntity<?> createFAQDocument(@RequestBody @Valid Document document) {
		documentService.createFAQDocument(document);
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", "CREATED");
		response.put("message", "Your document has been uploaded successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/**
	 * This API is used to create document of type Article.
	 * 
	 * @param document which contains the fields like title , content.
	 * @return If the document is uploaded successfully, it returns the HttpStatus
	 *         code and Message.
	 */
	@ApiOperation(value = "Creating a Document of Type Article")
	@PostMapping("/document/article")
	public ResponseEntity<?> createArticleDocument(@RequestBody @Valid Document document) {
		documentService.createArticleDocument(document);
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", "CREATED");
		response.put("message", "Your document has been uploaded successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/**
	 * This API is used to create single or multiple documents of FAQ or Article.
	 * 
	 * @param document which contains a list of documents to be uploaded
	 * @return If the documents are uploaded successfully, it returns the HttpStatus
	 *         code and Message.
	 */
	@ApiOperation(value = "Creating a list of documents of FAQ or Article or both")
	@PostMapping("/document")
	public ResponseEntity<?> createDocument(@RequestBody @Valid List<Document> document) {
		documentService.createDocument(document);
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", "CREATED");
		response.put("message", "Your document has been uploaded successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/**
	 * This API retrieves the documents which belong to a specific category.
	 * 
	 * @param categoryId
	 * @return The list of documents which belong to a given category.
	 */
	@ApiOperation(value = "Retrives the list of documents that belong to a specific category")
	@GetMapping("/documents1")
	public List<Document> getDocumentsByCategory(@RequestParam int categoryId) {
		List<Document> documents = documentService.getDocumentsByCategory(categoryId);
		return documents;
	}

	/**
	 * This API retrieves the documents which contains the given keyword.
	 * 
	 * @param keyword
	 * @return The list of documents which contains the given keyword.
	 */
	@ApiOperation(value = "Retrieves the list of documents that matches the given keyword")
	@RequestMapping(value = "/documents", method = RequestMethod.GET, params = "search")
	public List<Document> getDocumentsByKeyword(@ApiParam(value = "Description for Query parameter")@RequestParam("search") String keyword) {
		List<Document> documents = documentService.getDocumentsByKeyword(keyword);
		return documents;
	}

	/**
	 * This API is used to update the documents
	 * 
	 * @param document
	 * @param id
	 * @return If the document is updated successfully, it returns the HttpStatus
	 *         code and Message.
	 */
	@ApiOperation(value = "Updates the Existing Document")
	@PutMapping("/document/{id}")
	public ResponseEntity<?> updateDocument(@RequestBody @Valid Document document, @PathVariable Integer id) {
		documentService.udpateDocument(document, id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", "Updated");
		response.put("message", "Your document has been updated successfully");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * This API is used to delete the document
	 * 
	 * @param id
	 * @return If the document is deleted successfully, it returns the HttpStatus
	 *         code and Message.
	 */
	@ApiOperation(value = "Deletes the Existing Document")
	@DeleteMapping("/document/{id}")
	public ResponseEntity<?> deleteDocument(@PathVariable Integer id) {
		documentService.deleteDocument(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", "Updated");
		response.put("message", "Your document has been deleted successfully");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
