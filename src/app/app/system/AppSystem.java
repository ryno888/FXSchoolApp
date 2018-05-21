/*
 * Class 
 * @filename AppSystem 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 27 Nov 2017 * 
 */
package app.app.system;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ryno
 */
public class AppSystem {
    
    public static void set_look_and_feel(){ AppSystem.set_look_and_feel(null); }
    public static void set_look_and_feel(String type){
        try {
            if(type == null){
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }else{
                switch(type){
                    case "motif": UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"); break;
                    case "metal": UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"); break;
                    default: UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            // handle exception
            System.err.println(e);
        }
    }
}
