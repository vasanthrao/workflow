package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.activity.repository.ActivityRepository;
import com.metaverse.workflow.activity.repository.SubActivityRepository;
import com.metaverse.workflow.agency.repository.AgencyRepository;
import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.exceptions.*;
import com.metaverse.workflow.expenditure.repository.BulkExpenditureRepository;
import com.metaverse.workflow.expenditure.repository.BulkExpenditureTransactionRepository;
import com.metaverse.workflow.expenditure.repository.HeadOfExpenseRepository;
import com.metaverse.workflow.expenditure.repository.ProgramExpenditureRepository;
import com.metaverse.workflow.model.*;

import com.metaverse.workflow.program.repository.ProgramRepository;
import com.metaverse.workflow.program.repository.ProgramSessionFileRepository;
import com.metaverse.workflow.program.service.ProgramServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ExpenditureServiceAdepter implements ExpenditureService {
    @Autowired
    ProgramExpenditureRepository programExpenditureRepository;
    @Autowired
    BulkExpenditureRepository bulkExpenditureRepository;
    @Autowired
    private BulkExpenditureTransactionRepository transactionRepo;
    @Autowired
    AgencyRepository agencyRepository;
    @Autowired
    ProgramRepository programRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    SubActivityRepository subActivityRepository;
    @Autowired
    HeadOfExpenseRepository headOfExpenseRepository;
    @Autowired
    ProgramSessionFileRepository programSessionFileRepository;

    @Autowired
    ProgramServiceAdapter programServiceAdapter;

    @Override
    public WorkflowResponse saveBulkExpenditure(BulkExpenditureRequest expenditureRequest, List<MultipartFile> files) throws DataException {
        Agency agency = agencyRepository.findById(expenditureRequest.getAgencyId())
                .orElseThrow(() -> new DataException(
                        "Agency details for the agency id " + expenditureRequest.getAgencyId() + " do not exist.",
                        "AGENCY-DETAILS-NOT-FOUND",
                        400
                ));

        HeadOfExpense headOfExpense = headOfExpenseRepository.findById(expenditureRequest.getHeadOfExpenseId())
                .orElseThrow(() -> new DataException(
                        "HeadOfExpense details for the HeadOfExpense id " + expenditureRequest.getHeadOfExpenseId() + " do not exist.",
                        "HEAD-OF-EXPENSE-DETAILS-NOT-FOUND",
                        400
                ));

        boolean exists = bulkExpenditureRepository.existsByAgencyAndHeadOfExpenseAndItemName(
                agency,
                headOfExpense,
                expenditureRequest.getItemName()
        );

        if (exists) {
            throw new DataException("Same Item Name and Head of Expense already exists for your agency. Please change Item Name!", "BULK-EXPENDITURE_ALREADY-EXISTS", 400);
        }
        BulkExpenditure bulkExpenditure = bulkExpenditureRepository.save(
                ExpenditureRequestMapper
                        .mapBulkExpenditure(expenditureRequest, agency, headOfExpense)

        );
        if(files != null && !files.isEmpty()) {
            List<String> filePaths = programServiceAdapter.storageProgramFiles(files, expenditureRequest.getAgencyId(), "BulkExpenditure");
            List<ProgramSessionFile> sessionFiles = filePaths.stream()
                    .map(filePath -> ProgramSessionFile.builder()
                            .fileType("FILE")
                            .filePath(filePath)
                            .bulkExpenditure(bulkExpenditure)
                            .build())
                    .toList();
            programSessionFileRepository.saveAll(sessionFiles);
        }

        return WorkflowResponse.builder()
                .message("BulkExpenditure saved successfully")
                .data(ExpenditureResponseMapper.mapBulkExpenditure(
                    bulkExpenditure
                ))
                .status(200).build();
    }

    @Override
    public WorkflowResponse getAllBulkExpenditure() {
        List<BulkExpenditure> bulkExpenditureList = bulkExpenditureRepository.findAll();
        if (bulkExpenditureList.isEmpty())
            return WorkflowResponse.builder().message("BulkExpenditure is Empty").status(400).build();
        return WorkflowResponse.builder().message("Success").status(200)
                .data(
                        bulkExpenditureList.stream().map(
                                ExpenditureResponseMapper::mapBulkExpenditure).toList()
                )
                .build();
    }

    @Override
    public WorkflowResponse getAllProgramExpenditure(ExpenditureType expenditureType) {
        List<ProgramExpenditure> programExpendituresList = programExpenditureRepository.findByExpenditureType(expenditureType);
        if (programExpendituresList.isEmpty())
            return WorkflowResponse.builder().message("Expenditures is Empty").status(400).build();
        return WorkflowResponse.builder().message("Success").status(200)
                .data(
                        programExpendituresList.stream().map(
                                ExpenditureResponseMapper::mapProgramExpenditure).toList()
                )
                .build();
    }

    @Override
    public WorkflowResponse getAllProgramExpenditureByProgram(ExpenditureType expenditureType, Long programId) {
        List<ProgramExpenditure> programExpendituresList = programExpenditureRepository.findByExpenditureTypeAndProgram_ProgramId(expenditureType, programId);
        if (programExpendituresList.isEmpty())
            return WorkflowResponse.builder().message("Expenditures is Empty").status(400).build();
        return WorkflowResponse.builder().message("Success").status(200)
                .data(
                        programExpendituresList.stream().map(
                                ExpenditureResponseMapper::mapProgramExpenditure).toList()
                )
                .build();
    }

    @Override
    public BulkExpenditureTransactionResponse saveTransaction(BulkExpenditureTransactionRequest request) throws DataException {

        BulkExpenditure bulkExpenditure = bulkExpenditureRepository.findById(request.getBulkExpenditureId())
                .orElseThrow(() -> new DataException("Bulk expenditure data not found", "BULK-EXPENDITURE-DATA-NOT-FOUND", 400));
        Activity activity = activityRepository.findById(request.getActivityId())
                .orElseThrow(() -> new DataException("Activity data not found", "ACTIVITY-DATA-NOT-FOUND", 400));
        SubActivity subActivity = subActivityRepository.findById(request.getSubActivityId())
                .orElseThrow(() -> new DataException("Sub Activity data not found", "SUB-ACTIVITY-DATA-NOT-FOUND", 400));
        Program program = programRepository.findById(request.getProgramId())
                .orElseThrow(() -> new DataException("Program data not found", "PROGRAM-DATA-NOT-FOUND", 400));
        Agency agency = agencyRepository.findById(request.getAgencyId())
                .orElseThrow(() -> new DataException("Agency data not found", "AGENCY-DATA-NOT-FOUND", 400));
        HeadOfExpense headOfExpense = headOfExpenseRepository.findById(request.getHeadOfExpenseId())
                .orElseThrow(() -> new DataException("Head of expense data not found", "HEAD-OF-EXPENSE-DATA-NOT-FOUND", 400));
        
        BulkExpenditureTransaction saved = transactionRepo.save(ExpenditureRequestMapper.mapBulkExpenditureTransaction(request, activity, subActivity, program, agency, bulkExpenditure, headOfExpense));

        if (bulkExpenditure != null && request.getConsumedQuantity() != null) {
            int updatedAvailableQty = 0;
            if (bulkExpenditure.getAvailableQuantity() > request.getConsumedQuantity()) {
                updatedAvailableQty = bulkExpenditure.getAvailableQuantity() - request.getConsumedQuantity();
            }
            bulkExpenditure.setAvailableQuantity(updatedAvailableQty);
            bulkExpenditure.setConsumedQuantity(bulkExpenditure.getConsumedQuantity() + request.getConsumedQuantity());
            bulkExpenditureRepository.save(bulkExpenditure);
        }

        BulkExpenditureTransactionResponse response = new BulkExpenditureTransactionResponse();
        response.setId(saved.getBulkExpenditureTransactionId());
        response.setConsumedQuantity(saved.getConsumedQuantity());
        response.setAllocatedCost(saved.getAllocatedCost());

        return response;
    }

    @Override
    public BulkExpenditureLookupResponse getBulkExpendituresByExpenseAndItem(BulkExpenditureLookupRequest request) throws DataException {
        HeadOfExpense headOfExpense = headOfExpenseRepository.findById(request.getExpenseId())
                .orElseThrow(() -> new DataException("Head of Expense not found", "HEAD-OF-EXPENSE-NOT-FOUND", 400));
        BulkExpenditure bulkExpenditure = bulkExpenditureRepository.findByHeadOfExpenseAndItemNameIgnoreCase(headOfExpense, request.getItemName());
        return ExpenditureResponseMapper.mapBulkExpenditureDetails(bulkExpenditure);
    }

    @Override
    public List<String> getItemsByHeadOfExpense(Integer expenseId) {
        return bulkExpenditureRepository.findDistinctItemNamesByHeadOfExpense(expenseId);
    }

    @Override
    public WorkflowResponse getAllBulkExpenditureTransactionByProgram(Long programId) {
        List<BulkExpenditureTransaction> transactions = transactionRepo
                .findByProgram_ProgramId(programId);

        return WorkflowResponse.builder().message("Success").status(200)
                .data(
                        transactions.stream().map(
                                ExpenditureResponseMapper::mapBulkExpenditureTransaction).toList()
                )
                .build();
    }

    @Override
    public List<HeadOfExpense> getAllHeadOfExpenses() {
        return headOfExpenseRepository.findAll();
    }


    @Override
    public WorkflowResponse saveProgramExpenditure(ProgramExpenditureRequest expenditureRequest, List<MultipartFile> files) throws DataException {
        Program program = programRepository.findById(expenditureRequest.getProgramId())
                .orElseThrow(() -> new DataException(
                        "Program details for the program id " + expenditureRequest.getAgencyId() + " do not exist.",
                        "PROGRAM-DETAILS-NOT-FOUND",
                        400
                ));
        Agency agency = agencyRepository.findById(expenditureRequest.getAgencyId())
                .orElseThrow(() -> new DataException(
                        "Agency details for the agency id " + expenditureRequest.getAgencyId() + " do not exist.",
                        "AGENCY-DETAILS-NOT-FOUND",
                        400
                ));
        Activity activity = activityRepository.findById(expenditureRequest.getActivityId())
                .orElseThrow(() -> new DataException(
                        "Activity details for the activity id " + expenditureRequest.getAgencyId() + " do not exist.",
                        "ACTIVITY-DETAILS-NOT-FOUND",
                        400
                ));
        SubActivity subActivity = subActivityRepository.findById(expenditureRequest.getActivityId())
                .orElseThrow(() -> new DataException(
                        "SubActivity details for the subActivity id " + expenditureRequest.getAgencyId() + " do not exist.",
                        "SUB-ACTIVITY-DETAILS-NOT-FOUND",
                        400
                ));
        HeadOfExpense headOfExpense = headOfExpenseRepository.findById(expenditureRequest.getHeadOfExpenseId())
                .orElseThrow(() -> new DataException(
                        "HeadOfExpense details for the HeadOfExpense id " + expenditureRequest.getAgencyId() + " do not exist.",
                        "HEAD-OF-EXPENSE-DETAILS-NOT-FOUND",
                        400
                ));

        ProgramExpenditure programExpenditure = programExpenditureRepository.save(
                ExpenditureRequestMapper.mapProgramExpenditure(expenditureRequest, activity, subActivity, program, agency, headOfExpense));

        if(files != null && !files.isEmpty()) {
            List<String> filePaths = programServiceAdapter.storageProgramFiles(files, expenditureRequest.getProgramId(), "ProgramExpenditure");
            List<ProgramSessionFile> sessionFiles = filePaths.stream()
                    .map(filePath -> ProgramSessionFile.builder()
                            .fileType("FILE")
                            .filePath(filePath)
                            .programExpenditure(programExpenditure)
                            .build())
                    .toList();
            programSessionFileRepository.saveAll(sessionFiles);
        }

        return WorkflowResponse.builder()
                .message("Program Expenditure saved successfully")
                .data(ExpenditureResponseMapper.mapProgramExpenditure(programExpenditure
                ))
                .status(200).build();
    }

  @Override
    public WorkflowResponse updateProgramExpenditure(Long expenditureId, ProgramExpenditureRequest expenditureRequest, List<MultipartFile> files) throws DataException {
        ProgramExpenditure existingExpenditure = programExpenditureRepository.findById(expenditureId)
                .orElseThrow(() -> new DataException(
                        "Program Expenditure with ID " + expenditureId + " not found.",
                        "PROGRAM-EXPENDITURE-NOT-FOUND",
                        404
                ));

        Program program = programRepository.findById(expenditureRequest.getProgramId())
                .orElseThrow(() -> new DataException(
                        "Program details for ID " + expenditureRequest.getProgramId() + " do not exist.",
                        "PROGRAM-DETAILS-NOT-FOUND",
                        400
                ));

        Agency agency = agencyRepository.findById(expenditureRequest.getAgencyId())
                .orElseThrow(() -> new DataException(
                        "Agency details for ID " + expenditureRequest.getAgencyId() + " do not exist.",
                        "AGENCY-DETAILS-NOT-FOUND",
                        400
                ));

        Activity activity = activityRepository.findById(expenditureRequest.getActivityId())
                .orElseThrow(() -> new DataException(
                        "Activity details for ID " + expenditureRequest.getActivityId() + " do not exist.",
                        "ACTIVITY-DETAILS-NOT-FOUND",
                        400
                ));

        SubActivity subActivity = subActivityRepository.findById(expenditureRequest.getSubActivityId())
                .orElseThrow(() -> new DataException(
                        "SubActivity details for ID " + expenditureRequest.getSubActivityId() + " do not exist.",
                        "SUB-ACTIVITY-DETAILS-NOT-FOUND",
                        400
                ));

        HeadOfExpense headOfExpense = headOfExpenseRepository.findById(expenditureRequest.getHeadOfExpenseId())
                .orElseThrow(() -> new DataException(
                        "HeadOfExpense details for ID " + expenditureRequest.getHeadOfExpenseId() + " do not exist.",
                        "HEAD-OF-EXPENSE-DETAILS-NOT-FOUND",
                        400
                ));

        // Update fields
        ExpenditureRequestMapper.updateProgramExpenditure(existingExpenditure, expenditureRequest, activity, subActivity, program, agency, headOfExpense);
        ProgramExpenditure updatedExpenditure = programExpenditureRepository.save(existingExpenditure);

        // Handle files (optional: decide whether to replace or append)
        if (files != null && !files.isEmpty()) {
            List<String> filePaths = programServiceAdapter.storageProgramFiles(files, expenditureRequest.getProgramId(), "ProgramExpenditure");
            List<ProgramSessionFile> sessionFiles = filePaths.stream()
                    .map(filePath -> ProgramSessionFile.builder()
                            .fileType("FILE")
                            .filePath(filePath)
                            .programExpenditure(updatedExpenditure)
                            .build())
                    .toList();
            programSessionFileRepository.saveAll(sessionFiles);
        }

        return WorkflowResponse.builder()
                .message("Program Expenditure updated successfully")
                .data(ExpenditureResponseMapper.mapProgramExpenditure(updatedExpenditure))
                .status(200)
                .build();
    }

    @Override
    public WorkflowResponse deleteProgramExpenditure(Long expenditureId) throws DataException {
        if (!programExpenditureRepository.existsById(expenditureId)) {
            throw new DataException(
                    "Program Expenditure with ID " + expenditureId + " not found.",
                    "PROGRAM-EXPENDITURE-NOT-FOUND",
                    404
            );
        }
        programExpenditureRepository.deleteById(expenditureId);

        return WorkflowResponse.builder()
                .status(200)
                .message("Program Expenditure deleted successfully")
                .data(null)
                .build();
    }

    @Override
    public WorkflowResponse updateBulkExpenditure(Long expenditureId, BulkExpenditureRequest expenditureRequest, List<MultipartFile> files) throws DataException {
        BulkExpenditure existingExpenditure = bulkExpenditureRepository.findById(expenditureId)
                .orElseThrow(() -> new DataException(
                        "BulkExpenditure with ID " + expenditureId + " does not exist.",
                        "BULK-EXPENDITURE-NOT-FOUND",
                        404
                ));

        Agency agency = agencyRepository.findById(expenditureRequest.getAgencyId())
                .orElseThrow(() -> new DataException(
                        "Agency details for the agency id " + expenditureRequest.getAgencyId() + " do not exist.",
                        "AGENCY-DETAILS-NOT-FOUND",
                        400
                ));

        HeadOfExpense headOfExpense = headOfExpenseRepository.findById(expenditureRequest.getHeadOfExpenseId())
                .orElseThrow(() -> new DataException(
                        "HeadOfExpense details for the HeadOfExpense id " + expenditureRequest.getHeadOfExpenseId() + " do not exist.",
                        "HEAD-OF-EXPENSE-DETAILS-NOT-FOUND",
                        400
                ));
        boolean exists = bulkExpenditureRepository.existsByAgencyAndHeadOfExpenseAndItemNameAndBulkExpenditureIdNot(agency, headOfExpense, expenditureRequest.getItemName(), expenditureId);
        if (exists) {
            throw new DataException(
                    "Same Item Name and Head of Expense already exists for your agency. Please change Item Name!",
                    "BULK-EXPENDITURE_ALREADY-EXISTS",
                    400
            );
        }
        ExpenditureRequestMapper.updateBulkExpenditure(existingExpenditure, expenditureRequest, agency, headOfExpense);

        bulkExpenditureRepository.save(existingExpenditure);

        if (files != null && !files.isEmpty()) {
            List<String> filePaths = programServiceAdapter.storageProgramFiles(files, expenditureRequest.getAgencyId(), "BulkExpenditure");
            List<ProgramSessionFile> sessionFiles = filePaths.stream()
                    .map(filePath -> ProgramSessionFile.builder()
                            .fileType("FILE")
                            .filePath(filePath)
                            .bulkExpenditure(existingExpenditure)
                            .build())
                    .toList();
            programSessionFileRepository.saveAll(sessionFiles);
        }

        return WorkflowResponse.builder()
                .message("BulkExpenditure updated successfully")
                .data(ExpenditureResponseMapper.mapBulkExpenditure(existingExpenditure))
                .status(200)
                .build();
    }


    @Override
    @Transactional
    public WorkflowResponse deleteBulkExpenditure(Long expenditureId) throws DataException {
        if (!bulkExpenditureRepository.existsById(expenditureId)) {
            throw new DataException(
                    "Bulk Expenditure with ID " + expenditureId + " not found.",
                    "BULK-EXPENDITURE-NOT-FOUND",
                    404
            );
        }
        transactionRepo.deleteByExpenditureBulkExpenditureId(expenditureId);
        bulkExpenditureRepository.deleteById(expenditureId);


        return WorkflowResponse.builder()
                .status(200)
                .message("Bulk Expenditure deleted successfully")
                .data(null)
                .build();
    }


}
