/*
 * Class 
 * @filename ComUiFxTableViewModule 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 21 May 2018 * 
 */
package core.com.ui.fx.tableview;

import core.com.db.ComDBTable;

/**
 *
 * @author Ryno
 */
public abstract class ComUiFxComboboxModule {
    
    private Object comDBobj;

    //--------------------------------------------------------------------------
    public ComUiFxComboboxModule(Object comDBobj) {
        this.comDBobj = comDBobj;
    }
    //--------------------------------------------------------------------------
    public ComDBTable getComDBobj() {
        return (ComDBTable) comDBobj;
    }
    //--------------------------------------------------------------------------
}
