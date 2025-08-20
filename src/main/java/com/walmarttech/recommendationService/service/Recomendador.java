package com.walmarttech.recommendationService.service;

import com.walmarttech.recommendationService.model.Producto;

public interface Recomendador {
    String[] recomendarProductos(String productoEntrada, Producto[] productos, String[][] historialCompras);
}