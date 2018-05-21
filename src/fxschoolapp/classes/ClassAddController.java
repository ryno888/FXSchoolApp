/*
 * Class 
 * @filename ClassAddController 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 18 May 2018 * 
 */
package fxschoolapp.classes;

import app.db.DB_classes;
import core.com.ui.fx.imageview.ComUiFxImageView;
import core.com.ui.fx.tooltip.ComUiFxTooltip;
import core.interfaces.fx.ComFXController;
import fxschoolapp.classes.modules.ClassListTableModule;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ryno
 */
public class ClassAddController implements Initializable, ComFXController {
    
    @FXML private AnchorPane ap;
    @FXML private StackPane sp;
    @FXML private VBox classAddHeader;
    @FXML private ButtonBar classAddBtnBar;
    @FXML private Button btnClose;
    @FXML private Button btnSave;
    
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
        btnSave.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/save-8.png"));
        
        ComUiFxTooltip.setTooltip("Save new Class", btnSave);
    }
    //--------------------------------------------------------------------------
    @Override
    public void setActions() {
        btnSave.setOnMouseClicked((event) -> {
            tableData.add(new ClassListTableModule(new DB_classes(1)));
        });
        btnClose.setOnMouseClicked((event) -> {
            btnClose.getScene().getWindow().hide();
        });
        classAddHeader.setOnMousePressed(e -> {
            stage = (Stage) classAddHeader.getScene().getWindow();
            xOffset = stage.getX() - e.getScreenX();
            yOffset = stage.getY() - e.getScreenY();
        });
        classAddHeader.setOnMouseDragged(e -> {
            stage = (Stage) classAddHeader.getScene().getWindow();
            stage.setX(e.getScreenX() + xOffset);
            stage.setY(e.getScreenY() + yOffset);
        });
        classAddBtnBar.setOnMousePressed(e -> {
            stage = (Stage) classAddHeader.getScene().getWindow();
            xOffset = stage.getX() - e.getScreenX();
            yOffset = stage.getY() - e.getScreenY();
        });
        classAddBtnBar.setOnMouseDragged(e -> {
            stage = (Stage) classAddHeader.getScene().getWindow();
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
}
