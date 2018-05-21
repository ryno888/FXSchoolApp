/*
 * Class 
 * @filename DB_datatype 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date Nov 11, 2017 * 
 */
package core.interfaces.db;

import core.com.db.ComDBConnection;

/**
 *
 * @author Ryno Laptop
 */
public interface DB_datatype {
    public enum Datatype {
        REFERENCE   (null       , java.lang.Integer.class       , "REFERENCE"), 
        INT         (0          , java.lang.Integer.class       , "INT"), 
        TINYINT     (0          , java.lang.Integer.class       , "TINYINT"), 
        DECIMAL     (0.00       , java.math.BigDecimal.class    , "DECIMAL"), 
        DATETIME    (null       , java.sql.Timestamp.class      , "DATETIME"), 
        CHAR        (""         , java.lang.String.class        , "CHAR"), 
        VARCHAR     (""         , java.lang.String.class        , "VARCHAR"), 
        TEXT        (""         , java.lang.String.class        , "TEXT"), 
        FLOAT       (0.00       , java.lang.Float.class         , "FLOAT"), 
        DOUBLE      (0.00       , java.lang.Double.class        , "DOUBLE"); 
        
        private final Object item_default;
        private Class java_class;
        private final String code;
        private String refrence;

        private Datatype(Object item_default, Class java_class, String code) {
            this.item_default = item_default;
            this.set_java_class(code, java_class);
            this.code = code;
        }
        //----------------------------------------------------------------------
        public Object get_item_default() {
            return this.item_default;
        }
        //----------------------------------------------------------------------
        public Class get_java_class() {
            return this.java_class;
        }
        //----------------------------------------------------------------------
        public String get_code() {
            return this.code;
        }
        //----------------------------------------------------------------------
        public Datatype set_reference(String table) {
            this.refrence = table;
            return this;
        }
        //----------------------------------------------------------------------
        public String get_reference(){
            return this.refrence;
        }
        //----------------------------------------------------------------------
        private Class set_java_class(String code, Class defaultClass){
            return this.java_class = ComDBConnection.get_field_javaclass(code, defaultClass);
        }
        //----------------------------------------------------------------------
    }
}
