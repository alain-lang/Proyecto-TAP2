package view;

import javax.swing.*;
import java.awt.*;

public class ProductoView extends JFrame {
    // Campos del formulario
    public JTextField txtClave = new JTextField(15);
    public JTextField txtNombre = new JTextField(20);
    public JTextField txtMarca = new JTextField(20);
    public JTextField txtPrecio = new JTextField(10);
    public JTextArea txtDescripcion = new JTextArea(3, 20);

    // Botones principales
    public JButton btnGuardar = new JButton("Guardar");
    public JButton btnLimpiar = new JButton("Limpiar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnBuscar = new JButton("Buscar");

    // Tabla
    public JTable tabla = new JTable();
    public ProductoTableModel tableModel;

    public ProductoView() {
        super("Sistema de Gestión de Productos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        // Crear menú
        crearMenu();

        // Crear interfaz principal
        crearInterfaz();
    }

    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();

        // Menú principal único
        JMenu menuPrincipal = new JMenu("Operaciones");

        // Items del menú
        JMenuItem itemNuevo = new JMenuItem("Nuevo Producto");
        JMenuItem itemGuardar = new JMenuItem("Guardar");
        JMenuItem itemActualizar = new JMenuItem("Actualizar");
        JMenuItem itemEliminar = new JMenuItem("Eliminar");
        JMenuItem itemBuscar = new JMenuItem("Buscar");
        JMenuItem itemSalir = new JMenuItem("Salir");

        // Acciones de los items del menú
        itemNuevo.addActionListener(e -> limpiarFormulario());
        itemGuardar.addActionListener(e -> btnGuardar.doClick());
        itemActualizar.addActionListener(e -> btnActualizar.doClick());
        itemEliminar.addActionListener(e -> btnEliminar.doClick());
        itemBuscar.addActionListener(e -> btnBuscar.doClick());
        itemSalir.addActionListener(e -> System.exit(0));

        // Agregar items al menú
        menuPrincipal.add(itemNuevo);
        menuPrincipal.addSeparator();
        menuPrincipal.add(itemGuardar);
        menuPrincipal.add(itemActualizar);
        menuPrincipal.add(itemEliminar);
        menuPrincipal.add(itemBuscar);
        menuPrincipal.addSeparator();
        menuPrincipal.add(itemSalir);

        // Agregar menú a la barra
        menuBar.add(menuPrincipal);

        setJMenuBar(menuBar);
    }

    private void crearInterfaz() {
        // Panel principal con pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Pestaña 1: Formulario
        tabbedPane.addTab("Registro de Productos", crearPanelFormulario());

        // Pestaña 2: Lista de Productos
        tabbedPane.addTab("Lista de Productos", crearPanelTabla());

        add(tabbedPane);
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel del formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Datos del Producto"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Clave
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Clave:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtClave, gbc);

        // Fila 1: Nombre
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtNombre, gbc);

        // Fila 2: Marca
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Marca:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtMarca, gbc);

        // Fila 3: Precio
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Precio:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtPrecio, gbc);

        // Fila 4: Descripción
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        txtDescripcion.setLineWrap(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        formPanel.add(scrollDesc, gbc);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnBuscar);
        buttonPanel.add(btnLimpiar);

        // Estilos de botones
        btnGuardar.setBackground(new Color(34, 139, 34));
        btnGuardar.setForeground(Color.WHITE);
        btnActualizar.setBackground(new Color(30, 144, 255));
        btnActualizar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(220, 20, 60));
        btnEliminar.setForeground(Color.WHITE);
        btnBuscar.setBackground(new Color(255, 140, 0));
        btnBuscar.setForeground(Color.WHITE);

        // Agregar componentes al panel principal
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Configurar tabla
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setRowHeight(25);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Productos Registrados"));

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public void limpiarFormulario() {
        txtClave.setText("");
        txtNombre.setText("");
        txtMarca.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtClave.requestFocus();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public boolean confirmarAccion(String mensaje) {
        int respuesta = JOptionPane.showConfirmDialog(this, mensaje, "Confirmar",
                JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }
}
