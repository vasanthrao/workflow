package com.metaverse.workflow.callcenter.repository;

import com.metaverse.workflow.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository  extends JpaRepository<Question,Integer> {
}
