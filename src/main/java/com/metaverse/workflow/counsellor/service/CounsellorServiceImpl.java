package com.metaverse.workflow.counsellor.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.counsellor.repository.CounsellorAllotmentRepository;
import com.metaverse.workflow.counsellor.repository.CounsellorRegistrationRepository;
import com.metaverse.workflow.districtswithmandals.repository.DistrictRepository;
import com.metaverse.workflow.districtswithmandals.repository.MandalRepositrory;
import com.metaverse.workflow.model.CounsellorAllotment;
import com.metaverse.workflow.model.CounsellorRegistration;
import com.metaverse.workflow.model.District;
import com.metaverse.workflow.model.Mandal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CounsellorServiceImpl implements CounsellorService {
    @Autowired
    CounsellorRegistrationRepository counsellorRegistrationRepository;

    @Autowired
    CounsellorAllotmentRepository counsellorAllotmentRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    MandalRepositrory mandalRepositrory;

    @Override
    public WorkflowResponse saveCounseller(CounsellorRegistrationRequest counsellorRequest) {
        log.info("service");
        CounsellorRegistration counsellorRegistration = CounsellorRegistrationRequestMapper.map(counsellorRequest);
        Optional<District> allottedDistrict = districtRepository.findById(counsellorRequest.districtId);
        Optional<Mandal> allottedMandal = mandalRepositrory.findById(counsellorRequest.mandalId);
        if (!allottedDistrict.isPresent() || !allottedMandal.isPresent()) {
            return WorkflowResponse.builder().message("District or Mandal not found").status(400).build();
        }
        counsellorRegistration.setDistrict(allottedDistrict.get());
        counsellorRegistration.setMandal(allottedMandal.get());

        CounsellorAllotment counsellorAllotment = CounsellorAllotmentMapper.map(counsellorRegistration);
        Optional<District> district = districtRepository.findById(counsellorRequest.allortedDistrictId);
        Optional<Mandal> mandal = mandalRepositrory.findById(counsellorRequest.allortedMandalId);
        if (!district.isPresent() || !mandal.isPresent()) {
            return WorkflowResponse.builder().message("AllortedDistrict or AllortedMandal not found").status(400).build();
        }
        counsellorAllotment.setAllotedDistrict(district.get());
        counsellorAllotment.setAllotedMandal(mandal.get());
        counsellorRegistration.setCounsellorAllotments(Arrays.asList(counsellorAllotment));
        CounsellorRegistration response = counsellorRegistrationRepository.save(counsellorRegistration);
        return WorkflowResponse.builder().message("success").status(200).data(response).build();
    }


    @Override
    public CounsellorRegistrationResponse getCounsellerByMandal(String mandal) {
        List<CounsellorAllotment> counsellorAllotments = counsellorAllotmentRepository.findByAllotedMandalName(mandal);
        CounsellorRegistration counsellorRegistration = counsellorAllotments.get(0).getCounsellorRegistration();
        return CounsellorRegistrationResponseMapper.map(counsellorRegistration);
    }

}
