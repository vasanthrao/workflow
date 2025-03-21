package com.metaverse.workflow.activity.sevice;

import com.metaverse.workflow.model.Activity;

public class ActivityRequestMapper {

    public static Activity map(ActivityRequest activityRequest)
    {
        return Activity.builder()
                .activityName(activityRequest.getActivityName())
                .subActivities(activityRequest.getSubActivities())
                .build();
    }
}
