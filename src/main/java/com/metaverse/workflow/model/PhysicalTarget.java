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
@Table(name="physical_target")
public class PhysicalTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="physical_target_id")
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


    @Column(name = "apr_physical_target")
    private Integer aprPhysicalTarget;

    @Column(name = "may_physical_target")
    private Integer mayPhysicalTarget;

    @Column(name = "jun_physical_target")
    private Integer junPhysicalTarget;

    @Column(name = "jul_physical_target")
    private Integer julPhysicalTarget;

    @Column(name = "aug_physical_target")
    private Integer augPhysicalTarget;

    @Column(name = "sep_physical_target")
    private Integer sepPhysicalTarget;

    @Column(name = "oct_physical_target")
    private Integer octPhysicalTarget;

    @Column(name = "nov_physical_target")
    private Integer novPhysicalTarget;

    @Column(name = "dec_physical_target")
    private Integer decPhysicalTarget;

    @Column(name = "jan_physical_target")
    private Integer janPhysicalTarget;

    @Column(name = "feb_physical_target")
    private Integer febPhysicalTarget;

    @Column(name = "mar_physical_target")
    private Integer marPhysicalTarget;

}
