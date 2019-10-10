package com.knowledgeCenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "category")
@ApiModel(description = "Details about Category")
public class Category {

	@Id
	@GeneratedValue
	@ApiModelProperty(hidden = true)
	private int id;

	@Column(name = "categoryName")
	@ApiModelProperty(notes = "The Name of the category")
	private String categoryName;

	@ManyToOne
	@JoinColumn(name = "knowledgeBaseId")
	@ApiModelProperty(name = "Foreign Key relationship between Category and KnowledgeBase", hidden = true)
	private KnowledgeBase knowledgeBase;

	public String getCategoryName() {
		return categoryName;
	}

	public int getId() {
		return id;
	}

	public KnowledgeBase getKnowledgeBase() {
		return knowledgeBase;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setKnowledgeBase(KnowledgeBase knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}
}
