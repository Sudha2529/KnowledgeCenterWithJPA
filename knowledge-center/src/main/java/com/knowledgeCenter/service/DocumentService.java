package com.knowledgeCenter.service;

import java.util.List;

import javax.validation.Valid;

import com.knowledgeCenter.model.Document;

public interface DocumentService {

	void createFAQDocument(Document document);

	void createArticleDocument(Document document);

	void createDocument(List<Document> document);

	List<Document> getDocumentsByCategory(int categoryId);

	List<Document> getDocumentsByKeyword(String keyword);

	void udpateDocument(@Valid Document document, Integer id);

	void deleteDocument(Integer id);

}