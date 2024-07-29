package org.example.quiz.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Question {
    public long id;
    private String questionContent;
    private List<String> options;
    private int answer;

}
