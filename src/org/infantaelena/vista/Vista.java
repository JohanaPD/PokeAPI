package org.infantaelena.vista;

import org.infantaelena.modelo.entidades.TipoPokemon;

import javax.swing.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


/**
 * Clase que representa la vista de la aplicación
 * Implementar con menus de texto o con interfaz gráfica
 * @author
 * @version 1.0
 * @date 24/04/2023
 *
 */
public class Vista extends JFrame {
    private JPanel contenedor;
    private JPanel principal;
    private JScrollBar scrollBar1;
    private JButton buscarButton;
    private JButton anadirButton;
    private JButton mostrarTodosButton;
    private JButton editarButton;
    private JButton borrarButton;
    private JTextArea mostrarContenido;
    private JTextField nombre;
    private JComboBox comboTipo;
    private JTextField salud;
    private JTextField defensa;
    private JTextField velocidad;


    public Vista() {
        super("pokemon");
        // principal = new JPanel();
        setContentPane(principal);
        setSize(600, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

       List<TipoPokemon> tipos = new ArrayList<>(EnumSet.allOf(TipoPokemon.class));
        for (TipoPokemon tipo : tipos) {
            comboTipo.addItem(tipo);
        }
    }

    public JPanel getContenedor() {
        return contenedor;
    }

    public void setContenedor(JPanel contenedor) {
        this.contenedor = contenedor;
    }

    public JPanel getPrincipal() {
        return principal;
    }

    public void setPrincipal(JPanel principal) {
        this.principal = principal;
    }

    public JScrollBar getScrollBar1() {
        return scrollBar1;
    }

    public void setScrollBar1(JScrollBar scrollBar1) {
        this.scrollBar1 = scrollBar1;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public void setBuscarButton(JButton buscarButton) {
        this.buscarButton = buscarButton;
    }

    public JButton getAnadirButton() {
        return anadirButton;
    }

    public void setAnadirButton(JButton anadirButton) {
        this.anadirButton = anadirButton;
    }

    public JButton getMostrarTodosButton() {
        return mostrarTodosButton;
    }

    public void setMostrarTodosButton(JButton mostrarTodosButton) {
        this.mostrarTodosButton = mostrarTodosButton;
    }

    public JButton getEditarButton() {
        return editarButton;
    }

    public void setEditarButton(JButton editarButton) {
        this.editarButton = editarButton;
    }

    public JButton getBorrarButton() {
        return borrarButton;
    }

    public void setBorrarButton(JButton borrarButton) {
        this.borrarButton = borrarButton;
    }

    public JTextArea getMostrarContenido() {
        return mostrarContenido;
    }

    public void setMostrarContenido(JTextArea mostrarContenido) {
        this.mostrarContenido = mostrarContenido;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public void setNombre(JTextField nombre) {
        this.nombre = nombre;
    }

    public JComboBox getComboTipo() {
        return comboTipo;
    }

    public void setComboTipo(JComboBox comboTipo) {
        this.comboTipo = comboTipo;
    }

    public JTextField getSalud() {
        return salud;
    }

    public void setSalud(JTextField salud) {
        this.salud = salud;
    }

    public JTextField getDefensa() {
        return defensa;
    }

    public void setDefensa(JTextField defensa) {
        this.defensa = defensa;
    }

    public JTextField getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(JTextField velocidad) {
        this.velocidad = velocidad;
    }
}
