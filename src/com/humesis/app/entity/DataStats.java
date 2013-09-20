/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humesis.app.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * This class can also be moved to a Map<String,String> based data structure we
 * could store the info as key and value but we will use primitives for now
 * {frequencyDataType=2.6,dataLocationDepthIndex=0.7}
 *
 * @author vishwakarma
 */
public class DataStats {
    /*
     * FREQUENCY=2.6 (frequencyOfOccurenceOfThisDataTypeInBasePath)
     * PRIORITY="LOW"
     * LOCATION_WEIGHT=0.7 (averageLengthWithinTree/totalLengthOfTree)
     */
    public enum DataStatIdentifier {
        FREQUENCY,
        PRIORITY,
        LOCATION_WEIGHT
    }
    /*
     * Description of data held
     * dataType=jpeg extension type as per the types supported
     * dataCount=24 in number of files
     * dataSize=3456 in bytes
     * dataUpdatedTime="09/17/2013  04:53 PM" timestamp of last update
     * dataPriority="LOW"
     */
    public String dataType;
    public int dataCount;
    public int dataSize;
    public Timestamp dataUpdateTime;
    public Map<DataStatIdentifier, String> dataStatisticsMap;

    /*
     * Constructor
     */
    public DataStats() {
        this.dataType = "";
        this.dataCount = 0;
        this.dataSize = 0;
        this.dataUpdateTime = new Timestamp(0);
        this.dataStatisticsMap = new HashMap<>();
    }
    public DataStats(DataStats ds) {
        this.dataType = ds.dataType;
        this.dataCount = ds.dataCount;
        this.dataSize = ds.dataSize;
        this.dataUpdateTime = ds.dataUpdateTime;
        this.dataStatisticsMap = ds.dataStatisticsMap;
    }
    @Override
    public String toString() {
        return "DataStats{" + "dataType=" + dataType + ", dataCount=" + dataCount + ", dataSize=" + dataSize + ", dataUpdateTime=" + dataUpdateTime + ", dataStatisticsMap=" + dataStatisticsMap + '}';
    }
    
}