package sample;
import com.sun.org.apache.xerces.internal.impl.dtd.DTDGrammarBucket;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class Controller implements Initializable {

    @FXML
    private TableView<Student> table;

    @FXML
    private TableColumn<DataModel, Integer> idColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<DataModel, Integer> ageColumn;

    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TableColumn<DataModel, Double> toanColumn;
    @FXML
    private TableColumn<DataModel, Double> vanColumn;
    @FXML
    private  TableColumn<DataModel,Double> dtbColumn;
    @FXML
    private ObservableList<Student> studentList;
    @FXML
    private TextField idText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField ageText;
    @FXML
    private TextField toanText;
    @FXML
    private  TextField vanText;
    @FXML
    private TextField filterField;
    @FXML
    private MenuBar fileMenu;


    //    ObservableList<Student> observableStudentList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        studentList = FXCollections.observableArrayList(
                    new Student(4, "Chau", "chau@gmail.com", 21,7.5,6 ,0),
                new Student(3, "Chuong", "chuong@gmail.com", 20,10,5,0)
        );
        idColumn.setCellValueFactory(new PropertyValueFactory<DataModel, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<DataModel,Integer>("age"));
        toanColumn.setCellValueFactory(new PropertyValueFactory<DataModel,Double>("toan"));
        vanColumn.setCellValueFactory(new PropertyValueFactory<DataModel,Double>("van"));
        dtbColumn.setCellValueFactory(new PropertyValueFactory<DataModel,Double>("DTB"));
        table.setItems(studentList);
//        // h??m ??? d?????i d??ng ????? s???a t??n tr??n b???ng table wiew
//        table.setEditable(true);
//        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        /// h??m search
        FilteredList<Student> filteredData = new FilteredList<>(studentList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newvaluee) -> {
            filteredData.setPredicate(student -> {
                if (newvaluee == null || newvaluee.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newvaluee.toLowerCase();

                if (String.valueOf(student.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                }
                 else if (student.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                  else if (student.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1)
                {
                    return true;
                }
                 else if (String.valueOf(student.getAge()).indexOf(lowerCaseFilter) != -1)
                {
                    return true;
                }
                else
                    return false; // Does not match.
            });
        });

        // 3. B???c danh s??ch FilteredList trong SortedList.

        SortedList<Student> sortedData = new SortedList<>(filteredData);


// 4. Li??n k???t b??? so s??nh SortedList v???i b??? so s??nh TableView.
        // N???u kh??ng, vi???c s???p x???p TableView s??? kh??ng c?? hi???u l???c.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Th??m d??? li???u ???? s???p x???p (v?? ???????c l???c) v??o b???ng.

        table.setItems(sortedData);
        SetCellValueFromTableToTextFiled();

    }
    // th??m ch???c n??ng th??m h???c sinh
    public void ADD() {
        try {
        Student newStudent = new Student();
        newStudent.setId(parseInt(idText.getText()));
        newStudent.setName(nameText.getText());
        newStudent.setEmail(emailText.getText());
        newStudent.setAge(parseInt(ageText.getText()));
        newStudent.setToan(Double.parseDouble(toanText.getText()));
        newStudent.setVan(Double.parseDouble(vanText.getText()));
        newStudent.setDTB((Double.parseDouble(toanText.getText())+Double.parseDouble(vanText.getText()))/2);
        studentList.add(newStudent);
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Th??ng B??o");
            alert.setHeaderText("B???n ph???i th??m sinh vi??n");
            alert.showAndWait();
        }

    }


    // xo?? 1 th???ng ???? ch???n
    public void Delete(ActionEvent e) {
        try {
            Student selection = table.getSelectionModel().getSelectedItem();
            studentList.remove(selection);
        }
        catch (Exception evt)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Th??ng B??o");
            alert.setHeaderText("B???n ph???i th??m sinh vi??n");
            alert.showAndWait();
        }
    }

