package controller;

import M.Producto;
import view.ProductoTableModel;
import view.ProductoView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductoController {
    private final ProductoRepositorioArreglo repo;
    private final ProductoView view;

    public ProductoController(ProductoRepositorioArreglo repo, ProductoView view) {
        this.repo = repo;
        this.view = view;

        // Inicializar tabla
        view.tableModel = new ProductoTableModel(repo.findAll());
        view.tabla.setModel(view.tableModel);

        registrarEventos();
        refrescarTabla();
    }

    private void registrarEventos() {
        // GUARDAR: crea nuevo producto
        view.btnGuardar.addActionListener(e -> guardarProducto());

        // ACTUALIZAR: actualiza producto existente
        view.btnActualizar.addActionListener(e -> actualizarProducto());

        // ELIMINAR: elimina producto seleccionado
        view.btnEliminar.addActionListener(e -> eliminarProducto());

        // BUSCAR: busca producto por clave
        view.btnBuscar.addActionListener(e -> buscarProducto());

        // LIMPIAR: limpia formulario
        view.btnLimpiar.addActionListener(e -> view.limpiarFormulario());

        // Cargar datos al seleccionar fila en la tabla
        view.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = view.tabla.getSelectedRow();
                    if (row >= 0) cargarFormularioDesdeTabla(row);
                }
            }
        });
    }

    private void guardarProducto() {
        try {
            Producto producto = obtenerProductoDesdeFormulario();
            validarProducto(producto);

            // Verificar si ya existe
            if (repo.findByClave(producto.getClave()) != null) {
                view.mostrarError("Ya existe un producto con esta clave. Use Actualizar para modificarlo.");
                return;
            }

            repo.create(producto);
            view.mostrarMensaje("Producto guardado exitosamente!");
            refrescarTabla();
            view.limpiarFormulario();

        } catch (NumberFormatException nfe) {
            view.mostrarError("Precio inválido. Ejemplo: 199.99");
        } catch (Exception ex) {
            view.mostrarError(ex.getMessage());
        }
    }

    private void actualizarProducto() {
        try {
            Producto producto = obtenerProductoDesdeFormulario();
            validarProducto(producto);

            if (repo.findByClave(producto.getClave()) == null) {
                view.mostrarError("No existe un producto con esta clave. Use Guardar para crearlo.");
                return;
            }

            if (view.confirmarAccion("¿Está seguro de actualizar el producto con clave " + producto.getClave() + "?")) {
                repo.update(producto);
                view.mostrarMensaje("Producto actualizado exitosamente!");
                refrescarTabla();
                view.limpiarFormulario();
            }

        } catch (NumberFormatException nfe) {
            view.mostrarError("Precio inválido. Ejemplo: 199.99");
        } catch (Exception ex) {
            view.mostrarError(ex.getMessage());
        }
    }

    private void eliminarProducto() {
        String clave = view.txtClave.getText().trim();

        if (clave.isEmpty()) {
            view.mostrarError("Ingrese una clave para eliminar");
            return;
        }

        Producto producto = repo.findByClave(clave);
        if (producto == null) {
            view.mostrarError("No existe un producto con la clave: " + clave);
            return;
        }

        if (view.confirmarAccion("¿Está seguro de eliminar el producto con clave " + clave + "?")) {
            try {
                repo.deleteByClave(clave);
                view.mostrarMensaje("Producto eliminado exitosamente!");
                refrescarTabla();
                view.limpiarFormulario();
            } catch (Exception ex) {
                view.mostrarError(ex.getMessage());
            }
        }
    }

    private void buscarProducto() {
        String clave = view.txtClave.getText().trim();

        if (clave.isEmpty()) {
            view.mostrarError("Ingrese una clave para buscar");
            return;
        }

        Producto producto = repo.findByClave(clave);
        if (producto == null) {
            view.mostrarError("No se encontró producto con la clave: " + clave);
            return;
        }

        // Cargar datos en el formulario
        view.txtClave.setText(producto.getClave());
        view.txtNombre.setText(producto.getNombre());
        view.txtMarca.setText(producto.getMarca());
        view.txtDescripcion.setText(producto.getDescripcion());
        view.txtPrecio.setText(String.valueOf(producto.getPrecio()));

        view.mostrarMensaje("Producto encontrado: " + producto.getNombre());
    }

    private Producto obtenerProductoDesdeFormulario() {
        String clave = view.txtClave.getText().trim();
        String nombre = view.txtNombre.getText().trim();
        String marca = view.txtMarca.getText().trim();
        String descripcion = view.txtDescripcion.getText().trim();
        double precio = Double.parseDouble(view.txtPrecio.getText().trim());

        return new Producto(clave, nombre, marca, descripcion, precio);
    }

    private void validarProducto(Producto producto) {
        if (producto.getClave().isBlank()) {
            throw new IllegalArgumentException("La clave es requerida");
        }
        if (producto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es requerido");
        }
        if (producto.getMarca().isBlank()) {
            throw new IllegalArgumentException("La marca es requerida");
        }
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
    }

    private void cargarFormularioDesdeTabla(int row) {
        Producto p = view.tableModel.getAt(row);
        view.txtClave.setText(p.getClave());
        view.txtNombre.setText(p.getNombre());
        view.txtMarca.setText(p.getMarca());
        view.txtDescripcion.setText(p.getDescripcion());
        view.txtPrecio.setText(String.valueOf(p.getPrecio()));
    }

    private void refrescarTabla() {
        view.tableModel.setData(repo.findAll());
    }
}