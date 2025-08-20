package com.walmarttech.recommendationService.dto;

import jakarta.validation.constraints.NotBlank;

public record AddUsuarioRequest(@NotBlank String nombre) {}