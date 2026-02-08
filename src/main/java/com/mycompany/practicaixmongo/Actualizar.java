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

public class Actualizar {

    private static Monsters monstruoTemporal;

    public static void setMonstruoTemporal(Monsters monstruo){
        monstruoTemporal = monstruo;
    }

    private Monsters monstruoActual;

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
    public void initialize() {
        if(monstruoTemporal != null){
            cargarMonstruo(monstruoTemporal);
        }
    }

    public void cargarMonstruo(Monsters monstruo) {
        monstruoActual = monstruo;

        nombre.setText(monstruo.getName());
        especie.setText(monstruo.getSpecies());
        tipo.setText(monstruo.getType());
        descripcion.setText(monstruo.getDescription());

        if (monstruo.getElements() != null) {
            agua.setSelected(monstruo.getElements().contains("Agua"));
            fuego.setSelected(monstruo.getElements().contains("Fuego"));
            rayo.setSelected(monstruo.getElements().contains("Rayo"));
            hielo.setSelected(monstruo.getElements().contains("Hielo"));
            dragon.setSelected(monstruo.getElements().contains("Dragon"));
        }
    }

    @FXML
    private void guardar() throws IOException {

        monstruoActual.setName(nombre.getText());
        monstruoActual.setSpecies(especie.getText());
        monstruoActual.setType(tipo.getText());
        monstruoActual.setDescription(descripcion.getText());

        List<String> elementos = new ArrayList<>();

        if (agua.isSelected()) elementos.add("Agua");
        if (fuego.isSelected()) elementos.add("Fuego");
        if (rayo.isSelected()) elementos.add("Rayo");
        if (hielo.isSelected()) elementos.add("Hielo");
        if (dragon.isSelected()) elementos.add("Dragon");

        monstruoActual.setElements(elementos);

        dao.update(monstruoActual);

        Main.setRoot("inicio");
    }

    @FXML
    private void volver() throws IOException {
        Main.setRoot("inicio");
    }
}
