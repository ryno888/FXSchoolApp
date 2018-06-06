/*
 * Class 
 * @filename ClassComboboxModule 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 22 May 2018 * 
 */
package fxschoolapp.person.students.modules.combobox;

import app.db.DB_classes;
import core.com.ui.fx.tableview.ComUiFxComboboxModule;

/**
 *
 * @author Ryno
 */
public class ClassComboboxModule extends ComUiFxComboboxModule{
    
    private Object cla_id;
    private Object cla_name;
    //--------------------------------------------------------------------------
    public ClassComboboxModule(DB_classes comDBobj) {
        super(comDBobj);
        this.cla_id = comDBobj.get_id();
        this.cla_name = comDBobj.get("cla_name");
    }
    //--------------------------------------------------------------------------
    public Object getCla_id() {
        return cla_id;
    }
    //--------------------------------------------------------------------------
    public void setCla_id(Object cla_id) {
        this.cla_id = cla_id;
    }
    //--------------------------------------------------------------------------
    public Object getCla_name() {
        return cla_name;
    }
    //--------------------------------------------------------------------------
    public void setCla_name(Object cla_name) {
        this.cla_name = cla_name;
    }
    //--------------------------------------------------------------------------
}
