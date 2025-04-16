package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="financial_target")
public class FinancialTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="financial_target_id")
    private Long physicalTargetId;

    @ManyToOne
    @JoinColumn(name = "agency_id", nullable = false)
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "subactivity_id", nullable = false)
    private SubActivity subactivity;

    @Column(name ="year")
    private Integer year;


    @Column(name = "apr_financial_target")
    private Double aprFinancialTarget;

    @Column(name = "may_financial_target")
    private Double mayFinancialTarget;

    @Column(name = "jun_financial_target")
    private Double junFinancialTarget;

    @Column(name = "jul_financial_target")
    private Double julFinancialTarget;

    @Column(name = "aug_financial_target")
    private Double augFinancialTarget;

    @Column(name = "sep_financial_target")
    private Double sepFinancialTarget;

    @Column(name = "oct_financial_target")
    private Double octFinancialTarget;

    @Column(name = "nov_financial_target")
    private Double novFinancialTarget;

    @Column(name = "dec_financial_target")
    private Double decFinancialTarget;

    @Column(name = "jan_financial_target")
    private Double janFinancialTarget;

    @Column(name = "feb_financial_target")
    private Double febFinancialTarget;

    @Column(name = "mar_financial_target")
    private Double marFinancialTarget;
}
