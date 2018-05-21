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
public class DB_config extends ComDBTable implements DB_table_interface{
	
    //--------------------------------------------------------------------------
    public DB_config(){ this.get_fromdefault(); }
    //--------------------------------------------------------------------------
    public DB_config(Object mixed){ this.get_fromdb(mixed); }
    //--------------------------------------------------------------------------
    @Override
    public HashMap <String, DB_datatype.Datatype> get_field_arr() {
        HashMap arr = new HashMap();
        arr.put("con_id"            , DB_datatype.Datatype.INT);
        arr.put("con_name"          , DB_datatype.Datatype.VARCHAR);
        arr.put("con_value"         , DB_datatype.Datatype.VARCHAR);
        return arr;
    }
    //--------------------------------------------------------------------------
    @Override
    public String get_key() { return "con_id"; }
    //--------------------------------------------------------------------------
    @Override
    public String get_table() { return "config"; }
    //--------------------------------------------------------------------------
    @Override
    public String get_name() { return "Config"; }
    //--------------------------------------------------------------------------
    @Override
    public String get_display() { return "con_name"; }
    //--------------------------------------------------------------------------
}
