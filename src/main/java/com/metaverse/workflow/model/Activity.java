package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="activity")
public class Activity {
    @Id
    @Column(name="activity_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long activityId;
    @Column(name="activity_name")
    private String activityName;
    @Column(name="created_on",insertable = true,updatable = false)
    @CreationTimestamp
    private Date createdOn;
    @Column(name="updated_on",insertable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedOn;

    @ManyToMany(cascade = CascadeType.ALL,targetEntity = SubActivity.class,mappedBy = "activities")
    private List<SubActivity> subActivities;

}
