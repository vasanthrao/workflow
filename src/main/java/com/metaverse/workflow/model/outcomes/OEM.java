package com.metaverse.workflow.model.outcomes;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "outcome_oem_engagement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OEMEngagement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "oem_engagement_id")
    private Long id;

    @Column(name = "oem_registration_date")
    private Date oemRegistrationDate;

    @Column(name = "oem_registration_number")
    private String oemRegistrationNumber;

    @Column(name = "oem_targeted")
    private String oemTargeted;

    @Column(name = "oem_vendor_code")
    private String oemVendorCode;

    @Column(name = "products_supplied")
    private String productsSupplied;

    @Column(name = "vendor_registration_date")
    private Date vendorRegistrationDate;

    @Column(name = "first_po_date")
    private Date firstPurchaseOrderDate;

    @Column(name = "first_po_value")
    private Double firstPOValue;

    @Column(name = "monthly_supply_value")
    private Double currentMonthlySupplyValue;

    @Column(name = "certification_status")
    private Boolean isCertificationStatus;//Doubt

    @Column(name = "machinery_upgradation")
    private String machineryUpgradation;

    @Column(name = "oem_audit_score")
    private String oemAuditScore;

    @Column(name = "is_influenced")
    private Boolean isInfluenced;
    
    private List<String> names;
}
