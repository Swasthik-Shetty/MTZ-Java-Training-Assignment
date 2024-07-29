package org.example.quiz.service;

import org.example.quiz.domain.Question;

import java.util.List;
import java.util.Scanner;

public class QuizServiceImpl implements QuizService {

    @Override
    public void startQuiz(String username) {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Hello " + username + "!!");
        System.out.println("Welcome to the quiz!");
        System.out.println("----------------------------------------------------------------------");

        List<Question> questions = CSVReaderUtil.loadQuestions();
        int corrrectAnswer = 0;
        for (Question question : questions) {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Question Number : " + question.getId());
            System.out.println("Question : " + question.getQuestionContent());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println("Option " + (i + 1) + " : " + options.get(i));
            }
            System.out.println("Pick Right option:");
            int selectedOption = sc.nextInt();
            if (question.getAnswer() == selectedOption) {
                System.out.println("Correct answer");
                corrrectAnswer++;
            } else {
                System.out.println("Wrong answer");
            }
            System.out.println("----------------------------------------------------------------------");
        }
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Number of questions answered correct : " + corrrectAnswer);
        System.out.println("Number of questions answered wrong : " + (questions.size() - corrrectAnswer));
        System.out.println("----------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------");
    }
}
