package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class DataController {

    private Connector connector;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label YourDataLabel;

    @FXML
    private Button btnTeachergroups;

    @FXML
    private AnchorPane ancherPaneviewsgr;

    @FXML
    private TableView<Group> yourgrtable;

    @FXML
    private TableColumn<Group, Integer> youIdtable;

    @FXML
    private TableColumn<Group, String> yournamegrTable;

    @FXML
    private Button BtnDelete;

    @FXML
    private Button dtnTeacherupdate;

    @FXML
    private AnchorPane ancherPaneAllgr;

    @FXML
    private TableView<Group> Tableallgroup;

    @FXML
    private TableColumn<Group, Integer> TableallgroupId;

    @FXML
    private TableColumn<Group, String> TableAllgropsName;

    @FXML
    private Button btnaddgroup;

    @FXML
    private Button btnallgrpane;


    @FXML
    private Button btnAllgroup;

    @FXML
    private Button btnViewpupils;

    @FXML
    private AnchorPane paneviewpupil;

    @FXML
    private TableView<Pupil> tablebtnview;

    @FXML
    private TableColumn<Pupil, Integer> tableidview;

    @FXML
    private TableColumn<Pupil, String> tablenameview;

    @FXML
    private TableColumn<Pupil, String> tablefnameview;

    private ObservableList<Group> gr;
    private ObservableList<Pupil> pup;


    @FXML
    private Button btnaddSchedule;

    @FXML
    private TextField dayTextfield;

    @FXML
    private TextField timeTextfield;

    @FXML
    void initialize() {
        gr = FXCollections.observableArrayList();
        pup = FXCollections.observableArrayList();
        connector = Main.connector;
        btnTeachergroups.setOnAction(event -> {
            gr.clear();
            ancherPaneAllgr.setVisible(false);
            ancherPaneviewsgr.setVisible(true);
            paneviewpupil.setVisible(false);
            youIdtable.setCellValueFactory(new PropertyValueFactory<>("id"));
            yournamegrTable.setCellValueFactory(new PropertyValueFactory<>("name"));

            connector.getAllGroupDb();
            for(TeacherGroup tg : connector.getTeacherGroupDb()){
                    if(Main.currentTeacher.getId()==tg.getTeacher_id()){

                        gr.add(connector.getGroups().get(tg.getGroup_id()-1));
                }
            }
            yourgrtable.setItems(gr);

            BtnDelete.setOnAction(event1 -> {
                connector.DeleteTeacherGroup(Main.currentTeacher.getId() , yourgrtable.getSelectionModel().getSelectedItem().getId());
                gr.clear();
                yourgrtable.getItems().clear();
                for(TeacherGroup tg : connector.getTeacherGroupDb()){
                    if(Main.currentTeacher.getId()==tg.getTeacher_id()){
                        gr.add(connector.getGroups().get(tg.getGroup_id()-1));
                    }
                }
                yourgrtable.setItems(gr);
            });

            dtnTeacherupdate.setOnAction(event1 -> {
                gr.clear();
                connector.getAllGroupDb();
                for(TeacherGroup tg : connector.getTeacherGroupDb()){
                    if(Main.currentTeacher.getId()==tg.getTeacher_id()){
                        gr.add(connector.getGroups().get(tg.getGroup_id()-1));
                    }
                }
                yourgrtable.setItems(gr);
            });
            btnaddSchedule.setOnAction(event1 -> {
                String time = dayTextfield.getText()+" "+timeTextfield.getText();
                System.out.println(time);
                dayTextfield.setText("");
                timeTextfield.setText("");
                connector.addTimetable(Main.currentTeacher.getId() , time , yourgrtable.getSelectionModel().getSelectedItem().getId());
            });

        });

        btnAllgroup.setOnAction(event -> {
            ancherPaneAllgr.setVisible(true);
            ancherPaneviewsgr.setVisible(false);
            paneviewpupil.setVisible(false);
            TableallgroupId.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableAllgropsName.setCellValueFactory(new PropertyValueFactory<>("name"));
            gr.setAll(connector.getAllGroupDb());
            Tableallgroup.setItems(gr);
            btnallgrpane.setOnAction(event1 -> {

                gr.clear();
                TableallgroupId.setCellValueFactory(new PropertyValueFactory<>("id"));
                TableAllgropsName.setCellValueFactory(new PropertyValueFactory<>("name"));
                gr.setAll(connector.getAllGroupDb());
                Tableallgroup.setItems(gr);
            });
            btnaddgroup.setOnAction(event1 -> {
                connector.addTeacherGroup(Main.currentTeacher.getId() ,Tableallgroup.getSelectionModel().getSelectedItem().getId());
            });


        });

        btnViewpupils.setOnAction(event -> {
            paneviewpupil.setVisible(true);
            ancherPaneviewsgr.setVisible(false);
            ancherPaneAllgr.setVisible(false);
            pup.clear();
            tableidview.setCellValueFactory(new PropertyValueFactory<>("id"));
            tablenameview.setCellValueFactory(new PropertyValueFactory<>("lname"));
            tablefnameview.setCellValueFactory(new PropertyValueFactory<>("fname"));
            connector.getPupilDb();
            for(Pupil p : connector.getPupils()){
                if(p.getGroup_id()==yourgrtable.getSelectionModel().getSelectedItem().getId()){
                    pup.add(p);
                }
            }

            tablebtnview.setItems(pup);
        });

    }
}
