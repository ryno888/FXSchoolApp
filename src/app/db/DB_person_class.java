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
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author Ryno
 */
public class DB_person_class extends ComDBTable implements DB_table_interface {

    //--------------------------------------------------------------------------
    public DB_person_class() {
        super.get_fromdefault();
    }

    //--------------------------------------------------------------------------
    public DB_person_class(Object mixed) {
        super.get_fromdb(mixed);
    }

    //--------------------------------------------------------------------------
    @Override
    public HashMap<String, DB_datatype.Datatype> get_field_arr() {
        HashMap arr = new HashMap();
        arr.put("pec_id"            , DB_datatype.Datatype.INT);
        arr.put("pec_ref_person"    , DB_datatype.Datatype.REFERENCE.set_reference("person"));
        arr.put("pec_ref_classes"   , DB_datatype.Datatype.VARCHAR.set_reference("classes"));
        return arr;
    }
    //--------------------------------------------------------------------------
    @Override
    public String get_key() {
        return "pec_id";
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_table() {
        return "person_class";
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_name() {
        return "Person Class";
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_display() {
        return "pec_ref_person";
    }
    //--------------------------------------------------------------------------
    public DB_classes get_class() {
        ComDBQueryBuilder builder = new ComDBQueryBuilder();
        builder.select("*");
        builder.from("classes");
        builder.where("AND", "cla_id = " + this.get("pec_ref_classes"));
        
        ResultSet d = ComDBDatabase.query(builder);
        HashMap result = ComDBDatabase.resultsetToHashmap(d);
        return !result.isEmpty() && result.containsKey(0) ? new DB_classes(result.get(0)) : null;
    }
    //--------------------------------------------------------------------------
}
