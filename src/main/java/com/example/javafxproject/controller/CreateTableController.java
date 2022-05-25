package com.example.javafxproject.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import com.example.javafxproject.DatabaseConnection;
import com.example.javafxproject.model.SpreadsheetModel;
import com.example.javafxproject.model.TypeAttr;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;


public class CreateTableController implements Initializable {

    private boolean flagEdit = false;
    private  String SAVE_SQL_QUERY = "";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backScreen;

    @FXML
    private Button btnMinus;

    @FXML
    private Button btnPlus;

    @FXML
    private Button btnEdit;

    @FXML
    private TableColumn<SpreadsheetModel, String> nameAttribute;

    @FXML
    private TextField nameTable;

    @FXML
    private TableColumn<SpreadsheetModel, Boolean> primaryKeyAttribute;

    @FXML
    private Button saveTable;

    @FXML
    private Label validateSaveOperation;

    @FXML
    private TableView<SpreadsheetModel> spreadsheet;

    @FXML
    private TableColumn<SpreadsheetModel, TypeAttr> typeAttribute;

    @FXML
    private TableColumn<SpreadsheetModel, Boolean> uniqueAttribute;

    @FXML
    private TableColumn<SpreadsheetModel, String> valueAttribute;

    @FXML
    void onBackScreen(ActionEvent event) {

    }

    @FXML
    void onBtnMinus(ActionEvent event) {

    }

    @FXML
    void onBtnPlus(ActionEvent event) {

    }

    @FXML
    void onNameTable(ActionEvent event) {

    }

    @FXML
    void onSaveTable(ActionEvent event) {    }

    @FXML
    void onBtnEdit(ActionEvent event){}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //таблица доступна для редактирования
        spreadsheet.setEditable(true);
        //устанавливаем инициализацию полей (нужно для добавления новой строки в таблицу)
        nameAttribute.setCellValueFactory(new PropertyValueFactory<SpreadsheetModel, String>("AttributeName"));
//        typeAttribute.setCellValueFactory(new PropertyValueFactory<SpreadsheetModel, TypeAttr>("TypeAttribute"));
        valueAttribute.setCellValueFactory(new PropertyValueFactory<SpreadsheetModel, String>("defaultValue"));

