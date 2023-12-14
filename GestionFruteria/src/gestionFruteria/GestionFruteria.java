package gestionFruteria;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
    private static ArrayList<ProductoFruteria> inventario;

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
   
    // Método para dar entrada a un producto existente
    public void darEntradaProducto(String nombre, double cantidad) {
        // Buscamos el producto en el inventario por su nombre
        boolean encontrado = false;
        for (ProductoFruteria producto : inventario) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                // Si encontramos el producto, actualizamos su peso disponible
                producto.actualizarPesoDisponible(cantidad);
                System.out.println("Entrada de producto realizada: " + nombre + " (" + cantidad + " kg)");
                encontrado = true;
                break;
            }
        }
        // Si no encontramos el producto, mostramos un mensaje
        if (!encontrado) {
            System.out.println("Producto no encontrado en el inventario.");
        }
    }
    // Método para modificar el nombre y precio por kilo de un producto existente
    public void modificarProducto(String nombre, String nuevoNombre, double nuevoPrecio) {
        // Iteramos a través del inventario para buscar el producto por nombre
        for (ProductoFruteria producto : inventario) {
            // Verificamos si el nombre del producto coincide (ignorando mayúsculas/minúsculas)
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                // Actualizamos el nombre y el precio por kilo del producto
                producto.nombre = nuevoNombre;
                producto.precioKilo = nuevoPrecio;

                // Mostramos mensaje de éxito y el producto modificado
                System.out.println("Hemos modificado el producto: " + producto);

                // Salimos del método después de realizar la modificación
                return;
            }
        }

        // Mostramos mensaje si el producto no está en el inventario
        System.out.println("No hemos encontrado el producto en nuestro inventario.");
    }



    /// Método principal que maneja el menú y la interacción con el usuario
    public static void main(String[] args) {
        // Creamos una instancia de la clase GestionFruteria para gestionar nuestro inventario
        GestionFruteria gestionFruteria = new GestionFruteria();

        // Creamos un objeto Scanner para leer la entrada del usuario
        try (Scanner scanner = new Scanner(System.in)) {

        // Iniciamos un bucle infinito para mostrar el menú y recibir la entrada del usuario
        while (true) {
            // Mostramos nuestro menú de opciones
            System.out.println("\nMenú:");
            System.out.println("1. Agregar nuevo producto");
            System.out.println("2. Dar entrada a producto");
            System.out.println("3. Modificar producto");
            System.out.println("4. Mostrar inventario de la frutería");
            System.out.println("5. Vender producto");
            System.out.println("6. Salir");
           

            // Leemos la opción seleccionada por el usuario
            try {
                System.out.print("Seleccione una opción: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Entrada no válida. Por favor, seleccione una de las opciones.");
                    System.out.print("Seleccione una opción: ");
                    scanner.next(); // Limpiar el buffer del scanner
                }
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumimos el salto de línea después del número

                // Evaluamos la opción seleccionada
                switch (opcion) {
                case 1:
                    // Opción para agregar un nuevo producto al inventario
                    System.out.print("Ingrese el nombre del nuevo producto: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Ingrese el precio por kilo del nuevo producto: $");
                    double nuevoPrecioKilo = scanner.nextDouble();
                    scanner.nextLine(); // Consumimos el salto de línea después del número
                    System.out.print("Ingrese el peso disponible del nuevo producto en kilos: ");
                    double nuevoPeso = scanner.nextDouble();
                    gestionFruteria.agregarProducto(nuevoNombre, nuevoPrecioKilo, nuevoPeso);
                    break;
                case 2:
                    // Opción para dar entrada a producto existente
                    System.out.print("Ingrese el nombre del producto al que desea dar entrada: ");
                    String productoEntrada = scanner.nextLine();
                    System.out.print("Ingrese la cantidad de producto a añadir en kilos: ");
                    double cantidadEntrada = scanner.nextDouble();
                    gestionFruteria.darEntradaProducto(productoEntrada, cantidadEntrada);
                    break;
                case 3:
                    // Opción para modificar un producto existente
                    System.out.print("Ingrese el nombre del producto a modificar: ");
                    String productoModificar = scanner.nextLine();
                    // Verificamos si el producto existe antes de intentar modificarlo
                    boolean productoEncontrado = false;
                    for (ProductoFruteria producto : inventario) {
                        if (producto.getNombre().equalsIgnoreCase(productoModificar)) {
                            productoEncontrado = true;
                            System.out.print("Ingrese el nuevo nombre para el producto: ");
                            String nuevoNombre1 = scanner.nextLine();
                            System.out.print("Ingrese el nuevo precio por kilo para el producto: $");
                            double nuevoPrecio = scanner.nextDouble();
                            scanner.nextLine(); // Consumimos el salto de línea después del número
                            gestionFruteria.modificarProducto(productoModificar, nuevoNombre1, nuevoPrecio);
                            break;
                        }
                    }
                    // Mostramos mensaje si el producto no está en el inventario
                    if (!productoEncontrado) {
                        System.out.println("Producto no encontrado en el inventario.");
                    }
                    break;

                case 4:
                    // Opción para mostrar el inventario de la frutería
                    gestionFruteria.mostrarInventario();
                    break;
                case 5:
                    // Opción para vender un producto del inventario
                    System.out.print("Ingrese el nombre del producto a vender: ");
                    String productoVender = scanner.nextLine();
                    System.out.print("Ingrese el peso a vender en kilos: ");
                    double pesoVender = scanner.nextDouble();
                    gestionFruteria.venderProducto(productoVender, pesoVender);
                    break;
                case 6:
                    // Opción para salir del programa
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                default:
                    // Mensaje para opciones no válidas
                    System.out.println("Opción no válida. Intente de nuevo.");
                    
                }
            } catch (InputMismatchException e) {
                System.out.println("Error de entrada. Por favor, ingrese un número entero válido.");
                scanner.next(); // Limpiar el buffer del scanner
            }
        
        }
    }
   } 
}