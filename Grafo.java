import java.util.*;

public class Grafo {
    private List<String> ciudades = new ArrayList<>();
    private Map<String, Integer> indices = new HashMap<>();
    private int[][] matriz;
    private final int INF = Integer.MAX_VALUE;

    public Grafo() {
        matriz = new int[0][0]; // inicia vacío
    }

    // Agrega ciudad si no existe
    public void agregarCiudad(String ciudad) {
        if (!indices.containsKey(ciudad)) {
            ciudades.add(ciudad);
            indices.put(ciudad, ciudades.size() - 1);
            expandirMatriz();
        }
    }

    // Aumenta el tamaño de la matriz cuando se agregan ciudades
    private void expandirMatriz() {
        int n = ciudades.size();
        int[][] nueva = new int[n][n];
        for (int[] fila : nueva) Arrays.fill(fila, INF);
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - 1; j++)
                nueva[i][j] = matriz[i][j];
        matriz = nueva;
    }

    // Agrega conexión de una ciudad a otra con un tiempo (solo dirección A -> B)
    public void agregarConexion(String origen, String destino, int tiempo) {
        agregarCiudad(origen);
        agregarCiudad(destino);
        int i = indices.get(origen);
        int j = indices.get(destino);
        matriz[i][j] = tiempo;
    }

    public void mostrarMatriz() {
        System.out.println("   " + ciudades);
        for (int i = 0; i < matriz.length; i++) {
            System.out.print(ciudades.get(i) + " ");
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(matriz[i][j] + "   ");
            }
            System.out.println();
        }
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public List<String> getCiudades() {
        return ciudades;
    }
}
