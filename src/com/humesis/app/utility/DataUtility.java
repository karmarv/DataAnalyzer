/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humesis.app.utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author vishwakarma
 */
public class DataUtility {

    public static String getFormattedCurrentTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new java.util.Date();
        return sdf.format(d);
    }
    public static Timestamp getCurrentTimeStamp() {
        Date d = new java.util.Date();
        return new Timestamp(d.getTime());
    }
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date d = new java.util.Date();
        return sdf.format(d);
    }
    public static String getDataExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        String ext = fileName.substring(++dotIndex, fileName.length());
        return ext;
    }
}
