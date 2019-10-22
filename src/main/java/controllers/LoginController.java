package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    protected Button loginBtn;
    @FXML
    protected TextField idText, nameText, yearText, semesterText;
    @FXML
    private AnchorPane rootPane;
    @FXML
    protected Button startBtn;

    public void startHandlerBtn(ActionEvent event) throws Exception {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene(loader.load(),900, 650));
        stage.show();
    }

}
//
//    @FXML
//    public void loginBtnHandler(ActionEvent event) throws IOException {
//        if(idText.getText().length() == 10){
////            int year = Integer.parseInt(yearText.getText());
////            int semester = Integer.parseInt(semesterText.getText());
////            if(year >= 1 && year <= 4 && semester >=1 && semester <= 2){
////                student = new Student(idText.getText(),nameText.getText(),year,semester);
////            }
//            Button button = (Button) event.getSource();
//            Stage stage = (Stage) button.getScene().getWindow();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
//            stage.setScene(new Scene(loader.load(),800, 650));
//            stage.show();
//        }
//        // save in database and link to home.fxml
//    }
//
//}
