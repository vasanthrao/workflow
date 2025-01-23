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
@Table(name="sub_activity")
public class SubActivity {
    @Id
    @Column(name="sub_activity_id")
    private Long subActivityId;
    @Column(name="sub_activity_name")
    private String subActivityName;
    @Column(name="created_on")
    private Date createdOn;
    @Column(name="updated_on")
    private Date updatedOn;

}
