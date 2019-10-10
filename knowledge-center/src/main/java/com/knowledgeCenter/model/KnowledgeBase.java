package com.knowledgeCenter.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "knowledgeBase")
@ApiModel(description = "All details about Knowledge Base")
public class KnowledgeBase {

	@Id
	@GeneratedValue
	@ApiModelProperty(hidden = true)
	private int id;

	@Column(name = "knowledgeDescription")
	@ApiModelProperty(notes = "Name of the knowledge Base")
	private String knowledgeDescription;

	@Column(name = "supportedLanguage")
	@ApiModelProperty(name = "Supported Language")
	private String supportedLanguage;

	@OneToMany(mappedBy = "knowledgeBase", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<Category> Category;

	public KnowledgeBase() {

	}

	public KnowledgeBase(int id, String knowledgeDescription, String supportedLanguage) {
		this.id = id;
		this.knowledgeDescription = knowledgeDescription;
		this.supportedLanguage = supportedLanguage;
	}

	public List<Category> getCategory() {
		return Category;
	}

	public int getId() {
		return id;
	}

	public String getKnowledgeDescription() {
		return knowledgeDescription;
	}

	public String getSupportedLanguage() {
		return supportedLanguage;
	}

	public void setCategory(List<Category> category) {
		Category = category;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setKnowledgeDescription(String knowledgeDescription) {
		this.knowledgeDescription = knowledgeDescription;
	}

	public void setSupportedLanguage(String supportedLanguage) {
		this.supportedLanguage = supportedLanguage;
	}
}
