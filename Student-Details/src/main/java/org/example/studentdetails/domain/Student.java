package org.example.studentdetails.domain;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@ToString

public class Student {

    private String name;

    private String batch;

    private String completed;

    private String placed;

    private String qualification;

    private double score;
}
