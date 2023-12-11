package gestionFruteria;

import java.util.ArrayList;
import java.util.Scanner;

class ProductoFruteria {
    private String nombre;
    private double precioKilo;
    private double pesoDisponible;

    public ProductoFruteria(String nombre, double precioKilo, double pesoDisponible) {
        this.nombre = nombre;
        this.precioKilo = precioKilo;
        this.pesoDisponible = pesoDisponible;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioKilo() {
        return precioKilo;
    }

    public double getPesoDisponible() {
        return pesoDisponible;
    }

    public void venderProducto(double peso) {
        if (peso <= pesoDisponible) {
            pesoDisponible -= peso;
            System.out.println("Producto vendido: " + nombre + " (" + peso + " kg)");
        } else {
            System.out.println("No hay suficiente peso disponible de " + nombre);
        }
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Precio por Kilo: $" + precioKilo + ", Peso Disponible: " + pesoDisponible + " kg";
    }
}

public class GestionFruteria {
    private ArrayList<ProductoFruteria> inventario;

    public GestionFruteria() {
        inventario = new ArrayList<>();
    }

    public void agregarProducto(String nombre, double precioKilo, double pesoDisponible) {
        ProductoFruteria nuevoProducto = new ProductoFruteria(nombre, precioKilo, pesoDisponible);
        inventario.add(nuevoProducto);
        System.out.println("Producto agregado: " + nuevoProducto);
    }

    public void mostrarInventario() {
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("Inventario de la frutería:");
            for (ProductoFruteria producto : inventario) {
                System.out.println(producto);
            }
        }
    }

    public void venderProducto(String nombre, double peso) {
        boolean encontrado = false;
        for (ProductoFruteria producto : inventario) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                producto.venderProducto(peso);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Producto no encontrado en el inventario.");
        }
    }

    public static void main(String[] args) {
        GestionFruteria gestionFruteria = new GestionFruteria();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Agregar producto al inventario");
            System.out.println("2. Mostrar inventario de la frutería");
            System.out.println("3. Vender producto");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después del número

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el precio por kilo del producto: $");
                    double precioKilo = scanner.nextDouble();
                    scanner.nextLine(); // Consumir el salto de línea después del número
                    System.out.print("Ingrese el peso disponible del producto en kilos: ");
                    double peso = scanner.nextDouble();
                    gestionFruteria.agregarProducto(nombre, precioKilo, peso);
                    break;
                case 2:
                    gestionFruteria.mostrarInventario();
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del producto a vender: ");
                    String productoVender = scanner.nextLine();
                    System.out.print("Ingrese el peso a vender en kilos: ");
                    double pesoVender = scanner.nextDouble();
                    gestionFruteria.venderProducto(productoVender, pesoVender);
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}