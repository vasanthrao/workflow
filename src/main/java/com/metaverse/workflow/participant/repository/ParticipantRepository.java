package com.metaverse.workflow.participant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metaverse.workflow.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant,Long>{
}
