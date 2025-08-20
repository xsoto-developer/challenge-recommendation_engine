package com.walmarttech.recommendationService.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        // Hardcode datos originales en tests via endpoints
        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\": \"Camiseta\", \"categoria\": \"Ropa\"}"));
        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\": \"Pantalón\", \"categoria\": \"Ropa\"}"));
        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\": \"Zapatos\", \"categoria\": \"Calzado\"}"));
        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\": \"Gorra\", \"categoria\": \"Accesorios\"}"));
        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\": \"Calcetines\", \"categoria\": \"Ropa\"}"));

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\": \"Usuario1\"}"));
        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\": \"Usuario2\"}"));
        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\": \"Usuario3\"}"));

        mockMvc.perform(post("/api/compras")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"usuario\": \"Usuario1\", \"productos\": [\"Camiseta\", \"Pantalón\"]}"));
        mockMvc.perform(post("/api/compras")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"usuario\": \"Usuario2\", \"productos\": [\"Zapatos\", \"Gorra\"]}"));
        mockMvc.perform(post("/api/compras")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"usuario\": \"Usuario1\", \"productos\": [\"Calcetines\", \"Pantalón\"]}"));
        mockMvc.perform(post("/api/compras")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"usuario\": \"Usuario3\", \"productos\": [\"Camiseta\", \"Gorra\"]}"));
        mockMvc.perform(post("/api/compras")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"usuario\": \"Usuario2\", \"productos\": [\"Calcetines\"]}"));
    }

    @Test
    void testRecomendar() throws Exception {
        String json = "{\"productoEntrada\": \"Camiseta\"}";
        mockMvc.perform(post("/api/recomendar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("Pantalón"));
    }

    @Test
    void testGetDatos() throws Exception {
        mockMvc.perform(get("/api/datos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productos[0].nombre").value("Camiseta"));
    }
}