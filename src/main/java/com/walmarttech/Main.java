package com.walmarttech;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Reto para el desarrollador:
        // 1. Implementar la función 'recomendarProductos' que, dado un historial de compras y un producto de entrada,
        //    devuelva una lista de productos recomendados basados en la frecuencia con la que aparecen
        //    en el historial de compras junto con productos de la misma categoría.
        // 2. La función debe manejar el caso en que no se encuentre la categoría del producto de entrada.
        // 3. La función debe evitar recomendar el producto de entrada.
        // 4. Escribir pruebas unitarias para verificar la correcta funcionalidad de la función 'recomendarProductos'.

        // Datos de prueba
        Producto[] productos = {
                new Producto("Camiseta", "Ropa"),
                new Producto("Pantalón", "Ropa"),
                new Producto("Zapatos", "Calzado"),
                new Producto("Gorra", "Accesorios"),
                new Producto("Calcetines", "Ropa")
        };
        String[][] historialCompras = {
                {"Usuario1", "Camiseta", "Pantalón"},
                {"Usuario2", "Zapatos", "Gorra"},
                {"Usuario1", "Calcetines", "Pantalón"},
                {"Usuario3", "Camiseta", "Gorra"},
                {"Usuario2", "Calcetines"}
        };
        String productoEntrada = "Camiseta";

        // Ejecutar recomendación
        String[] recomendados = recomendarProductos(productoEntrada, productos, historialCompras);

        // Mostrar resultados
        System.out.println("Productos recomendados para " + productoEntrada + ":");
        if (recomendados != null) {
            for (String producto : recomendados) {
                System.out.println("- " + producto);
            }
        } else {
            System.out.println("No se encontraron recomendaciones.");
        }
    }

    public record Producto(String nombre, String categoria) {}

    public static String[] recomendarProductos(String productoEntrada, Producto[] productos, String[][] historialCompras) {
        // Mapear productos a categorías
        Map<String, String> productoCategoria = new HashMap<>();
        for (Producto p : productos) {
            productoCategoria.put(p.nombre(), p.categoria());
        }

        // Validar categoría del producto de entrada
        String categoriaEntrada = productoCategoria.get(productoEntrada);
        if (categoriaEntrada == null) {
            return null;
        }

        // Contar frecuencias de productos co-comprados en la misma categoría
        Map<String, Long> frecuencias = new HashMap<>();
        for (String[] compra : historialCompras) {
            Arrays.stream(compra, 1, compra.length) // Saltar el usuario
                    .filter(producto -> !producto.equals(productoEntrada))
                    .filter(producto -> categoriaEntrada.equals(productoCategoria.get(producto)))
                    .forEach(producto -> frecuencias.merge(producto, 1L, Long::sum));
        }

        // Ordenar por frecuencia descendente
        return frecuencias.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toArray(String[]::new);
    }
}
