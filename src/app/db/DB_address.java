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
public class DB_address extends ComDBTable implements DB_table_interface{
	
    //--------------------------------------------------------------------------
    public DB_address(){ this.get_fromdefault(); }
    //--------------------------------------------------------------------------
    public DB_address(Object mixed){ this.get_fromdb(mixed); }
    //--------------------------------------------------------------------------
    @Override
    public HashMap <String, DB_datatype.Datatype> get_field_arr() {
        HashMap arr = new HashMap();
        arr.put("add_id"			, DB_datatype.Datatype.INT);
        arr.put("add_line1"			, DB_datatype.Datatype.VARCHAR);
        arr.put("add_line2"			, DB_datatype.Datatype.VARCHAR);
        arr.put("add_suburb"                    , DB_datatype.Datatype.VARCHAR);
        arr.put("add_town"			, DB_datatype.Datatype.VARCHAR);
        arr.put("add_country"                   , DB_datatype.Datatype.VARCHAR);
        arr.put("add_code"			, DB_datatype.Datatype.VARCHAR);
        arr.put("add_ref_person"                , DB_datatype.Datatype.REFERENCE.set_reference("person"));
        return arr;
    }
    //--------------------------------------------------------------------------
    @Override
    public String get_key() { return "add_id"; }
    //--------------------------------------------------------------------------
    @Override
    public String get_table() { return "address"; }
    //--------------------------------------------------------------------------
    @Override
    public String get_name() { return "Address"; }
    //--------------------------------------------------------------------------
    @Override
    public String get_display() { return "add_line1"; }
    //--------------------------------------------------------------------------
}
