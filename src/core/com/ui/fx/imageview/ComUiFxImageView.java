/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.com.ui.fx.imageview;

import core.Core;
import javafx.scene.image.Image;

/**
 *
 * @author ryno8
 */
public class ComUiFxImageView extends javafx.scene.image.ImageView{
    //--------------------------------------------------------------------------
    public static javafx.scene.image.ImageView getImageView(String resource){
        return new javafx.scene.image.ImageView(new Image(Core.loadResourceAsStream(resource)));
    }
    //--------------------------------------------------------------------------
}
