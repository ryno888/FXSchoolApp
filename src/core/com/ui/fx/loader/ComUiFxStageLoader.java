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

import fxschoolapp.classes.ClassAddController;
import java.net.URL;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ryno
 */
public class ComUiFxStageLoader extends ComUiFxLoader{
    private Stage classAddStage;
    
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
        classAddStage = super.getStage();
        classAddStage.initStyle(StageStyle.UNDECORATED);
        classAddStage.setAlwaysOnTop(true);
        classAddStage.initModality(Modality.APPLICATION_MODAL);
    }
    //--------------------------------------------------------------------------
    public void showAndWait(){
        classAddStage.showAndWait();
    }
    //--------------------------------------------------------------------------
}
