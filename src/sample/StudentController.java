package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class StudentController {
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
    private TextField searchField;

    @FXML
    private Button btnsearch;

    @FXML
    private ComboBox<String> searchbox;

    @FXML
    private TableView<Pupil> TableI;

    @FXML
    private TableColumn<Pupil, Integer> TableID;

    @FXML
    private TableColumn<Pupil, String> TableIUsername;

    @FXML
    private TableColumn<Pupil, String> TableISurname;

    @FXML
    private TableColumn<Pupil, String> TableIName;

    @FXML
    private TableColumn<Pupil, Integer> TableIGroup;

    @FXML
    private TableColumn<Pupil, String> TableICountry;

    @FXML
    private TableColumn<Pupil, String> TableIDob;
    @FXML
    private Button btnAll;

    private ObservableList<Pupil> pp ;

    @FXML
    private Button btnstudents;

    @FXML
    void initialize() {
        pp =FXCollections.observableArrayList();
        connector = Main.connector;
        ObservableList<String> langs = FXCollections.observableArrayList("Name" , "Username" , "Country");
        searchbox.setItems(langs);

        TableID.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableIName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        TableISurname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        TableIUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableIGroup.setCellValueFactory(new PropertyValueFactory<>("group_id"));
        TableIDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        TableICountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        btnAll.setOnAction(event -> {
            connector.getPupilDb();
            TableI.setItems(connector.getPupils());
        });
        btnsearch.setOnAction(event -> {
            TableI.getItems().removeAll();
            pp.clear();
            String search = searchField.getText();
            searchField.setText("");
            if(searchbox.getValue().equals("Name")){
                for(Pupil p : connector.getPupilDb()){
                    if(p.getLname().equals(search)){
                        pp.add(p);
                    }
                }
                TableI.setItems(pp);
            }

            else if(searchbox.getValue().equals("Username")){
                for(Pupil p : connector.getPupilDb()){
                    if(p.getUsername().equals(search)){
                        pp.add(p);
                    }
                }
                TableI.setItems(pp);
            }

            else if(searchbox.getValue().equals("Country")){
                for(Pupil p : connector.getPupilDb()){
                    if(p.getCountry().equals(search)){
                        pp.add(p);
                    }
                }
                TableI.setItems(pp);
            }

        });
        btnstudents.setOnAction(event -> {
            pp.clear();
            for(Pupil p : connector.getPupils()){
                for(TeacherGroup tg : connector.getTeacherGroupDb()){
                        if(p.getGroup_id()==tg.getGroup_id()){
                            if(tg.getTeacher_id() == Main.currentTeacher.getId()){
                                pp.add(p);
                            }
                        }
                }
            }
            TableI.setItems(pp);
        });

    }
}
