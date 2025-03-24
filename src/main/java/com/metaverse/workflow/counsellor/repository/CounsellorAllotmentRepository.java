package com.metaverse.workflow.counsellor.repository;

import com.metaverse.workflow.model.CounsellorAllotment;
import com.metaverse.workflow.model.Mandal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounsellorAllotmentRepository extends JpaRepository<CounsellorAllotment,Long> {

   /* @Query(value = "SELECT * FROM counsellor_allotment ca JOIN mandal m ON ca.mandal_id = m.mandal_id WHERE m.mandal_Name = :mandalName", nativeQuery = true)
    @Query("SELECT ca FROM CounsellorAllotment ca WHERE ca.allotedMandal.mandalName = :mandalName")
    List<CounsellorAllotment> findByAllocatedMandalName(@Param("mandalName") String mandalName);
    @Query("SELECT ca FROM CounsellorAllotment ca WHERE ca.allotedMandal.mandalName = :mandalName")
    List<CounsellorAllotment> findByAllocatedMandalName(@Param("mandalName") String mandalName);
*/

    List<CounsellorAllotment> findByAllotedMandal(Mandal allotedManda);



}
