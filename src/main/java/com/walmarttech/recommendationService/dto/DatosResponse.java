package com.walmarttech.recommendationService.dto;


import com.walmarttech.recommendationService.model.Producto;

public record DatosResponse(Producto[] productos, String[][] historial) {}