/*
 * Class 
 * @filename Connection 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 25 Aug 2017 * 
 */
package core.com.db;

import core.com.utils.ComHashmap;
import fxschoolapp.classes.ClassListController;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Ryno
 */
public class ComDBDatabase {
    //--------------------------------------------------------------------------
    public static ResultSet query(Object sql){
        ComDBConnection dbConn = new ComDBConnection();
        return dbConn.query(sql.toString());
    }
    //--------------------------------------------------------------------------
    public static HashMap query(Object sql, boolean toHashMap) {
        ComDBConnection dbConn = new ComDBConnection();
        return resultsetToHashmap(dbConn.query(sql.toString()));
    }
    //--------------------------------------------------------------------------
    public static void statement(Object sql){
        ComDBConnection dbConn = new ComDBConnection();
        dbConn.statement(sql.toString());
    }
    //--------------------------------------------------------------------------
    public static String selectsingle(String sql){
        ResultSet rs = ComDBDatabase.query(sql);
        try {
            while (rs.next()){
                return rs.getObject(1).toString();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComDBDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //--------------------------------------------------------------------------
    public static HashMap resultsetToHashmap(ResultSet rs){
        HashMap data_arr = new HashMap();
        try {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            while (rs.next()) {
                HashMap row = new HashMap();
                for (int i = 1; i <= columns; i++) {
                    row.put(md.getColumnName(i), rs.getObject(i));
                }
                data_arr.put(data_arr.size(), row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data_arr;
    }
    //--------------------------------------------------------------------------
}
