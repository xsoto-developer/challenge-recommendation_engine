package com.walmarttech.recommendationService.service;

import com.walmarttech.recommendationService.dto.AddCompraRequest;
import com.walmarttech.recommendationService.dto.AddProductoRequest;
import com.walmarttech.recommendationService.dto.AddUsuarioRequest;
import com.walmarttech.recommendationService.dto.RecomendacionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecommendationServiceTest {
    private RecommendationService service;

    @BeforeEach
    void setUp() {
        service = new RecommendationService();

        // Hardcode datos originales en tests
        service.addProducto(new AddProductoRequest("Camiseta", "Ropa"));
        service.addProducto(new AddProductoRequest("Pantalón", "Ropa"));
        service.addProducto(new AddProductoRequest("Zapatos", "Calzado"));
        service.addProducto(new AddProductoRequest("Gorra", "Accesorios"));
        service.addProducto(new AddProductoRequest("Calcetines", "Ropa"));

        service.addUsuario(new AddUsuarioRequest("Usuario1"));
        service.addUsuario(new AddUsuarioRequest("Usuario2"));
        service.addUsuario(new AddUsuarioRequest("Usuario3"));

        service.addCompra(new AddCompraRequest("Usuario1", java.util.Arrays.asList("Camiseta", "Pantalón")));
        service.addCompra(new AddCompraRequest("Usuario2", java.util.Arrays.asList("Zapatos", "Gorra")));
        service.addCompra(new AddCompraRequest("Usuario1", java.util.Arrays.asList("Calcetines", "Pantalón")));
        service.addCompra(new AddCompraRequest("Usuario3", java.util.Arrays.asList("Camiseta", "Gorra")));
        service.addCompra(new AddCompraRequest("Usuario2", java.util.Arrays.asList("Calcetines")));
    }

    @Test
    void testRecomendarProductos() {
        String[] resultado = service.recomendarProductos(new RecomendacionRequest("Camiseta"));
        assertArrayEquals(new String[]{"Pantalón", "Calcetines"}, resultado);
    }

    @Test
    void testProductoNoExistente() {
        String[] resultado = service.recomendarProductos(new RecomendacionRequest("Inexistente"));
        assertNull(resultado);
    }

    @Test
    void testGetDatos() {
        var datos = service.getDatos();
        assertEquals(5, datos.productos().length);
        assertEquals(5, datos.historial().length);
    }
}