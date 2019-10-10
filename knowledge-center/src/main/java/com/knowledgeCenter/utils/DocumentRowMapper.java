package com.knowledgeCenter.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.knowledgeCenter.model.Document;

public class DocumentRowMapper implements RowMapper<Document> {

	@Override
	public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
		Document document = new Document();
		document.setId(rs.getInt("id"));
		document.setType(rs.getString("Type"));
		document.setCategoryId(rs.getInt("category_id"));
		document.setContent(rs.getString("content"));
		document.setTitle(rs.getString("title"));
		document.setQuestion(rs.getString("question"));
		document.setAnswer(rs.getString("answer"));
		return document;
	}
}
