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
public class DB_person_person extends ComDBTable implements DB_table_interface {

    //--------------------------------------------------------------------------
    public DB_person_person() {
        super.get_fromdefault();
    }

    //--------------------------------------------------------------------------
    public DB_person_person(Object mixed) {
        super.get_fromdb(mixed);
    }

    //--------------------------------------------------------------------------
    @Override
    public HashMap<String, DB_datatype.Datatype> get_field_arr() {
        HashMap arr = new HashMap();
        arr.put("pep_id"                    , DB_datatype.Datatype.INT);
        arr.put("pep_ref_person_primary"    , DB_datatype.Datatype.REFERENCE.set_reference("person"));
        arr.put("pep_ref_person_secondary"  , DB_datatype.Datatype.REFERENCE.set_reference("person"));
        arr.put("pep_type"                  , DB_datatype.Datatype.TINYINT);
        return arr;
    }
    //--------------------------------------------------------------------------
    public enum Type {
        NONE(0, "None"),
        FATHER(1, "Father"),
        MOTHER(2, "Monther");
        private final int type;
        private final String label;

        Type(int type, String label) {
            this.type = type;
            this.label = label;
        }

        public String label() {
            return this.label;
        }
        
        public int type() {
            return this.type;
        }
        
        public static Type getType(int type) {
            switch (type) {
                case 1: return Type.FATHER;
                case 2: return Type.MOTHER;
                default: return Type.NONE;
            }
        }
    }
    //--------------------------------------------------------------------------
    @Override
    public String get_key() {
        return "pep_id";
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_table() {
        return "person_person";
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_name() {
        return "Person person";
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_display() {
        return "pep_ref_person_primary";
    }
    //--------------------------------------------------------------------------
    // methods
    //--------------------------------------------------------------------------
}
