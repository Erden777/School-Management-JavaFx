package sample;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label nameLabel;

    @FXML
    private Label nameLabel1;

    @FXML
    private Button btndata;

    @FXML
    private Button btnStudents;

    @FXML
    private Button btnsettings;

    @FXML
    private Button btnupdate;


    @FXML
    void initialize() {
        nameLabel.setText(Main.currentTeacher.getFname()+" "+Main.currentTeacher.getLname());

        btndata.setOnAction(event -> {
            //btndata.getScene().getWindow().hide();
            loadStage("/sample/DataPage.fxml");

        });
        btnStudents.setOnAction(event -> {
            loadStage("/sample/StudentsPage.fxml");
        });

        btnsettings.setOnAction(event2 -> {
            btnsettings.getScene().getWindow().hide();
            loadStage("/sample/sample.fxml");

        });
        btnupdate.setOnAction(event -> {
            loadStage("/sample/TimeTable.fxml");
        });

    }

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent){
        if(mouseEvent.getSource() == btndata){
            loadStage("/sample/DataPage.fxml");
        }
        else if(mouseEvent.getSource() == btnStudents){
            loadStage("/sample/StudentsPage.fxml");
        }
        else if(mouseEvent.getSource() == btnupdate ){
            loadStage("/sample/TimeTable.fxml");
        }
        else if(mouseEvent.getSource() == btnsettings){
            new FXMLLoader(getClass().getResource("sample.fxml"));

        }

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
