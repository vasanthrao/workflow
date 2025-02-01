package com.metaverse.workflow.agency.service;

import com.metaverse.workflow.location.service.LocationResponse;
import com.metaverse.workflow.model.Agency;
import com.metaverse.workflow.model.Location;
import com.metaverse.workflow.model.Resource;
import com.metaverse.workflow.resouce.service.ResourceResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AgencyResponseMapper {

    public static AgencyResponse map(Agency agency) {
        AgencyResponse response = AgencyResponse.builder()
                .agencyId(agency.getAgencyId())
                .agencyName(agency.getAgencyName())
                .email(agency.getEmail())
                .address(agency.getAddress())
                .mobileNo(agency.getMobileNo())
                .createdOn(agency.getCreatedOn())
                .updatedOn(agency.getUpdatedOn())
                .build();
        return response;
    }

    public static List<ResourceResponse> mapResources(List<Resource> resourceList) {
        return resourceList != null ? resourceList.stream().map(resource ->
                ResourceResponse.builder()
                        .resourceId(resource.getResourceId())
                        .name(resource.getName())
                        .build()).collect(Collectors.toList()) : null;

    }

    public static List<LocationResponse> mapLocations(List<Location> locationList) {
        return locationList != null ? locationList.stream().map(location ->
                LocationResponse.builder()
                        .locationId(location.getLocationId())
                        .locationName(location.getLocationName())
                        .build()).collect(Collectors.toList()) : null;
    }



}
