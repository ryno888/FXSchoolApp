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

import app.config.Constants;
import app.config.Setup;
import core.interfaces.db.DB_datatype;
import core.interfaces.db.DB_datatype.Datatype;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ryno
 */
public class ComDBConnection {

    Connection conn = null;
    ResultSet resultSet = null;
    Statement stmt = null;
    Properties properties = new Properties();
    String dbDriver = null;
    String dbURL = null;
    String dbName = null;
    String dbUser = null;
    String dbPassword = null;
    boolean isResource = false;

    //--------------------------------------------------------------------------
    public ComDBConnection() {
        this.dbDriver = Setup.DB_DRIVER;
        this.dbURL = Setup.DB_URL;
        this.dbName = Setup.DB_NAME;
        this.dbUser = Setup.DB_USER;
        this.dbPassword = Setup.DB_PASSWORD;
        this.isResource = Setup.IS_RESOURCE;
    }

    //--------------------------------------------------------------------------
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    //--------------------------------------------------------------------------
    public Connection getConnection() {
        if (this.conn != null) {
            return this.conn;
        }
        try {
            Class.forName(this.dbDriver);
            if (this.isResource) {
                this.conn = DriverManager.getConnection("jdbc:sqlite::resource:" + System.class.getResource(this.dbURL + this.dbName).toString());
            } else {
                this.conn = DriverManager.getConnection("jdbc:sqlite:"+Constants.DIR_ROOT+"/resources/quoteapp.sqlite", this.dbUser, this.dbPassword);
//                this.conn = DriverManager.getConnection(this.dbURL + this.dbName, this.dbUser, this.dbPassword);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex);
        }

        return this.conn;
    }

    //--------------------------------------------------------------------------
    public ResultSet query(String sql) {
        try {
            this.conn = this.getConnection();
            this.stmt = this.conn.createStatement();
            this.resultSet = stmt.executeQuery(sql);

            return this.resultSet;
        } catch (SQLException ex) {
            System.out.println("SQL: " + sql);
            System.err.println(ex.getMessage());
            System.err.println(Arrays.toString(Thread.currentThread().getStackTrace()));
        }
        return null;
    }

    //--------------------------------------------------------------------------
    public Object statement(String sql) {
        String generatedColumns[] = {"ID"};
        Object generated_id = null;
        try {
            this.conn = this.getConnection();
            PreparedStatement preparedStmt = this.conn.prepareStatement(sql, generatedColumns);
            preparedStmt.executeUpdate();
            generated_id = this.get_generated_id(preparedStmt);
            this.conn.close();
        } catch (SQLException ex) {
            System.err.println(sql);
            Logger.getLogger(ComDBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return generated_id;
    }
    //--------------------------------------------------------------------------

    public ResultSet getColumns() {
        try {
            DatabaseMetaData md = conn.getMetaData();
            this.resultSet = md.getTables(null, null, "%", null);
        } catch (SQLException ex) {
            Logger.getLogger(ComDBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.resultSet;
    }
    //--------------------------------------------------------------------------

    public ComDBConnection consoleResultset() {
        try {
            while (this.resultSet.next()) {
                System.out.println(this.resultSet.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComDBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }
    //--------------------------------------------------------------------------

    public Integer get_generated_id(PreparedStatement stmt) {
        try {
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                int id = rs.getInt(1);
                return id;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComDBTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //--------------------------------------------------------------------------
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }

    //--------------------------------------------------------------------------
    public static void shutdown() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException ex) {
            Logger.getLogger(ComDBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //--------------------------------------------------------------------------
    public static Class get_field_javaclass(String code, Class defaultClass) {
        switch(Setup.DB_DRIVER){
            case "org.sqlite.JDBC":
                switch (code){
                    case "DATETIME": return java.lang.String.class;
                }
                break;
            default: return defaultClass;
        }
        return defaultClass;
    }
    //--------------------------------------------------------------------------
}
