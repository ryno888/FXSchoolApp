/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author ryno8
 */
public class Core {
    public Core(){}
    //--------------------------------------------------------------------------
    public URL getResource( String strResource){
       return getClass().getClassLoader().getResource(strResource);
    }
    //--------------------------------------------------------------------------
    public InputStream getResourceAsStream( String strResource){
       return getClass().getClassLoader().getResourceAsStream(strResource);
    }
    //--------------------------------------------------------------------------
    public static URL loadResource(String strResource){
        return new Core().getResource(strResource);
    }
    //--------------------------------------------------------------------------
    public static InputStream loadResourceAsStream(String strResource){
        return new Core().getResourceAsStream(strResource);
    }
    //--------------------------------------------------------------------------
}
