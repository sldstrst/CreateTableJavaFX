package com.example.javafxproject;

import com.example.javafxproject.model.SpreadsheetModel;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

public class CheckBoxCellFactory implements Callback {
    @Override
    public TableCell call(Object param) {
        CheckBoxTableCell<SpreadsheetModel,Boolean> checkBoxCell = new CheckBoxTableCell();
        return checkBoxCell;
    }
}