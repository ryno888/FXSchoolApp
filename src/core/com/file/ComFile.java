/*
 * Class 
 * @filename ComFile 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 21 May 2018 * 
 */
package core.com.file;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryno
 */
public class ComFile {
    //--------------------------------------------------------------------------
    public static boolean copy(InputStream source , String destination) {
        boolean succeess = true;
        System.out.println("Copying ->" + source + "\n\tto ->" + destination);
        try {
            Files.copy(source, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(System.class.getName()).log(Level.SEVERE, null, ex);
            succeess = false;
        }
        return succeess;
    }
    //--------------------------------------------------------------------------
}
