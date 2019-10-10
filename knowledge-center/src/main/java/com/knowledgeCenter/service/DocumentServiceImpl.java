package com.knowledgeCenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.knowledgeCenter.exception.DocumentNotFoundException;
import com.knowledgeCenter.model.Document;
import com.knowledgeCenter.repository.JPADocumentRepository;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private JPADocumentRepository documentRepository;

	@Override
	public void createFAQDocument(Document document) {
		// The validation throws an IllegalArgException which will be caught by
		// DocumentExcpetionHandler
		Assert.notNull(document.getQuestion(), "An invalid faq sent in request");
		Assert.notNull(document.getAnswer(), "An invalid faq sent in request");
		documentRepository.save(document);
	}

	@Override
	public void createArticleDocument(Document document) {
		// The validation throws an IllegalArgException which will be caught by
		// DocumentExcpetionHandler
		Assert.notNull(document.getContent(), "An invalid content sent in request");
		documentRepository.save(document);
	}

	@Override
	public void createDocument(List<Document> document) {
		documentRepository.save(document);
	}

	@Override
	public List<Document> getDocumentsByCategory(int categoryId) {
		List<Document> documents = documentRepository.findByCategoryId(categoryId);
		return documents;
	}

	@Override
	public List<Document> getDocumentsByKeyword(String keyword) {
		List<Document> documents = documentRepository.findByanswerOrquestionOrcontentOrtitle(keyword);

		if (documents == null || documents.size() == 0) {
			throw new DocumentNotFoundException("No Documents found with given keyword");
		}
		return documents;
	}

	@Override
	public void udpateDocument(Document document, Integer id) {
		Document documentEntity = documentRepository.findOne(id);
		if (documentEntity == null) {
			throw new DocumentNotFoundException("No Documents found with given ID");
		} else {
			documentEntity.setCategoryId(document.getCategoryId());
			documentEntity.setAnswer(document.getAnswer());
			documentEntity.setContent(document.getContent());
			documentEntity.setLocale(document.getLocale());
			documentEntity.setQuestion(document.getQuestion());
			documentEntity.setTitle(document.getTitle());
			documentEntity.setType(document.getType());
			documentRepository.save(documentEntity);
		}

	}

	@Override
	public void deleteDocument(Integer id) {
		documentRepository.delete(id);
	}
}
