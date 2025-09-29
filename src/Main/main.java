package main;

import view.ProductoView;
import controller.ProductoController;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductoRepositorioArreglo repo = new ProductoRepositorioArreglo();
            ProductoView view = new ProductoView();
            new ProductoController(repo, view);
            view.setVisible(true);
        });
    }
}

