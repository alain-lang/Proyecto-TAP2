package model;

public class Producto {
    private String clave;
    private String nombre;
    private String marca;
    private String descripcion;
    private double precio;

    public Producto(String clave, String nombre, String marca, String descripcion, double precio) {
        this.clave = clave;
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getClave() { return clave; }
    public String getNombre() { return nombre; }
    public String getMarca() { return marca; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPrecio(double precio) { this.precio = precio; }
}
