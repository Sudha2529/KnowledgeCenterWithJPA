package com.knowledgeCenter.controller;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.knowledgeCenter.model.Category;
import com.knowledgeCenter.model.KnowledgeBase;
import com.knowledgeCenter.service.KnowledgeBaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1")
@Api(value = "Knowledge Center Management", description = "Operations related to  creation of knowledgeBase and category")
public class KnowledgeBaseController {
	@Autowired
	private KnowledgeBaseService knowledgeBaseService;

	@GetMapping("/hello")
	public String sayHi() {
		return "hi";
	}

	/**
	 * This API is used to create a knowledgeBase.
	 * 
	 * @param knowledge which contains the knowledge name and Knowledge Description
	 * @return If the knowledgeBase is created successfully, it returns the
	 *         HttpStatus code and Message.
	 */
	@ApiOperation(value = "Create a Knowledge Base")
	@PostMapping("/knowledge")
	public ResponseEntity<?> createKnowledgeBase(@RequestBody KnowledgeBase knowledge) {
		knowledgeBaseService.createKnowledge(knowledge);
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", "CREATED");
		response.put("message", "Your KnowledgeBase has been created successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/**
	 * This API is used to create a Category.
	 * 
	 * @param category which contains the category Name and knowledgeBase to which
	 *                 it belongs to.
	 * @return If the category is created successfully, it returns the HttpStatus
	 *         code and Message.
	 */
	@ApiOperation(value = "Create a Category")
	@PostMapping("/knowledge/{knowledgeBaseId}/category")
	public ResponseEntity<?> createCategory(@PathVariable (value = "knowledgeBaseId") Integer knowledgeBaseId,@RequestBody Category category) {
		knowledgeBaseService.createCategory(knowledgeBaseId,category);
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", "CREATED");
		response.put("message", "Your category has been uploaded successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/**
	 * This API is used to update the existing knowledgeBase
	 * 
	 * @param knowledgeBase
	 * @param id
	 * @return If the knowledgeBase is created successfully, it returns the
	 *         HttpStatus code and Message.
	 */
	@ApiOperation(value = "Update a Knowledge Base")
	@PutMapping("knowledge/{id}")
	public ResponseEntity<?> updateKnowledgeBase(@RequestBody KnowledgeBase knowledgeBase, @PathVariable Integer id) {
		knowledgeBaseService.updateKnowledge(knowledgeBase, id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", "Updated");
		response.put("message", "Your KnowledgeBase has been updated successfully");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * This API is used to update the existing category
	 * 
	 * @param category
	 * @param id
	 * @return If the category is updated successfully , it returns the HttpsStatus
	 *         code and Message
	 */
	@ApiOperation(value = "Update a category")
	@PutMapping("/knowledge/{knowledgeBaseId}/category/{id}")
	public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable Integer knowledgeBaseId,@PathVariable Integer id) {
		knowledgeBaseService.updateCategory(category,knowledgeBaseId,id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", "Updated");
		response.put("message", "Your Category has been updated successfully");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * This API is used to delete knowledgeBase
	 * 
	 * @param id
	 * @return If the knowledgeBase is deleted successfully, it returns the
	 *         HttpStatus code and Message.
	 */
	@ApiOperation(value = "Delete a Knowledge Base")
	@DeleteMapping("knowledge/{id}")
	public ResponseEntity<?> deleteKnowledgeBase(@PathVariable Integer id) {
		knowledgeBaseService.deleteKnowledge(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", "Deleted");
		response.put("message", "Your KnowledgeBase has been deleted successfully");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * This API is used to delete category
	 * 
	 * @param id
	 * @return If the category is deleted successfully, it returns the HttpStatus
	 *         code and Message.
	 */
	@ApiOperation(value = "Delete a Category")
	@DeleteMapping("/knowledge/category/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
		knowledgeBaseService.deleteCategory(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", "Deleted");
		response.put("message", "Your Category has been deleted successfully");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
