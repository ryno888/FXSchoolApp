/*
 * Class 
 * @filename StudentListController 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 21 May 2018 * 
 */
package fxschoolapp.person.students;

import app.db.DB_classes;
import app.db.DB_person;
import core.com.db.ComDBDatabase;
import core.com.db.ComDBQueryBuilder;
import core.com.ui.fx.dialog.ComUiFxDialog;
import core.com.ui.fx.imageview.ComUiFxImageView;
import core.com.ui.fx.loader.ComUiFxLoader;
import core.com.ui.fx.loader.ComUiFxStageLoader;
import core.com.ui.fx.tooltip.ComUiFxTooltip;
import core.com.utils.ComClipboard;
import core.interfaces.fx.ComFXController;
import fxschoolapp.FXSchoolApp;
import fxschoolapp.classes.ClassAddController;
import fxschoolapp.classes.ClassEditController;
import fxschoolapp.person.students.modules.ClassComboboxModule;
import fxschoolapp.person.students.modules.PersonListTableModule;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Ryno
 */
public class StudentListController implements Initializable, ComFXController{
    
    @FXML private VBox headerBackground;
    @FXML private TableView classTable;
    
    @FXML private Button addEntry;
    @FXML private Button btnImport;
    @FXML private Button btnDeleteEntry;
    @FXML private Button btnClearFilter;
    
    @FXML private Button btnBack;
    @FXML private Button btnClose;
    @FXML private Button btnMaximize;
    @FXML private Button btnIconified;
    
    @FXML private ButtonBar btnBar;
    @FXML private ComboBox comboboxClasses;
    
