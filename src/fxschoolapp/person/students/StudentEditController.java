/*
 * Class 
 * @filename StudentEditController 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 24 May 2018 * 
 */
package fxschoolapp.person.students;

import app.db.DB_grade;
import app.db.DB_person;
import app.db.DB_person_grade;
import core.com.ui.fx.imageview.ComUiFxImageView;
import core.com.ui.fx.tooltip.ComUiFxTooltip;
import core.interfaces.fx.ComFXController;
import fxschoolapp.person.students.modules.StudentGradeCheckComboboxModule;
import fxschoolapp.person.students.modules.StudentPreviousGradeComboboxModule;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author Ryno
 */
public class StudentEditController implements Initializable, ComFXController {
    @FXML private VBox header;
    @FXML private ButtonBar btnBar;
    @FXML private Button btnClose;
    @FXML private Button btnSave;
    
    @FXML private TextField studentFirstname;
    @FXML private TextField studentLastname;
    @FXML private TextField studentCemisNr;
    @FXML private ToggleGroup studentGender;
    @FXML private DatePicker studentBirthday;
    @FXML private ComboBox studentPreviousGrade;
    @FXML private TextField studentYearInPhase;
    @FXML private TextField studentPreviousSchool;
    @FXML private CheckComboBox studentGradeRepeated;
    
    @FXML private RadioButton maleRadio;
    @FXML private RadioButton femaleRadio;
    
    @FXML private TextField fatherFirstname;
    @FXML private TextField fatherLastname;
    @FXML private TextField fatherEmail;
    @FXML private TextField fatherContactNr;
    
    @FXML private TextField motherFirstname;
    @FXML private TextField motherLastname;
    @FXML private TextField motherEmail;
    @FXML private TextField motherContactNr;
    
