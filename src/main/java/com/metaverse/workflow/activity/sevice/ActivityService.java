package com.metaverse.workflow.activity.sevice;

import com.metaverse.workflow.common.response.WorkflowResponse;

public interface ActivityService {
    WorkflowResponse getActivities();
    WorkflowResponse getActivityById(Long id);
    WorkflowResponse getActivityByAgencyId(Long id);
    WorkflowResponse getSubActivityById(Long id);
    WorkflowResponse saveActivity(ActivityRequest activityRequest);
}
