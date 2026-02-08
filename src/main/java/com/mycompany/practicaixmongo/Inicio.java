package com.mycompany.practicaixmongo;

import com.mycompany.practicaixmongo.DAO.MHDao;
import com.mycompany.practicaixmongo.Model.Monsters;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class Inicio {

    @FXML
    private TableView<Monsters> tabla;

    @FXML
    private TableColumn<Monsters, Integer> id;

    @FXML
    private TableColumn<Monsters, String> nombre;

    @FXML
    private TableColumn<Monsters, String> especie;

    @FXML
    private TableColumn<Monsters, String> tipo;

    @FXML
    private TableColumn<Monsters, String> descrip;

    @FXML
    private TableColumn<Monsters, String> elemento;

    @FXML
    private Button insertar;

    @FXML
    private Button actualizar;

    @FXML
    private Button borrar;

    @FXML
    private Button filtros;

    private MHDao dao;

    private ObservableList<Monsters> monsterList;

    @FXML
    public void initialize() {
        dao = new MHDao();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        especie.setCellValueFactory(new PropertyValueFactory<>("species"));
        tipo.setCellValueFactory(new PropertyValueFactory<>("type"));
        descrip.setCellValueFactory(new PropertyValueFactory<>("description"));
        elemento.setCellValueFactory(cellData -> {
            Monsters montruo = cellData.getValue();
            String elementos = String.join(", ", montruo.getElements() != null ? montruo.getElements() : List.of());
            return new SimpleStringProperty(elementos);
        });
        cargarDatos();
    }

    private void cargarDatos() {
        monsterList = FXCollections.observableArrayList(dao.selectAll());
        tabla.setItems(monsterList);
    }

    @FXML
    protected void insertar() throws IOException {
        Main.setRoot("insertar");
    }

    @FXML
    protected void actualizar() throws IOException {
        Monsters seleccionado = tabla.getSelectionModel().getSelectedItem();
        if (seleccionado == null) return;

        Actualizar.setMonstruoTemporal(seleccionado);
        Main.setRoot("actualizar");
    }

    @FXML
    protected void borrar() {
        Monsters seleccionado = tabla.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            dao.delete(seleccionado.getId());
            monsterList.remove(seleccionado);
        }
    }

    @FXML
    private void apagar() throws IOException{
        System.exit(0);
    }
}
