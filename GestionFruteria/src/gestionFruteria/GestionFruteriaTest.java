package gestionFruteria;
import org.junit.Test;
import static org.junit.Assert.*;

public class GestionFruteriaTest {
	/*
    @Test
    public void testAgregarProducto() {
        GestionFruteria gestionFruteria = new GestionFruteria();
        gestionFruteria.agregarProducto("Manzana", 2.5, 10.0);
        assertEquals(1, gestionFruteria.getInventario().size());
        gestionFruteria.agregarProducto("pera", 4, 10.0);
        assertEquals(2, gestionFruteria.getInventario().size());
    }

    @Test
    public void testVenderProducto() {
        GestionFruteria gestionFruteria = new GestionFruteria();
        gestionFruteria.agregarProducto("Pera", 3.0, 15.0);

        // Venta exitosa
        gestionFruteria.venderProducto("Pera", 5.0);
        assertEquals(10.0, gestionFruteria.getInventario().get(0).getPesoDisponible(), 0.01);
    }

    @Test
    public void testVenderProductoSinStock() {
        GestionFruteria gestionFruteria = new GestionFruteria();
        gestionFruteria.agregarProducto("Uva", 4.0, 5.0);

        // Intento de venta con stock insuficiente
        gestionFruteria.venderProducto("Uva", 8.0);
        assertEquals(5.0, gestionFruteria.getInventario().get(0).getPesoDisponible(), 0.01);
    }

    @Test
    public void testVenderProductoNoExistente() {
        GestionFruteria gestionFruteria = new GestionFruteria();
        gestionFruteria.agregarProducto("Naranja", 2.0, 12.0);

        // Intento de venta de un producto que no está en el inventario
        gestionFruteria.venderProducto("Manzana", 3.0);
        assertEquals(12.0, gestionFruteria.getInventario().get(0).getPesoDisponible(), 0.01);
    }*/
    @Test
    public void testRendimiento() {
        GestionFruteria gestionFruteria = new GestionFruteria();

        // Agregar productos aleatorios x veces
        gestionFruteria.agregarProductosAutomatizado(5000000);

        // Vender productos aleatorios x veces
        gestionFruteria.venderProductosAleatorios(5000000);

        // Verificar que el tamaño del inventario sea igual a 6
        assertEquals(5, gestionFruteria.getInventario().size());
    }
}
