package com.xcompany.teamwalk.repository;

import com.xcompany.teamwalk.model.Team;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TeamRepositoryInMemoryImpl implements TeamRepository {

    private final ConcurrentHashMap<String, Team> storage = new ConcurrentHashMap<>();

    @Override
    public boolean saveIfAbsent(Team team) {
        return storage.putIfAbsent(team.getId(), team) == null;
    }

    @Override
    public boolean delete(String teamId) {
        return storage.remove(teamId) != null;
    }

    @Override
    public Team findById(String teamId) {
        return storage.get(teamId);
    }

    @Override
    public List<Team> findAll() {
        return storage.values().stream().toList();
    }
}
