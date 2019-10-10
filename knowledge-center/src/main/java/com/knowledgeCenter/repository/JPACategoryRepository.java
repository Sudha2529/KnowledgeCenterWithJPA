package com.knowledgeCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowledgeCenter.model.Category;

@Repository
public interface JPACategoryRepository extends JpaRepository<Category, Integer> {

}
