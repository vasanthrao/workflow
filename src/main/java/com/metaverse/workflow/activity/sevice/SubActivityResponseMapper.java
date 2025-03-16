package com.metaverse.workflow.activity.sevice;

import com.metaverse.workflow.model.Activity;
import com.metaverse.workflow.model.SubActivity;

public class SubActivityResponseMapper {

    public static SubActivityResponse map(SubActivity subActivity)
    {
        SubActivityResponse response = SubActivityResponse.builder()
                .activityId(subActivity.getSubActivityId())
                .activityName(subActivity.getSubActivityName())
                .createdOn(subActivity.getCreatedOn())
                .updatedOn(subActivity.getUpdatedOn())
                .Activities(subActivity.getActivities())
                .build();
        return response;
    }
}
