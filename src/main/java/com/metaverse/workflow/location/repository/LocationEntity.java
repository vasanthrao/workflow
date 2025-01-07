package com.metaverse.workflow.location.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="location")
public class LocationEntity {
    @Id
    @Column(name="location_id")
    private Long locationId;
    private String locationType;
    private String otherLocationType;
    private String ownershiptType;
    private Double latitude;
    private Double longitude;
    private Integer capacity;
    private String locationImage;
}
