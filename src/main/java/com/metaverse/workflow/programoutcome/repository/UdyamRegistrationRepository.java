package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.UdyamRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UdyamRegistrationRepository extends JpaRepository<UdyamRegistration,Long> {
    @Query("SELECT COUNT(u) FROM UdyamRegistration u WHERE u.agency.agencyId = :agencyId AND u.udyamRegistationDate BETWEEN :startDate AND :endDate")
    long countByAgencyAndRegistrationDateBetween(@Param("agencyId") Long agencyId,
                                                 @Param("startDate") Date startDate,
                                                 @Param("endDate") Date endDate);
}
