/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humesis.app.entity;

import java.util.ArrayList;

/**
 * This entity class enumerates the analyzed data for the various types
 * Also describes the group type for the list of data information
 * 
 * @author vishwaka
 */
public class AnalyzedData {
    /*
     * Description of data held, is expected to be a 2 stage structure for configured group types and
     * the data extensions under this group
     * 
     * DataInfo = {dataType="IMAGE",dataCount=2488,dataSize=3456243,
     *             dataUpdatedTime="09/17/2013  04:53 PM" timestamp of last update}
     * ArrayList<DataInfo> = {List of the various types of dataType Extensions(jpg,bmp) available}
     */
    public DataStats dataGroupStatistics;
    public ArrayList<DataStats> listDataSatistics;    

    /*
     * Constructor with initialization
     */
    public AnalyzedData() {
        this.dataGroupStatistics = new DataStats();
        this.listDataSatistics = new ArrayList<>();
    }
    public AnalyzedData(DataStats dataGroupStatistics, ArrayList<DataStats> listDataSatistics) {
        this.dataGroupStatistics = dataGroupStatistics;
        this.listDataSatistics = listDataSatistics;
    }
    
}
