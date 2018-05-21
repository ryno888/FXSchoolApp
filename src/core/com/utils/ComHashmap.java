/*
 * Class 
 * @filename UtilHashMap 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 25 Aug 2017 * 
 */
package core.com.utils;

import java.util.HashMap;

/**
 *
 * @author Ryno
 */
public class ComHashmap {
    //--------------------------------------------------------------------------
    public static HashMap merge(HashMap fromMap, HashMap toMap) {
        if(toMap == null){
            return fromMap;
        }
        fromMap.putAll(toMap);
        return toMap;
    }
    //--------------------------------------------------------------------------
}
