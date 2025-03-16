package com.metaverse.workflow.activity.sevice;

import com.metaverse.workflow.model.SubActivity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ActivityRequest {
    private Long activityId;
    private String activityName;
    private Date createdOn;
    private Date updatedOn;
    private List<SubActivity> subActivities;
}
