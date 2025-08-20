package com.walmarttech.recommendationService.dto;

import jakarta.validation.constraints.NotBlank;

public record AddProductoRequest(@NotBlank String nombre, @NotBlank String categoria) {

}
