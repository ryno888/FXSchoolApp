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

import app.config.Constants;
import app.db.DB_grade;
import app.db.DB_person;
import app.db.DB_person_grade;
import app.db.DB_person_person;
import core.com.date.ComDate;
import core.com.ui.fx.imageview.ComUiFxImageView;
import core.com.ui.fx.tooltip.ComUiFxTooltip;
import core.interfaces.fx.ComFXController;
import fxschoolapp.person.students.modules.StudentGradeCheckComboboxModule;
import fxschoolapp.person.students.modules.StudentPreviousGradeComboboxModule;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
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
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.MaskerPane;

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
    @FXML private MaskerPane messagePane;
    
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
    private DB_person dbObjFather;
    private DB_person dbObjMother;
    
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
        
        this.setStudentGradeRepeated();
        this.setStudentPreviousGradeBox();
    }
    //--------------------------------------------------------------------------
    @Override
    public void setActions() {
        btnSave.setOnMouseClicked((event) -> {
            stage = (Stage) btnSave.getScene().getWindow();
            this.saveChanges();
            
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
        this.studentFirstname.setText(this.dbObj.get("per_firstname").toString());
        this.studentLastname.setText(this.dbObj.get("per_lastname").toString());
        this.studentCemisNr.setText(this.dbObj.get("per_cemis_nr").toString());
        this.studentYearInPhase.setText(this.dbObj.get("per_year_in_phase").toString());
        this.studentPreviousSchool.setText(this.dbObj.get("per_previous_school").toString());
        this.studentBirthday.setValue(ComDate.toLocalDate(this.dbObj.get("per_birthday"), Constants.DATE));
        
        //set gender
        DB_person.Gender gender = this.dbObj.get_gender();
        if(gender == DB_person.Gender.FEMALE){
            femaleRadio.setSelected(true);
            maleRadio.setSelected(false);
        }else if(gender == DB_person.Gender.MALE){
            femaleRadio.setSelected(false);
            maleRadio.setSelected(true);
        }
        
        //set previous grade
        DB_person_grade previous_grade = this.dbObj.get_previous_grade();
        if(!previous_grade.is_empty("peg_ref_grade")) studentPreviousGrade.getSelectionModel().select(new StudentPreviousGradeComboboxModule(previous_grade.get_grade()));
        
        //set reqpeated grades
        studentGradeRepeated.getItems().forEach((t) -> {
            StudentGradeCheckComboboxModule module = (StudentGradeCheckComboboxModule) t;
            DB_grade grade = (DB_grade) module.getComDBobj();
            if(!this.dbObj.is_empty() && this.dbObj.isGradeRepeated(grade)){
                studentGradeRepeated.getCheckModel().check(t);
            }
        });
        
        // set father
        this.dbObjFather = this.dbObj.getParent(DB_person_person.Type.FATHER);
        if(this.dbObjFather != null){
            this.fatherFirstname.setText(this.dbObjFather.get("per_firstname").toString());
            this.fatherLastname.setText(this.dbObjFather.get("per_lastname").toString());
            this.fatherEmail.setText(this.dbObjFather.get("per_email").toString());
            this.fatherContactNr.setText(this.dbObjFather.get("per_contact_nr").toString());
        }
        
        // set mother
        this.dbObjMother = this.dbObj.getParent(DB_person_person.Type.MOTHER);
        if(this.dbObjMother != null){
            this.motherFirstname.setText(this.dbObjMother.get("per_firstname").toString());
            this.motherLastname.setText(this.dbObjMother.get("per_lastname").toString());
            this.motherEmail.setText(this.dbObjMother.get("per_email").toString());
            this.motherContactNr.setText(this.dbObjMother.get("per_contact_nr").toString());
        }
        
    }
    //--------------------------------------------------------------------------
    public void saveChanges() {
        this.messagePane.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            dbObj.set("per_firstname", this.studentFirstname.getText());
            dbObj.set("per_lastname", this.studentLastname.getText());
            dbObj.set("per_cemis_nr", this.studentCemisNr.getText());
            dbObj.set("per_year_in_phase", this.studentYearInPhase.getText());
            dbObj.set("per_previous_school", this.studentPreviousSchool.getText());
            dbObj.set("per_birthday", this.studentBirthday.getValue());
            dbObj.set("per_gender", this.femaleRadio.isSelected() ? DB_person.Gender.FEMALE.type() : DB_person.Gender.MALE.type());
            dbObj.update();
            
            Object previousGrade = studentPreviousGrade.getValue();
            if(previousGrade != null){
                StudentPreviousGradeComboboxModule previousGradeModule = (StudentPreviousGradeComboboxModule) previousGrade;
                dbObj.set_previous_grade((DB_grade) previousGradeModule.getComDBobj());
            }
            
            
            studentGradeRepeated.getCheckModel().getCheckedItems().forEach((t) -> {
                StudentGradeCheckComboboxModule module = (StudentGradeCheckComboboxModule) t;
                System.out.println(module);
            });
//            studentGradeRepeated.getItems().forEach((t) -> {
//                StudentGradeCheckComboboxModule module = (StudentGradeCheckComboboxModule) t;
//                DB_grade grade = (DB_grade) module.getComDBobj();
//                if(!this.dbObj.is_empty() && this.dbObj.isGradeRepeated(grade)){
//                    studentGradeRepeated.getCheckModel().check(t);
//                }
//            });
            
            
            this.messagePane.setVisible(false);
            stage.hide();
        });
        pause.play();
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

    private void setStudentGradeRepeated() {
        final ObservableList<StudentGradeCheckComboboxModule> moduleItems = FXCollections.observableArrayList();
        HashMap gradeMap = new DB_grade().select();
        gradeMap.forEach((k,v) -> {
            moduleItems.add((int)k,new StudentGradeCheckComboboxModule(new DB_grade(v)));
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
