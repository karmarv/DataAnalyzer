/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humesis.app.entity;

/**
 *
 * @author vishwaka
 */
public class DataStatsTable extends DataStats{
   
    public DataStatsTable() {
    }
    public DataStatsTable(DataStats ds) {
        super(ds);
        dataCategory="";
    }
    public DataStatsTable(DataStats ds,String dataCategory) {
        super(ds);
        this.dataCategory=dataCategory;
    }
    
    public String dataCategory;

}