        //для инициализации чекбоксов
        uniqueAttribute.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SpreadsheetModel, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SpreadsheetModel, Boolean> tableModelBooleanCellDataFeatures) {
                SpreadsheetModel table = tableModelBooleanCellDataFeatures.getValue();
                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(table.getUniqueAttribute());
                booleanProperty.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                        table.setUniqueAttribute(t1);
                    }
                });
                return booleanProperty;
            }
        });
        primaryKeyAttribute.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SpreadsheetModel, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SpreadsheetModel, Boolean> tableModelBooleanCellDataFeatures) {
                SpreadsheetModel table = tableModelBooleanCellDataFeatures.getValue();
                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(table.getUniqueAttribute());
                booleanProperty.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                        table.setUniqueAttribute(t1);
                    }
                });
                return booleanProperty;
            }
        });

        //инициализация выпадающего списка
        ObservableList<TypeAttr> typeAttrs = FXCollections.observableArrayList(
                TypeAttr.values());
        typeAttribute.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SpreadsheetModel, TypeAttr>, ObservableValue<TypeAttr>>() {
            @Override
            public ObservableValue<TypeAttr> call(TableColumn.CellDataFeatures<SpreadsheetModel, TypeAttr> spreadsheetModelStringCellDataFeatures) {
                SpreadsheetModel spreadsheetModel = spreadsheetModelStringCellDataFeatures.getValue();
                String typeCode = spreadsheetModel.getTypeAttribute();
                TypeAttr typeAttr = TypeAttr.getByCode(typeCode);
                return new SimpleObjectProperty<TypeAttr>(typeAttr);
            }
        });


        //необходимо для возможности редактирования таблицы каждой ячейки
        nameAttribute.setCellFactory(TextFieldTableCell.forTableColumn());
        uniqueAttribute.setCellFactory(CheckBoxTableCell.forTableColumn(uniqueAttribute));
        primaryKeyAttribute.setCellFactory(CheckBoxTableCell.forTableColumn(primaryKeyAttribute));
        valueAttribute.setCellFactory(TextFieldTableCell.forTableColumn());
        typeAttribute.setCellFactory(ComboBoxTableCell.forTableColumn(typeAttrs));

        typeAttribute.setOnEditCommit((TableColumn.CellEditEvent<SpreadsheetModel, TypeAttr> event) -> {
            TablePosition<SpreadsheetModel, TypeAttr> pos = event.getTablePosition();
            TypeAttr newType = event.getNewValue();
            int row = pos.getRow();
            SpreadsheetModel table = event.getTableView().getItems().get(row);
            table.setTypeAttribute(newType.getCode());
        });

        //события кнопки добавления новой строки в таблицу
        btnPlus.setOnAction(actionEvent -> {
            ObservableList<SpreadsheetModel> row = FXCollections.observableArrayList(
                    new SpreadsheetModel("", "", false, false, "")
            );
            spreadsheet.getItems().addAll(row);
//            spreadsheet.setItems(row);
            spreadsheet.setEditable(true);
        });

        //события кнопки удаления строки из таблицы
        btnMinus.setOnAction(actionEvent -> {

        });

        //события кнопки редактирования таблицы
        btnEdit.setOnAction(actionEvent -> {
            if (flagEdit == false) {
                spreadsheet.setEditable(true);
                flagEdit = true;
            } else {
                spreadsheet.setEditable(false);
                flagEdit = false;
            }
            spreadsheet.setVisible(false);
            spreadsheet.setVisible(true);
        });

        //вернуться в меню
        backScreen.setOnAction(actionEvent -> {
            backScreen.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/javafxproject/generalScreen.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        //сохранить таблицу в Базу данных
        saveTable.setOnAction(actionEvent -> {
            String primaryKeyValue = "";
            TypeAttr types = null;
            String typeCode;
            String typ = null;

            SAVE_SQL_QUERY = "CREATE TABLE IF NOT EXISTS public.\"" +
                    nameTable.getText() + "\"( ";
            for (int i = 0; i < spreadsheet.getItems().size(); i++){
                SpreadsheetModel table = spreadsheet.getItems().get(i);
                typeCode = table.getTypeAttribute();
                TypeAttr typeAttr = TypeAttr.getByCode(typeCode);
                System.out.println(typeAttr);
                if (i == 0) {

                    System.out.println( table.getUniqueAttribute() + " " + table.getPrimaryKey());
                    SAVE_SQL_QUERY = SAVE_SQL_QUERY +
                            "\"" + table.getAttributeName() +
                            "\" " + typeAttr + " "
                            + "NOT NULL,";
                    primaryKeyValue = table.getAttributeName();
                }
                else if (i == spreadsheet.getItems().size() - 1){
                    System.out.println( table.getUniqueAttribute() + " " + table.getPrimaryKey());
                    SAVE_SQL_QUERY = SAVE_SQL_QUERY +
                            "\"" + table.getAttributeName() +
                            "\" " + typeAttr;
                }
                else {
                    System.out.println( table.getUniqueAttribute() + " " + table.getPrimaryKey());
                    SAVE_SQL_QUERY = SAVE_SQL_QUERY + table.getAttributeName() + " " + typeAttr + ",";
                }
            }
            SAVE_SQL_QUERY = SAVE_SQL_QUERY + ", CONSTRAINT \"" + nameTable.getText() +
                    "_pkey\" PRIMARY KEY (\"" + primaryKeyValue +"\"));";
            System.out.println(SAVE_SQL_QUERY);
            if (SAVE_SQL_QUERY != "" && nameTable.getText() != "") {
                DatabaseConnection connectionDBSaveNewTable = new DatabaseConnection();
                validateSaveOperation.setText("query zavis");
                try {
                    connectionDBSaveNewTable.setCreateTable(SAVE_SQL_QUERY);
                    validateSaveOperation.setText("Complete!");
                } catch (SQLException e) {
                    validateSaveOperation.setText("ERRRRRoR: " + e.toString());
                    e.printStackTrace();
                }
            }
            else
                System.out.println("Bad query(");
        });
    }

    //методы для редактирования ячеек
    public void changesNameAttributeCellEvent(TableColumn.CellEditEvent editEvent){
        SpreadsheetModel tableSelected = spreadsheet.getSelectionModel().getSelectedItem();
        tableSelected.setAttributeName(editEvent.getNewValue().toString());
    }

    public void changesTypeAttributeCellEvent(TableColumn.CellEditEvent editEvent){
        SpreadsheetModel tableSelected = spreadsheet.getSelectionModel().getSelectedItem();
        tableSelected.setTypeAttribute(editEvent.getNewValue().toString());
    }

    public void changesPrimaryAttributeCellEvent(TableColumn.CellEditEvent editEvent){
        SpreadsheetModel tableSelected = spreadsheet.getSelectionModel().getSelectedItem();
        tableSelected.setPrimaryKey((Boolean) editEvent.getNewValue());
    }

    public void changesUniqueAttributeCellEvent(TableColumn.CellEditEvent editEvent){
        SpreadsheetModel tableSelected = spreadsheet.getSelectionModel().getSelectedItem();
        tableSelected.setUniqueAttribute((Boolean)(editEvent.getNewValue()));
    }

    public void changesDefaultAttributeCellEvent(TableColumn.CellEditEvent editEvent){
        SpreadsheetModel tableSelected = spreadsheet.getSelectionModel().getSelectedItem();
        tableSelected.setDefaultValue(editEvent.getNewValue().toString());
    }
}
