package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer questionAnswersId;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    @Column(name = "answer_value")
    private String answers ;

}
