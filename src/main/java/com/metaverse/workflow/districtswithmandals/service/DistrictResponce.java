package com.metaverse.workflow.districtswithmandals.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Builder
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistrictResponce {

    private Integer districtId;
    private String districtName;
    private Date createdOn;
    private Date updatedOn;

}
