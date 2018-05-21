/*
 * Class 
 * @filename UtilArray 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 25 Aug 2017 * 
 */
package core.com.array;

import java.util.ArrayList;

/**
 *
 * @author Ryno
 */
public class ComArray {
    //--------------------------------------------------------------------------
    public static String implode(String seperator, ArrayList list){
        return String.join(seperator, list);
    }
    //--------------------------------------------------------------------------
}
