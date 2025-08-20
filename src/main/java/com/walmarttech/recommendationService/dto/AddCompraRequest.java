package com.walmarttech.recommendationService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AddCompraRequest(@NotBlank String usuario, @NotEmpty List<String> productos) {}