package com.metaverse.workflow.resouce.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.metaverse.workflow.agency.service.AgencyResponse;
import com.metaverse.workflow.agency.service.AgencyResponseMapper;
import com.metaverse.workflow.common.response.WorkflowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metaverse.workflow.agency.service.AgencyService;
import com.metaverse.workflow.model.Agency;
import com.metaverse.workflow.model.Resource;
import com.metaverse.workflow.resouce.repository.ResourceRepository;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceRepository repository;
	@Autowired
	private AgencyService agencyService;

	@Override
	public ResourceResponse saveResource(ResourceRequest resourceRequest) {
		List<Agency> agencies = new ArrayList<>();
		Set<Long> agencyIds = resourceRequest.getAgencyIds();
		for (Long id : agencyIds) {
			Agency agency = agencyService.getAgencyById(id);
			if (agency != null) {
				agencies.add(agency);
			}
		}
		Resource resource = ResourceRequestMapper.map(resourceRequest, agencies);
		Resource savedResource = repository.save(resource);
		return ResourceResponseMapper.map(savedResource);
	}

	@Override
	public WorkflowResponse getResources() {
		List<Resource> resourceList = repository.findAll();
		List<ResourceResponse> response = resourceList != null ? resourceList.stream().map(resource -> ResourceResponseMapper.map(resource)).collect(Collectors.toList()) : null;
		return WorkflowResponse.builder().message("Success").status(200).data(response).build();
	}

	@Override
	public List<Resource> getResourceEntities() {
		return repository.findAll();
	}

}
