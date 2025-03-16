package com.metaverse.workflow.activity.sevice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.metaverse.workflow.model.SubActivity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
@Getter
@Builder
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityResponse {

    private Long activityId;
    private String activityName;
    private Date createdOn;
    private Date updatedOn;
    private List<SubActivity> subActivities;

}
