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
import core.com.string.ComString;
import core.interfaces.db.DB_datatype;
import core.interfaces.db.DB_table_interface;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author Ryno
 */
public class DB_person extends ComDBTable implements DB_table_interface {

    //--------------------------------------------------------------------------
    public DB_person() {
        super.get_fromdefault();
    }

    //--------------------------------------------------------------------------
    public DB_person(Object mixed) {
        super.get_fromdb(mixed);
    }

    //--------------------------------------------------------------------------
    @Override
    public HashMap<String, DB_datatype.Datatype> get_field_arr() {
        HashMap arr = new HashMap();
        arr.put("per_id"                , DB_datatype.Datatype.INT);
        arr.put("per_name"              , DB_datatype.Datatype.VARCHAR);
        arr.put("per_firstname"         , DB_datatype.Datatype.VARCHAR);
        arr.put("per_lastname"          , DB_datatype.Datatype.VARCHAR);
        arr.put("per_email"             , DB_datatype.Datatype.VARCHAR);
        arr.put("per_trading_name"      , DB_datatype.Datatype.VARCHAR);
        arr.put("per_contact_nr"        , DB_datatype.Datatype.VARCHAR);
        arr.put("per_account_nr"        , DB_datatype.Datatype.VARCHAR);
        arr.put("per_is_active"         , DB_datatype.Datatype.TINYINT);
        arr.put("per_gender"            , DB_datatype.Datatype.TINYINT);
        arr.put("per_birthday"          , DB_datatype.Datatype.DATETIME);
        arr.put("per_year_in_phase"     , DB_datatype.Datatype.VARCHAR);
        arr.put("per_previous_school"   , DB_datatype.Datatype.VARCHAR);
        arr.put("per_cemis_nr"          , DB_datatype.Datatype.VARCHAR);
        return arr;
    }

    //--------------------------------------------------------------------------
    public enum Gender {
        NONE(0, "None"),
        MALE(1, "Male"),
        FEMALE(2, "Female");
        private final int type;
        private final String label;

        Gender(int type, String label) {
            this.type = type;
            this.label = label;
        }

        public String label() {
            return this.label;
        }
        
        public int type() {
            return this.type;
        }
        
        public static Gender getGender(int type) {
            switch (type) {
                case 1: return Gender.MALE;
                case 2: return Gender.FEMALE;
                default: return Gender.NONE;
            }
        }
    }
    //--------------------------------------------------------------------------
    @Override
    public String get_key() {
        return "per_id";
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_table() {
        return "person";
    }

    //--------------------------------------------------------------------------
    @Override
    public String get_name() {
        return "Person";
    }
    //--------------------------------------------------------------------------
    @Override
    public String get_display() {
        return "per_name";
    }
    //--------------------------------------------------------------------------
    // methods
    //--------------------------------------------------------------------------
    @Override
    public void on_insert() {
        Object nextId = this.get_next_id();
        this.set("per_account_nr", this.generate_account_nr());
    }

    //--------------------------------------------------------------------------
    @Override
    public void on_update() {
        if (this.is_empty("per_account_nr")) {
            this.set("per_account_nr", this.generate_account_nr(this.get_id()));
        }
    }

    //--------------------------------------------------------------------------
    @Override
    public void on_delete() {
        DB_address address = new DB_address("add_ref_person = " + this.get_id());
        if (!address.is_empty()) {
            address.delete();
        }
    }

    //--------------------------------------------------------------------------
    public Gender get_gender() {
        return Gender.getGender((int) this.get("per_gender"));
    }
    //--------------------------------------------------------------------------
    public String format_name() {
        return this.get("per_firstname") + " " + this.get("per_lastname");
    }
    
    //--------------------------------------------------------------------------
    private String generate_account_nr() {
        return this.generate_account_nr(null);
    }
    //--------------------------------------------------------------------------
    private String generate_account_nr(Object id) {
        Object nextId = id == null ? this.get_next_id() : id;
        return ComString.pad("PER", 7, '0') + (nextId == null ? 1 : nextId);
    }

    //--------------------------------------------------------------------------
    public DB_address get_address() {
        DB_address address = null;
        if (!this.is_empty("per_id")) {
            address = new DB_address("add_ref_person = " + this.get("per_id"));
            if (address.is_empty()) {
                address = new DB_address();
                address.set("add_ref_person", this.get("per_id"));
            }
        }
        return address;
    }
    //--------------------------------------------------------------------------
    public DB_person_grade get_previous_grade() {
        
        int type = DB_person_grade.Type.PREVIOUS_GRADE.type();
        ComDBQueryBuilder builder = new ComDBQueryBuilder();
        builder.where("AND", "peg_ref_person = " + this.get_id());
        builder.where("AND", "peg_type = " + type);
        
        DB_person_grade previous_grade = new DB_person_grade(builder.get_parts("where"));
        return previous_grade;
    }
    //--------------------------------------------------------------------------
    public boolean isGradeRepeated(DB_grade gradeRepeated) {
        ResultSet d = ComDBDatabase.query("SELECT * "
            + "FROM person_grade "
            + "WHERE peg_ref_grade = "+gradeRepeated.get_id()+" "
            + "AND peg_ref_person = "+this.get_id()+" "
            + "AND peg_type = "+DB_person_grade.Type.PREVIOUS_GRADE.type()
        );
        
        HashMap map = ComDBDatabase.resultsetToHashmap(d);
        return !map.isEmpty();
    }
    //--------------------------------------------------------------------------
    public DB_person getParent(DB_person_person.Type type){
        ComDBQueryBuilder builder = new ComDBQueryBuilder();
        builder.select("*");
        builder.from("person LEFT JOIN person_person ON (pep_ref_person_secondary = per_id AND pep_type = " + type.type() + ")");
        builder.where("AND", "pep_ref_person_primary = " + this.get_id());
        
        ResultSet d = ComDBDatabase.query(builder);
        HashMap result = ComDBDatabase.resultsetToHashmap(d);
        return !result.isEmpty() && result.containsKey(0) ? new DB_person(result.get(0)) : null;
    }
    //--------------------------------------------------------------------------
}
