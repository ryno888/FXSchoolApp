/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.com.ui.fx.dialog;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.StageStyle;

/**
 *
 * @author ryno8
 */
public class ComUiFxDialog extends Alert{
    //--------------------------------------------------------------------------
    public ComUiFxDialog(AlertType alertType) {
        super(alertType);
        this.init();
    }
    //--------------------------------------------------------------------------
    public ComUiFxDialog(AlertType alertType, String contentText, ButtonType... buttons) {
        super(alertType, contentText, buttons);
        this.init();
    }
    //--------------------------------------------------------------------------
    private void init() {
        DialogPane dialogPane = this.getDialogPane();
        this.initStyle(StageStyle.UNDECORATED);
        dialogPane.getStylesheets().add(
           getClass().getResource("/assets/css/style.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
    }
    //--------------------------------------------------------------------------
}