    private Stage stage;
    private double xOffset;
    private double yOffset;
    private TableView classTable;
    private ObservableList tableData;
    private DB_person dbObj;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.init();
        this.setActions();
    }    
    //--------------------------------------------------------------------------
    @Override
    public void init() {
        btnClose.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/x-mark-8.png"));
        btnSave.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/save-12.png"));
        
        ComUiFxTooltip.setTooltip("Save new Student", btnSave);
        
        this.setStudentGradeBox();
        this.setStudentPreviousGradeBox();
    }
    //--------------------------------------------------------------------------
    @Override
    public void setActions() {
        btnSave.setOnMouseClicked((event) -> {
//            DB_classes dbObj = new DB_classes();
//            dbObj.set("cla_name", dataClassName.getText());
//            dbObj.set("cla_date", ComDate.getDate(dataDatePicker.getValue()));
//            dbObj.insert();
//            tableData.add(new ClassListTableModule(dbObj));
//            ClassListTableModule.sort(tableData);
//            btnSave.getScene().getWindow().hide();
            
        });
        btnClose.setOnMouseClicked((event) -> {
            btnClose.getScene().getWindow().hide();
        });
        header.setOnMousePressed(e -> {
            stage = (Stage) header.getScene().getWindow();
            xOffset = stage.getX() - e.getScreenX();
            yOffset = stage.getY() - e.getScreenY();
        });
        header.setOnMouseDragged(e -> {
            stage = (Stage) header.getScene().getWindow();
            stage.setX(e.getScreenX() + xOffset);
            stage.setY(e.getScreenY() + yOffset);
        });
        btnBar.setOnMousePressed(e -> {
            stage = (Stage) header.getScene().getWindow();
            xOffset = stage.getX() - e.getScreenX();
            yOffset = stage.getY() - e.getScreenY();
        });
        btnBar.setOnMouseDragged(e -> {
            stage = (Stage) header.getScene().getWindow();
            stage.setX(e.getScreenX() + xOffset);
            stage.setY(e.getScreenY() + yOffset);
        });
    }
    //--------------------------------------------------------------------------
    @Override
    public void setDisabled() {
        
    }
    //--------------------------------------------------------------------------
    @Override
    public void setEnabled() {
        
    }
    //--------------------------------------------------------------------------
    public TableView getClassTable() {
        return classTable;
    }
    //--------------------------------------------------------------------------
    public void setDBObj(Object dbObj) {
        this.dbObj = (DB_person) dbObj;
        System.out.println(this.dbObj.get_gender());
        this.studentFirstname.setText(this.dbObj.get("per_firstname").toString());
        this.studentLastname.setText(this.dbObj.get("per_lastname").toString());
        this.studentCemisNr.setText(this.dbObj.get("per_firstname").toString());
        this.studentYearInPhase.setText(this.dbObj.get("per_firstname").toString());
        this.studentPreviousSchool.setText(this.dbObj.get("per_firstname").toString());
        
        DB_person.Gender gender = this.dbObj.get_gender();
        if(gender == DB_person.Gender.FEMALE){
            femaleRadio.setSelected(true);
            maleRadio.setSelected(false);
        }else if(gender == DB_person.Gender.MALE){
            femaleRadio.setSelected(false);
            maleRadio.setSelected(true);
        }
        
        DB_person_grade previous_grade = this.dbObj.get_previous_grade();
        studentPreviousGrade.getSelectionModel().select(new StudentPreviousGradeComboboxModule(previous_grade.get_grade()));
        
//        @FXML private TextField studentFirstname;
//    @FXML private TextField studentLastname;
//    @FXML private TextField studentCemisNr;
//    @FXML private ToggleGroup studentGender;
//    @FXML private DatePicker studentBirthday;
//    @FXML private ComboBox studentPreviousGrade;
//    @FXML private TextField studentYearInPhase;
//    @FXML private TextField studentPreviousSchool;
//    @FXML private CheckComboBox studentGradeRepeated;
    }
    //--------------------------------------------------------------------------
    public void setClassTable(TableView classTable) {
        this.classTable = classTable;
    }
    //--------------------------------------------------------------------------
    public ObservableList getTableData() {
        return tableData;
    }
    //--------------------------------------------------------------------------
    public void setTableData(ObservableList tableData) {
        this.tableData = tableData;
    }
    //--------------------------------------------------------------------------

    private void setStudentGradeBox() {
        final ObservableList<StudentGradeCheckComboboxModule> moduleItems = FXCollections.observableArrayList();
        HashMap gradeMap = new DB_grade().select();
        gradeMap.forEach((k,v) -> {
            moduleItems.add(new StudentGradeCheckComboboxModule(new DB_grade(v)));
        });
        studentGradeRepeated.getItems().addAll(moduleItems);
        
        StringConverter<StudentGradeCheckComboboxModule> converter = new StringConverter<StudentGradeCheckComboboxModule>() {
            @Override
            public String toString(StudentGradeCheckComboboxModule object) {
                return object.getGra_name().toString();
            }

            @Override
            public StudentGradeCheckComboboxModule fromString(String string) {
                return null;
            }
        };
        studentGradeRepeated.setConverter(converter);
    }
    //--------------------------------------------------------------------------

    private void setStudentPreviousGradeBox() {
        final ObservableList<StudentPreviousGradeComboboxModule> moduleItems = FXCollections.observableArrayList();
        HashMap gradeMap = new DB_grade().select();
        gradeMap.forEach((k,v) -> {
            moduleItems.add(new StudentPreviousGradeComboboxModule(new DB_grade(v)));
        });
        studentPreviousGrade.getItems().addAll(moduleItems);
        
        StringConverter<StudentPreviousGradeComboboxModule> converter = new StringConverter<StudentPreviousGradeComboboxModule>() {
            @Override
            public String toString(StudentPreviousGradeComboboxModule object) {
                return object.getGra_name().toString();
            }

            @Override
            public StudentPreviousGradeComboboxModule fromString(String string) {
                return null;
            }
        };
        studentPreviousGrade.setConverter(converter);
    }
    
}
