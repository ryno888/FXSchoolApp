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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.Timer;

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
    /**
     * returns a date that is formatted to a certain format
     * @param date
     * @param format
     * @return 
     */
    public static String getDate(String date, String format){
        
        SimpleDateFormat formatter = new SimpleDateFormat(DATETIME);
        String dateInString = date;

        try {

            Date dateFormatter = formatter.parse(dateInString);
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        
        return null;
    }
    //----------------------------------------------------------------------------------------
//    /**
//     * returns a date that is formatted to a certain format
//     * @param date
//     * @param format
//     * @return 
//     */
//    public static void setTimeOnElement(JComponent component){
//        Timer SimpleTimer = new Timer(1000, new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Calendar cal = new GregorianCalendar();
//                int second = cal.get(Calendar.SECOND);
//                int min = cal.get(Calendar.MINUTE);
//                int hour = cal.get(Calendar.HOUR);
//                String s =(checkTime(hour)+":"+checkTime(min)+":"+checkTime(second));
//                try {
//                    component.getClass().getMethod("setText", s.getClass()).invoke(component, s);
//                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
//                    Logger.getLogger(ComDate.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//        SimpleTimer.start();
//    }
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
    
}
