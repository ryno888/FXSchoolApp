/*
 * Class 
 * @filename StudentAddController 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 22 May 2018 * 
 */
package fxschoolapp.person.students;

import app.db.DB_classes;
import app.db.DB_grade;
import app.db.DB_person;
import app.db.DB_person_person;
import core.com.db.ComDBQueryBuilder;
import core.com.ui.fx.imageview.ComUiFxImageView;
import core.com.ui.fx.tooltip.ComUiFxTooltip;
import core.interfaces.fx.ComFXController;
import fxschoolapp.person.students.modules.combobox.ClassComboboxModule;
import fxschoolapp.person.students.modules.combobox.StudentGradeCheckComboboxModule;
import fxschoolapp.person.students.modules.combobox.StudentPreviousGradeComboboxModule;
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
public class StudentAddController extends ComFXController implements Initializable {
    
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
    @FXML private ComboBox studentClassCurrent;
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
    private ObservableList<Object> studentClassData;
    private DB_person_person dbObjPpF;
    private DB_person dbObjMother;
    private DB_person_person dbObjPpM;
    private DB_person dbObjFather;
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
        this.setStudentCurrentClass();
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
    public void saveChanges() {
        this.messagePane.setVisible(true);
        
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            
            dbObj = new DB_person();
            dbObj.set("per_firstname", studentFirstname.getText());
            dbObj.set("per_lastname", studentLastname.getText());
            dbObj.set("per_cemis_nr", studentCemisNr.getText());
            dbObj.set("per_year_in_phase", this.studentYearInPhase.getText());
            dbObj.set("per_previous_school", this.studentPreviousSchool.getText());
            dbObj.set("per_birthday", this.studentBirthday.getValue());
            dbObj.set("per_gender", this.femaleRadio.isSelected() ? DB_person.Gender.FEMALE.type() : DB_person.Gender.MALE.type());
            dbObj.save();
            
            Object previousGrade = studentPreviousGrade.getValue();
            if(previousGrade != null){
                StudentPreviousGradeComboboxModule previousGradeModule = (StudentPreviousGradeComboboxModule) previousGrade;
                dbObj.set_previous_grade((DB_grade) previousGradeModule.getComDBobj());
            }
            
            studentGradeRepeated.getCheckModel().getCheckedItems().forEach((t) -> {
                StudentGradeCheckComboboxModule module = (StudentGradeCheckComboboxModule) t;
                dbObj.set_grade_repeated((DB_grade) module.getComDBobj());
            });
            studentGradeRepeated.getItems().forEach((t) -> {
                StudentGradeCheckComboboxModule module = (StudentGradeCheckComboboxModule) t;
                if(studentGradeRepeated.getCheckModel().isChecked(t)){
                    dbObj.set_grade_repeated((DB_grade) module.getComDBobj());
                }else{
                    dbObj.remove_grade_repeated((DB_grade) module.getComDBobj());
                }
            });
            
            Object studentClass = studentClassCurrent.getValue();
            if(studentClass != null){
                ClassComboboxModule studentClassCurrentModule = (ClassComboboxModule) studentClass;
                dbObj.set_person_class((DB_classes) studentClassCurrentModule.getComDBobj());
            }
            
            dbObjFather = new DB_person();
            dbObjFather.set("per_firstname", fatherFirstname.getText());
            dbObjFather.set("per_lastname", fatherLastname.getText());
            dbObjFather.set("per_email", fatherEmail.getText());
            dbObjFather.set("per_contact_nr", fatherContactNr.getText());
            dbObjFather.save();
            
            dbObjPpF = new DB_person_person();
            dbObjPpF.set("pep_ref_person_primary", dbObj.get_id());
            dbObjPpF.set("pep_ref_person_secondary", dbObjFather.get_id());
            dbObjPpF.set("pep_type", DB_person_person.Type.FATHER.type());
            dbObjPpF.save();
            
            dbObjMother = new DB_person();
            dbObjMother.set("per_firstname", motherFirstname.getText());
            dbObjMother.set("per_lastname", motherLastname.getText());
            dbObjMother.set("per_email", motherEmail.getText());
            dbObjMother.set("per_contact_nr", motherContactNr.getText());
            dbObjMother.save();
            
            dbObjPpM = new DB_person_person();
            dbObjPpM.set("pep_ref_person_primary", dbObj.get_id());
            dbObjPpM.set("pep_ref_person_secondary", dbObjMother.get_id());
            dbObjPpM.set("pep_type", DB_person_person.Type.MOTHER.type());
            dbObjPpM.save();
            
            btnSave.getScene().getWindow().hide();
            
            stage.hide();
        });
        pause.play();
    }
    //--------------------------------------------------------------------------
    public TableView getClassTable() {
        return classTable;
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
    //--------------------------------------------------------------------------
    private void setStudentCurrentClass() {
        
        studentClassData = FXCollections.observableArrayList();
        
        ComDBQueryBuilder builder = new ComDBQueryBuilder();
        builder.where("AND", "cla_is_deleted = 0");
        builder.orderBy("cla_name ASC");
        HashMap classesArr = new DB_classes().select(builder);
        classesArr.forEach((k, v) -> {
            this.studentClassData.add(new ClassComboboxModule(new DB_classes(v)));
        });
        this.studentClassCurrent.setItems(this.studentClassData);
        
        StringConverter<ClassComboboxModule> converter = new StringConverter<ClassComboboxModule>() {
            @Override
            public String toString(ClassComboboxModule object) {
                return object.getCla_name().toString();
            }

            @Override
            public ClassComboboxModule fromString(String string) {
                return null;
            }
        };
        this.studentClassCurrent.setConverter(converter);
        
    }
}
