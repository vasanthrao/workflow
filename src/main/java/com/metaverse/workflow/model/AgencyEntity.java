package com.metaverse.workflow.model;

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
@Table(name="agency")
public class AgencyEntity {

    @Id
    @Column(name="agency_id")
    private Long agencyId;
    @Column(name="email")
    private String email;
    @Column(name="created_on")
    private Date createdOn;
    @Column(name="updated_on")
    private Date updatedOn;
    @Column(name="agency_name")
    private String agencyName;
    @Column(name="mobile_no")
    private Long mobileNo;
    @Column(name="address")
    private String address;

}
