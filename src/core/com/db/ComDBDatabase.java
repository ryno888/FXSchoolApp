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

import java.sql.ResultSet;
import java.sql.SQLException;
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
}
