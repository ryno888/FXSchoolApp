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

import app.db.DB_classes;
import core.com.db.ComDBTable;
import fxschoolapp.classes.modules.ClassListTableModule;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 *
 * @author Ryno
 */
public abstract class ComUiFxTableViewModule {
    
    private Object comDBobj;

    //--------------------------------------------------------------------------
    public ComUiFxTableViewModule(Object comDBobj) {
        this.comDBobj = comDBobj;
    }
    //--------------------------------------------------------------------------
    public ComDBTable getComDBobj() {
        return (ComDBTable) comDBobj;
    }
    //--------------------------------------------------------------------------
    public static Object getSelectedItem(TableView tableView) {
        return tableView.getSelectionModel().getSelectedItem();
    }
    //--------------------------------------------------------------------------
    public static void sort(ObservableList tableData) {
        Collections.sort(tableData,
                new Comparator<ClassListTableModule>() {
            public int compare(ClassListTableModule f1, ClassListTableModule f2) {
                return f1.getCla_name().toString().compareTo(f2.getCla_name().toString());
            }
        });
    }
}
