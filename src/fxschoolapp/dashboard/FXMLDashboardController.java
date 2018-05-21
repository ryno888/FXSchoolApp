/*
 * Class 
 * @filename FXMLDocumentController 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 15 May 2018 * 
 */
package fxschoolapp.dashboard;

import core.Core;
import core.com.ui.fx.dialog.ComUiFxDialog;
import core.com.ui.fx.imageview.ComUiFxImageView;
import core.com.ui.fx.loader.ComUiFxLoader;
import core.com.ui.fx.tooltip.ComUiFxTooltip;
import core.interfaces.fx.ComFXController;
import fxschoolapp.FXSchoolApp;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ryno
 */
public class FXMLDashboardController implements Initializable, ComFXController {
    
    @FXML private VBox headerBackground;
    @FXML private Button btnClasses;
    @FXML private Button btnStudents;
    @FXML private Button btnDocuments;
    @FXML private Button btnSettings;
    
    @FXML private Button btnClose;
    @FXML private Button btnMaximize;
    @FXML private Button btnIconified;
    
    @FXML private ButtonBar btnBar;
    
    private Stage stage;
    private double xOffset;
    private double yOffset;
    
    //--------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.init();
        this.setActions();
    }   
    //--------------------------------------------------------------------------
    @Override
    public void init(){
        stage = FXSchoolApp.getPrimaryStage();
        btnClasses.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/categorize-64.png"));
        btnStudents.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/conference-64.png"));
        btnSettings.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/gear-64.png"));
        btnDocuments.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/document-64.png"));
        
        btnMaximize.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/square-outline-8.png"));
        btnIconified.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/minus-8.png"));
        btnClose.setGraphic(ComUiFxImageView.getImageView("assets/icon/png/white/x-mark-8.png"));
        
        
        ComUiFxTooltip.setTooltip("Classes", btnClasses, false);
        ComUiFxTooltip.setTooltip("Students", btnStudents, false);
        ComUiFxTooltip.setTooltip("Settings", btnSettings, false);
        ComUiFxTooltip.setTooltip("Documents", btnDocuments, false);
    }
    //--------------------------------------------------------------------------
    @Override
    public void setActions(){
        btnClose.setOnMouseClicked((event) -> {
            this.setDisabled();
            
            ComUiFxDialog alert = new ComUiFxDialog(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit Program");
            alert.setContentText("Are you sure you want to exit the program?");

            Optional<ButtonType> result = alert.showAndWait();
            if((result.isPresent()) && (result.get() == ButtonType.OK))System.exit(0);
            this.setEnabled();
        });
        
        btnClasses.setOnMouseClicked((event) -> {
            ComUiFxLoader loader = new ComUiFxLoader("fxschoolapp/classes/ClassList.fxml");
            Scene scene = loader.getScene();
            FXSchoolApp.setScene(scene);
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
        
    }
    //--------------------------------------------------------------------------
    public void setDisabled(){
        headerBackground.setDisable(true);
        btnClasses.setDisable(true);
        btnStudents.setDisable(true);
        btnSettings.setDisable(true);
        btnDocuments.setDisable(true);
    }
    //--------------------------------------------------------------------------
    public void setEnabled(){
        headerBackground.setDisable(false);
        btnClasses.setDisable(false);
        btnStudents.setDisable(false);
        btnSettings.setDisable(false);
        btnDocuments.setDisable(false);
    }
    //--------------------------------------------------------------------------
    
}
