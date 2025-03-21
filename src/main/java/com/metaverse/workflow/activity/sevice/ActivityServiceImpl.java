package com.metaverse.workflow.activity.sevice;

import com.metaverse.workflow.activity.repository.ActivityRepository;
import com.metaverse.workflow.activity.repository.SubActivityRepository;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.model.Activity;
import com.metaverse.workflow.model.SubActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
@Service
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private SubActivityRepository subActivityRepository;



    @Override
    public WorkflowResponse saveActivity(ActivityRequest activityRequest) {
        List<SubActivity> savedSubActivities = activityRequest.getSubActivities()
                .stream()
                .map(subActivityRepository::save)
                .toList();

        Activity activity=ActivityRequestMapper.map(activityRequest);

        activityRepository.save(activity);
        return WorkflowResponse.builder()
                .message("Activity saved successfully")
                .status(200)
                .data(ActivityResponseMapper.map(activity))
                .build();
    }
    @Override
    public WorkflowResponse getSubActivityById(Long id) {
        Optional<SubActivity> subActivity = subActivityRepository.findById(id);
        if(!subActivity.isPresent())return WorkflowResponse.builder()
                .message("SubActivity Not found")
                .status(400)
                .build();
        SubActivityResponse  response = SubActivityResponseMapper.map(subActivity.get());
        return WorkflowResponse.builder()
                .message("Success")
                .status(200)
                .data(response)
                .build();
    }


    @Override
    public WorkflowResponse getActivityById(Long id) {
        Optional<Activity> activity = activityRepository.findById(id);
        if(!activity.isPresent())return WorkflowResponse.builder()
                .message("Activity Not found")
                .status(400)
                .build();
        ActivityResponse response = ActivityResponseMapper.map(activity.get());
        return WorkflowResponse.builder()
                .message("Success")
                .status(200)
                .data(response)
                .build();
    }


    @Override
    public WorkflowResponse getActivities() {
        List<Activity> activityList = activityRepository.findAll();
        if (activityList.isEmpty()) {
            return WorkflowResponse.builder()
                    .message("No activities found")
                    .status(404)
                    .data(Collections.emptyList())
                    .build();
        }
        List<ActivityResponse> response = activityList.stream()
                .map(activity -> ActivityResponseMapper.map(activity))
                .collect(Collectors.toList());
        return WorkflowResponse.builder()
                .message("Success")
                .status(200)
                .data(response)
                .build();
    }

}
