package com.walmarttech.recommendationSimple;

import com.walmarttech.Main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecomendadorTest {
    private final Main.Producto[] productos = {
            new Main.Producto("Camiseta", "Ropa"),
            new Main.Producto("Pantalón", "Ropa"),
            new Main.Producto("Calcetines", "Ropa"),
            new Main.Producto("Zapatos", "Calzado")
    };
    private final String[][] historial = {
            {"Usuario1", "Camiseta", "Pantalón"},
            {"Usuario2", "Calcetines", "Pantalón"},
            {"Usuario3", "Camiseta", "Calcetines"}
    };

    @Test
    void testRecomendarProductos() {
        String[] resultado = Main.recomendarProductos("Camiseta", productos, historial);
        assertArrayEquals(new String[]{"Pantalón", "Calcetines"}, resultado);
    }

    @Test
    void testProductoNoExistente() {
        String[] resultado = Main.recomendarProductos("Inexistente", productos, historial);
        assertNull(resultado);
    }

    @Test
    void testSinRecomendaciones() {
        String[][] historialVacio = {{"Usuario1", "Camiseta"}};
        String[] resultado = Main.recomendarProductos("Camiseta", productos, historialVacio);
        assertArrayEquals(new String[]{}, resultado);
    }

    @Test
    void testCategoriaSinCoCompras() {
        String[] resultado = Main.recomendarProductos("Zapatos", productos, historial);
        assertArrayEquals(new String[]{}, resultado);
    }
}