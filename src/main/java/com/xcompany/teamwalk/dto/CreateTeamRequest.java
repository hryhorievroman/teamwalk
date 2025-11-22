package com.xcompany.teamwalk.dto;

import jakarta.validation.constraints.NotNull;

public record CreateTeamRequest(@NotNull(message = "teamId must not be null") String teamId) {}
