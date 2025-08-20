package com.walmarttech.recommendationService.service;

import com.walmarttech.recommendationService.dto.AddCategoriaRequest;
import com.walmarttech.recommendationService.dto.AddCompraRequest;
import com.walmarttech.recommendationService.dto.AddProductoRequest;
import com.walmarttech.recommendationService.dto.AddUsuarioRequest;
import com.walmarttech.recommendationService.dto.DatosResponse;
import com.walmarttech.recommendationService.dto.RecomendacionRequest;
import com.walmarttech.recommendationService.model.Producto;
import com.walmarttech.recommendationService.service.impl.RecomendadorPorFrecuencia;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecommendationService {
    private final Recomendador recomendador;

    private final List<Producto> productos = new ArrayList<>(); // Vacío inicialmente
    private final List<String[]> historial = new ArrayList<>(); // Vacío inicialmente
    private final Set<String> categorias = new HashSet<>(); // Vacío inicialmente
    private final Set<String> usuarios = new HashSet<>(); // Vacío inicialmente

    public RecommendationService() {
        this.recomendador = new RecomendadorPorFrecuencia();
    }

    public String[] recomendarProductos(RecomendacionRequest request) {
        return recomendador.recomendarProductos(request.productoEntrada(), productos.toArray(new Producto[0]), historial.toArray(new String[0][]));
    }

    public DatosResponse getDatos() {
        return new DatosResponse(productos.toArray(new Producto[0]), historial.toArray(new String[0][]));
    }

    public void addProducto(AddProductoRequest request) {
        productos.add(new Producto(request.nombre(), request.categoria()));
        categorias.add(request.categoria()); // Agrega categoría implícitamente
    }

    public void addCategoria(AddCategoriaRequest request) {
        categorias.add(request.nombre());
    }

    public void addUsuario(AddUsuarioRequest request) {
        usuarios.add(request.nombre());
    }

    public void addCompra(AddCompraRequest request) {
        // Verificar usuario (opcional)
        if (!usuarios.contains(request.usuario())) {
            usuarios.add(request.usuario());
        }
        // Verificar productos existen (opcional, asume que existen)
        String[] compra = new String[request.productos().size() + 1];
        compra[0] = request.usuario();
        for (int i = 0; i < request.productos().size(); i++) {
            compra[i + 1] = request.productos().get(i);
        }
        historial.add(compra);
    }
}