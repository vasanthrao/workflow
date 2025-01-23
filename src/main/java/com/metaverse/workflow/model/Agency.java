package com.metaverse.workflow.model;

import jakarta.persistence.*;
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
public class Agency {

    @Id
    @Column(name="agency_id")
    private Long agencyId;
    @Column(name="agency_name")
    private String agencyName;
    @Column(name="mobile_no")
    private Long mobileNo;
    @Column(name="email")
    private String email;
    @Column(name="address")
    private String address;
    @Column(name="created_on")
    private Date createdOn;
    @Column(name="updated_on")
    private Date updatedOn;

}
