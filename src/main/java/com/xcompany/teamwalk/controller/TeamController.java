package com.xcompany.teamwalk.controller;

import com.xcompany.teamwalk.dto.AddStepsRequest;
import com.xcompany.teamwalk.dto.AddStepsResponse;
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
        boolean isCreated = teamService.createTeam(request.teamId());
        return isCreated
                ? new ResponseEntity<>(true, HttpStatus.CREATED)
                : new ResponseEntity<>(false, HttpStatus.CONFLICT);

    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Boolean> deleteTeam(@PathVariable String teamId) {
        boolean isDeleted = teamService.removeTeam(teamId);
        return isDeleted
                ? new ResponseEntity<>(true, HttpStatus.OK)
                : new ResponseEntity<>(false, HttpStatus.CONFLICT);
    }

    @PostMapping("/{teamId}/steps")
    public ResponseEntity<AddStepsResponse> addSteps(@PathVariable String teamId, @Valid @RequestBody AddStepsRequest request) {
        long steps = teamService.addSteps(teamId, request.steps());
        return ResponseEntity.ok(new AddStepsResponse(teamId, steps));
    }

    @GetMapping("/{teamId}/steps")
    public ResponseEntity<TeamStepDto> getSteps(@PathVariable String teamId) {
        long steps = teamService.getSteps(teamId);
        return ResponseEntity.ok(new TeamStepDto(teamId, steps));
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<TeamScoreDto>> getLeaderBoard() {
        return ResponseEntity.ok(teamService.getLeaderBoard());
    }

}
