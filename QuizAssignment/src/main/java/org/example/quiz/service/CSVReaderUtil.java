package org.example.quiz.service;

import org.example.quiz.domain.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class CSVReaderUtil {
    private CSVReaderUtil() {


    }

    public static List<Question> loadQuestions() {

        List<Question> questionList = new ArrayList<>();
        try {

            InputStream inputStream = CSVReaderUtil.class.getResourceAsStream("/quiz.txt");
            assert inputStream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));


            int questionNumber = 1;

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    String questionText = reader.readLine();
                    List<String> options = new ArrayList<>();
                    for (int i = 0; i < 4; i++) {
                        options.add(reader.readLine());
                    }
                    String answerLine = reader.readLine();
                    int answer = Integer.parseInt(answerLine.split(":")[1]);

                    Question question = new Question();
                    question.setId(questionNumber++);
                    question.setQuestionContent(questionText);
                    question.setOptions(options);
                    question.setAnswer(answer);
                    questionList.add(question);
                }
            }


        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
        return questionList;
    }
}
