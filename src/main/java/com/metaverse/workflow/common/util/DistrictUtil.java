package com.metaverse.workflow.common.util;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.districtswithmandals.service.DistrictResponce;
import com.metaverse.workflow.districtswithmandals.service.DistrictService;
import com.metaverse.workflow.districtswithmandals.service.MandalResponce;
import com.metaverse.workflow.model.District;
import com.metaverse.workflow.model.Mandal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@AllArgsConstructor
@Slf4j
public class DistrictUtil {

    @Autowired
    public static DistrictService districtService;

    public static Map<Integer, DistrictResponce> districtList;
    public static Map<Integer, MandalResponce> mandalList;

    public static Map<Integer, District> districtEntityList;
    public static Map<Integer, Mandal> mandalEntityList;



    static {
        WorkflowResponse response = districtService.getAllDistricts();
        List<DistrictResponce> districts = (List<DistrictResponce>) response.getData();
        districtList = districts.stream().collect(Collectors.toMap(dist -> dist.getDistrictId(), dist -> dist));

        WorkflowResponse response1 = districtService.getAllMandals();
        List<MandalResponce> mandals = (List<MandalResponce>) response1.getData();
        mandalList = mandals.stream().collect(Collectors.toMap(mandal -> mandal.getMandalId(), mandal -> mandal));

        List<District> districts1 = districtService.getAllDistrictsEntity();
        districtEntityList = districts1.stream().collect(Collectors.toMap(dist -> dist.getDistrictId(), dist -> dist));

        List<Mandal> mandals1 = districtService.getAllMandalsEntity();
        mandalEntityList = mandals1.stream().collect(Collectors.toMap(mandal -> mandal.getMandalId(), mandal -> mandal));


    }


}
