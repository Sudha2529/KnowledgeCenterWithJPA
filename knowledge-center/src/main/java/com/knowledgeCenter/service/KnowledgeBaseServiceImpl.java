package com.knowledgeCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowledgeCenter.exception.KnowledgeNotFoundException;
import com.knowledgeCenter.model.Category;
import com.knowledgeCenter.model.KnowledgeBase;
import com.knowledgeCenter.repository.JPACategoryRepository;
import com.knowledgeCenter.repository.JPAKnowledgeRepository;

@Service
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {

	@Autowired
	private JPAKnowledgeRepository knowledgeBaseRepository;

	@Autowired
	private JPACategoryRepository categoryRepository;

	@Override
	public void createKnowledge(KnowledgeBase knowledge) {
		knowledgeBaseRepository.save(knowledge);
	}

	@Override
	public void updateKnowledge(KnowledgeBase knowledgeBase, int id) {
		KnowledgeBase knowledgeEntity = knowledgeBaseRepository.findOne(id);
		if (knowledgeEntity == null) {
			throw new KnowledgeNotFoundException("No Knowledge Base found with given ID");
		} else {
			knowledgeEntity.setCategory(knowledgeBase.getCategory());
			knowledgeEntity.setKnowledgeDescription(knowledgeBase.getKnowledgeDescription());
			knowledgeEntity.setSupportedLanguage(knowledgeBase.getSupportedLanguage());
			knowledgeBaseRepository.save(knowledgeEntity);
		}
	}

	@Override
	public void createCategory(Integer knowledgeBaseId, Category category) {
		KnowledgeBase knowledge = knowledgeBaseRepository.findOne(knowledgeBaseId);
		if (knowledge == null) {
			throw new KnowledgeNotFoundException("No Knowledge Base exists with given ID");
		} else {
			category.setKnowledgeBase(knowledge);
			categoryRepository.save(category);
		}
	}

	@Override
	public void updateCategory(Category category, Integer knowledgeBaseId, Integer id) {
		KnowledgeBase knowledge = knowledgeBaseRepository.findOne(knowledgeBaseId);
		if (knowledge == null) {
			throw new KnowledgeNotFoundException("No Knowledge Exists with the given ID");
		}
		Category categoryEntity = categoryRepository.findOne(id);
		categoryEntity.setCategoryName(category.getCategoryName());
		categoryRepository.save(categoryEntity);
	}

	@Override
	public void deleteKnowledge(Integer id) {
		knowledgeBaseRepository.delete(id);
	}

	@Override
	public void deleteCategory(Integer id) {
		categoryRepository.delete(id);
	}

}
