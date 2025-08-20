package com.walmarttech.recommendationService.dto;

import jakarta.validation.constraints.NotBlank;

public record AddCategoriaRequest(@NotBlank String nombre) {}