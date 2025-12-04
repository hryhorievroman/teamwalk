package com.xcompany.teamwalk.service;

import com.xcompany.teamwalk.dto.TeamScoreDto;
import com.xcompany.teamwalk.exception.TeamAlreadyExistsException;
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
        boolean isCreated = teamRepository.saveIfAbsent(new Team(teamId));
        if (!isCreated) {
            throw new TeamAlreadyExistsException(teamId);
        }
    }

    public void removeTeam(String teamId) {
        boolean isRemoved = teamRepository.delete(teamId);
        if (!isRemoved) {
            throw new TeamNotFoundException(teamId);
        }
    }

    public long addSteps(String teamId, long steps) {
        if (steps <= 0) {
            throw new IllegalArgumentException("Steps must be greater than 0");
        }

        Team team = findTeamById(teamId);
        return team.addStepAndGet(steps);

    }

    public long getSteps(String teamId) {
        Team team = findTeamById(teamId);
        return team.getSteps();
    }

    public Team findTeamById(String teamId) {
        Team team = teamRepository.findById(teamId);
        if (team == null) {
            throw new TeamNotFoundException("The team " + teamId + " is not found");
        }
        return team;
    }

    public List<TeamScoreDto> getLeaderBoard() {
        List<Team> teams = teamRepository.findAll();

        return teams
                .stream()
                .map(team -> new TeamScoreDto(team.getId(), team.getSteps()))
                .sorted(Comparator.comparingLong(TeamScoreDto::steps).reversed().thenComparing(TeamScoreDto::teamId))
                .toList();

    }


}
