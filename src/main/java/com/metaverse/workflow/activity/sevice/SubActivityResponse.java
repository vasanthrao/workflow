package com.metaverse.workflow.activity.sevice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.metaverse.workflow.model.Activity;
import com.metaverse.workflow.model.SubActivity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubActivityResponse {

    private Long activityId;
    private String activityName;
    private Date createdOn;
    private Date updatedOn;
    private List<Activity> Activities;

}
