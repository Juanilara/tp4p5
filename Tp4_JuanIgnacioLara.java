
// Alumno: Juan Ignacio Lara
// Materia: Programación III - UTN A Distancia
// Trabajo Práctico 4: Uso de equals() y hashCode() para comparación de objetos y manejo de duplicados

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Producto {
    private String codigo;
    private String nombre;
    private double precio;

    public Producto(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }

    // Sobrescribimos equals para que dos productos sean iguales si tienen el mismo código
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(codigo, producto.codigo);
    }

    // Es una buena práctica sobrescribir hashCode cuando se sobrescribe equals
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Producto [Código: " + codigo + ", Nombre: " + nombre + ", Precio: $" + precio + "]";
    }
}

public class Main {
    public static void main(String[] args) {
        List<Producto> carrito = new ArrayList<>();

        // Creamos algunos productos
        Producto p1 = new Producto("P001", "Teclado Mecánico", 45500.0);
        Producto p2 = new Producto("P002", "Mouse Inalámbrico", 18200.0);
        Producto p3 = new Producto("P001", "Teclado Mecánico Genérico (Duplicado)", 40000.0); // Mismo código P001

        // Intentamos agregar productos a la lista evitando duplicados usando contains()
        agregarProductoUnico(carrito, p1);
        agregarProductoUnico(carrito, p2);
        agregarProductoUnico(carrito, p3); // Este debería ser rechazado por tener el mismo código

        // Imprimimos el contenido final del carrito
        System.out.println("--- Listado de Productos en el Carrito ---");
        for (Producto p : carrito) {
            System.out.println(p);
        }
    }

    public static void agregarProductoUnico(List<Producto> lista, Producto nuevoProducto) {
        if (lista.contains(nuevoProducto)) {
            System.out.println("Aviso: El producto con código " + nuevoProducto.getCodigo() + " ya existe en la lista. No se puede duplicar.");
        } else {
            lista.add(nuevoProducto);
            System.out.println("Éxito: Producto " + nuevoProducto.getNombre() + " agregado correctamente.");
        }
    }
}