//    // edit 1 th???ng ???? ch???n
//    public void changename(TableColumn.CellEditEvent<Student,String> edit) {
//        Student selection = table.getSelectionModel().getSelectedItem();
//        selection.setName(edit.getNewValue());
//
//    }
//    public void changeemail(TableColumn.CellEditEvent<Student,String> edit) {
//        Student selection = table.getSelectionModel().getSelectedItem();
//        selection.setEmail(edit.getNewValue());
//
//    }
//    public void changeid(TableColumn.CellEditEvent<Student,String> edit) {
//        Student selection = table.getSelectionModel().getSelectedItem();
//        selection.setId(Integer.parseInt(edit.getNewValue()));
//
//    }
//    public void changeage(TableColumn.CellEditEvent<Student,String> edit) {
//        Student selection = table.getSelectionModel().getSelectedItem();
//        selection.setAge(Integer.parseInt(edit.getNewValue()));
//
//    }
    // Hu??? th??ng tin nh???p
    public void Clean(ActionEvent e) {
        idText.setText("");
        nameText.setText("");
        emailText.setText("");
        ageText.setText("");
        vanText.setText("");
        toanText.setText("");
    }

    // s???a  th??ng tin
    public void Edit() {
        try {
            Student EditStudent = table.getSelectionModel().getSelectedItem();
            nameText.setText(EditStudent.getName());
            emailText.setText(EditStudent.getEmail());
            idText.setText(String.valueOf(EditStudent.getId()));
            ageText.setText(String.valueOf(EditStudent.getAge()));
            toanText.setText(String.valueOf(EditStudent.getToan()));
            vanText.setText(String.valueOf((EditStudent.getVan())));



        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Th??ng B??o");
            alert.setHeaderText("B???n ch??a ch???n sinh vi??n ????? s???a ");
            alert.showAndWait();
        }
    }
    // l??u th??ng tin khi s???a
    public void Save()
    {
        try{

        Student p1= table.getSelectionModel().getSelectedItem();
        p1.setName(nameText.getText());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        p1.setEmail(emailText.getText());
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        p1.setId(Integer.parseInt(idText.getText()));
        p1.setAge(Integer.parseInt(ageText.getText()));
        p1.setToan(Double.parseDouble(toanText.getText()));
        p1.setVan(Double.parseDouble(vanText.getText()));
        p1.setDTB((Double.parseDouble(toanText.getText())+Double.parseDouble(vanText.getText()))/2);
        }
        catch (Exception ex)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thong bao");
            alert.setHeaderText("B???n ch??a ch???n sinh vi??n ????? update xem");


            alert.showAndWait();
        }
    }

    // CHUY???N SENCE sang SENCE kh??c
    public void changeSceneStudentDetail(ActionEvent e) throws IOException {
        try {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("StudentDetail.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        StudentDetailController controller = loader.getController();
        Student selected = table.getSelectionModel().getSelectedItem();
        controller.setStudent(selected);
        stage.setScene(scene);
        }
        catch (Exception ee)
       {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Th??ng B??o");
        alert.setHeaderText("B???n ph???i ch???n sinh vi??n ????? xem ");
        alert.showAndWait();
       }
    }
    // h??m chuy???n t??? tableview sang text view
    public void SetCellValueFromTableToTextFiled ()
    {

        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Student p1= table.getItems().get(table.getSelectionModel().getFocusedIndex());
                idText.setText(String.valueOf(p1.getId()));
                nameText.setText(p1.getName());
                ageText.setText(String.valueOf(p1.getAge()));
                emailText.setText(p1.getEmail());
                toanText.setText(String.valueOf(p1.getToan()));
                vanText.setText(String.valueOf((p1.getVan())));

            }
        });
    }
    public void closeApp(ActionEvent event) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm", ButtonType.OK, ButtonType.CANCEL);
        Stage stage = (Stage) fileMenu.getScene().getWindow();
        exitAlert.setContentText("Are you sure you want to exit?");
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.initOwner(stage);
        exitAlert.showAndWait();

        if(exitAlert.getResult() == ButtonType.OK) {
            Platform.exit();
        }
        else {
            exitAlert.close();
        }
    }

}



