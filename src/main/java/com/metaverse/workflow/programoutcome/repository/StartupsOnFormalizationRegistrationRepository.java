package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.StartupsOnFormalizationRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StartupsOnFormalizationRegistrationRepository extends JpaRepository<StartupsOnFormalizationRegistration,Long> {
}
