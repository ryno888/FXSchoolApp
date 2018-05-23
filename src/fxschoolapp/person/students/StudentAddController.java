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
import core.com.date.ComDate;
import core.com.ui.fx.imageview.ComUiFxImageView;
import core.com.ui.fx.tooltip.ComUiFxTooltip;
import core.interfaces.fx.ComFXController;
import fxschoolapp.classes.modules.ClassListTableModule;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author Ryno
 */
public class StudentAddController implements Initializable, ComFXController {
    
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
        final ObservableList<String> strings = FXCollections.observableArrayList();
        for (int i = 0; i <= 100; i++) {
            strings.add("Item " + i);
        }
        studentGradeRepeated.getItems().addAll(strings);
        
        // and listen to the relevant events (e.g. when the selected indices or 
        // selected items change).
//        checkComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
//            public void onChanged(ListChangeListener.Change<? extends String> c) {
//                System.out.println(checkComboBox.getCheckModel().getSelectedItems());
//            }
//        });
    }
}
