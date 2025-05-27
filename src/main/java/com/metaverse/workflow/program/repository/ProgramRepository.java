package com.metaverse.workflow.program.repository;

import com.metaverse.workflow.model.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    Page<Program> findByAgencyAgencyId(Long agencyId, Pageable pageable);

    List<Program> findByAgencyAgencyId(Long agencyId);

    List<Program> findByAgencyAgencyIdAndStatus(Long agencyId, String status);

    @Query(value = "SELECT * FROM program WHERE DATE_ADD(start_date, INTERVAL 2 DAY) = CURRENT_DATE", nativeQuery = true)
    List<Program> findProgramsWithStartDateTwoDaysAgo();

    List<Program> findByAgencyAgencyIdAndStatusIn(Long agencyId, List<String> statuses);

    Boolean existsByLocation_LocationId(Long locationId);
}
