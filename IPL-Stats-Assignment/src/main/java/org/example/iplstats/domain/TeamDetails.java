package org.example.iplstats.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class TeamDetails {


    private String city;
    private String coach;
    private String home;
    private String name;
    private String label;
    private List<PlayerDetails> players;
}
