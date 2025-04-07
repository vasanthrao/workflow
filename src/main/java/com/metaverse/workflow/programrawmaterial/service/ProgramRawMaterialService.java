package com.metaverse.workflow.programrawmaterial.service;

import com.metaverse.workflow.common.response.WorkflowResponse;

public interface ProgramRawMaterialService {
    WorkflowResponse rawMaterialByProgramId(Long programId);
    WorkflowResponse updateProgramRawMaterial(ProgramRawMaterialRequest request);
}
