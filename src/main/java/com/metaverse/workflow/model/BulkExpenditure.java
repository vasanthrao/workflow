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
@Table(name = "bulk_expenditure")
public class BulkExpenditure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="bulk_expenditure_id")
    private Long bulkExpenditureId;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @ManyToOne
    @JoinColumn(name="expense_id")
    private HeadOfExpense headOfExpense;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "purchased_quantity")
    private Integer purchasedQuantity;

    @Column(name = "unit_cost")
    private Double unitCost;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "bill_no")
    private Integer billNo;

    @Column(name = "bill_date")
    private Date billDate;

    @Column(name = "mode_of_payment")
    private String modeOfPayment;

    @Column(name = "payee_name")
    private String payeeName;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "ifsc_code")
    private String ifscCode;

    @Column(name="remarks")
    private String remarks;

    @Column(name = "bill_url")
    private String uploadBillUrl;

    @Column(name="created_on", insertable = true, updatable = false)
    @CreationTimestamp
    private Date createdOn;

    @Column(name="updated_on", insertable = false, updatable = true)
    @UpdateTimestamp
    private Date updatedOn;




}
