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

import core.com.db.ComDBDatabase;
import core.com.db.ComDBQueryBuilder;
import core.com.db.ComDBTable;
import core.interfaces.db.DB_datatype;
import core.interfaces.db.DB_table_interface;
import java.util.HashMap;

/**
 *
 * @author Ryno
 */
public class DB_classes extends ComDBTable implements DB_table_interface{
	
    //--------------------------------------------------------------------------
    public DB_classes(){ super.get_fromdefault(); }
    //--------------------------------------------------------------------------
    public DB_classes(Object mixed){ 
        super.get_fromdb(mixed); 
    }
    //--------------------------------------------------------------------------
    @Override
    public HashMap <String, DB_datatype.Datatype> get_field_arr() {
        HashMap arr = new HashMap();
        arr.put("cla_id"            , DB_datatype.Datatype.INT);
        arr.put("cla_name"          , DB_datatype.Datatype.VARCHAR);
        arr.put("cla_date"          , DB_datatype.Datatype.DATETIME);
        arr.put("cla_is_deleted"    , DB_datatype.Datatype.TINYINT);
        return arr;
    }
    //--------------------------------------------------------------------------
    @Override
    public String get_key() { return "cla_id"; }
    //--------------------------------------------------------------------------
    @Override
    public String get_table() { return "classes"; }
    //--------------------------------------------------------------------------
    @Override
    public String get_name() { return "Classes"; }
    //--------------------------------------------------------------------------
    @Override
    public String get_display() { return "cla_name"; }
    //--------------------------------------------------------------------------
    public Object get_total_students() { 
        ComDBQueryBuilder builder = new ComDBQueryBuilder();
        builder.select("COUNT(per_id)");
        builder.from("person LEFT JOIN person_class ON (pec_ref_person = per_id)");
        builder.where("AND", "pec_ref_classes = " + this.get_id());
        return ComDBDatabase.selectsingle(builder.get_sql());
    }
    //--------------------------------------------------------------------------
}
