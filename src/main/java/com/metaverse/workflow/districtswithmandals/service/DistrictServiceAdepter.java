package com.metaverse.workflow.districtswithmandals.service;


import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.districtswithmandals.repository.DistrictRepository;
import com.metaverse.workflow.districtswithmandals.repository.MandalRepositrory;
import com.metaverse.workflow.model.District;
import com.metaverse.workflow.model.Mandal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DistrictServiceAdepter implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private MandalRepositrory mandalRepositrory;

    @Override
    public WorkflowResponse saveDistrict(DistrictRequest districtRequest) {
        if (districtRequest == null && districtRequest.getDistrictName() == null) {
            return WorkflowResponse.builder().message("Invalid request: DistrictRequest is null")
                    .status(400).build();
        }
        District district = District.builder()
                .districtId(districtRequest.getDistrictId())
                .districtName(districtRequest.getDistrictName())
                .createdOn(districtRequest.getCreatedOn())
                .updatedOn(districtRequest.getUpdatedOn())
                .build();
        List<Mandal> mandals= new ArrayList<>();
        if(districtRequest.getMandals()!=null) {
            mandals = districtRequest.getMandals().stream()
                    .map(mandalRequest ->
                    {
                        return Mandal.builder().mandalName(mandalRequest.getMandalName())
                                .district(district)
                                .build();
                    }).collect(Collectors.toList());
        }
        else return WorkflowResponse.builder().message("Mandals are required").status(400).build();

        district.setMandals(mandals);
            districtRepository.save(district);


        return WorkflowResponse.builder().message("District saved successfully")
                .status(200).data(district).build();
    }

    @Override
    public WorkflowResponse getAllDistricts() {
        List<District> districtList = districtRepository.findAll();
        List<DistrictResponce> responces = districtList.stream()
                .map(district -> DistrictResponceMapper.map(district))
                .collect(Collectors.toList());

        return WorkflowResponse.builder()
                .message("Success")
                .status(200)
                .data(responces)
                .build();


    }

    @Override
    public WorkflowResponse getAllMandals() {
        List<Mandal> mandaltList = mandalRepositrory.findAll();
        List<MandalResponce> responces = mandaltList.stream()
                .map(mandal -> MandalResponceMapper.map(mandal))
                .collect(Collectors.toList());

        return WorkflowResponse.builder()
                .message("Success")
                .status(200)
                .data(responces)
                .build();
    }

    @Override
    public WorkflowResponse getAllMandalOfDistrict(Integer districtaId) {
        List<Mandal> mandalList = mandalRepositrory.findByDistrictId(districtaId);
        List<MandalResponce> mandalResponce = mandalList.stream()
                .map(mandal -> MandalResponceMapper.map(mandal))
                .collect(Collectors.toList());

        return WorkflowResponse.builder()
                .message("Mandals get successfully")
                .status(200)
                .data(mandalResponce)
                .build();
    }


    @Override
    public WorkflowResponse getDistrictById(Integer districtId) {
        Optional<District> district = districtRepository.findById(districtId);
        DistrictResponce districtResponce = DistrictResponceMapper.map(district.get());

        return WorkflowResponse.builder()
                .message("District get successfully")
                .status(200)
                .data(districtResponce)
                .build();
    }
    @Override
    public List<District> getAllDistrictsEntity(){
        return districtRepository.findAll();
    }
    @Override
   public  List<Mandal> getAllMandalsEntity(){
        return mandalRepositrory.findAll();
    }



    @Override
    public Mandal getMandalById(Integer id) {
        return mandalRepositrory.findById(id).get();
    }
}
