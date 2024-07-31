package org.example;


import org.example.iplstats.domain.PlayerDetails;

import org.example.iplstats.service.JsonReaderUtil;
import org.example.iplstats.service.RoleCountServiceImpl;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        RoleCountServiceImpl roleCountService = new RoleCountServiceImpl();
        roleCountService.getCountByRole("MI");




    }
}
