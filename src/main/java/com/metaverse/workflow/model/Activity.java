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
@Table(name="activity")
public class Activity {
    @Id
    @Column(name="activity_id")
    private Long activityId;
    @Column(name="activity_name")
    private String activityName;
    @Column(name="created_on")
    private Date createdOn;
    @Column(name="updated_on")
    private Date updatedOn;

}
