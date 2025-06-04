package com.metaverse.workflow.reports.service;

import com.metaverse.workflow.model.PhysicalTarget;
import com.metaverse.workflow.programoutcome.repository.*;
import com.metaverse.workflow.programoutcometargets.repository.PhysicalRepository;
import com.metaverse.workflow.reports.dto.OutcomeTargetDTO;
import com.metaverse.workflow.reports.dto.OutcomeTargetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OutcomeTargetServiceImpl implements OutcomeTargetService {

    private final PhysicalRepository physicalTargetRepository;

    private final ONDCRegistrationRepository ondcRegistrationRepository;

    private final ONDCTransactionRepository ondcTransactionRepository;

    private final UdyamRegistrationRepository udyamRegistrationRepository;

    private final TReDSRegistrationRepository tredsRegistrationRepository;

    private final TReDSTransactionRepository tReDSTransactionRepository;

    private final ZEDCertificationRepository zedCertificationRepository;

    private final BarcodeRepository barcodeRepository;

    private final GIProductRepository giProductRepository;

    private final ICSchemeRepository icSchemeRepository;

    private final PatentsRepository patentsRepository;

    private final TreadMarkRepository treadMarkRepository;

    public List<OutcomeTargetDTO> getTargetsByYear(String financialYear, Long agencyId) {
        List<PhysicalTarget> targets = physicalTargetRepository.findByFinancialYear(financialYear);
        OutcomeTargetResponse outcomeTargetResponse = null;
        int fyStartYear = Integer.parseInt(financialYear.split("-")[0]);
        LocalDate q1Start = LocalDate.of(fyStartYear, 4, 1);
        LocalDate q1End = LocalDate.of(fyStartYear, 6, 30);
        LocalDate q2Start = LocalDate.of(fyStartYear, 7, 1);
        LocalDate q2End = LocalDate.of(fyStartYear, 9, 30);
        LocalDate q3Start = LocalDate.of(fyStartYear, 10, 1);
        LocalDate q3End = LocalDate.of(fyStartYear, 12, 31);
        LocalDate q4Start = LocalDate.of(fyStartYear + 1, 1, 1);
        LocalDate q4End = LocalDate.of(fyStartYear + 1, 3, 31);
        Date dQ1Start = toDate(q1Start);
        Date dQ1End = toDate(q1End);
        Date dQ2Start = toDate(q2Start);
        Date dQ2End = toDate(q2End);
        Date dQ3Start = toDate(q3Start);
        Date dQ3End = toDate(q3End);
        Date dQ4Start = toDate(q4Start);
        Date dQ4End = toDate(q4End);

        List<OutcomeTargetDTO> dtoList = new ArrayList<>();

        // ONDC Registration
        dtoList.add(createOutcomeDto("ONDCRegistration", financialYear,
                physicalTargetRepository.findTarget("ONDCRegistration", financialYear, agencyId),
                ondcRegistrationRepository.countByAgencyAndDateBetween(agencyId, dQ1Start, dQ1End),
                ondcRegistrationRepository.countByAgencyAndDateBetween(agencyId, dQ2Start, dQ2End),
                ondcRegistrationRepository.countByAgencyAndDateBetween(agencyId, dQ3Start, dQ3End),
                ondcRegistrationRepository.countByAgencyAndDateBetween(agencyId, dQ4Start, dQ4End)
        ));

        // ONDC Transaction
        dtoList.add(createOutcomeDto("ONDCTransaction", financialYear,
                physicalTargetRepository.findTarget("ONDCTransaction", financialYear, agencyId),
                ondcTransactionRepository.countByAgencyAndTransactionDateBetween(agencyId, dQ1Start, dQ1End),
                ondcTransactionRepository.countByAgencyAndTransactionDateBetween(agencyId, dQ2Start, dQ2End),
                ondcTransactionRepository.countByAgencyAndTransactionDateBetween(agencyId, dQ3Start, dQ3End),
                ondcTransactionRepository.countByAgencyAndTransactionDateBetween(agencyId, dQ4Start, dQ4End)
        ));

        // Udyam Registration
        dtoList.add(createOutcomeDto("UdyamRegistration", financialYear,
                physicalTargetRepository.findTarget("UdyamRegistration", financialYear, agencyId),
                udyamRegistrationRepository.countByAgencyAndRegistrationDateBetween(agencyId, dQ1Start, dQ1End),
                udyamRegistrationRepository.countByAgencyAndRegistrationDateBetween(agencyId, dQ2Start, dQ2End),
                udyamRegistrationRepository.countByAgencyAndRegistrationDateBetween(agencyId, dQ3Start, dQ3End),
                udyamRegistrationRepository.countByAgencyAndRegistrationDateBetween(agencyId, dQ4Start, dQ4End)
        ));

        //TReDsRegistration
        dtoList.add(createOutcomeDto("TReDS Registration", financialYear,
                physicalTargetRepository.findTarget("TReDSRegistration", financialYear, agencyId),
                tredsRegistrationRepository.countByAgencyIdAndTredsRegistrationDateBetween(agencyId, dQ1Start, dQ1End),
                tredsRegistrationRepository.countByAgencyIdAndTredsRegistrationDateBetween(agencyId, dQ2Start, dQ2End),
                tredsRegistrationRepository.countByAgencyIdAndTredsRegistrationDateBetween(agencyId, dQ3Start, dQ3End),
                tredsRegistrationRepository.countByAgencyIdAndTredsRegistrationDateBetween(agencyId, dQ4Start, dQ4End)
        ));

        //TReDSTransaction
        dtoList.add(createOutcomeDto("TReDS Transaction", financialYear,
                physicalTargetRepository.findTarget("TReDSTransaction", financialYear, agencyId),
                tReDSTransactionRepository.countByAgencyAndTransactionDateBetween(agencyId, dQ1Start, dQ1End),
                tReDSTransactionRepository.countByAgencyAndTransactionDateBetween(agencyId, dQ2Start, dQ2End),
                tReDSTransactionRepository.countByAgencyAndTransactionDateBetween(agencyId, dQ3Start, dQ3End),
                tReDSTransactionRepository.countByAgencyAndTransactionDateBetween(agencyId, dQ4Start, dQ4End)
        ));

        // ZED Certification - Bronze
        dtoList.add(createOutcomeDto("ZED Certification (Bronze)", financialYear,
                physicalTargetRepository.findTarget("ZEDCertification", financialYear, agencyId),
                zedCertificationRepository.countByAgencyAgencyIdAndZedCertificationTypeAndCertificationDateBetween(agencyId, "Bronze", dQ1Start, dQ1End),
                zedCertificationRepository.countByAgencyAgencyIdAndZedCertificationTypeAndCertificationDateBetween(agencyId, "Bronze", dQ2Start, dQ2End),
                zedCertificationRepository.countByAgencyAgencyIdAndZedCertificationTypeAndCertificationDateBetween(agencyId, "Bronze", dQ3Start, dQ3End),
                zedCertificationRepository.countByAgencyAgencyIdAndZedCertificationTypeAndCertificationDateBetween(agencyId, "Bronze", dQ4Start, dQ4End)
        ));

        // ZED Certification - Silver
        dtoList.add(createOutcomeDto("ZED Certification (Silver)", financialYear,
                physicalTargetRepository.findTarget("ZEDCertification", financialYear, agencyId),
                zedCertificationRepository.countByAgencyAgencyIdAndZedCertificationTypeAndCertificationDateBetween(agencyId, "Silver", dQ1Start, dQ1End),
                zedCertificationRepository.countByAgencyAgencyIdAndZedCertificationTypeAndCertificationDateBetween(agencyId, "Silver", dQ2Start, dQ2End),
                zedCertificationRepository.countByAgencyAgencyIdAndZedCertificationTypeAndCertificationDateBetween(agencyId, "Silver", dQ3Start, dQ3End),
                zedCertificationRepository.countByAgencyAgencyIdAndZedCertificationTypeAndCertificationDateBetween(agencyId, "Silver", dQ4Start, dQ4End)
        ));

        // ZED Certification - Gold
        dtoList.add(createOutcomeDto("ZED Certification (Gold)", financialYear,
                physicalTargetRepository.findTarget("ZEDCertification", financialYear, agencyId),
                zedCertificationRepository.countByAgencyAgencyIdAndZedCertificationTypeAndCertificationDateBetween(agencyId, "Gold", dQ1Start, dQ1End),
                zedCertificationRepository.countByAgencyAgencyIdAndZedCertificationTypeAndCertificationDateBetween(agencyId, "Gold", dQ2Start, dQ2End),
                zedCertificationRepository.countByAgencyAgencyIdAndZedCertificationTypeAndCertificationDateBetween(agencyId, "Gold", dQ3Start, dQ3End),
                zedCertificationRepository.countByAgencyAgencyIdAndZedCertificationTypeAndCertificationDateBetween(agencyId, "Gold", dQ4Start, dQ4End)
        ));

        //Barcode
        dtoList.add(createOutcomeDto("Barcode", financialYear,
                physicalTargetRepository.findTarget("Barcode", financialYear, agencyId),
                barcodeRepository.countByAgencyAgencyIdAndDateOfRegistrationBetween(agencyId, dQ1Start, dQ1End),
                barcodeRepository.countByAgencyAgencyIdAndDateOfRegistrationBetween(agencyId, dQ2Start, dQ2End),
                barcodeRepository.countByAgencyAgencyIdAndDateOfRegistrationBetween(agencyId, dQ3Start, dQ3End),
                barcodeRepository.countByAgencyAgencyIdAndDateOfRegistrationBetween(agencyId, dQ4Start, dQ4End)
        ));

        //GI Product
        dtoList.add(createOutcomeDto("GI Product", financialYear,
                physicalTargetRepository.findTarget("GIProduct", financialYear, agencyId),
                giProductRepository.countByAgencyAgencyIdAndDateOfGIRegistrationBetween(agencyId, dQ1Start, dQ1End),
                giProductRepository.countByAgencyAgencyIdAndDateOfGIRegistrationBetween(agencyId, dQ2Start, dQ2End),
                giProductRepository.countByAgencyAgencyIdAndDateOfGIRegistrationBetween(agencyId, dQ3Start, dQ3End),
                giProductRepository.countByAgencyAgencyIdAndDateOfGIRegistrationBetween(agencyId, dQ4Start, dQ4End)
        ));

        //IC Scheme
        dtoList.add(createOutcomeDto("IC Scheme", financialYear,
                physicalTargetRepository.findTarget("ICScheme", financialYear, agencyId),
                icSchemeRepository.countByAgencyAgencyIdAndDateOfExportBetween(agencyId, dQ1Start, dQ1End),
                icSchemeRepository.countByAgencyAgencyIdAndDateOfExportBetween(agencyId, dQ2Start, dQ2End),
                icSchemeRepository.countByAgencyAgencyIdAndDateOfExportBetween(agencyId, dQ3Start, dQ3End),
                icSchemeRepository.countByAgencyAgencyIdAndDateOfExportBetween(agencyId, dQ4Start, dQ4End)
        ));

        //Patents
        dtoList.add(createOutcomeDto("Patents", financialYear,
                physicalTargetRepository.findTarget("Patents", financialYear, agencyId),
                patentsRepository.countByAgencyAgencyIdAndDateOfExportBetween(agencyId, dQ1Start, dQ1End),
                patentsRepository.countByAgencyAgencyIdAndDateOfExportBetween(agencyId, dQ2Start, dQ2End),
                patentsRepository.countByAgencyAgencyIdAndDateOfExportBetween(agencyId, dQ3Start, dQ3End),
                patentsRepository.countByAgencyAgencyIdAndDateOfExportBetween(agencyId, dQ4Start, dQ4End)
        ));

        //TradeMark
        dtoList.add(createOutcomeDto("Trade Mark", financialYear,
                physicalTargetRepository.findTarget("TreadMark", financialYear, agencyId),
                treadMarkRepository.countByAgencyAgencyIdAndDateOfRegistrationBetween(agencyId, dQ1Start, dQ1End),
                treadMarkRepository.countByAgencyAgencyIdAndDateOfRegistrationBetween(agencyId, dQ2Start, dQ2End),
                treadMarkRepository.countByAgencyAgencyIdAndDateOfRegistrationBetween(agencyId, dQ3Start, dQ3End),
                treadMarkRepository.countByAgencyAgencyIdAndDateOfRegistrationBetween(agencyId, dQ4Start, dQ4End)
        ));
        return dtoList;

    }

    private Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private OutcomeTargetDTO createOutcomeDto(String program, String year, PhysicalTarget target,
                                              long q1, long q2, long q3, long q4) {
        int t1 = target != null ? target.getQ1() : 0;
        int t2 = target != null ? target.getQ2() : 0;
        int t3 = target != null ? target.getQ3() : 0;
        int t4 = target != null ? target.getQ4() : 0;

        return OutcomeTargetDTO.builder()
                .outcomeName(program)
                .financialYear(year)
                .physicalTargetQ1(t1)
                .physicalTargetQ2(t2)
                .physicalTargetQ3(t3)
                .physicalTargetQ4(t4)
                .achievedQ1((int) q1)
                .achievedQ2((int) q2)
                .achievedQ3((int) q3)
                .achievedQ4((int) q4)
                .totalTarget(t1 + t2 + t3 + t4)
                .totalAchieved((int) (q1 + q2 + q3 + q4))
                .build();
    }
}
