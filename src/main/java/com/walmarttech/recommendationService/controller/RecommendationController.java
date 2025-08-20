package com.walmarttech.recommendationService.controller;

import com.walmarttech.recommendationService.dto.AddCategoriaRequest;
import com.walmarttech.recommendationService.dto.AddCompraRequest;
import com.walmarttech.recommendationService.dto.AddProductoRequest;
import com.walmarttech.recommendationService.dto.AddUsuarioRequest;
import com.walmarttech.recommendationService.dto.DatosResponse;
import com.walmarttech.recommendationService.dto.RecomendacionRequest;
import com.walmarttech.recommendationService.service.RecommendationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecommendationController {
    private final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @PostMapping("/api/recomendar")
    public ResponseEntity<String[]> recomendar(@Valid @RequestBody RecomendacionRequest request) {
        String[] resultado = service.recomendarProductos(request);
        return ResponseEntity.ok(resultado != null ? resultado : new String[]{});
    }

    @GetMapping("/api/datos")
    public ResponseEntity<DatosResponse> getDatos() {
        return ResponseEntity.ok(service.getDatos());
    }

    @PostMapping("/api/productos")
    public ResponseEntity<Void> addProducto(@Valid @RequestBody AddProductoRequest request) {
        service.addProducto(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/categorias")
    public ResponseEntity<Void> addCategoria(@Valid @RequestBody AddCategoriaRequest request) {
        service.addCategoria(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/usuarios")
    public ResponseEntity<Void> addUsuario(@Valid @RequestBody AddUsuarioRequest request) {
        service.addUsuario(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/compras")
    public ResponseEntity<Void> addCompra(@Valid @RequestBody AddCompraRequest request) {
        service.addCompra(request);
        return ResponseEntity.ok().build();
    }
}