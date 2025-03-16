package com.metaverse.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="mandal")
public class Mandal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mandal_id")
    private Integer mandalId;

    @Column(name ="mandal_Name")
    private  String mandalName;

    @ManyToOne
    @JoinColumn(name="district")
    @JsonIgnore
    private District district;

    @Column(name="created_on",insertable = true,updatable = false)
    @CreationTimestamp
    private Date createdOn;

    @Column(name="updated_on",insertable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedOn;
}
