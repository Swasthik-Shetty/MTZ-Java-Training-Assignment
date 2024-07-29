package org.example;


import org.example.quiz.service.QuizServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter Your Name to start the test:");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        QuizServiceImpl quizService = new QuizServiceImpl();
        quizService.startQuiz(username);
    }
}
