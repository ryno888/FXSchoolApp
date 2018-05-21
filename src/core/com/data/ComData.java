/*
 * Class 
 * @filename UtilArray 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 25 Aug 2017 * 
 */
package core.com.data;

import java.util.ArrayList;

/**
 *
 * @author Ryno
 */
public class ComData {
    //--------------------------------------------------------------------------
    public static boolean isInt(Object val){
        try {
            int i = Integer.parseInt(val.toString());
            return true;
        } catch (NumberFormatException er) {
            return false;
        }
    }
    //--------------------------------------------------------------------------
    public static boolean isLong(Object val){
        try {
            Long i = Long.parseLong(val.toString());
            return true;
        } catch (NumberFormatException er) {
            return false;
        }
    }
    //--------------------------------------------------------------------------
}
