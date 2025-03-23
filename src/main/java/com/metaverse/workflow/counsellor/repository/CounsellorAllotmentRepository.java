package com.metaverse.workflow.counsellor.repository;

import com.metaverse.workflow.model.CounsellorAllotment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CounsellorAllotmentRepository extends JpaRepository<CounsellorAllotment,Long> {

    @Query("SELECT c FROM CounsellorAllotment c WHERE c.allotedMandal.mandalName = :mandalName")
    List<CounsellorAllotment> findByAllotedMandalName(@Param("mandalName") String mandalName);
}
