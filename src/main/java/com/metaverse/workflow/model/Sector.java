package com.metaverse.workflow.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="sector")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="sector_id")
    private Long sectorId;

    @Column(name="sector_name", nullable = false, unique = true)
    private String sectorName;

    @ManyToMany(mappedBy = "sectors", cascade = CascadeType.ALL)
    private List<Organization> organizations;

    @Column(name="created_on", insertable = true, updatable = false)
    @CreationTimestamp
    private Date createdOn;

    @Column(name="updated_on", insertable = false, updatable = true)
    @UpdateTimestamp
    private Date updatedOn;

}
