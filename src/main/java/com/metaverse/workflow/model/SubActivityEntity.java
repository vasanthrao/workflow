package com.metaverse.workflow.model;

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
@Table(name="sub_activity")
public class SubActivityEntity {
    @Id
    @Column(name="sub_activity_id")
    private Long subActivityId;
    @Column(name="sub_activity_name")
    private String subActivityName;

    @Column(name="organization_type")
    private String organizationType;

    @Column(name="organization_category")
    private String organizationCategory;

}
