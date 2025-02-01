package com.metaverse.workflow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metaverse.workflow.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long>{
}
