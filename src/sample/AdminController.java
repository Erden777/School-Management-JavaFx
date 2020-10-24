package sample;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;

public class AdminController {

    private Connector connector;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane colorpane;

    @FXML
    private Label nameLabel1;

    @FXML
    private Button btn_addPupil;

    @FXML
    private Button btn_addTeacher;

    @FXML
    private Button btnSignUp;

    @FXML
    private AnchorPane addPupilPane;

    @FXML
    private TextField PupilusernameField;

    @FXML
    private TextField PupilpasswordField;

    @FXML
    private TextField PupilFnameField;

    @FXML
    private TextField PupilLnameField;

    @FXML
    private TextField PupilDobField;

    @FXML
    private TextField PupilCountryField;

    @FXML
    private ComboBox<String> PupilGroupIDBox;

    @FXML
    private AnchorPane addTchrPane;

    @FXML
    private TextField TeacherusernameField;

    @FXML
    private TextField TeacherpasswordField;

    @FXML
    private TextField TeacherFnameField;

    @FXML
    private TextField TeacherLnameField;

    @FXML
    private TextField TeacherDobField;

    @FXML
    private TextField TeacherEcperField;

    @FXML
    private ComboBox<String> TSubject_IDBox;

    @FXML
    private TextField TeacherEmailField;

    private ObservableList<Subject> subjects;
    private ObservableList<Group> groups = FXCollections.observableArrayList();

    public ObservableList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ObservableList<Group> groups) {
        this.groups = groups;
    }


    @FXML
    private Button btn_addGroup;

    @FXML
    private AnchorPane addGrouprPane1;

    @FXML
    private TextField groupname;
    @FXML
    private TableColumn<Group, Integer> tableID;

    @FXML
    private TableColumn<Group, String> TableName;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_save;
    @FXML
    private TableView<Group> TableGroups;

    @FXML
    void initialize() {
        connector = Main.connector;
        btn_addPupil.setOnAction(event->{
            TSubject_IDBox.getItems().clear();

            addTchrPane.setVisible(false);
            addPupilPane.setVisible(true);
            addGrouprPane1.setVisible(false);
            for(Group g : connector.getAllGroupDb()){
                PupilGroupIDBox.getItems().add(g.getName());
            }
            btnSignUp.setOnAction(event1 ->{
                String username = PupilusernameField.getText();
                PupilusernameField.setText("");
                String password = PupilpasswordField.getText();
                PupilpasswordField.setText("");
                String fname = PupilFnameField.getText();
                PupilFnameField.setText("");
                String lname = PupilLnameField.getText();
                PupilLnameField.setText("");
                String dob = PupilDobField.getText();
                PupilDobField.setText("");
                int group_id=0;
                for(Group g : connector.getAllGroupDb()){
                    if(g.getName().equals(PupilGroupIDBox.getValue())){
                        group_id = g.getId();
                    }
                }
                String country = PupilCountryField.getText();
                PupilCountryField.setText("");
                Pupil pupil = connector.registerPupil(username,password,fname,lname,country,dob,group_id);
                if(pupil != null){
                    Main.currentUser = pupil;
                    loadStage("/sample/AdminPage.fxml");
                }
            });
        });

        btn_addGroup.setOnAction(event ->{
            addTchrPane.setVisible(false);
            addPupilPane.setVisible(false);
            addGrouprPane1.setVisible(true);
            TSubject_IDBox.getItems().clear();
            PupilGroupIDBox.getItems().clear();

            btn_save.setOnAction(event1 -> {
                String group_name = groupname.getText();
                groupname.setText("");
                connector.addGroup(group_name);
            });

            btn_update.setOnAction(event2->{
                connector.getAllGroupDb();
                tableID.setCellValueFactory(new PropertyValueFactory<>("id"));
                TableName.setCellValueFactory(new PropertyValueFactory<>("name"));
                TableGroups.setItems(connector.getGroups());
            });

        });
        btn_addTeacher.setOnAction(event -> {
            System.out.println("teaccher btn");

            addTchrPane.setVisible(true);
            addPupilPane.setVisible(false);
            addGrouprPane1.setVisible(false);
            PupilGroupIDBox.getItems().clear();
            System.out.println("Next");
             for (Subject s:connector.getSubjectsDb()){
                 TSubject_IDBox.getItems().add(s.getSubname());
             }
             

            btnSignUp.setOnAction(event1 ->{
                String username = TeacherusernameField.getText();
                TeacherusernameField.setText("");
                String password = TeacherpasswordField.getText();
                TeacherpasswordField.setText("");
                String fname = TeacherFnameField.getText();
                TeacherFnameField.setText("");
                String lname = TeacherLnameField.getText();
                TeacherLnameField.setText("");
                String dob = TeacherDobField.getText();
                TeacherDobField.setText("");
                String email = TeacherEmailField.getText();
                TeacherEmailField.setText("");
                String experience = TeacherEcperField.getText();
                TeacherEcperField.setText("");
                int subj_id =0;
                for (Subject s:connector.getSubjectsDb()){
                    if(TSubject_IDBox.getValue().equals(s.getSubname())){
                        subj_id =s.getId();
                    }
                }

                System.out.println(experience);
                Teacher teacher =connector.registerTeacher(username,password,fname,lname,experience,dob,email,subj_id);
                if(teacher != null){
                    Main.currentUser = teacher;
                }

            });
        });


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
        stage.showAndWait();
    }
}
