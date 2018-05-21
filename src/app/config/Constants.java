/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.config;

/**
 *
 * @author Ryno
 */
public final class Constants {
    //--------------------------------------------------------------------------
    //date constants
    //--------------------------------------------------------------------------
    
    //Date formats
    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE = "yyyy-MM-dd";
    public static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_1 = "d-MMM-yyyy";        // 16-Aug-2016
    public static final String DATE_FORMAT_2 = "d/MM/yyyy";         // 16/08/2016
    public static final String DATE_FORMAT_3 = "E, MMM d yyyy";         // Tue, Aug 16 2016
    /**
     * 12:08 PM
     */
    public static final String TIME = "h:mm a";
    /**
     * Wed, 4 Jul 2001
     */
    public static final String DATE_CUSTOM_1 = "EEE, d MMM yyyy";
    /**
     * Wed, 4 Jul 2001 12:08:54 PM
     */
    public static final String DATE_CUSTOM_2 = "EEE, d MMM yyyy h:mm:ss a";
    /**
     * Wed, 4 Jul 2001 12:08 PM
     */
    public static final String DATE_CUSTOM_3 = "EEE, d MMM yyyy h:mm a";
    /**
     * 200105021208
     */
    public static final String DATE_CUSTOM_4 = "yyyyMMddHHmmss";
    /**
     * 1208 - time
     */
    public static final String DATE_CUSTOM_5 = "mmss";
    /**
     * 20010502 - date
     */
    public static final String DATE_CUSTOM_6 = "yyyyMMdd";
    /**
     * 010502 - date
     */
    public static final String DATE_CUSTOM_7 = "yyMMdd";
    
    
    
    //directories
    public static final String DIR_ROOT = System.getProperty("user.dir");
    public static final String DIR_ASSETS = "/assets";
    public static final String DIR_FILES = "/files";
    public static final String DIR_IMAGES = DIR_FILES+"/images";
    public static final String DIR_DATA = "/data";
    public static final String DIR_CORE = DIR_DATA + "/core";
    public static final String DIR_CORE_PROJECT = DIR_CORE+"/project";
    public static final String DIR_CORE_TEMPLATES = DIR_CORE_PROJECT+"/templates";
    public static final String DIR_CORE_IMAGES = DIR_CORE+"/images";
    
    
    //icon images
    public static final String ICON_ADD_16 = DIR_CORE_IMAGES + "/colour/add16.png";
    public static final String ICON_ADD_32 = DIR_CORE_IMAGES + "/colour/add32.png";
    public static final String ICON_SETTINGS_16 = DIR_CORE_IMAGES + "/colour/settings16.png";
    public static final String ICON_SETTINGS_32 = DIR_CORE_IMAGES + "/colour/settings32.png";
    public static final String ICON_EDIT_16 = DIR_CORE_IMAGES + "/colour/pencil16.png";
    public static final String ICON_EDIT_32 = DIR_CORE_IMAGES + "/colour/pencil32.png";
    public static final String ICON_DELETE_16 = DIR_CORE_IMAGES + "/colour/delete16.png";
    public static final String ICON_DELETE_32 = DIR_CORE_IMAGES + "/colour/delete32.png";
    public static final String ICON_INFO_16 = DIR_CORE_IMAGES + "/colour/info16.png";
    public static final String ICON_INFO_32 = DIR_CORE_IMAGES + "/colour/info16.png";
    public static final String ICON_BLOCK_16 = DIR_CORE_IMAGES + "/colour/block16.png";
    public static final String ICON_BLOCK_32 = DIR_CORE_IMAGES + "/colour/block32.png";
    public static final String ICON_LOGO = DIR_CORE_IMAGES + "/logo/logo12.png";
    
    
}
