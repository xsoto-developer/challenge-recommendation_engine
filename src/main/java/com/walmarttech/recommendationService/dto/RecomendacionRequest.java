package com.walmarttech.recommendationService.dto;

import jakarta.validation.constraints.NotBlank;

public record RecomendacionRequest(@NotBlank String productoEntrada) {}