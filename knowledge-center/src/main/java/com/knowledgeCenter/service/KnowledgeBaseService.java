package com.knowledgeCenter.service;

import com.knowledgeCenter.model.Category;
import com.knowledgeCenter.model.KnowledgeBase;

public interface KnowledgeBaseService {

	void createKnowledge(KnowledgeBase knowledge);

	void createCategory(Integer knowledgeBaseId, Category category);

	void updateKnowledge(KnowledgeBase knowledgeBase, int id);

	void updateCategory(Category category, Integer knowledgeBaseId, Integer id);

	void deleteKnowledge(Integer id);

	void deleteCategory(Integer id);

}