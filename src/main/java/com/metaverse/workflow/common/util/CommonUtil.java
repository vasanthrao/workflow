package com.metaverse.workflow.common.util;

import com.metaverse.workflow.activity.sevice.ActivityService;
import com.metaverse.workflow.agency.service.AgencyService;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.districtswithmandals.service.DistrictService;
import com.metaverse.workflow.model.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@AllArgsConstructor
@Slf4j
@Component
public class CommonUtil {

    @Autowired
    DistrictService districtService;

    @Autowired
    ActivityService activityService;

    @Autowired
    AgencyService agencyService;

    public static Map<Integer, String> districtMap;
    public static Map<Integer, String> mandalMap;
    public static Map<Long, String> activityMap;
    public static Map<Long, String> subActivityMap;

    public static Map<Long, String> agencyMap;


    public void init() {
        List<District> districtList = districtService.getAllDistrictsEntity();
        districtMap = districtList.stream().collect(Collectors.toMap(dist -> dist.getDistrictId(), dist -> dist.getDistrictName()));

        List<Mandal> mandalList = districtService.getAllMandalsEntity();
        mandalMap = mandalList.stream().collect(Collectors.toMap(mandal -> mandal.getMandalId(), mandal -> mandal.getMandalName()));

        List<Activity> activityList = activityService.getActivityEntities();
        activityMap = activityList.stream().collect(Collectors.toMap(activity -> activity.getActivityId(), activity -> activity.getActivityName()));

        List<SubActivity> subActivityList = activityService.getSubActivityEntities();
        subActivityMap = subActivityList.stream().collect(Collectors.toMap(subactivity -> subactivity.getSubActivityId(), subactivity -> subactivity.getSubActivityName()));

        List<Agency> agencyList = agencyService.getAllAgencies();
        agencyMap = agencyList.stream().collect(Collectors.toMap(agency -> agency.getAgencyId(), agency -> agency.getAgencyName()));

    }


}
