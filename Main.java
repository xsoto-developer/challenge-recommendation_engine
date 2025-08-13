public class Main {
    public static void main(String[] args) {
        // Reto para el desarrollador:
        // 1. Implementar la función 'recomendarProductos' que, dado un historial de compras y un producto de entrada,
        //    devuelva una lista de productos recomendados basados en la frecuencia con la que aparecen
        //    en el historial de compras junto con productos de la misma categoría.
        // 2. La función debe manejar el caso en que no se encuentre la categoría del producto de entrada.
        // 3. La función debe evitar recomendar el producto de entrada.
        // 4. Escribir pruebas unitarias para verificar la correcta funcionalidad de la función 'recomendarProductos'.

        String[] productos = {"Camiseta", "Pantalón", "Zapatos", "Gorra", "Calcetines"};
        String[] categorias = {"Ropa", "Ropa", "Calzado", "Accesorios", "Ropa"};
        String[][] historialCompras = {
                {"Usuario1", "Camiseta", "Pantalón"},
                {"Usuario2", "Zapatos", "Gorra"},
                {"Usuario1", "Calcetines", "Pantalón"},
                {"Usuario3", "Camiseta", "Gorra"},
                {"Usuario2", "Calcetines"}
        };

        String productoEntrada = "Camiseta";

        String[] productosRecomendados = recomendarProductos(productoEntrada, productos, categorias, historialCompras);

        System.out.println("Productos recomendados para " + productoEntrada + ":");
        if (productosRecomendados != null) {
            for (String producto : productosRecomendados) {
                System.out.println("- " + producto);
            }
        } else {
            System.out.println("No se encontraron recomendaciones.");
        }
    }

    public static String[] recomendarProductos(String productoEntrada, String[] productos, String[] categorias, String[][] historialCompras) {
        // TODO: Implementar la lógica de recomendación aquí
        return null; // Devolver null si no hay