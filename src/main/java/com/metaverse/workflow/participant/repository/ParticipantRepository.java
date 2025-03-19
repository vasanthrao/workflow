package com.metaverse.workflow.participant.repository;

import com.metaverse.workflow.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import com.metaverse.workflow.model.Participant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByPrograms(Program program);

    List<Participant> findByOrganizationMandal(String mandal);

    Participant findByMobileNo(Long mobileNo);

    @Query("SELECT DISTINCT p FROM Participant p JOIN p.programs prog WHERE prog.programType = :typeOfProgram")
    List<Participant> findByProgramType(@Param("typeOfProgram") String typeOfProgram);
}
