/*
 * Class 
 * @filename DBTable 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 25 Aug 2017 * 
 */
package core.com.db;

import core.com.utils.ComHashmap;
import core.interfaces.db.DB_datatype;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryno
 */
public abstract class ComDBTable {

    //table class
    public HashMap<String, DB_datatype.Datatype> field_arr = null;

    //instance objects
    public HashMap<String, Object> obj = new HashMap<>();
    public HashMap<String, Object> original_obj = new HashMap<>();
    private Object id;
    private String source;

    //--------------------------------------------------------------------------
    public void on_update() {
    }

    public void on_insert() {
    }

    public void on_delete() {
    }

    //--------------------------------------------------------------------------
    public Object get_fromdb(Object mixed) {
        Class<?> c = mixed.getClass();
        Object result = null;
        switch(c.getName()){
            case "java.lang.Integer": 
                 result = this.get_fromdb(mixed, null);
                break;
            case "java.util.HashMap": 
                 result = this.get_frommap(mixed);
                break;
        }
        return result;
    }
    //--------------------------------------------------------------------------
    public Object get_frommap(Object mixed) {
        HashMap dataMap = (HashMap) mixed;
        this.get_field_arr().entrySet().stream().forEach((entry) -> {
            Object val = dataMap.get(entry.getKey());
            DB_datatype.Datatype data_type = entry.getValue();
            this.obj.put(entry.getKey(), val!= null ? val : data_type.get_item_default());
            this.original_obj.put(entry.getKey(), data_type.get_item_default());
            this.source = "map";
        });
        
        return this.obj;
    }

