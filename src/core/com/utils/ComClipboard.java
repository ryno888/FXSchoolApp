/*
 * Class 
 * @filename UtilArray 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 25 Aug 2017 * 
 */
package core.com.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;

/**
 *
 * @author Ryno
 */
public class ComClipboard {
    //--------------------------------------------------------------------------
    public static String implode(String seperator, ArrayList list){
        return String.join(seperator, list);
    }
    //---------------------------------------------------------------------------
    public static Clipboard getSystemClipboard() {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Clipboard systemClipboard = defaultToolkit.getSystemClipboard();

        return systemClipboard;
    }
    //---------------------------------------------------------------------------
    public static Clipboard copy(String str) {
        Clipboard clipboard = getSystemClipboard();

        clipboard.setContents(new StringSelection(str), null);
        
        return clipboard;
    }
    //--------------------------------------------------------------------------
}
