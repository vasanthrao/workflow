package com.metaverse.workflow.agency.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.metaverse.workflow.common.response.WorkflowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metaverse.workflow.agency.repository.AgencyRepository;
import com.metaverse.workflow.model.Agency;

@Service
public class AgencyServiceImpl implements AgencyService{

	@Autowired
	private AgencyRepository agencyRepository;
	
	@Override
	public String saveAgency(Agency agency) {
		Agency registeredAgency = agencyRepository.save(agency);
		return "AgencyDetails saved With id: "+registeredAgency.getAgencyId();
	}

	@Override
	public Agency getAgencyById(Long agencyId) {
		Optional<Agency> findById = agencyRepository.findById(agencyId);
		if(findById.isPresent())
		{
			return findById.get();
		}
		
		return null;
	}

	@Override
	public WorkflowResponse getAgencies() {
		List<Agency> agencyList = agencyRepository.findAll();
		List<AgencyResponse> response = agencyList != null ? agencyList.stream().map(agency -> AgencyResponseMapper.map(agency)).collect(Collectors.toList()) : null;
		return WorkflowResponse.builder().message("Success").status(200).data(response).build();
	}

}
