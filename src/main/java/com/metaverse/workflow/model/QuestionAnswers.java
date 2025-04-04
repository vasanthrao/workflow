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
@Table(name="question_answers")
public class QuestionAnswers {
    @Id
    @Column(name="question_answers_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionAnswersId;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    @Column(name = "answer_value")
    private String answers ;

    @Column(name="created_on",insertable = true,updatable = false)
    @CreationTimestamp
    private Date createdOn;

    @Column(name="updated_on",insertable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedOn;

}
