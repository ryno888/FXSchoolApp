/*
 * Class 
 * @filename ComUiFxStageLoader 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 21 May 2018 * 
 */
package core.com.ui.fx.loader;

import core.com.utils.ComHashmap;
import fxschoolapp.classes.ClassAddController;
import java.net.URL;
import java.util.HashMap;
import javafx.geometry.Rectangle2D;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ryno
 */
public class ComUiFxStageLoader extends ComUiFxLoader{
    private Stage stage;
    private boolean center;
    
    //--------------------------------------------------------------------------
    public ComUiFxStageLoader(String resource, HashMap options){
        super(resource);
        options = ComHashmap.merge(new HashMap() {{
            put("multiple", false);
            put("customsql", false);
        }}, options);
        this.init();
    }
    //--------------------------------------------------------------------------
    public ComUiFxStageLoader(String resource){
        super(resource);
        this.init();
    }
    //--------------------------------------------------------------------------
    public ComUiFxStageLoader(URL resource){
        super(resource);
        this.init();
    }
    //--------------------------------------------------------------------------
    private void init(){
        stage = super.getStage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        if(center){
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        }
    }
    //--------------------------------------------------------------------------
    public void showAndWait(){
        stage.showAndWait();
    }
    //--------------------------------------------------------------------------
}
