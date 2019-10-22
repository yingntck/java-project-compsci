package controllers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import models.Subject;
import database.HomeConnector;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.Optional;

public class HomeController {

    private String year, semester;

    @FXML
    protected TableView<Subject> homeTableView;
    @FXML
    public ComboBox selectYearBtn;
    @FXML
    public ComboBox selectSemesterBtn;
    @FXML
    public Button saveBtn;
    @FXML
    private TableColumn<Subject, String> difficulty;

    public void initialize() {
        setHomeTableView();
        setTableView();
        setFilterBtn();
        setColorDifficulty();
    }

    public void resetBtnHandler(ActionEvent event) {
        selectYearBtn.getSelectionModel().select(null);
        selectSemesterBtn.getSelectionModel().select(null);
        homeTableView.setItems(HomeConnector.getSubject());
        selectSemesterBtn.setDisable(true);
    }

    private void setHomeTableView() {
        homeTableView.setItems(HomeConnector.getSubject());
        homeTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Subject>() {
            @Override
            public void changed(ObservableValue<? extends Subject> observable, Subject oldValue, Subject newValue) {
                if (homeTableView.getSelectionModel().getSelectedItem() != null) {
                }
            }
        });

        selectYearBtn.setOnAction(event -> {
            if (selectYearBtn.getValue() != null) {
                year = selectYearBtn.getValue().toString();
                selectSemesterBtn.setDisable(false);
                selectSemesterBtn.getSelectionModel().select(null);
            }
        });
    }

    //filter
    private void setFilterBtn() {
        selectYearBtn.setItems(FXCollections.observableArrayList("Year 1", "Year 2", "Year 3", "Year 4"));
        selectSemesterBtn.setItems(FXCollections.observableArrayList("Semester 1", "Semester 2"));
        selectSemesterBtn.setDisable(true);
        selectSemesterBtn.setOnAction(event -> {
            if (selectSemesterBtn.getValue() != null) {
                semester = selectSemesterBtn.getValue().toString();
                String[] semesterString = semester.split(" ");
                String[] yearString = year.split(" ");
//                System.out.println(yearString[1] +" POOM "+ semesterString[1]);
                homeTableView.setItems(HomeConnector.getSubjectFilter(yearString[1], semesterString[1]));
            }
        });
    }

    private void setColorDifficulty() {
        difficulty.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDifficulty()));
        difficulty.setCellFactory(column -> {
            return new TableCell<Subject, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item);
                        setTextFill(Color.WHITE);
                        if (item.equalsIgnoreCase("Hard")) {
                            setStyle("-fx-background-color: #c81339");
                        } else if (item.equalsIgnoreCase("Medium")) {
                            setStyle("-fx-background-color: #3557b6");
                        } else if (item.equalsIgnoreCase("Easy")) {
                            setStyle("-fx-background-color: seagreen");
                        }
                    }
                }

            };

        });
    }

    // set status
    private void setTableView() {
        homeTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()) {
                    if (homeTableView.getSelectionModel().getSelectedItem() != null) {
                        if (homeTableView.getSelectionModel().getSelectedItem().getStatus().equals("Pass")) {
                            homeTableView.getSelectionModel().getSelectedItem().setStatus(false);
                            homeTableView.refresh();
                        } else {
                            homeTableView.getSelectionModel().getSelectedItem().setStatus(true);
                            homeTableView.refresh();
                        }
                    }
                }
            }
        });
    }

    // save on DB
    public void saveBtnHandler(ActionEvent event) {
        //popup error
        if (homeTableView.getSelectionModel().getSelectedItem() == null) {
            Alert informationAlert = new Alert(Alert.AlertType.ERROR,"Nothing to save.");
            informationAlert.setTitle("ERROR");
            informationAlert.setHeaderText("");
            informationAlert.showAndWait();
        } else {
            //popup confirm
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save changes?", ButtonType.OK, ButtonType.CANCEL);
            alert.setTitle("Confirm Saving");
            alert.setHeaderText("");
            Optional optional = alert.showAndWait();
            if (optional.get() == ButtonType.OK) {
                //save
                for (int i = 0; i < homeTableView.getItems().size(); i++) {
                    HomeConnector.saveSubject(homeTableView.getItems().get(i).getSubjectId(), homeTableView.getItems().get(i).getStatusInteger());
                }
                //information alert
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION, "Saved");
                informationAlert.setTitle("Saved");
                informationAlert.setHeaderText("");
                informationAlert.showAndWait();
            }
        }
    }
}