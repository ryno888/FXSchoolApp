/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.com.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static app.config.Constants.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Ryno Laptop
 */
public class ComDate {
    
    //----------------------------------------------------------------------------------------
    /**
     * returns the current date
     * @return 
     */
    public static String getDate(){
        return getDate(new Date(), DATETIME);
    }
    //----------------------------------------------------------------------------------------
    /**
     * returns the current date formatted
     * @param format
     * @return 
     */
    public static String getDate(String format){
        if(format== null){ format = DATETIME; } 
        return getDate(new Date(), format== null ? DATETIME : format);
    }
    //----------------------------------------------------------------------------------------
    /**
     * returns the current date formatted
     * @param date
     * @return 
     */
    public static String getDate(Date date){
        return getDate(date, DATETIME);
    }
    //----------------------------------------------------------------------------------------
    /**
     * returns a date that is formatted to a certain format
     * @param date
     * @param format
     * @return 
     */
    public static String getDate(Date date, String format){
        if(format== null){
            format = DATETIME;
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        String currentDate = dateFormat.format(date); //2014/08/06 15:59:48
        
        return currentDate;
    }
    //----------------------------------------------------------------------------------------
    public static String checkTime(int t) {
        String time1;
        if (t < 10) {
            time1 = ("0" + t);
        } else {
            time1 = ("" + t);
        }
        return time1;
    }
    //----------------------------------------------------------------------------------------
    public static String getDate(LocalDate localDate) {
        return getDate(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }
    //----------------------------------------------------------------------------------------
    public static LocalDate toLocalDate(Object date) {
        return toLocalDate(date, null);
    }
    //----------------------------------------------------------------------------------------
    public static LocalDate toLocalDate(Object date, String format) {
        String strDate = getDate(date.toString());
        
        if(format == null) format = DATETIME;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(strDate, formatter);
    }
    //----------------------------------------------------------------------------------------
    
}
