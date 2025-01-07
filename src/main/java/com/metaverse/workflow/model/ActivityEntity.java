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
@Table(name="activity")
public class ActivityEntity {
    @Id
    @Column(name="activity_id")
    private Long activityId;
    @Column(name="activity_name")
    private String activityName;

}
