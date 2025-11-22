package com.xcompany.teamwalk.repository;

import com.xcompany.teamwalk.model.Team;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TeamRepositoryInMemoryImpl implements TeamRepository {

    private final ConcurrentHashMap<String, Team> storage = new ConcurrentHashMap<>();

    @Override
    public void save(Team team) {
        storage.putIfAbsent(team.getId(), team);
    }

    @Override
    public void delete(String teamId) {
        storage.remove(teamId);
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
