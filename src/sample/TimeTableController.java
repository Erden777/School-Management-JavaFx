package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class TimeTableController {
    private Connector connector;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label YourDataLabel;

    @FXML
    private TableView<RelationManager> TableI;

    @FXML
    private TableColumn<RelationManager, Integer> Tablename;

    @FXML
    private TableColumn<RelationManager, String> TableTime;

    @FXML
    private TableColumn<RelationManager, String> TableIGroup;

    @FXML
    private Button btnUpdate;

    private ObservableList<RelationManager> relationManager;

    @FXML
    void initialize() {
        relationManager = FXCollections.observableArrayList();
        connector = Main.connector;
        Tablename.setCellValueFactory(new PropertyValueFactory<>("teacher_name"));
        TableTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        TableIGroup.setCellValueFactory(new PropertyValueFactory<>("group_name"));

        btnUpdate.setOnAction(event -> {
            connector.getAllGroupDb();
            connector.getAllTimeTableDb();
            for(Schedule s : connector.getAllTimeTableDb()){
                if(Main.currentTeacher.getId()==s.getTeacher_id()){
                    for(Group g : connector.getGroups()) {
                        if(g.getId() == s.getGroup_id()) {
                            relationManager.add(new RelationManager(Main.currentTeacher.getFname(), s.getTime(),g.getName()));
                        }
                    }
                }
            }

            TableI.setItems(relationManager);
        });
    }
}
