package com.xcompany.teamwalk.repository;

import com.xcompany.teamwalk.model.Team;

import java.util.List;

public interface TeamRepository {

    boolean saveIfAbsent(Team team);

    void delete(String teamId);

    Team findById(String teamId);

    List<Team> findAll();
}
