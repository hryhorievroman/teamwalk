package com.xcompany.teamwalk.controller;

import com.xcompany.teamwalk.dto.AddStepsRequest;
import com.xcompany.teamwalk.dto.CreateTeamRequest;
import com.xcompany.teamwalk.dto.TeamScoreDto;
import com.xcompany.teamwalk.dto.TeamStepDto;
import com.xcompany.teamwalk.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<Boolean> addTeam(@RequestBody @Valid CreateTeamRequest request) {
        teamService.createTeam(request.teamId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Boolean> deleteTeam(@PathVariable String teamId) {
        teamService.removeTeam(teamId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{teamId}/steps")
    public ResponseEntity<Long> addSteps(@PathVariable String teamId, @Valid @RequestBody AddStepsRequest request) {
        teamService.addSteps(teamId, request.steps());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{teamId}/steps")
    public ResponseEntity<TeamStepDto> getSteps(@PathVariable String teamId) {
        long steps = teamService.getSteps(teamId);
        TeamStepDto response = new TeamStepDto(teamId, steps);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<TeamScoreDto>> getLeaderBoard() {
        return new ResponseEntity<>(teamService.getLeaderBoard(), HttpStatus.OK);
    }

}
