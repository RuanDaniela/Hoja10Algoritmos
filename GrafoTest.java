import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

public class GrafoTest {

    @Test
    public void testAgregarCiudades() {
        Grafo grafo = new Grafo();

        grafo.agregarCiudad("CiudadA");
        grafo.agregarCiudad("CiudadB");

        List<String> ciudades = grafo.getCiudades();
        assertEquals(2, ciudades.size());
        assertTrue(ciudades.contains("CiudadA"));
        assertTrue(ciudades.contains("CiudadB"));
    }

    @Test
    public void testAgregarConexion() {
        Grafo grafo = new Grafo();

        grafo.agregarConexion("A", "B", 5);

        int[][] matriz = grafo.getMatriz();
        List<String> ciudades = grafo.getCiudades();

        int i = ciudades.indexOf("A");
        int j = ciudades.indexOf("B");

        assertEquals(2, ciudades.size());
        assertEquals(5, matriz[i][j]); // Verificamos conexión A → B
        assertEquals(Integer.MAX_VALUE, matriz[j][i]); // No hay B → A (es dirigido)
    }

    @Test
    public void testExpandirMatriz() {
        Grafo grafo = new Grafo();

        grafo.agregarCiudad("X");
        grafo.agregarCiudad("Y");

        int[][] matriz = grafo.getMatriz();

        assertEquals(2, matriz.length);        // Matriz 2x2
        assertEquals(Integer.MAX_VALUE, matriz[0][1]); // Sin conexión todavía
    }
}
