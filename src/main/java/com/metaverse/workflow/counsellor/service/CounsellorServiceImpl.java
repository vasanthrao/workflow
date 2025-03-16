package com.metaverse.workflow.counsellor.service;

import com.metaverse.workflow.counsellor.repository.CounsellorAllotmentRepository;
import com.metaverse.workflow.counsellor.repository.CounsellorRegistrationRepository;
import com.metaverse.workflow.districtswithmandals.repository.DistrictRepository;
import com.metaverse.workflow.districtswithmandals.repository.MandalRepositrory;
import com.metaverse.workflow.model.CounsellorAllotment;
import com.metaverse.workflow.model.CounsellorRegistration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public CounsellorRegistrationResponse saveCounseller(CounsllorRegistrationRequest counsellorRequest) {
        CounsellorRegistration counsellorRegistration = CounsellorRegistrationRequestMapper.map(counsellorRequest);
        counsellorRegistration.setDistrict(districtRepository.findById(counsellorRequest.getDistrictId()).get());
        counsellorRegistration.setMandal(mandalRepositrory.findById(counsellorRequest.getMandalId()).get());
        counsellorRegistration.setAllotedDistrict(districtRepository.findById(counsellorRequest.getAllortedDistrictId()).get());
        counsellorRegistration.setAllotedMandal(mandalRepositrory.findById(counsellorRequest.getAllortedMandalId()).get());
        CounsellorRegistration counsellorRegistration1 = counsellorRegistrationRepository.save(counsellorRegistration);
        return CounsellorRegistrationResponseMapper.map(counsellorRegistration1);
    }


    @Override
    public CounsellorRegistrationResponse getCounsellerByMandal(String mandal) {
        List<CounsellorAllotment> counsellorAllotments = counsellorAllotmentRepository.findByAllotedMandalName(mandal);
        CounsellorRegistration counsellorRegistration = counsellorAllotments.get(0).getCounsellorRegistration();
        return CounsellorRegistrationResponseMapper.map(counsellorRegistration);
    }

}
