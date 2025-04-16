package com.metaverse.workflow.expenditure.repository;

import com.metaverse.workflow.model.CommonExpenditure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonExpenditureRepository extends JpaRepository<CommonExpenditure,Long> {
}
