package gestionFruteria;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Clase que representa un producto en la frutería
class ProductoFruteria {
    public String nombre;
    public double precioKilo;
    public double pesoDisponible;

    // Constructor para inicializar un producto
    public ProductoFruteria(String nombre, double precioKilo, double pesoDisponible) {
        this.nombre = nombre;
        this.precioKilo = precioKilo;
        this.pesoDisponible = pesoDisponible;
    }


    // Métodos para obtener información del producto
    public String getNombre() {
        return nombre;
    }

    public double getPrecioKilo() {
        return precioKilo;
    }

    public double getPesoDisponible() {
        return pesoDisponible;
    }
    
    public void actualizarPesoDisponible(double peso) {
        this.pesoDisponible += peso;
    }

    // Método para vender una cantidad específica del producto
    public void venderProducto(double peso) {
        if (peso <= pesoDisponible) {
            pesoDisponible -= peso;
            System.out.println("Producto vendido: " + nombre + " (" + peso + " kg)");
        } else {
            System.out.println("No hay suficiente peso disponible de " + nombre);
        }
    }

    // Método para representar el producto como una cadena de texto
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Precio por Kilo: $" + precioKilo + ", Peso Disponible: " + pesoDisponible + " kg";
    }
}

// Clase que gestiona el inventario de la frutería
public class GestionFruteria {
    private ArrayList<ProductoFruteria> inventario;

    // Constructor para inicializar la gestión de la frutería
    public GestionFruteria() {
        inventario = new ArrayList<>();
    }

    public void agregarProducto(String nombre, double precioKilo, double pesoDisponible) {
        // Verificar si ya existe un producto con el mismo nombre en el inventario
        boolean productoExistente = false;
        for (ProductoFruteria producto : inventario) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                // Si existe, sumar al peso disponible directamente
                producto.pesoDisponible += pesoDisponible;
                System.out.println("Se han añadido " + pesoDisponible + "kg agregados a: " + nombre);
                productoExistente = true;
                break;
            }
        }

        // Si no existe, agregar un nuevo producto al inventario
        if (!productoExistente) {
            ProductoFruteria nuevoProducto = new ProductoFruteria(nombre, precioKilo, pesoDisponible);
            inventario.add(nuevoProducto);
            System.out.println("Nuevo Producto agregado: " + nuevoProducto);
        }
    }



    // Método para mostrar el inventario de la frutería
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

    // Método para vender una cantidad específica de un producto del inventario
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
    // Método para obtener el inventario de la frutería
    public ArrayList<ProductoFruteria> getInventario() {
        return inventario;
    }
    // Nombres de productos posibles
    private static String[] NOMBRES_PRODUCTOS = {"Manzana", "Pera", "Uva", "Platano", "Naranja"};

    // Método para agregar una cantidad específica de productos al inventario
    public void agregarProductosAutomatizado(int numeroPruebas) {
        Random random = new Random();

        for (int i = 0; i < numeroPruebas; i++) {
            String nombre = NOMBRES_PRODUCTOS[random.nextInt(NOMBRES_PRODUCTOS.length)];
            double precioKilo = random.nextInt(10+1); // Precio aleatorio entre 1 y 10
            double pesoDisponible = random.nextInt(50+1); // Peso aleatorio entre 1 y 50
            agregarProducto(nombre, precioKilo, pesoDisponible);
        }
    }

    // Método para vender productos aleatorios en el inventario
    public void venderProductosAleatorios(int numeroPruebas) {
        Random random = new Random();

        for (int i = 0; i < numeroPruebas; i++) {
            ProductoFruteria producto = inventario.get(random.nextInt(inventario.size()));
            double pesoVender = random.nextInt(5+1); // Vender entre 1 y 5 kilos
            venderProducto(producto.getNombre(), pesoVender);
        }
    }


    // Método principal que maneja el menú y la interacción con el usuario
    public static void main(String[] args) {
        // Creamos una instancia de la clase GestionFruteria para gestionar nuestro inventario
        GestionFruteria gestionFruteria = new GestionFruteria();

        // Creamos un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Iniciamos un bucle infinito para mostrar el menú y recibir la entrada del usuario
        while (true) {
            // Mostramos nuestro menú de opciones
            System.out.println("\nMenú:");
            System.out.println("1. Agregar producto al inventario");
            System.out.println("2. Mostrar inventario de la frutería");
            System.out.println("3. Vender producto");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            // Leemos la opción seleccionada por nosotros, los usuarios
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumimos el salto de línea después del número

            // Evaluamos la opción seleccionada
            switch (opcion) {
                case 1:
                    // Opción para agregar un nuevo producto al inventario
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el precio por kilo del producto: $");
                    double precioKilo = scanner.nextDouble();
                    scanner.nextLine(); // Consumimos el salto de línea después del número
                    System.out.print("Ingrese el peso disponible del producto en kilos: ");
                    double peso = scanner.nextDouble();
                    gestionFruteria.agregarProducto(nombre, precioKilo, peso);
                    break;
                case 2:
                    // Opción para mostrar el inventario de la frutería
                    gestionFruteria.mostrarInventario();
                    break;
                case 3:
                    // Opción para vender un producto del inventario
                    System.out.print("Ingrese el nombre del producto a vender: ");
                    String productoVender = scanner.nextLine();
                    System.out.print("Ingrese el peso a vender en kilos: ");
                    double pesoVender = scanner.nextDouble();
                    gestionFruteria.venderProducto(productoVender, pesoVender);
                    break;
                case 4:
                    // Opción para salir del programa
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                default:
                    // Mensaje para opciones no válidas
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}