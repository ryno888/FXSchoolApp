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

import app.db.DB_grade;
import core.com.ui.fx.tableview.ComUiFxComboboxModule;

/**
 *
 * @author Ryno
 */
public class StudentPreviousGradeComboboxModule extends ComUiFxComboboxModule{
    
    private Object gra_id;
    private Object gra_name;
    //--------------------------------------------------------------------------
    public StudentPreviousGradeComboboxModule(DB_grade comDBobj) {
        super(comDBobj);
        this.gra_id = comDBobj.get_id();
        this.gra_name = comDBobj.get("gra_name");
    }
    //--------------------------------------------------------------------------
    
    public Object getGra_id() {
        return gra_id;
    }
    //--------------------------------------------------------------------------

    public void setGra_id(Object gra_id) {
        this.gra_id = gra_id;
    }
    //--------------------------------------------------------------------------

    public Object getGra_name() {
        return gra_name;
    }
    //--------------------------------------------------------------------------

    public void setGra_name(Object gra_name) {
        this.gra_name = gra_name;
    }
    //--------------------------------------------------------------------------

}
