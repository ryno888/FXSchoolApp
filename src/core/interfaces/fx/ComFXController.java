/*
 * Class 
 * @filename DB_datatype 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date Nov 11, 2017 * 
 */
package core.interfaces.fx;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 *
 * @author Ryno Laptop
 */
public abstract class ComFXController {
    
    public ValidationSupport validationSupport = new ValidationSupport();
    
    public abstract void init();
    public abstract void setActions();
    public abstract void setDisabled();
    public abstract void setEnabled();
    
    //--------------------------------------------------------------------------
    public ComFXController(){
        validationSupport.setErrorDecorationEnabled(false);
    }
    //--------------------------------------------------------------------------
    public boolean validate(){ 
        if(validationSupport.isInvalid()){
            validationSupport.setErrorDecorationEnabled(true);
            return false;
        }
        return true;
    }
    //--------------------------------------------------------------------------
    public void addRequiredInputs(String text, Control... n){ 
        for (Control control : n) {
            addRequiredInput(control, text);
        }
    }
    //--------------------------------------------------------------------------
    public void addRequiredInputs(Control... n){ 
        for (Control control : n) {
            addRequiredInput(control);
        }
    }
    //--------------------------------------------------------------------------
    public void addRequiredInput(Control n){ 
        addRequiredInput(n, "This is a required field");
    }
    //--------------------------------------------------------------------------
    public void addRequiredInput(Control n, String text){ 
        validationSupport.registerValidator(n, true, Validator.createEmptyValidator(text));
    }
    //--------------------------------------------------------------------------
}
