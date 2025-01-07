package com.metaverse.workflow.organization.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="organization")
public class OrganizationEntity {
    @Id
    @Column(name="organization_id")
    private Long organizationId;
    private String organisationCategory;
    private String organisationType;
    private String businessSector;
    private Character isUdyamRegistrationEnable;
    private String registrationNo;
    private Date yearOfReistration;
    private String state;
    private String dist;
    private String address;
    private Double latitude;
    private Double longitude;
    private String email;
    private Long contactNo;
    private String website;
    private String ownerName;
    private Long ownerContactNo;
    private String ownerEmail;
    private String ownerAddress;
}
