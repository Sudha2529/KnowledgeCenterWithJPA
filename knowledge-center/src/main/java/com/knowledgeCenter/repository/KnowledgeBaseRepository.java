package com.knowledgeCenter.repository;

import com.knowledgeCenter.model.Category;
import com.knowledgeCenter.model.KnowledgeBase;

public interface KnowledgeBaseRepository {

	void createKnowledge(KnowledgeBase knowledge);

	void createCategory(Category category);

}