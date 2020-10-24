package sample;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;


public class Controller {
    private Connector connector;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private ComboBox<String> optionBox;

    @FXML
    private Label userlabel;

    @FXML
    private Label passworlabel;

    @FXML
    void initialize() {
        connector = Main.connector;
        ObservableList<String> langs = FXCollections.observableArrayList("Admin" , "Teacher");
        optionBox.setItems(langs);

                loginButton.setOnAction(event -> {
                    System.out.println("sign in");
                    String username = nameField.getText();
                String password = passwordField.getText();

                if (optionBox.getValue().equals("Pupils")) {
                    Pupil pupil = connector.PupilLogin(username, password);
                    if (pupil != null) {
                        Main.currentPupil = pupil;
                        loadStage("/sample/PupilPage.fxml");
                    }else {
                        System.out.println("enter again");
                    }

                } else if (optionBox.getValue().equals("Teacher")) {
                    Teacher teacher = connector.TeacherLogin(username, password);
                    if (teacher != null) {
                        System.out.println(username);
                        System.out.println(password);
                        loginButton.getScene().getWindow().hide();
                        Main.currentTeacher = teacher;
                        loadStage("/sample/TeacherPage.fxml");
                    }else {
                        System.out.println("enter again");
                    }

                } else if (optionBox.getValue().equals("Admin")) {
                    System.out.println(username);
                    System.out.println(password);
                    Admin admin = connector.AdminLogin(username, password);
                    if (admin != null) {
                        loginButton.getScene().getWindow().hide();
                        System.out.println(admin.getId());
                        loadStage("/sample/AdminPage.fxml");
                    } else {
                        System.out.println("enter again");
                    }
                }

            });
    }


    @FXML
    private void showMain(MouseEvent mouseEvent) {
        Main.showMainScene();
    }

    private void loadStage(String fxml) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(fxml));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        }
    }


