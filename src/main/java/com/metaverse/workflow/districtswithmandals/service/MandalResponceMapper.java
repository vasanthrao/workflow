package com.metaverse.workflow.districtswithmandals.service;

import com.metaverse.workflow.model.Mandal;

public class MandalResponceMapper {

    public static MandalResponce map(Mandal mandal)
    {
        MandalResponce response =  MandalResponce.builder()
                .mandalId(mandal.getMandalId())
                .mandalName(mandal.getMandalName())
                .createdOn(mandal.getCreatedOn())
                .updatedOn(mandal.getUpdatedOn())
                .build();

        return response;
    }
}
