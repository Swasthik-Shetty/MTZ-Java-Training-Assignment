package org.example.iplstatsjdbc.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Team {
    private String city;
    private String coach;
    private String home;
    private String name;
    private String label;
    private List<Player> players;
}
