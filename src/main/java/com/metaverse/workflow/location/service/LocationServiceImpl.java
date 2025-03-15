package com.metaverse.workflow.location.service;

import com.metaverse.workflow.agency.service.AgencyResponse;
import com.metaverse.workflow.agency.service.AgencyResponseMapper;
import com.metaverse.workflow.agency.service.AgencyService;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.location.repository.LocationRepository;
import com.metaverse.workflow.model.Agency;
import com.metaverse.workflow.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository repository;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private LocationRequestMapper requestMapper;

    @Override
    public LocationResponse saveLocation(LocationRequest locationRequest) {
        Agency agency = agencyService.getAgencyById(locationRequest.getAgencyId());
        if (agency == null) return null;
        Location location = requestMapper.map(locationRequest);
        List<Location> locations = agency.getLocations();
        locations.add(location);
        agency.setLocations(locations);
        location.setAgency(agency);
        Location savedLocation = repository.save(location);
        return LocationResponseMapper.map(savedLocation);
    }

    @Override
    public List<LocationResponse> getLocationByAgencyId(Long agencyId) {
        List<Location> locationList = repository.findByAgencyAgencyId(agencyId);
        ArrayList<LocationResponse> LocationResponseList = new ArrayList<LocationResponse>();
        for (Location location : locationList) {
            LocationResponseList.add(LocationResponseMapper.map(location));
        }
        return LocationResponseList;
    }

    @Override
    public WorkflowResponse getLocations() {
        List<Location> locationList = repository.findAll();
        List<LocationResponse> response = locationList != null ? locationList.stream().map(location -> LocationResponseMapper.map(location)).collect(Collectors.toList()) : null;
        return WorkflowResponse.builder().message("Success").status(200).data(response).build();

    }

}
