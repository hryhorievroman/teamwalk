package com.xcompany.teamwalk.service;

import com.xcompany.teamwalk.exception.TeamNotFoundException;
import com.xcompany.teamwalk.model.Team;
import com.xcompany.teamwalk.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void createTeam(String teamId) {
        Team team = teamRepository.findById(teamId);
        if (team == null) {
            teamRepository.save(new Team(teamId));
        }
    }

    public void removeTeam(String teamId) {
        findTeamById(teamId);
        teamRepository.delete(teamId);
    }

    public void addSteps(String teamId, long steps) {
        Team team = findTeamById(teamId);
        team.addStep(steps);
    }

    public long getSteps(String teamId) {
        Team team = findTeamById(teamId);
        return team.getSteps();
    }

    public Team findTeamById(String teamId) {
        Team team = teamRepository.findById(teamId);
        if (team == null) {
            throw new TeamNotFoundException("Team " + teamId + " is not found");
        }
        return team;
    }

    public List<Team> getLeaderBoard() {
        List<Team> teams = teamRepository.findAll();

        return teams.stream().sorted(Comparator.comparingLong(Team::getSteps).reversed().thenComparing(Team::getId)).toList();

    }


}
