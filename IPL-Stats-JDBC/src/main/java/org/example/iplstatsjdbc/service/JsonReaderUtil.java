package org.example.iplstatsjdbc.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.iplstatsjdbc.domain.Team;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonReaderUtil {
    private JsonReaderUtil(){}
    public static List<Team> readTeams() {
        List<Team> teamList = new ArrayList<>();
        try{

            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = JsonReaderUtil.class.getClassLoader().getResourceAsStream("ipl2020.json");
            assert inputStream != null;
            teamList = objectMapper.readValue(inputStream, new TypeReference<>() {});

        }catch (IOException e)
        {
            System.out.println("IOException occured");
        }
        return teamList;
    }
}
