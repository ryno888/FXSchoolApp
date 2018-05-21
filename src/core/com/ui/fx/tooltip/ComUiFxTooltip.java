/*
 * Class 
 * @filename ComUiFxTooltip 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 18 May 2018 * 
 */
package core.com.ui.fx.tooltip;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Ryno
 */
public class ComUiFxTooltip extends Tooltip{
    private double positionX = 10;
    private double positionY = 25;
    
    public ComUiFxTooltip(){}
    //--------------------------------------------------------------------------
    public ComUiFxTooltip(String title){
        this.setText(title);
    }
    //--------------------------------------------------------------------------
    public void bind(final Node node){
        ComUiFxTooltip t = this;
        node.setOnMouseMoved(new EventHandler<MouseEvent>(){
           @Override  
           public void handle(MouseEvent event) {
              t.show(node, event.getScreenX() + positionX, event.getScreenY() + positionY);
           }
        });  
        node.setOnMouseExited(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event){
              t.hide();
           }
        });
     }
    //--------------------------------------------------------------------------
    public double getPositionX() {
        return positionX;
    }
    //--------------------------------------------------------------------------
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }
    //--------------------------------------------------------------------------
    public double getPositionY() {
        return positionY;
    }
    //--------------------------------------------------------------------------
    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
    //--------------------------------------------------------------------------
    public static void setTooltip(String title, final Node node) {
        ComUiFxTooltip.setTooltip(title, node, true);
    }
    //--------------------------------------------------------------------------
    public static void setTooltip(String title, final Node node, boolean instant) {
        if(instant){
            new ComUiFxTooltip(title).bind(node);
        }else{
            Control c = (Control)node;
            Tooltip t = new Tooltip(title);
            c.setTooltip(t);
        }
    }
    //--------------------------------------------------------------------------
}
