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
@Table(name="resource")
public class Resource {
    @Id
    @Column(name="resource_id")
    private Long resourceId;
    @Column(name="name")
    private String name;
    @Column(name="gender")
    private Character gender;
    @Column(name="category")
    private String category;
    @Column(name="aadhar_no")
    private Long aadharNo;
    @Column(name="mobile_no")
    private Long mobileNo;
    @Column(name="capacity")
    private Integer capacity;
    @Column(name="email")
    private String email;
    @Column(name="state_id")
    private String stateId;
    @Column(name="dist_id")
    private String distId;
    @Column(name="mandal")
    private String mandal;
    @Column(name="town")
    private String town;
    @Column(name="street_or_locality")
    private String streetOrLocality;
    @Column(name="address")
    private String address;
    @Column(name="created_on")
    private Date createdOn;
    @Column(name="updated_on")
    private Date updatedOn;
}