    private Stage stage;
    private double xOffset;
    private double yOffset;
    private ObservableList tableData;
    private ContextMenu contextMenu;
    private DB_classes dbClassObj = null;
    private ObservableList<Object> comnboboxData;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.init();
        this.setActions();
    }    
    //--------------------------------------------------------------------------
    @Override
    public void init() {
        stage = FXSchoolApp.getPrimaryStage();
        btnMaximize.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/square-outline-8.png"));
        btnIconified.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/minus-8.png"));
        btnClose.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/x-mark-8.png"));
        btnImport.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/data-transfer-download-8.png"));
        
        btnBack.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/arrow-back-8.png"));
        addEntry.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/plus-8.png"));
        btnDeleteEntry.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/delete-8.png"));
        btnClearFilter.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/x-mark-8.png"));
        
        ComUiFxTooltip.setTooltip("Back to Dashboard", btnBack);
        ComUiFxTooltip.setTooltip("Delete", btnDeleteEntry);
        ComUiFxTooltip.setTooltip("Add new Class", addEntry);
        ComUiFxTooltip.setTooltip("Import students from csv", btnImport);
        
        tableData = FXCollections.observableArrayList();
        comnboboxData = FXCollections.observableArrayList();
        
        contextMenu = this.getContextMenu();
        classTable.setContextMenu(contextMenu);
        
        this.tableInit();
        this.comboboxInit();
    }
    //--------------------------------------------------------------------------
    @Override
    public void setActions() {
        btnClose.setOnMouseClicked((event) -> {
            this.setDisabled();
            
            ComUiFxDialog alert = new ComUiFxDialog(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit Program");
            alert.setContentText("Are you sure you want to exit the program?");
            Optional<ButtonType> result = alert.showAndWait();
            if((result.isPresent()) && (result.get() == ButtonType.OK))System.exit(0);
            
            this.setEnabled();
        });
        
        btnBack.setOnMouseClicked((event) -> {
            FXSchoolApp.goBack();
        });
        
        btnMaximize.setOnMouseClicked((event) -> {
            FXSchoolApp.setMaximized(!stage.isMaximized());
        });
        
        headerBackground.setOnMousePressed(e -> {
            xOffset = stage.getX() - e.getScreenX();
            yOffset = stage.getY() - e.getScreenY();
        });
        headerBackground.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() + xOffset);
            stage.setY(e.getScreenY() + yOffset);
        });
        btnBar.setOnMousePressed(e -> {
            xOffset = stage.getX() - e.getScreenX();
            yOffset = stage.getY() - e.getScreenY();
        });
        btnBar.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() + xOffset);
            stage.setY(e.getScreenY() + yOffset);
        });
        btnIconified.setOnMousePressed(e -> {
            FXSchoolApp.setIconified(true);
        });
        
        addEntry.setOnMousePressed(e -> {
            this.setDisabled();

            ComUiFxStageLoader load = new ComUiFxStageLoader("fxschoolapp/person/students/StudentAdd.fxml");
            StudentAddController classController = (StudentAddController) load.getController();
            load.showAndWait();
            
            this.setEnabled();
            
        });
        btnDeleteEntry.setOnMousePressed(e -> {
            PersonListTableModule d = (PersonListTableModule) classTable.getSelectionModel().getSelectedCells();
        });
        
        classTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                this.setDisabled();

                ComUiFxStageLoader load = new ComUiFxStageLoader("fxschoolapp/classes/ClassEdit.fxml");
                ClassEditController classController = (ClassEditController) load.getController();
                load.showAndWait();

                this.setEnabled();
            }
        });
        
        btnClearFilter.setOnMousePressed(event -> {
            this.dbClassObj = null;            
            this.comboboxClasses.valueProperty().set(null);
            tableData();
        });
        
        comboboxClasses.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null){
                ClassComboboxModule classModule = (ClassComboboxModule) newval;
                this.dbClassObj = new DB_classes(classModule.getCla_id());            
                tableData();
            }
        });
    }
    //--------------------------------------------------------------------------
    public void setDisabled(){
        classTable.setOpacity(0.5);
    }
    //--------------------------------------------------------------------------
    public void setClass(DB_classes dbObj){
        this.dbClassObj = dbObj;
        tableData();
        comboboxInit();
    }
    //--------------------------------------------------------------------------
    public void setEnabled(){
        classTable.setOpacity(1);
    }
    //--------------------------------------------------------------------------
    public ContextMenu getContextMenu(){
        ContextMenu menu = new ContextMenu();
        
        MenuItem edit = new MenuItem("Edit");
        MenuItem copy = new MenuItem("Copy");
        MenuItem remove = new MenuItem("Remove");
        
        edit.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/black/edit-8.png"));
        copy.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/black/copy-8.png"));
        remove.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/black/delete-8.png"));
        
        menu.getItems().addAll(edit, copy, remove);
        
        edit.setOnAction(e -> {
            this.setDisabled();
            //Set up instance instead of using static load() method
            
            ComUiFxStageLoader load = new ComUiFxStageLoader("fxschoolapp/classes/ClassEdit.fxml");
            ClassEditController classController = (ClassEditController) load.getController();
            load.showAndWait();
            
            this.setEnabled();
        });
        remove.setOnAction(e -> {
            PersonListTableModule classListTableModule =  (PersonListTableModule) classTable.getSelectionModel().getSelectedItem();
            classListTableModule.getComDBobj().delete();
            classTable.getItems().remove(classListTableModule);
        });
        copy.setOnAction(e -> {
            PersonListTableModule classListTableModule = (PersonListTableModule) classTable.getSelectionModel().getSelectedItem();
            ComClipboard.copy(classListTableModule.getPer_firstname()+ ", " + classListTableModule.getPer_lastname());
        });
        
        return menu;
    }
    //--------------------------------------------------------------------------
    public void tableInit(){
        TableColumn<PersonListTableModule, Object> nameColumn = new TableColumn<>("Firstname");
        nameColumn.setCellValueFactory(new PropertyValueFactory("per_firstname"));
        
        TableColumn<PersonListTableModule, Object> surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setCellValueFactory(new PropertyValueFactory("per_lastname"));
        
        TableColumn<PersonListTableModule, Object> birthdayColumn = new TableColumn<>("Birthday");
        birthdayColumn.setCellValueFactory(new PropertyValueFactory("per_birthday"));
        classTable.getColumns().addAll(nameColumn, surnameColumn, birthdayColumn);
        this.tableData();
    }
    //--------------------------------------------------------------------------
    public void tableData(){
        
        ComDBQueryBuilder builder = new ComDBQueryBuilder();
        builder.select("*");
        builder.from("person LEFT JOIN person_class ON pec_ref_person = per_id");
        builder.orderBy("per_name ASC");
        if(this.dbClassObj != null){
            builder.where("AND", "pec_ref_classes = "+this.dbClassObj.get_id());
        }
        
        HashMap dataArr = ComDBDatabase.query(builder.get_sql(), true);
        
        tableData.removeAll(tableData);
        dataArr.forEach((k,v) -> {
            this.tableData.add(new PersonListTableModule(new DB_person(v)));
        });
        classTable.setItems(tableData);
    }
    //--------------------------------------------------------------------------
    public void deleteClass(){
        PersonListTableModule d = (PersonListTableModule) classTable.getSelectionModel().getSelectedCells();
    }
    //--------------------------------------------------------------------------
    private void comboboxInit() {
        HashMap classesArr = ComDBDatabase.query("SELECT * FROM classes ORDER BY cla_name ASC", true);
        classesArr.forEach((k, v) -> {
            this.comnboboxData.add(new ClassComboboxModule(new DB_classes(v)));
        });
        this.comboboxClasses.setItems(this.comnboboxData);
        
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
        this.comboboxClasses.setConverter(converter);
        
        if(this.dbClassObj != null) this.comboboxClasses.getSelectionModel().select(new ClassComboboxModule(dbClassObj));
    }
    //--------------------------------------------------------------------------
}