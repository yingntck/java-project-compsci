import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

//        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
//        primaryStage.setTitle("Register");
//        primaryStage.setScene(new Scene(root, 900, 650));
//        primaryStage.show();
//        primaryStage.setResizable(false);

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("KU Register");
        primaryStage.setScene(new Scene(root, 900, 650));
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
