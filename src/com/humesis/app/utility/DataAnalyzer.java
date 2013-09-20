/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humesis.app.utility;

import com.humesis.app.entity.AnalyzedData;
import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author vishwakarma
 */
public interface DataAnalyzer {
    
    /*
     * Master data Map to store the file type and the extensions corresponding
     * Read a properties file for the mapping
     * Eg: 
     *      Key="IMAGE",Value="jpg,bmp,jpeg,gif"
     *      Key="DOCUMENT",Value="xls,xlsx,doc,ppt,pdf"
     */
    public TreeMap<String, ArrayList<String>> mSupportedDataTypeExtensionStore = new TreeMap<>();
    
    /*
     * Cache to store the Analyzed data set for a folder
     * Key="path",Value="Analysis list for all the types of dataTypes{IMAGE,DOCUMENT}"
     */
    public TreeMap<String, ArrayList<AnalyzedData>> mAnalyzedDataStore = new TreeMap<>();
    
    /*
     * Cache to keep the folder/file descriptors
     */
    public TreeSet<File> lFolderMetaDataStore = new TreeSet<>();
    public TreeSet<File> lFileMetaDataStore = new TreeSet<>();
    
    /*
     * The descriptor for a particular path for traversing the tree
     */
    public File getFileDescripter(String path);
    /*
     * Update the folder metaDataStore
     */
    public void updateFolderMetaDataStore(File folder);
    public void updateFolderMetaDataStore(String folderPath);
    /*
     * Update the master data for data extention types
     */
    public void updateSupportedDataTypeExtensionStore();
    /*
     * Update the analysis result of a directory to store
     */
    public void updateAnalyzedDirectoryDataStore(String folderPath);
    /*
     * Get the extension and the data category type
     */
    public String getFileDataTypeCategory(File file);
    public String getFileDataType(File file);
}
