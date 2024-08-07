package org.example.iplstatsjdbc.service;

import java.util.List;
import java.util.Map;

public interface IPLJDBCService {
    void getAllTeamDetails();
    void getMaxPaidPlayersByRole();
    void getPlayersByTeam(String role, String label);
    void getCountByRole(String label);
    void getPlayersBySort(String fieldName);
    void getTeamAmountByRole(String label, String role);
    void getTotalAmountSpentByTeam();
    void getTeamLabels();
    void getPlayersByTeam(String label);
}
