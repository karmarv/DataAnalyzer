/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humesis.app.utility;

import com.humesis.app.entity.AnalyzedData;
import com.humesis.app.entity.DataStats;
import static com.humesis.app.utility.DataAnalyzer.lFileMetaDataStore;
import static com.humesis.app.utility.DataAnalyzer.lFolderMetaDataStore;
import static com.humesis.app.utility.DataAnalyzer.mAnalyzedDataStore;
import static com.humesis.app.utility.DataAnalyzer.mSupportedDataTypeExtensionStore;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vishwaka
 */
public class FileSystemAnalyzer implements DataAnalyzer {

    static final Logger logger = Logger.getLogger(FileSystemAnalyzer.class.getName());
    int folderCount = 0;
    int fileCount = 0;

    public FileSystemAnalyzer() {
        //Constructor
    }

    @Override
    public File getFileDescripter(String path) {
        File file = new File(path);
        return file;
    }
    
    @Override
    public void updateFolderMetaDataStore(String folderPath) {
        File file = new File(folderPath);
        updateFolderMetaDataStore(file);
    }

    @Override
    public void updateFolderMetaDataStore(File folder) {
        try {
            File[] parentList = folder.listFiles();
            for (int i = 0; i < parentList.length; i++) {
                /*
                 * Check whether its a file or directory
                 * Get to the leaf using recursion if dir
                 * Update the file/folder data store
                 */
                if (parentList[i].isDirectory()) {
                    FileSystemAnalyzer childAnalyze = new FileSystemAnalyzer();
                    childAnalyze.updateFolderMetaDataStore(parentList[i]);
                    logger.log(Level.FINE, null, "Dir : " + parentList[i].toString());
                    lFolderMetaDataStore.add(parentList[i]);
                } else if (parentList[i].isFile()) {
                    logger.log(Level.FINE, null, "File: " + parentList[i].toString());
                    lFileMetaDataStore.add(parentList[i]);
                } else {
                    logger.log(Level.INFO, null, "Not a File/Dir: " + parentList[i].toString());
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateSupportedDataTypeExtensionStore() {
        try {
            PropertyResourceBundle p = new PropertyResourceBundle(new FileInputStream("resources\\configuration.properties"));
            System.out.println(p.keySet().size() + " Properties read successfully ");
            for (String key : p.keySet()) {
                //Read a type property
                String[] dTypeExts = p.getString(key).split(",");
                mSupportedDataTypeExtensionStore.put(key, new ArrayList<>(Arrays.asList(dTypeExts)));
            }
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getFileDataType(File file) {
        String dataType = "";
        try {
            //LOGIC: Get the Data type by the file extension and ext cannot be greater than 10 chars
            dataType = DataUtility.getDataExtension(file.getCanonicalPath());
            if(dataType.length()>10)
                dataType = "";
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return dataType;
    }

    @Override
    public String getFileDataTypeCategory(File file) {
        String categoryDataType = "UNKNOWN";
        /*
         * Get the dataType or extension
         * Find the categoryType in the mSupportedDataTypeExtensionStore
         */
        String dataType = getFileDataType(file);
        for (Map.Entry<String, ArrayList<String>> m : mSupportedDataTypeExtensionStore.entrySet()) {
            if (m.getValue().contains(dataType)) {
                return m.getKey();
            }
        }
        return categoryDataType;
    }

    @Override
    public void updateAnalyzedDirectoryDataStore(String folderPath) {

        /*
         *  Load the properties
         */
        updateSupportedDataTypeExtensionStore();
        /*
         * For each DataType={IMAGE,DOCUMENT,AUDIO,VIDEO...} create & update an AnalyzedData object
         * For each ExtensionType{IMAGE->jpg,bmp,png} in a DataType create & update a DataStats list object
         */
        System.out.println("Analyzing "+ folderPath);
        //Clear Master Analysis
        mAnalyzedDataStore.clear();
        //List for the interim store of the analyzed data
        Map<String, DataStats> mInterimAnalysisCategoryStore = new HashMap<>();
        Map<String, Map<String, DataStats>> mInterimAnalysisExtensionStore = new HashMap<>();

        /*
         * Get the ExtensionType for a file
         * Find the DataStats for this ExtensionType
         * 
         * Variables Updated: Increment dataCount,dataSize,dataModifiedTime
         * TODO: Properties, frequency etc
         * 
         */
        int extCount = 0, catCount = 0;
        for (File f : lFileMetaDataStore) {

            String extensionType = getFileDataType(f);
            String categoryType = getFileDataTypeCategory(f);
            //System.out.println(categoryType + ", " + extensionType);
            //Find & Update existing category of data
            //TODO: implement the compareTo interface to compare based on the dataType in DataStat
            if (mInterimAnalysisExtensionStore.containsKey(categoryType)) {
                //Find & Update existing extension from map
                if (mInterimAnalysisExtensionStore.get(categoryType).containsKey(extensionType)) {
                    //Extension Type exists hance update
                    DataStats de = mInterimAnalysisExtensionStore.get(categoryType).get(extensionType);
                    de.dataCount++;
                    de.dataSize += f.length();
                    Timestamp ts = new Timestamp(f.lastModified());
                    if (de.dataUpdateTime.after(ts)) {
                        //Updating the latest modification time for file
                        de.dataUpdateTime = ts;
                    }
                } else {
                    //Create Extension Type & put in map
                    DataStats de = new DataStats();
                    de.dataType = extensionType;
                    de.dataCount++;
                    de.dataSize += f.length();
                    Timestamp ts = new Timestamp(f.lastModified());
                    de.dataUpdateTime = ts;
                    mInterimAnalysisExtensionStore.get(categoryType).put(extensionType, de);
                }
                extCount++;
            } else {
                Map<String, DataStats> h = new HashMap<>();
                //Create Extension Type & put in map
                DataStats de = new DataStats();
                de.dataType = extensionType;
                de.dataCount++;
                de.dataSize += f.length();
                Timestamp ts = new Timestamp(f.lastModified());
                de.dataUpdateTime = ts;
                h.put(extensionType, de);

                //Create a categoryType
                DataStats dc = new DataStats();
                dc.dataType = categoryType;
                dc.dataCount++;
                dc.dataSize += f.length();
                dc.dataUpdateTime = ts;

                mInterimAnalysisExtensionStore.put(categoryType, h);
                mInterimAnalysisCategoryStore.put(categoryType, dc);
                catCount++;
            }
        }                
        
        ArrayList<AnalyzedData> lAnalyzed = new ArrayList<>();
        for (Map.Entry<String, Map<String, DataStats>> e : mInterimAnalysisExtensionStore.entrySet()) {
            System.out.println(e.getKey() + ", Extensions: " + e.getValue().keySet());
            AnalyzedData ad = new AnalyzedData();
            ad.dataGroupStatistics = mInterimAnalysisCategoryStore.get(e.getKey());
            ad.listDataSatistics.addAll(e.getValue().values());
            lAnalyzed.add(ad);
        }
        // update the mAnalyzedDataStore
        mAnalyzedDataStore.put(folderPath, lAnalyzed);
    }
}
