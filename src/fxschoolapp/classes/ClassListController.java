/*
 * Class 
 * @filename ClassListController 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 17 May 2018 * 
 */
package fxschoolapp.classes;

import app.db.DB_classes;
import core.com.db.ComDBDatabase;
import core.com.ui.fx.dialog.ComUiFxDialog;
import core.com.ui.fx.imageview.ComUiFxImageView;
import core.com.ui.fx.loader.ComUiFxLoader;
import core.com.ui.fx.loader.ComUiFxStageLoader;
import core.com.ui.fx.tooltip.ComUiFxTooltip;
import core.com.utils.ComClipboard;
import core.interfaces.fx.ComFXController;
import fxschoolapp.FXSchoolApp;
import fxschoolapp.classes.modules.ClassListTableModule;
import fxschoolapp.person.students.StudentListController;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ryno
 */
public class ClassListController extends ComFXController implements Initializable{
    
    @FXML private VBox headerBackground;
    @FXML private TableView classTable;
    
    @FXML private Button btnAddClass;
    @FXML private Button btnDeleteClass;
    
    @FXML private Button btnBack;
    @FXML private Button btnClose;
    @FXML private Button btnMaximize;
    @FXML private Button btnIconified;
    
    @FXML private ButtonBar btnBar;
    
    private Stage stage;
    private double xOffset;
    private double yOffset;
    private ObservableList tableData;
    private ContextMenu contextMenu;
    
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
        
        btnBack.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/arrow-back-8.png"));
        btnAddClass.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/plus-8.png"));
        btnDeleteClass.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/delete-8.png"));
        
        ComUiFxTooltip.setTooltip("Back to Dashboard", btnBack);
        ComUiFxTooltip.setTooltip("Delete", btnDeleteClass);
        ComUiFxTooltip.setTooltip("Add new Class", btnAddClass);
        
        tableData = FXCollections.observableArrayList();
        
        contextMenu = this.getContextMenu();
        classTable.setContextMenu(contextMenu);
        
        this.tableInit();
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
            
            ComUiFxLoader loader = new ComUiFxLoader("fxschoolapp/dashboard/FXMLDashboard.fxml");
            FXSchoolApp.setScene(loader.getScene(), FXSchoolApp.getWidth(), FXSchoolApp.getHeight());
            
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
        
        btnAddClass.setOnMousePressed(e -> {
            this.setDisabled();

            ComUiFxStageLoader load = new ComUiFxStageLoader("fxschoolapp/classes/ClassAdd.fxml");
            ClassAddController classController = (ClassAddController) load.getController();
            classController.setClassTable(classTable);
            classController.setTableData(tableData);
            load.showAndWait();
            
            this.setEnabled();
            
        });
        btnDeleteClass.setOnMousePressed(e -> {
            
            ClassListTableModule d = (ClassListTableModule) classTable.getSelectionModel().getSelectedCells();
        });
        
        classTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                this.setDisabled();
                //Set up instance instead of using static load() method

                ComUiFxLoader loader = new ComUiFxLoader("fxschoolapp/person/students/StudentList.fxml");
                Scene scene = loader.getScene(stage.getWidth(), stage.getHeight());
                StudentListController studentController = (StudentListController) loader.getController();
                ClassListTableModule classListTableModule = (ClassListTableModule) classTable.getSelectionModel().getSelectedItem();
                studentController.setClass((DB_classes) classListTableModule.getComDBobj());
                FXSchoolApp.setScene(scene);

                this.setEnabled();
            }
        });
    }
    //--------------------------------------------------------------------------
    public void setDisabled(){
        classTable.setOpacity(0.5);
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
        MenuItem manageStudents = new MenuItem("Manage Students");
        MenuItem remove = new MenuItem("Remove");
        
        edit.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/black/edit-8.png"));
        copy.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/black/copy-8.png"));
        manageStudents.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/black/group-8.png"));
        remove.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/black/delete-8.png"));
        
        menu.getItems().addAll(edit, manageStudents, copy, remove);
        
        edit.setOnAction(e -> {
            this.setDisabled();
            ComUiFxStageLoader load = new ComUiFxStageLoader("fxschoolapp/classes/ClassEdit.fxml");
            ClassEditController classController = (ClassEditController) load.getController();
            classController.setObservibleItem((ClassListTableModule) classTable.getSelectionModel().getSelectedItem());
            load.showAndWait();
            this.setEnabled();
        });
        manageStudents.setOnAction(e -> {
            ComUiFxLoader loader = new ComUiFxLoader("fxschoolapp/person/students/StudentList.fxml");
            Scene scene = loader.getScene(stage.getWidth(), stage.getHeight());
            StudentListController studentController = (StudentListController) loader.getController();
            ClassListTableModule classListTableModule = (ClassListTableModule) classTable.getSelectionModel().getSelectedItem();
            studentController.setClass((DB_classes) classListTableModule.getComDBobj());
            FXSchoolApp.setScene(scene);
        });
        remove.setOnAction(e -> {
            deleteClass();
        });
        copy.setOnAction(e -> {
            ClassListTableModule classListTableModule = (ClassListTableModule) classTable.getSelectionModel().getSelectedItem();
            ComClipboard.copy(classListTableModule.getCla_name() + ", " + classListTableModule.getCla_date());
        });
        
        return menu;
    }
    //--------------------------------------------------------------------------
    public void tableInit(){
        
        HashMap dataArr = ComDBDatabase.query("SELECT * FROM classes WHERE cla_is_deleted = 0 ORDER BY cla_name ASC", true);
        
        dataArr.forEach((k,v) -> {
            DB_classes dbObj = new DB_classes();
            this.tableData.add(new ClassListTableModule(new DB_classes(v)));
        });
        
        TableColumn<ClassListTableModule, Object> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory("cla_name"));
        
        TableColumn<ClassListTableModule, Object> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory("cla_date"));
        
        TableColumn<ClassListTableModule, Object> totalStudentsColumn = new TableColumn<>("Total Students");
        totalStudentsColumn.setCellValueFactory(new PropertyValueFactory("total_students"));

        classTable.setItems(tableData);
        classTable.getColumns().addAll(nameColumn, dateColumn, totalStudentsColumn);
    }
    //--------------------------------------------------------------------------
    public void deleteClass(){
        ComUiFxDialog alert = new ComUiFxDialog(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete this class?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if((result.isPresent()) && (result.get() == ButtonType.OK)){
            ClassListTableModule classListTableModule = (ClassListTableModule) classTable.getSelectionModel().getSelectedItem();
            DB_classes dbObj = (DB_classes) classListTableModule.getComDBobj();
            dbObj.set("cla_is_deleted", 1);
            dbObj.update();
            
            classTable.getItems().remove(classListTableModule);
        }
    }
    //--------------------------------------------------------------------------
}


