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
public class DB_intervention extends ComDBTable implements DB_table_interface {

    //--------------------------------------------------------------------------
    public DB_intervention() {
        super.get_fromdefault();
    }

    //--------------------------------------------------------------------------
    public DB_intervention(Object mixed) {
        super.get_fromdb(mixed);
    }

    //--------------------------------------------------------------------------
    @Override
    public HashMap<String, DB_datatype.Datatype> get_field_arr() {
        HashMap arr = new HashMap();
        arr.put("int_id"                , DB_datatype.Datatype.INT);
        arr.put("int_type"              , DB_datatype.Datatype.VARCHAR);
        arr.put("int_year"              , DB_datatype.Datatype.VARCHAR);
        arr.put("int_remark"            , DB_datatype.Datatype.TEXT);
        arr.put("int_ref_person"        , DB_datatype.Datatype.REFERENCE.set_reference("person"));
        return arr;
    }

    //--------------------------------------------------------------------------
    public enum Type {
        NONE            (0, "None"),
        CLASS_TUTORING  (1, "CLASS TUTORING"),
        OT              (2, "OT"),
        REM             (3, "REM"),
        LANGUAGE_SPEECH (4, "LANGUAGE/SPEECH"),
        PSYCHOLOGIST    (5, "PSYCHOLOGIST"),
        SOCIAL_WELFARE  (6, "SOCIAL/WELFARE"),
        MEDICAL         (7, "MEDICAL"),
        OTHER           (8, "OTHER");
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
                case 1: return Type.CLASS_TUTORING;
                case 2: return Type.OT;
                case 3: return Type.REM;
                case 4: return Type.LANGUAGE_SPEECH;
                case 5: return Type.PSYCHOLOGIST;
                case 6: return Type.SOCIAL_WELFARE;
                case 7: return Type.MEDICAL;
                case 8: return Type.OTHER;
                default: return Type.NONE;
            }
        }
    }
    //--------------------------------------------------------------------------
    @Override
    public String get_key() {
        return "int_id";
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_table() {
        return "intervention";
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_name() {
        return "Intervention";
    }
    //--------------------------------------------------------------------------
    @Override
    public String get_display() {
        return "int_type";
    }
    //--------------------------------------------------------------------------
    // methods
    //--------------------------------------------------------------------------
}
