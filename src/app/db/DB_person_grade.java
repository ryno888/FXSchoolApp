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
public class DB_person_grade extends ComDBTable implements DB_table_interface {

    //--------------------------------------------------------------------------
    public DB_person_grade() {
        super.get_fromdefault();
    }

    //--------------------------------------------------------------------------
    public DB_person_grade(Object mixed) {
        super.get_fromdb(mixed);
    }

    //--------------------------------------------------------------------------
    @Override
    public HashMap<String, DB_datatype.Datatype> get_field_arr() {
        HashMap arr = new HashMap();
        arr.put("peg_id"            , DB_datatype.Datatype.INT);
        arr.put("peg_ref_person"    , DB_datatype.Datatype.REFERENCE.set_reference("person"));
        arr.put("peg_ref_grade"     , DB_datatype.Datatype.VARCHAR.set_reference("grade"));
        arr.put("peg_type"          , DB_datatype.Datatype.VARCHAR);
        return arr;
    }

    //--------------------------------------------------------------------------
    public enum Type {
        NONE(0, "None"),
        GRADES_REPEATED(1, "Grades Repeated"),
        PREVIOUS_GRADE(2, "Previous Grade");
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
                case 1: return Type.GRADES_REPEATED;
                case 2: return Type.PREVIOUS_GRADE;
                default: return Type.NONE;
            }
        }
    }
    //--------------------------------------------------------------------------
    @Override
    public String get_key() {
        return "peg_id";
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_table() {
        return "person_grade";
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_name() {
        return "Person Grade";
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_display() {
        return "peg_name";
    }
    //--------------------------------------------------------------------------
    public DB_grade get_grade() {
        Object grade = this.get("peg_ref_grade");
        if(grade != null){
            return new DB_grade(Integer.parseInt(grade.toString()));
        }
        return null;
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
