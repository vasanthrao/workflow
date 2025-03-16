package com.metaverse.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="sub_activity")
public class SubActivity {
    @Id
    @Column(name="sub_activity_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long subActivityId;
    @Column(name="sub_activity_name")
    private String subActivityName;
    @Column(name="created_on",insertable = true,updatable = false)
    @CreationTimestamp
    private Date createdOn;
    @Column(name="updated_on",insertable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedOn;

    @ManyToMany
    @JoinTable(name ="activity_sub_activity",
    joinColumns = @JoinColumn(name="sub_activity_id",referencedColumnName = "sub_activity_id"),
    inverseJoinColumns = @JoinColumn(name="activity_id",referencedColumnName = "activity_id"))
    @JsonIgnore
    private List<Activity> activities;
}
