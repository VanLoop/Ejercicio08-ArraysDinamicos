package org.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Ventana extends JFrame {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JTextPane textPane1;
    private JTextField textField2;
    private JSlider slider1;
    private JLabel lblEdad;
    private JButton btnGuardar;
    private JButton btnProcesar;
    private JComboBox comboBox1;
    ArrayList<Persona> apers;
    private Persona per;

    public Ventana() {
        super("Cuestionario");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Arreglo dinámico para almacenar los datos de cada persona
        apers = new ArrayList<Persona>();

        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (slider1.getValue() == 1)
                    lblEdad.setText("" + slider1.getValue() + " año");
                else
                    lblEdad.setText("" + slider1.getValue() + " años");
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos(apers);
            }
        });
        btnProcesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarDatos(apers);
            }
        });
    }

    public void guardarDatos(ArrayList<Persona> ap) {
        if (!textField1.getText().equals("") && !textField2.getText().equals("") &&
                !comboBox1.getSelectedItem().equals("")) {
            // Objeto de la clase persona para almacenar todos los datos de una persona
            per = new Persona();
            per.setId(textField1.getText());
            per.setNombre(textField2.getText());
            per.setCiudad(comboBox1.getSelectedItem().toString());
            per.setEdad(slider1.getValue());
            ap.add(per);

            btnProcesar.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Registro almacenado");

            // Limpiar los controles para ingresar nuevos datos
            textField1.setText("");
            textField1.requestFocus();
            textField2.setText("");
            slider1.setValue(1);
            comboBox1.setSelectedIndex(0);
        }
        else
            JOptionPane.showMessageDialog(null, "Debe ingresar todos los datos");
    }

    public void procesarDatos(ArrayList<Persona> ap) {
        int sum = 0;
        // Esta es otra forma de recorrer un ArrayList de personas
        // for (int i = 0; i < ap.size(); i++) {
        //    per2 = ap.get(i);
        //    sum += per2.getEdad();
        // }

        // Crear una lista de ciudades
        List lsCiudades = new ArrayList<String>();

        // Recorrer el ArrayList ap, extrayendo un objeto persona por cada iteración
        for (Persona per2: ap) {
            sum += per2.getEdad();
            // Adicionar la ciudad de cada persona a la lista de ciudades
            lsCiudades.add(per2.getCiudad());
        }

        // Imprime la lista de ciudades
        System.out.println("Lista de ciudades");
        System.out.println(lsCiudades);

        // Crear un conjunto con las ciudades de cada persona
        Set<String> cjCiudades = new HashSet<String>(lsCiudades);

        // Los conjuntos no admiten duplicados
        // Imprime el conjunto de ciudades sin duplicados
        System.out.println("Conjunto de ciudades sin duplicados");
        System.out.println(cjCiudades);

        // Crear una lista ciudades sin duplicados a partir del conjunto de ciudades para poder ordenarlas
        List<String> lsCiudadesSinDuplicados = new ArrayList<>(cjCiudades);
        // Ordenar la lista de ciudades sin duplicados
        Collections.sort(lsCiudadesSinDuplicados);

        String c = "";
        // Recorrer la lista de ciudades sin duplicados
        System.out.println("Frecuencia con la que se repiten las ciudades que estan en el lista de ciudades sin duplicados");
        for(String ciudad: lsCiudadesSinDuplicados) {
            // Imprime las ciudades con la cantidad de personas
            // Se obtiene la frecuencia con la que se repiten las ciudades que estan en la lista de ciudades sin duplicados
            System.out.println(ciudad + ": " + Collections.frequency(lsCiudades, ciudad));
            // Concatenar las ciudades con la frecuencia de repetición
            c += ciudad + ": " + Collections.frequency(lsCiudades, ciudad) + "\n";
        }
        textPane1.setText(
                "RESULTADOS" +
                "\nNo. de encuestados: " + ap.size() +
                "\nPromedio de edades: " + (sum / ap.size()) +
                "\nPersonas por municipio:\n" + c
        );

        tabbedPane1.setSelectedIndex(1);
        textPane1.requestFocus();
    }
}
