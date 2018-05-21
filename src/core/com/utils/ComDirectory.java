/*
 * Class 
 * @filename ComDirectory 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 06 Dec 2017 * 
 */
package core.com.utils;

import java.io.File;

/**
 *
 * @author Ryno
 */
public class ComDirectory {
    //--------------------------------------------------------------------------
    public static String get_base_dir() {
        return System.getProperty("user.dir");
    }
    //--------------------------------------------------------------------------
    public static boolean file_exists(String filename) {
        File f = new File(filename);
		if(f.exists() && !f.isDirectory()) { 
			return true;
		}
		return false;
    }
    //--------------------------------------------------------------------------
}