    //--------------------------------------------------------------------------
    public Object get_fromdb(Object mixed, HashMap options) {
        options = ComHashmap.merge(new HashMap() {
            {
                put("multiple", false);
                put("customsql", false);
            }
        }, options);

        this.source = "db";

        ResultSet result;
        ComDBQueryBuilder sql = new ComDBQueryBuilder();
        sql.select(this.get_table() + ".*");
        sql.from(this.get_table());

        if (mixed.getClass().equals(java.lang.Integer.class)) {
            sql.where("AND", this.get_key() + " = " + mixed.toString());
        } else {
            sql.where("AND", mixed.toString());
        }
        result = ComDBDatabase.query(sql.get_sql());

        try {
            while (result.next()) {
                this.get_field_arr().entrySet().stream().forEach((entry) -> {
                    try {
                        DB_datatype.Datatype data_type = entry.getValue();
                        
                        Object obj = result.getObject(entry.getKey());
                        
                        this.obj.put(entry.getKey(), data_type.get_java_class().cast(obj));
                        this.original_obj.put(entry.getKey(), data_type.get_java_class().cast(obj));
                    } catch (SQLException ex) {
                        Logger.getLogger(ComDBTable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComDBTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.obj;
    }

    //--------------------------------------------------------------------------
    public Object get(String field) {
        if (this.validate_field(field)) {
            return this.obj.get(field);
        }
        return null;
    }

    //--------------------------------------------------------------------------
    public Collection<Object> get_array() {
        return this.obj.values();
    }

    //--------------------------------------------------------------------------
    public boolean is_empty(String field) {
        if (this.validate_field(field)) {
            DB_datatype.Datatype data_type = this.get_field_data_type(field);
            if (this.get(field).equals(data_type.get_item_default())) {
                return true;
            }
        }
        return false;
    }

    //--------------------------------------------------------------------------
    public boolean is_empty() {
        return this.obj.isEmpty();
    }

    //--------------------------------------------------------------------------
    public DB_datatype.Datatype get_field_data_type(String field) {
        HashMap<String, DB_datatype.Datatype> __field_arr = this.get_field_arr();
        return __field_arr.get(field);
    }

    //--------------------------------------------------------------------------
    public void set(String field, Object value) {
        if (this.validate_field(field)) {
            this.obj.put(field, value);
        }
    }

    //--------------------------------------------------------------------------
    public Object get_fromdefault() {
        this.get_field_arr().entrySet().stream().forEach((entry) -> {
            DB_datatype.Datatype data_type = entry.getValue();
            this.obj.put(entry.getKey(), data_type.get_item_default());
            this.original_obj.put(entry.getKey(), data_type.get_item_default());
            this.source = "new";
        });

        return null;
    }

    //--------------------------------------------------------------------------
    public boolean validate_field(String field) {
        if (this.obj.keySet().contains(field)) {
            return true;
        } else {
            try {
                StringBuilder builder = new StringBuilder();
                if (this.obj.isEmpty()) {
                    builder.append("The object you are trying to modify does not exist.");
                } else {
                    builder.append("The field ");
                    builder.append(field);
                    builder.append(" could not be found in the DB HashMap \n");
                    builder.append(this.obj.keySet());
                }
                throw new Exception(builder.toString());
            } catch (Exception ex) {
                Logger.getLogger(ComDBTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    //--------------------------------------------------------------------------
    public HashMap get_changes() {
        HashMap<String, Object> map = new HashMap<>();
        this.obj.entrySet().stream().forEach((entry) -> {
            String field = entry.getKey();
            Object value = entry.getValue();
            try {
                if (this.validate_field(field) && value != null && !value.equals(this.original_obj.get(field))) {
                    map.put(field, value);
                }
            } catch (Exception ex) {
                Logger.getLogger(ComDBTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        return map;
    }
    //--------------------------------------------------------------------------

    public Object update() {

        on_update();

        HashMap<String, Object> changes = this.get_changes();
        Object id = this.get_id();
        if (!changes.isEmpty()) {
            ComDBConnection db_connection = new ComDBConnection();
            try {

                StringBuilder builder = new StringBuilder();
                StringBuilder builder_fields = new StringBuilder();
                builder.append("UPDATE");
                builder.append(" ");
                builder.append(this.get_table());
                builder.append(" ");
                builder.append("SET");

                changes.entrySet().stream().forEach((entry) -> {
                    String field = entry.getKey();
                    Object value = entry.getValue();
                    if (!builder_fields.toString().isEmpty()) {
                        builder_fields.append(",");
                    }
                    builder_fields.append(" ").append(field).append(" ");
                    builder_fields.append("=");
                    builder_fields.append(this.dbvalue(value, field));
                });

                builder.append(builder_fields);
                builder.append(" ").append("WHERE").append(" ").append(this.get_key()).append(" = ").append(id);
                db_connection.statement(builder.toString());
            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        }
        return id;
    }

    //--------------------------------------------------------------------------
    public Object insert() {

        on_insert();

        Object[] values = this.obj.values().toArray();
        Object[] keys = this.obj.keySet().toArray();
        StringBuilder builder = new StringBuilder();
        StringBuilder builder_keys = new StringBuilder();
        StringBuilder builder_values = new StringBuilder();

        builder.append("INSERT INTO").append(" ").append(this.get_table());
        for (int i = 0; i < values.length; i++) {
            if (keys[i] == this.get_key()) {
                continue;
            }
            if (builder_keys.length() != 0) {
                builder_keys.append(", ");
                builder_values.append(", ");
            }
            builder_keys.append(keys[i]);
            builder_values.append(dbvalue(values[i], keys[i].toString()));
        }

        builder.append(" ").append("(").append(builder_keys.toString()).append(")");
        builder.append(" ").append("VALUES").append("(").append(builder_values.toString()).append(")");

        ComDBConnection db_connection = new ComDBConnection();
        this.id = db_connection.statement(builder.toString());
        this.set(this.get_key(), this.id);
        return this.id;
    }

    //--------------------------------------------------------------------------
    public Object save() {
        switch (this.get_source()) {
            case "db":
                return this.update();
            case "new":
                return this.insert();
        }
        return null;
    }

    //--------------------------------------------------------------------------
    public void delete() {
        this.delete(null);
    }

    //--------------------------------------------------------------------------
    public void delete(HashMap options) {
        StringBuilder builder = new StringBuilder();
        builder.append("DELETE FROM ");
        builder.append(this.get_table());
        builder.append(" WHERE ");
        builder.append(this.get_key());
        builder.append(" = ");
        builder.append(this.get_id()).append(";");

        on_delete();

        ComDBDatabase.statement(builder.toString());
    }

    //--------------------------------------------------------------------------
    public Object dbvalue(Object value) {
        return this.dbvalue(value, null);
    }

    //--------------------------------------------------------------------------
    public Object dbvalue(Object value, String field) {
        if (value == null) {
            if (!field.isEmpty()) {
                return this.get_field_data_type(field).get_item_default();
            }
            return null;
        }

        String val = value.toString();
        if (field != null) {
            DB_datatype.Datatype data_type = this.get_field_data_type(field);
            switch (data_type.get_code()) {
                case "TINYINT":
                    val = Boolean.parseBoolean(val) ? "1" : "0";
            }
        }

        String replace = val.replace("'", "''");
        return "'" + replace + "'";
    }

    //--------------------------------------------------------------------------
    public Object get_next_id() {
        ComDBQueryBuilder builder = new ComDBQueryBuilder();
        builder.select(this.get_key()).from(this.get_table()).orderBy(this.get_key() + " DESC").limit(1);
        ResultSet rs = ComDBDatabase.query(builder.get_sql());
        Object _id = null;
        try {
            while (rs.next()) {
                _id = rs.getInt(this.get_key()) + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComDBTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _id;
    }

    //--------------------------------------------------------------------------
    public abstract String get_key();

    //--------------------------------------------------------------------------
    public abstract String get_table();

    //--------------------------------------------------------------------------
    public abstract String get_name();

    //--------------------------------------------------------------------------
    public abstract String get_display();

    //--------------------------------------------------------------------------
    public abstract HashMap<String, DB_datatype.Datatype> get_field_arr();

    //--------------------------------------------------------------------------
    public Object get_id() {
        return !this.is_empty() ? this.obj.get(this.get_key()) : null;
    }

    //--------------------------------------------------------------------------
    public String get_source() {
        return source;
    }
    //--------------------------------------------------------------------------
}
