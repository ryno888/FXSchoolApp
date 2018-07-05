/*
 * Class 
 * @filename EditInterventionController 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 22 Jun 2018 * 
 */
package fxschoolapp.observation.intervention;

import app.db.DB_intervention;
import app.db.DB_person;
import core.com.date.ComDate;
import core.com.ui.fx.imageview.ComUiFxImageView;
import core.com.ui.fx.tooltip.ComUiFxTooltip;
import core.interfaces.fx.ComFXController;
import fxschoolapp.observation.intervention.module.InterventionTypeModule;
import fxschoolapp.person.students.StudentEditController;
import fxschoolapp.person.students.modules.table.InterventionTableModule;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Ryno
 */
public class EditInterventionController  extends ComFXController implements Initializable {

    @FXML private VBox header;
    @FXML private ButtonBar btnBar;
    @FXML private Button btnClose;
    @FXML private Button btnSave;
    @FXML private ComboBox interventionTypeBox;
    @FXML private TextField yearTextField;
    @FXML private TextArea remarkTextField;
    
    
    private Stage stage;
    private double xOffset;
    private double yOffset;
    private DB_person dbObj;
    private DB_intervention interventionObj;
    private boolean isHistory;
    private StudentEditController editController;
    
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
        
        addRequiredInputs(yearTextField, remarkTextField);
        addRequiredInput(interventionTypeBox, "Intervention selection is required");
        
        btnClose.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/x-mark-8.png"));
        btnSave.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/save-12.png"));
        
        ComUiFxTooltip.setTooltip("Save new Class", btnSave);
        this.setInterventionTypeBox();
        
        if(!this.isHistory){
            yearTextField.setText(ComDate.getDate("Y"));
            yearTextField.setEditable(false);
        }
    }
    //--------------------------------------------------------------------------
    @Override
    public void setActions() {
        btnSave.setOnMouseClicked((event) -> {
            if(validate()){
                stage = (Stage) header.getScene().getWindow();
                InterventionTypeModule item = (InterventionTypeModule) interventionTypeBox.getSelectionModel().getSelectedItem();
                DB_intervention dbInterventionObj = new DB_intervention();
                dbInterventionObj.set("int_type", item.getInt_type());
                dbInterventionObj.set("int_remark", remarkTextField.getText());
                dbInterventionObj.set("int_year", yearTextField.getText());
                dbInterventionObj.set("int_ref_person", dbObj.get_id());
                dbInterventionObj.insert();
                
                editController.getInterventionCurrentList().add(new InterventionTableModule(dbInterventionObj));
                stage.hide();
            }
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

    public void isHistory(boolean b) {
        this.isHistory = b;
    }
    //--------------------------------------------------------------------------

    public void setDbIntervention(Object b) {
        this.interventionObj = (DB_intervention) b;
        interventionTypeBox.getSelectionModel().select(new InterventionTypeModule(DB_intervention.Type.getType((int) this.interventionObj.get("int_type"))));
        yearTextField.setText(this.interventionObj.get("int_year").toString());
        remarkTextField.setText(this.interventionObj.get("int_remark").toString());
    }
    //--------------------------------------------------------------------------

    private void setInterventionTypeBox() {
        final ObservableList<InterventionTypeModule> moduleItems = FXCollections.observableArrayList();
        
        for (DB_intervention.Type value : DB_intervention.Type.values()) {
            moduleItems.add(new InterventionTypeModule(value));
        }
        interventionTypeBox.getItems().addAll(moduleItems);
        
        StringConverter<InterventionTypeModule> converter = new StringConverter<InterventionTypeModule>() {
            @Override
            public String toString(InterventionTypeModule object) {
                return object.getInt_type_label();
            }

            @Override
            public InterventionTypeModule fromString(String string) {
                return null;
            }
        };
        interventionTypeBox.setConverter(converter);
    }
    //--------------------------------------------------------------------------

    public void setDbPerson(DB_person dbObj) {
        this.dbObj = dbObj;
    }
    //--------------------------------------------------------------------------

    public void setEditController(StudentEditController aThis) {
        editController = aThis;
    }
    //--------------------------------------------------------------------------
    
}