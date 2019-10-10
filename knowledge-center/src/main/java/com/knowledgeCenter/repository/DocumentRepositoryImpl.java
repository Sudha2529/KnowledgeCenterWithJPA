package com.knowledgeCenter.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.knowledgeCenter.model.Document;
import com.knowledgeCenter.utils.DocumentRowMapper;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

	private JdbcTemplate jdbcTemplate;

	// This query is used to insert a document of type FAQ into the database.
	private final String SQL_INSERT_FAQ_DOCUMENT = "INSERT INTO document (type, category_id, question, answer) VALUES (?,?,?,?)";

	// This query is used to insert a document of type Article into the database.
	private final String SQL_INSERT_ARTICLE_DOCUMENT = "INSERT INTO document (type, category_id, title, content) VALUES (?,?,?,?)";

	// This query is used to insert a list of documents into the database.
	private final String SQL_INSERT_DOCUMENT = "INSERT INTO document (type, category_id, title, content, question, answer) VALUES (?,?,?,?,?,?)";

	// This query is returns the list of documents which belongs to a given
	// category.
	private final String SQL_GET_DOCUMENTS_BY_CATEGORY = "SELECT * FROM document WHERE category_id = ?";

	// This query returns the list of documents which contains the given keyword and
	// are not uploaded immediately.
	private final String SQL_GET_DOCUMENTS_BY_KEYWORD = "SELECT * FROM document WHERE createdts <= DATE_SUB(SYSDATE(), INTERVAL 12 HOUR) AND (content LIKE ? OR title LIKE ? OR question LIKE ? OR answer LIKE ?)";

	@Autowired
	public DocumentRepositoryImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void createFAQDocument(Document document) {
		jdbcTemplate.update(SQL_INSERT_FAQ_DOCUMENT, document.getType(), document.getCategoryId(),
				document.getQuestion(), document.getAnswer());
	}

	@Override
	public void createArticleDocument(Document document) {
		jdbcTemplate.update(SQL_INSERT_ARTICLE_DOCUMENT, document.getType(), document.getCategoryId(),
				document.getTitle(), document.getContent());
	}

	@Override
	public void createDocument(List<Document> documentList) {
		for (int i = 0; i < documentList.size(); i++) {
			jdbcTemplate.batchUpdate(SQL_INSERT_DOCUMENT, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Document document = documentList.get(i);
					ps.setString(1, document.getType());
					ps.setInt(2, document.getCategoryId());
					ps.setString(3, document.getTitle());
					ps.setString(4, document.getContent());
					ps.setString(5, document.getQuestion());
					ps.setString(6, document.getAnswer());
				}

				@Override
				public int getBatchSize() {
					return documentList.size();
				}
			});
		}
	}

	@Override
	public List<Document> getDocumentsByCategory(int categoryId) {
		return jdbcTemplate.query(SQL_GET_DOCUMENTS_BY_CATEGORY, new Object[] { categoryId }, new DocumentRowMapper());
	}

	@Override
	public List<Document> getDocumentsByKeyword(String keyword) {
		return jdbcTemplate.query(SQL_GET_DOCUMENTS_BY_KEYWORD,
				new String[] { "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%" },
				new DocumentRowMapper());
	}

}
