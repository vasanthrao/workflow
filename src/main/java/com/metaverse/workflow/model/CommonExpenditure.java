package com.metaverse.workflow.model;

import com.metaverse.workflow.common.enums.ExpenditureType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "common_expenditure")
public class CommonExpenditure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="common_expenditures_id")
    private Long commonExpenditureId;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "sub_activity_id", nullable = false)
    private SubActivity subActivity;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    @Column(name = "expenditure_type")
    private ExpenditureType expenditureType;
    @Column(name = "head_of_expense")
    private String headOfExpense;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "bill_no")
    private Integer billNo;

    @Column(name = "bill_date")
    private Date billDate;

    @Column(name = "payee_name")
    private String payeeName;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "ifsc_code")
    private String ifscCode;

    @Column(name = "mode_of_payment")
    private String modeOfPayment;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "bill_url")
    private String uploadBillUrl;
}
