package com.knowledgeCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowledgeCenter.model.KnowledgeBase;

@Repository
public interface JPAKnowledgeRepository extends JpaRepository<KnowledgeBase, Integer> {

}
