package com.metaverse.workflow.model;

import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.common.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "program_expenditure")
public class ProgramExpenditure {
    
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name="program_expenditure_id")
        private Long programExpenditureId;

        @ManyToOne
        @JoinColumn(name = "activity_id")
        private Activity activity;

        @ManyToOne
        @JoinColumn(name = "sub_activity_id")
        private SubActivity subActivity;

        @ManyToOne
        @JoinColumn(name = "program_id")
        private Program program;

        @ManyToOne
        @JoinColumn(name = "agency_id")
        private Agency agency;

        @Column(name = "expenditure_type")
        private ExpenditureType expenditureType;

        @ManyToOne
        @JoinColumn(name="expense_id")
        private HeadOfExpense headOfExpense;

        @Column(name = "cost")
        private Double cost;

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

        @Column(name="created_on", insertable = true, updatable = false)
        @CreationTimestamp
        private Date createdOn;

        @Column(name="updated_on", insertable = false, updatable = true)
        @UpdateTimestamp
        private Date updatedOn;
}
