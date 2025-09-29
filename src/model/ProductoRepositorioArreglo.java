package model;

import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioArreglo {
    private final List<Producto> data = new ArrayList<>();

    public List<Producto> findAll() {
        return new ArrayList<>(data);
    }

    public Producto findByClave(String clave) {
        for (Producto p : data) {
            if (p.getClave().equalsIgnoreCase(clave)) return p;
        }
        return null;
    }

    // Crea si no existe; si existe, lanza excepción
    public void create(Producto p) {
        if (findByClave(p.getClave()) != null) {
            throw new IllegalArgumentException("La clave ya existe: " + p.getClave());
        }
        data.add(p);
    }

    // Actualiza si existe; si no existe, lanza excepción
    public void update(Producto p) {
        Producto existing = findByClave(p.getClave());
        if (existing == null) throw new IllegalArgumentException("No existe clave: " + p.getClave());
        existing.setNombre(p.getNombre());
        existing.setMarca(p.getMarca());
        existing.setDescripcion(p.getDescripcion());
        existing.setPrecio(p.getPrecio());
    }

    public void deleteByClave(String clave) {
        Producto existing = findByClave(clave);
        if (existing == null) throw new IllegalArgumentException("No existe clave: " + clave);
        data.remove(existing);
    }
}
