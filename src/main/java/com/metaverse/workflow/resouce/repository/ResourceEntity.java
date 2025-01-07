package com.metaverse.workflow.resouce.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="resource")
public class ResourceEntity {
    @Id
    @Column(name="resource_id")
    private Long resourceId;
    private String name;
    private Character gender;
    private String category;
    private Long aadhar_no;
    private Long mobileNo;
    private Integer capacity;
    private String email;
    private String address;
    private String state;
    private String dist;
}
