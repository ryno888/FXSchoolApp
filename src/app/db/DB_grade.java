/*
 * Class 
 * @filename DB_quote_item 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 25 Aug 2017 * 
 */
package app.db;

import core.com.db.ComDBTable;
import core.interfaces.db.DB_datatype;
import core.interfaces.db.DB_table_interface;
import java.util.HashMap;

/**
 *
 * @author Ryno
 */
public class DB_grade extends ComDBTable implements DB_table_interface {

    //--------------------------------------------------------------------------
    public DB_grade() {
        super.get_fromdefault();
    }

    //--------------------------------------------------------------------------
    public DB_grade(Object mixed) {
        super.get_fromdb(mixed);
    }

    //--------------------------------------------------------------------------
    @Override
    public HashMap<String, DB_datatype.Datatype> get_field_arr() {
        HashMap arr = new HashMap();
        arr.put("gra_id"            , DB_datatype.Datatype.INT);
        arr.put("gra_name"          , DB_datatype.Datatype.VARCHAR);
        return arr;
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_key() {
        return "gra_id";
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_table() {
        return "grade";
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_name() {
        return "Grade";
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_display() {
        return "gra_name";
    }
    //--------------------------------------------------------------------------
    // methods
    //--------------------------------------------------------------------------
    @Override
    public void on_insert() {}

    //--------------------------------------------------------------------------
    @Override
    public void on_update() {}

    //--------------------------------------------------------------------------
    @Override
    public void on_delete() {}

    //--------------------------------------------------------------------------
}
