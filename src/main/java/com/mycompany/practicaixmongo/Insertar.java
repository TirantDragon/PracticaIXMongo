package com.mycompany.practicaixmongo;

import com.mycompany.practicaixmongo.DAO.MHDao;
import com.mycompany.practicaixmongo.Model.Monsters;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Insertar {

    @FXML private TextField nombre;
    @FXML private TextField especie;
    @FXML private TextField tipo;
    @FXML private TextArea descripcion;

    @FXML private CheckBox agua;
    @FXML private CheckBox fuego;
    @FXML private CheckBox rayo;
    @FXML private CheckBox hielo;
    @FXML private CheckBox dragon;

    private MHDao dao = new MHDao();

    @FXML
    private void guardar() {

        try {

            Monsters monstruo = new Monsters();

            monstruo.setName(nombre.getText());
            monstruo.setSpecies(especie.getText());
            monstruo.setType(tipo.getText());
            monstruo.setDescription(descripcion.getText());

            List<String> elementos = new ArrayList<>();

            if (agua.isSelected()) elementos.add("water");
            if (fuego.isSelected()) elementos.add("fire");
            if (rayo.isSelected()) elementos.add("thunder");
            if (hielo.isSelected()) elementos.add("ice");
            if (dragon.isSelected()) elementos.add("dragon");

            monstruo.setElements(elementos);

            dao.insert(monstruo);

            System.out.println("Monstruo insertado correctamente");

            limpiarFormulario();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void limpiarFormulario() {
        nombre.clear();
        especie.clear();
        tipo.clear();
        descripcion.clear();
        agua.setSelected(false);
        fuego.setSelected(false);
        rayo.setSelected(false);
        hielo.setSelected(false);
        dragon.setSelected(false);
    }

    @FXML
    private void volver() throws IOException {
        Main.setRoot("inicio");
    }
}
