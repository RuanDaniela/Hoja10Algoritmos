import java.util.*;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        // Agregamos rutas a mano
        grafo.agregarConexion("BuenosAires", "SaoPaulo", 10);
        grafo.agregarConexion("BuenosAires", "Lima", 15);
        grafo.agregarConexion("Lima", "Quito", 10);

        // Mostramos matriz de adyacencia
        grafo.mostrarMatriz();

        // Aplicamos Floyd
        Floyd.Resultado resultado = Floyd.calcular(grafo.getMatriz());

        // Mostramos distancias más cortas
        System.out.println("\nDistancias más cortas:");
        int[][] dist = resultado.dist;
        List<String> ciudades = grafo.getCiudades();

        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                String desde = ciudades.get(i);
                String hasta = ciudades.get(j);

                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.println(desde + " -> " + hasta + ": INF");
                } else {
                    System.out.println(desde + " -> " + hasta + ": " + dist[i][j] + " hrs");
                }
            }
        }

        // Probar ruta entre dos ciudades
        int origen = grafo.getCiudades().indexOf("BuenosAires");
        int destino = grafo.getCiudades().indexOf("Quito");

        System.out.println("\nMostrando ruta más corta con ciudades intermedias:");
        mostrarRuta(origen, destino, resultado, grafo);
    }

    // Método para mostrar la ruta completa
    public static void mostrarRuta(int origen, int destino, Floyd.Resultado resultado, Grafo grafo) {
        if (resultado.dist[origen][destino] == Integer.MAX_VALUE) {
            System.out.println("No hay ruta disponible.");
            return;
        }

        List<String> ruta = new ArrayList<>();
        reconstruirRuta(origen, destino, resultado.camino, ruta, grafo);
        ruta.add(grafo.getCiudades().get(destino));

        System.out.println("Ruta más corta:");
        for (int i = 0; i < ruta.size(); i++) {
            System.out.print(ruta.get(i));
            if (i != ruta.size() - 1) System.out.print(" -> ");
        }
        System.out.println("\nTiempo total: " + resultado.dist[origen][destino] + " hrs");
    }

    public static void reconstruirRuta(int i, int j, int[][] camino, List<String> ruta, Grafo grafo) {
        if (camino[i][j] == -1 || camino[i][j] == i) {
            ruta.add(grafo.getCiudades().get(i));
            return;
        }
        reconstruirRuta(i, camino[i][j], camino, ruta, grafo);
        ruta.add(grafo.getCiudades().get(camino[i][j]));
    }
}
