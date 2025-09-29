package view;

import model.Producto;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProductoTableModel extends AbstractTableModel {
    private final String[] cols = {"Clave","Nombre","Marca","Descripción","Precio"};
    private List<Producto> rows;

    public ProductoTableModel(List<Producto> data) {
        this.rows = data;
    }

    public void setData(List<Producto> data) {
        this.rows = data;
        fireTableDataChanged();
    }

    public Producto getAt(int row) { return rows.get(row); }

    @Override public int getRowCount() { return rows == null ? 0 : rows.size(); }
    @Override public int getColumnCount() { return cols.length; }
    @Override public String getColumnName(int column) { return cols[column]; }

    @Override public Object getValueAt(int rowIndex, int columnIndex) {
        Producto p = rows.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getClave();
            case 1 -> p.getNombre();
            case 2 -> p.getMarca();
            case 3 -> p.getDescripcion();
            case 4 -> p.getPrecio();
            default -> "";
        };
    }

    @Override public Class<?> getColumnClass(int columnIndex) {
        return columnIndex == 4 ? Double.class : String.class;
    }
}

