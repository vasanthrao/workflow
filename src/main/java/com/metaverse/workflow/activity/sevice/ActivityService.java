package com.metaverse.workflow.activity.sevice;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.model.Activity;
import com.metaverse.workflow.model.Agency;
import com.metaverse.workflow.model.SubActivity;

import java.util.Optional;

public interface ActivityService {
    WorkflowResponse getActivities();
    WorkflowResponse getActivityById(Long id);
    WorkflowResponse getSubActivityById(Long id);
    WorkflowResponse saveActivity(ActivityRequest activityRequest);
}
