package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.ONDCRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ONDCRegistrationRepository extends JpaRepository<ONDCRegistration, Long> {
}
