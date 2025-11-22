package com.xcompany.teamwalk.controller;

import com.xcompany.teamwalk.model.Team;
import com.xcompany.teamwalk.service.TeamService;
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
    public ResponseEntity<Boolean> addTeam(@RequestBody String teamId) {
        teamService.createTeam(teamId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Boolean> deleteTeam(@PathVariable String teamId) {
        teamService.removeTeam(teamId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{teamId}")
    public ResponseEntity<Long> addSteps(@PathVariable String teamId, @RequestBody Long steps) {
        teamService.addSteps(teamId, steps);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Long> getSteps(@PathVariable String teamId) {
        return new ResponseEntity<>(teamService.getSteps(teamId), HttpStatus.OK);
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<Team>> getLeaderBoard() {
        return new ResponseEntity<>(teamService.getLeaderBoard(), HttpStatus.OK);
    }

}
