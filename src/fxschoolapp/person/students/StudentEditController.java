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
import app.db.DB_classes;
import app.db.DB_grade;
import app.db.DB_person;
import app.db.DB_person_grade;
import app.db.DB_person_person;
import core.com.date.ComDate;
import core.com.db.ComDBQueryBuilder;
import core.com.ui.fx.imageview.ComUiFxImageView;
import core.com.ui.fx.loader.ComUiFxLoader;
import core.com.ui.fx.loader.ComUiFxStageLoader;
import core.com.ui.fx.tooltip.ComUiFxTooltip;
import core.interfaces.fx.ComFXController;
import fxschoolapp.FXSchoolApp;
import fxschoolapp.classes.ClassAddController;
import fxschoolapp.observation.term.EditTermController;
import fxschoolapp.person.students.modules.ClassComboboxModule;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
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
    @FXML private Button btnObsTerm1;
    @FXML private Button btnObsTerm2;
    @FXML private Button btnObsTerm3;
    @FXML private Button btnObsTerm4;
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
    @FXML private TitledPane titledPane1;
    @FXML private TitledPane titledPane2;
    @FXML private TitledPane titledPane3;
    
    @FXML private TextField classTutoringCurrentYear;
    @FXML private TextArea classTutoringCurrentRemark;
    @FXML private TextArea otCurrentRemark;
    @FXML private TextArea langCurrentRemark;
    @FXML private TextArea psycCurrentRemark;
    @FXML private TextArea socialCurrentRemark;
    @FXML private TextArea medicalCurrentRemark;
    @FXML private TextArea learnsuppCurrentRemark;
    @FXML private TextArea otherCurrentRemark1;
    @FXML private TextArea otherCurrentRemark2;
    @FXML private TextArea otherCurrentRemark3;
    @FXML private TextArea otherCurrentRemark4;
    @FXML private TextField otCurrentYear;
    @FXML private TextField langCurrentYear;
    @FXML private TextField psycCurrentYear;
    @FXML private TextField socialCurrentYear;
    @FXML private TextField medicalCurrentYear;
    @FXML private TextField learnsuppCurrentYear;
    @FXML private TextField otherCurrentYear1;
    @FXML private TextField otherCurrentYear2;
    @FXML private TextField otherCurrentYear3;
    @FXML private TextField otherCurrentYear4;
    @FXML private TextArea remCurrentRemark;
    @FXML private TextField remCurrentYear;
    
    @FXML private TextField classTutoringHistoryYear;
    @FXML private TextArea classTutoringHistoryRemark;
    @FXML private TextArea otHistoryRemark;
    @FXML private TextArea langHistoryRemark;
    @FXML private TextArea psycHistoryRemark;
    @FXML private TextArea socialHistoryRemark;
    @FXML private TextArea medicalHistoryRemark;
    @FXML private TextArea learnsuppHistoryRemark;
    @FXML private TextArea otherHistoryRemark1;
    @FXML private TextArea otherHistoryRemark2;
    @FXML private TextArea otherHistoryRemark3;
    @FXML private TextArea otherHistoryRemark4;
    @FXML private TextField otHistoryYear;
    @FXML private TextField langHistoryYear;
    @FXML private TextField psycHistoryYear;
    @FXML private TextField socialHistoryYear;
    @FXML private TextField medicalHistoryYear;
    @FXML private TextField learnsuppHistoryYear;
    @FXML private TextField otherHistoryYear1;
    @FXML private TextField otherHistoryYear2;
    @FXML private TextField otherHistoryYear3;
    @FXML private TextField otherHistoryYear4;
    @FXML private TextArea remHistoryRemark;
    @FXML private TextField remHistoryYear;

    
    
    private Stage stage;
    private double xOffset;
    private double yOffset;
    private TableView classTable;
    private ObservableList tableData;
    private ObservableList<Object> studentClassData;
    private DB_person dbObj;
    private DB_person dbObjFather;
    private DB_person dbObjMother;
    private DB_classes dbClassObj = null;
    
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
        this.setStudentCurrentClass();
    }
    //--------------------------------------------------------------------------
    @Override
    public void setActions() {
        btnSave.setOnMouseClicked((event) -> {
            stage = (Stage) btnSave.getScene().getWindow();
            this.saveChanges();
            
        });
        btnObsTerm1.setOnMouseClicked((event) -> {
            this.setDisabled();

            ComUiFxStageLoader load = new ComUiFxStageLoader("fxschoolapp/observation/term/EditTerm.fxml");
            EditTermController classController = (EditTermController) load.getController();
            classController.setTerm(1);
            load.showAndWait();
            
            this.setEnabled();
        });
        btnObsTerm2.setOnMouseClicked((event) -> {
            this.setDisabled();

            ComUiFxStageLoader load = new ComUiFxStageLoader("fxschoolapp/observation/term/EditTerm.fxml");
            EditTermController classController = (EditTermController) load.getController();
            classController.setTerm(2);
            load.showAndWait();
            
            this.setEnabled();
        });
        btnObsTerm3.setOnMouseClicked((event) -> {
            this.setDisabled();

            ComUiFxStageLoader load = new ComUiFxStageLoader("fxschoolapp/observation/term/EditTerm.fxml");
            EditTermController classController = (EditTermController) load.getController();
            classController.setTerm(3);
            load.showAndWait();
            
            this.setEnabled();
        });
        btnObsTerm4.setOnMouseClicked((event) -> {
            this.setDisabled();

            ComUiFxStageLoader load = new ComUiFxStageLoader("fxschoolapp/observation/term/EditTerm.fxml");
            EditTermController classController = (EditTermController) load.getController();
            classController.setTerm(4);
            load.showAndWait();
            
            this.setEnabled();
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
        
        dbClassObj = this.dbObj.get_class();
        setStudentCurrentClass();
        
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
            
            Object studentClass = studentClassCurrent.getValue();
            if(studentClass != null){
                ClassComboboxModule studentClassCurrentModule = (ClassComboboxModule) studentClass;
                dbObj.set_person_class((DB_classes) studentClassCurrentModule.getComDBobj());
            }
            
            this.messagePane.setVisible(false);
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
    //--------------------------------------------------------------------------
    private void setStudentCurrentClass() {
        
        studentClassData = FXCollections.observableArrayList();
        
        ComDBQueryBuilder builder = new ComDBQueryBuilder();
        builder.where("AND", "cla_is_deleted = 0");
        builder.orderBy("cla_name ASC");
        HashMap classesArr = new DB_classes().select(builder);
//        HashMap classesArr = ComDBDatabase.query("SELECT * FROM classes WHERE cla_is_deleted = 0 ORDER BY cla_name ASC", true);
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
        
        if(this.dbClassObj != null) this.studentClassCurrent.getSelectionModel().select(new ClassComboboxModule(dbClassObj));
    }
    
}
