package com.knowledgeCenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "document")
@ApiModel(description = "Details about Document")
public class Document {

	@Id
	@GeneratedValue
	@ApiModelProperty(hidden = true)
	private int id;

	@NotNull
	@Column(name = "documentType")
	@ApiModelProperty(notes = "Type of the Document either FAQ or Article")
	private String Type;

	@NotNull
	@Column(name = "categoryId")
	@ApiModelProperty(notes = "The Id of the category to which it belongs")
	private int categoryId;

	@Column(name = "content")
	@ApiModelProperty(notes = "The content of the document")
	private String content;

	@Column(name = "title")
	@ApiModelProperty(notes = "Title of the document")
	private String title;

	@Column(name = "answer")
	@ApiModelProperty(notes = "Answer of the FAQ document")
	private String answer;

	@Column(name = "question")
	@ApiModelProperty(notes = "Question of the FAQ document")
	private String question;

	@Column(name = "locale")
	@ApiModelProperty(notes = "Locale")
	private String locale;

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}
