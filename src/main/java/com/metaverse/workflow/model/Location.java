package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="location")
public class Location {
    @Id
    @Column(name="location_id")
    private Long locationId;
    @Column(name="location_type")
    private String locationType;
    @Column(name="other_location_type")
    private String otherLocationType;
    @Column(name="ownership_type")
    private String ownershipType;
    @Column(name="latitude")
    private Double latitude;
    @Column(name="longitude")
    private Double longitude;
    @Column(name="capacity")
    private Integer capacity;
    @Column(name="location_image")
    private String locationImage;
    @Column(name="created_on")
    private Date createdOn;
    @Column(name="updated_on")
    private Date updatedOn;
}
