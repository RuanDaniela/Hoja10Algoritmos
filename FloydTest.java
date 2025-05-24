import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FloydTest {

    @Test
    public void testFloydBasico() {
        int INF = Integer.MAX_VALUE;

        int[][] grafo = {
            {0, 3, INF, 7},
            {8, 0, 2, INF},
            {5, INF, 0, 1},
            {2, INF, INF, 0}
        };

        Floyd.Resultado res = Floyd.calcular(grafo);
        int[][] dist = res.dist;
        int[][] camino = res.camino;

        // Verificamos distancias mínimas
        assertEquals(0, dist[0][0]);
        assertEquals(3, dist[0][1]);
        assertEquals(5, dist[0][2]); // 0→1→2
        assertEquals(6, dist[0][3]); // 0→2→3

        // Verificamos predecesores
        assertEquals(1, camino[0][2]);
        assertEquals(2, camino[0][3]);
    }

    @Test
    public void testSinConexion() {
        int INF = Integer.MAX_VALUE;
        int[][] grafo = {
            {0, INF},
            {INF, 0}
        };

        Floyd.Resultado res = Floyd.calcular(grafo);
        assertEquals(INF, res.dist[0][1]);
        assertEquals(-1, res.camino[0][1]);
    }
}

