package com.walmarttech.recommendationService.service.impl;

import com.walmarttech.recommendationService.model.Producto;
import com.walmarttech.recommendationService.service.Recomendador;

import java.util.*;
import java.util.stream.Collectors;

public class RecomendadorPorFrecuencia implements Recomendador {
    @Override
    public String[] recomendarProductos(String productoEntrada, Producto[] productos, String[][] historialCompras) {
        Map<String, String> productoCategoria = new HashMap<>();
        for (Producto p : productos) {
            productoCategoria.put(p.nombre(), p.categoria());
        }

        String categoriaEntrada = productoCategoria.get(productoEntrada);
        if (categoriaEntrada == null) {
            return null;
        }

        Map<String, Long> frecuencias = new HashMap<>();
        for (String[] compra : historialCompras) {
            for (String producto : Arrays.copyOfRange(compra, 1, compra.length)) {
                if (!producto.equals(productoEntrada) && productoCategoria.get(producto) != null &&
                        productoCategoria.get(producto).equals(categoriaEntrada)) {
                    frecuencias.merge(producto, 1L, Long::sum);
                }
            }
        }

        return frecuencias.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toArray(String[]::new);
    }
}