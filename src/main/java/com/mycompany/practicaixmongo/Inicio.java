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
            Monsters m = cellData.getValue();
            String elementos = String.join(", ", m.getElements() != null ? m.getElements() : List.of());
            return new SimpleStringProperty(elementos);
        });
        cargarDatos();
    }

    private void cargarDatos() {
        monsterList = FXCollections.observableArrayList(dao.selectAll());
        tabla.setItems(monsterList);
    }

    @FXML
    protected void insertar() {
        System.out.println("Insertar pulsado");
        // Aquí pones la lógica para abrir ventana insertar o lo que sea
    }

    @FXML
    protected void actualizar() {
        System.out.println("Actualizar pulsado");
        // Aquí pones la lógica para actualizar el registro seleccionado
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
    protected void filtros() {
        System.out.println("Filtros pulsado");
        // Aquí pones la lógica para abrir filtro o lo que quieras
    }
}
