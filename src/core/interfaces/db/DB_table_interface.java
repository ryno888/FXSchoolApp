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

import java.util.HashMap;

/**
 *
 * @author Ryno Laptop
 */
public interface DB_table_interface {
    //--------------------------------------------------------------------------
    
    public abstract HashMap <String, DB_datatype.Datatype> get_field_arr();
    public abstract String get_key();
    public abstract String get_table();
    public abstract String get_name();
    public abstract String get_display();
}
