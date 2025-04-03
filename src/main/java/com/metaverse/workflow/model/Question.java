package com.metaverse.workflow.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "question_field_type")
    private String questionFieldType;


    @Column(name = "question")
    private String question;


    @Column(name = "option_value")
    private String options ;

    @Column(name="created_on",insertable = true,updatable = false)
    @CreationTimestamp
    private Date createdOn;
    @Column(name="updated_on",insertable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedOn;

}
