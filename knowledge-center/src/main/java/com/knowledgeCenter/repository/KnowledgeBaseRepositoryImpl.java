package com.knowledgeCenter.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.knowledgeCenter.model.Category;
import com.knowledgeCenter.model.KnowledgeBase;

@Repository
public class KnowledgeBaseRepositoryImpl implements KnowledgeBaseRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public KnowledgeBaseRepositoryImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// This query is used to insert the knowledgebase into the database.
	private final String SQL_INSERT_KNOWLEDGEBASE = "INSERT INTO knowledgebase (knowledgeDescription, supportedLanguage) VALUES (?,?)";

	// This query is used to insert the category into the database.
	private final String SQL_INSERT_CATEGORY = "INSERT INTO category (categoryName, knowledgebase_id) VALUES (?,?)";

	@Override
	public void createKnowledge(KnowledgeBase knowledge) {
		jdbcTemplate.update(SQL_INSERT_KNOWLEDGEBASE, knowledge.getKnowledgeDescription(),
				knowledge.getSupportedLanguage());
	}

	@Override
	public void createCategory(Category category) {
		//jdbcTemplate.update(SQL_INSERT_CATEGORY, category.getCategoryName(), category.getKnowledgeBaseId());
	}
}
