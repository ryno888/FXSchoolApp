/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.com.ui.fx.loader;

import core.Core;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ryno8
 */
public class ComUiFxLoader {
    private Parent root = null;
    private FXMLLoader fxmlLoader = null;
    private Scene scene;
    private URL resource = null;
    private String strResource;
    private Object controller;
    
    //--------------------------------------------------------------------------
    public ComUiFxLoader(){}
    //--------------------------------------------------------------------------
    public ComUiFxLoader(String resource){
        this.strResource = resource;
    }
    //--------------------------------------------------------------------------
    public ComUiFxLoader(URL resource){
        this.resource = resource;
    }
    //--------------------------------------------------------------------------
    public Parent loadContent(){
        try{
            fxmlLoader = new FXMLLoader(this.resource);
            this.root = fxmlLoader.load();
            this.controller = fxmlLoader.getController();
        }catch(IOException ex){
            System.err.println(ex);
        }
        return this.root;
    }
    //--------------------------------------------------------------------------
    public Scene getScene(){
        if(this.resource == null){
            this.resource = new Core().getResource(this.strResource);
        }
        this.loadContent();
        this.scene = new Scene(this.root);
        return this.scene;
    }
    //--------------------------------------------------------------------------
    public Stage getStage(){
        Stage stage = new Stage();
        stage.setScene(this.getScene());
        return stage;
    }
    //--------------------------------------------------------------------------
    public Parent getRoot() {
        return root;
    }
    //--------------------------------------------------------------------------
    public URL getResource() {
        return resource;
    }
    //--------------------------------------------------------------------------
    public String getStrResource() {
        return strResource;
    }
    //--------------------------------------------------------------------------
    public Object getController() {
        return this.controller;
    }
    //--------------------------------------------------------------------------
}
