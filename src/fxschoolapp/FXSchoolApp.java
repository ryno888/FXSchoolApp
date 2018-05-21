/*
 * Class 
 * @filename FXSchoolApp 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 15 May 2018 * 
 */
package fxschoolapp;

import app.config.Constants;
import app.config.Setup;
import app.db.DB_classes;
import core.Core;
import core.com.db.ComDBConnection;
import core.com.file.ComFile;
import core.com.ui.fx.dialog.ComUiFxDialog;
import core.com.ui.fx.loader.ComUiFxLoader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 *
 * @author Ryno
 */
public class FXSchoolApp extends Application {
    private static Stage primaryStage; // **Declare static Stage**
    
    //--------------------------------------------------------------------------
    @Override
    public void start(Stage stage) throws Exception {
        setPrimaryStage(stage); // **Set the Stage**
        
        stage.initStyle(StageStyle.UNDECORATED);
        
        ComUiFxLoader loader = new ComUiFxLoader("fxschoolapp/dashboard/FXMLDashboard.fxml");
        stage.setScene(loader.getScene());
        stage.show();
    }

    //--------------------------------------------------------------------------
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ComDBConnection comDBcon = new ComDBConnection();
        try {
            Connection con = comDBcon.getConnection();
            if(con.isValid(15))launch(args);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DB connection failed");
            Logger.getLogger(System.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //--------------------------------------------------------------------------

    private void setPrimaryStage(Stage stage) {
        FXSchoolApp.primaryStage = stage;
    }
    //--------------------------------------------------------------------------
    static public Stage getPrimaryStage() {
        return FXSchoolApp.primaryStage;
    }
    //--------------------------------------------------------------------------
    static public Stage setScene(Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.show();
        
        return primaryStage;
    }
    //--------------------------------------------------------------------------
    static public void setMaximized(boolean maximized) {
        primaryStage.setMaximized(maximized);
        if(!maximized){
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        }
    }
    //--------------------------------------------------------------------------
    static public void setIconified(boolean iconified) {
        primaryStage.setIconified(iconified);
    }
    //--------------------------------------------------------------------------
    static public double getMaxScreenWidth() {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        return primScreenBounds.getWidth();
    }
    //--------------------------------------------------------------------------
    static public double getMaxScreenHeight() {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        return primScreenBounds.getHeight();
    }
    //--------------------------------------------------------------------------
    
}
