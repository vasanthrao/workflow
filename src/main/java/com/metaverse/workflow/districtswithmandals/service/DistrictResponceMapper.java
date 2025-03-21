package com.metaverse.workflow.districtswithmandals.service;


import com.metaverse.workflow.model.District;
import org.springframework.stereotype.Component;

@Component
public class DistrictResponceMapper {

    public static DistrictResponce map(District district)
    {
        DistrictResponce response =  DistrictResponce.builder()
                .districtId(district.getDistrictId())
                .districtName(district.getDistrictName())
                .createdOn(district.getCreatedOn())
                .updatedOn(district.getUpdatedOn())
                .build();

        return response;
    }
}
