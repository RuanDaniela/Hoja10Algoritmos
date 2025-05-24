public class Floyd {

    public static Resultado calcular(int[][] matriz) {
        int n = matriz.length;
        int[][] dist = new int[n][n];
        int[][] camino = new int[n][n];

        // Copiar matriz y preparar caminos
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = matriz[i][j];
                if (i != j && matriz[i][j] != Integer.MAX_VALUE) {
                    camino[i][j] = i; // predecesor
                } else {
                    camino[i][j] = -1;
                }
            }
        }

        // Aplicar el algoritmo de Floyd
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        int nuevo = dist[i][k] + dist[k][j];
                        if (nuevo < dist[i][j]) {
                            dist[i][j] = nuevo;
                            camino[i][j] = camino[k][j];
                        }
                    }
                }
            }
        }

        return new Resultado(dist, camino);
    }

    public static class Resultado {
        public int[][] dist;
        public int[][] camino;

        public Resultado(int[][] dist, int[][] camino) {
            this.dist = dist;
            this.camino = camino;
        }
    }
}
