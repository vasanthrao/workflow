package com.metaverse.workflow.organization.service;

import com.metaverse.workflow.location.service.LocationResponse;
import com.metaverse.workflow.model.Location;
import org.springframework.stereotype.Component;

import com.metaverse.workflow.model.Organization;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrganizationResponseMapper {

	public static OrganizationResponse map(Organization request)
	{
		return OrganizationResponse.builder().organizationId(request.getOrganizationId())
				.organizationName(request.getOrganizationName()).organizationCategory(request.getOrganizationCategory())
				.organizationType(request.getOrganizationType()).udyamregistrationNo(request.getUdyamregistrationNo())
				.dateOfRegistration(request.getDateOfRegistration()).startupCertificateNo(request.getStartupCertificateNo())
				.natureOfStartup(request.getNatureOfStartup()).areasOfWorking(request.getAreasOfWorking())
				.incorporationDate(request.getIncorporationDate()).dateOfIssue(request.getDateOfIssue()).validUpto(request.getValidUpto())
				.stateId(request.getStateId()).distId(request.getDistId()).mandal(request.getMandal()).town(request.getTown())
				.streetNo(request.getStreetNo()).houseNo(request.getHouseNo()).latitude(request.getLatitude()).longitude(request.getLongitude())
				.contactNo(request.getContactNo()).email(request.getEmail()).contactNo(request.getContactNo()).email(request.getEmail())
				.website(request.getWebsite()).ownerName(request.getOwnerName()).ownerContactNo(request.getOwnerContactNo())
				.ownerEmail(request.getOwnerEmail()).ownerEmail(request.getOwnerEmail()).ownerAddress(request.getOwnerAddress())
				.nameOfTheSHG(request.getNameOfTheSHG()).nameOfTheVO(request.getNameOfTheVO()).gramaPanchayat(request.getGramaPanchayat()).build();
	}

	public static List<OrganizationResponse> mapOrganization(List<Organization> organizationList) {
		return organizationList != null ? organizationList.stream().map(organization ->
				OrganizationResponse.builder()
						.organizationId(organization.getOrganizationId())
						.organizationName(organization.getOrganizationName())
						.build()).collect(Collectors.toList()) : null;
	}

}
