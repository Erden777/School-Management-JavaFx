package sample;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main extends Application {
    public static Connector connector;
    public static Human currentUser=null;
    public static Pupil currentPupil=null;
    public static Teacher currentTeacher=null;
    private static Stage stage;
    private static Scene mainScene;

    public Main(){}

    public static void showMainScene() {
        stage.setScene(mainScene);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        mainScene = new Scene(root);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(windowEvent -> connector.close());
    }


    public static void main(String[] args) {
        connector = new Connector();
        launch(args);
    }
}
