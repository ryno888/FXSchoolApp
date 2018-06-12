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
        arr.put("per_type"              , DB_datatype.Datatype.INT);
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
    public enum Type {
        NONE(0, "None"),
        INDIVIDUAL(1, "Individual"),
        GUARDIAN(2, "Guardian");
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
                case 1: return Type.INDIVIDUAL;
                case 2: return Type.GUARDIAN;
                default: return Type.NONE;
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
    public void set_previous_grade(DB_grade dbObj) {
        DB_person_grade previous_grade = get_previous_grade();
        
        if(previous_grade.obj.isEmpty()){
            previous_grade = new DB_person_grade();
            previous_grade.set("peg_ref_person", this.get_id());
            previous_grade.set("peg_type", DB_person_grade.Type.PREVIOUS_GRADE.type());
        }
        previous_grade.set("peg_ref_grade", dbObj.get_id());
        previous_grade.save();
    }
    //--------------------------------------------------------------------------
    public HashMap get_repeated_grade_arr() {
        
        int type = DB_person_grade.Type.GRADES_REPEATED.type();
        ComDBQueryBuilder builder = new ComDBQueryBuilder();
        builder.select("*");
        builder.from("grade LEFT JOIN person_grade ON (peg_ref_grade = gra_id)");
        builder.where("AND", "peg_ref_person = " + this.get_id());
        builder.where("AND", "peg_type = " + type);
        
        return ComDBDatabase.query(builder.get_sql(), true);
    }
    //--------------------------------------------------------------------------
    public void set_grade_repeated(DB_grade dbObj) {
        DB_person_grade grade_repeated = new DB_person_grade(
            "peg_ref_grade = "+dbObj.get_id()
            +" AND peg_ref_person = "+ this.get_id() 
            +" AND peg_type = "+DB_person_grade.Type.GRADES_REPEATED.type()
        );
        
        if(grade_repeated.obj.isEmpty()){
            grade_repeated = new DB_person_grade();
            grade_repeated.set("peg_ref_person", this.get_id());
            grade_repeated.set("peg_type", DB_person_grade.Type.GRADES_REPEATED.type());
        }
        grade_repeated.set("peg_ref_grade", dbObj.get_id());
        grade_repeated.save();
    }
    //--------------------------------------------------------------------------
    public void remove_grade_repeated(DB_grade db_grade) {
        DB_person_grade grade_repeated = new DB_person_grade(
            "peg_ref_grade = "+db_grade.get_id()
            +" AND peg_ref_person = "+ this.get_id() 
            +" AND peg_type = "+DB_person_grade.Type.GRADES_REPEATED.type()
        );
        
        if(!grade_repeated.obj.isEmpty()){
            grade_repeated.delete();
        }
    }
    //--------------------------------------------------------------------------
    public boolean isGradeRepeated(DB_grade gradeRepeated) {
        ComDBQueryBuilder builder = new ComDBQueryBuilder();
        builder.select("*");
        builder.from("person_grade");
        builder.where("AND", "peg_ref_grade = "+gradeRepeated.get_id());
        builder.where("AND", "peg_ref_person = "+this.get_id());
        builder.where("AND", "peg_type = "+DB_person_grade.Type.GRADES_REPEATED.type());
        builder.limit(1);
        
        HashMap map = ComDBDatabase.query(builder.get_sql(), true);
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
    public DB_person_class get_person_class(){
        ComDBQueryBuilder builder = new ComDBQueryBuilder();
        builder.select("*");
        builder.from("person_class");
        builder.where("AND", "pec_ref_person = " + this.get_id());
        
        ResultSet d = ComDBDatabase.query(builder);
        HashMap result = ComDBDatabase.resultsetToHashmap(d);
        return !result.isEmpty() && result.containsKey(0) ? new DB_person_class(result.get(0)) : null;
    }
    //--------------------------------------------------------------------------
    public void set_person_class(DB_classes dbObj){
        DB_person_class dbObjPersonClass = get_person_class();
        if(dbObjPersonClass == null) {
            dbObjPersonClass = new DB_person_class();
            dbObjPersonClass.set("pec_ref_person", this.get_id());
            dbObjPersonClass.set("pec_ref_classes", dbObj.get_id());
            dbObjPersonClass.insert();
        }else{
            dbObjPersonClass.set("pec_ref_classes", dbObj.get_id());
            dbObjPersonClass.update();
        }
    }
    //--------------------------------------------------------------------------
    public DB_classes get_class(){
        DB_person_class person_class = get_person_class();
        
        return person_class != null ? person_class.get_class() : null;
    }
    //--------------------------------------------------------------------------
}
