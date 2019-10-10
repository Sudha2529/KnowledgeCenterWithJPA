package com.knowledgeCenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.knowledgeCenter.model.Document;

@Repository
public interface JPADocumentRepository extends JpaRepository<Document, Integer> {
	List<Document> findByCategoryId(int categoryId);
	
	@Query(
			value = "SELECT * FROM document WHERE (content LIKE %?1% OR title LIKE %?1% OR question LIKE %?1% OR answer LIKE %?1%)",
			nativeQuery = true)
	public List<Document> findByanswerOrquestionOrcontentOrtitle(String keyword);
}
