package com.xcompany.teamwalk.dto;

import jakarta.validation.constraints.NotNull;

public record AddStepsRequest(@NotNull(message = "Steps must be provided") Long steps) {}
