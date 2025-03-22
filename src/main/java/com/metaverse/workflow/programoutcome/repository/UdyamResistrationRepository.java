package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.UdyamRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UdyamResistrationRepository extends JpaRepository<UdyamRegistration,Long> {
}
