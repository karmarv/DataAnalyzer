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
import java.util.ArrayList;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author vishwaka
 */
public class FileSystemAnalyzerTest {
    
    public FileSystemAnalyzerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getFileDescripter method, of class FileSystemAnalyzer.
     */
    @Ignore
    @Test
    public void testGetFileDescripter() {
        System.out.println("getFileDescripter");
        String path = "";
        FileSystemAnalyzer instance = new FileSystemAnalyzer();
        File expResult = null;
        File result = instance.getFileDescripter(path);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateFolderMetaDataStore method, of class FileSystemAnalyzer.
     */
    @Ignore
    @Test
    public void testUpdateFolderMetaDataStore() {
        System.out.println("updateFolderMetaDataStore");
        File folder = null;
        FileSystemAnalyzer instance = new FileSystemAnalyzer();
        instance.updateFolderMetaDataStore(folder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSupportedDataTypeExtensionStore method, of class FileSystemAnalyzer.
     */
    @Ignore
    @Test
    public void testUpdateSupportedDataTypeExtensionStore() {
        System.out.println("updateSupportedDataTypeExtensionStore");
        FileSystemAnalyzer instance = new FileSystemAnalyzer();
        instance.updateSupportedDataTypeExtensionStore();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileDataType method, of class FileSystemAnalyzer.
     */
    @Ignore
    @Test
    public void testGetFileDataType() {
        System.out.println("getFileDataType");
        File file = null;
        FileSystemAnalyzer instance = new FileSystemAnalyzer();
        String expResult = "";
        String result = instance.getFileDataType(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileDataTypeCategory method, of class FileSystemAnalyzer.
     */
    @Ignore
    @Test
    public void testGetFileDataTypeCategory() {
        System.out.println("getFileDataTypeCategory");
        File file = null;
        FileSystemAnalyzer instance = new FileSystemAnalyzer();
        String expResult = "";
        String result = instance.getFileDataTypeCategory(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAnalyzedDirectoryDataStore method, of class FileSystemAnalyzer.
     */
    @Ignore
    @Test
    public void testUpdateAnalyzedDirectoryDataStore() {
        System.out.println("updateAnalyzedDirectoryDataStore");
        String folderPath = "";
        FileSystemAnalyzer instance = new FileSystemAnalyzer();
        instance.updateAnalyzedDirectoryDataStore(folderPath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

 

    /**
     * Test of testPropertyReader method, of class FileSystemAnalyzer.
     */
    @Ignore
    @Test
    public void testPropertyReader() {
        System.out.println("testPropertyReader");
        FileSystemAnalyzer instance = new FileSystemAnalyzer();
        instance.updateSupportedDataTypeExtensionStore();
        System.out.println("Ext Map: " + mSupportedDataTypeExtensionStore.keySet().toString());
        for (String key : mSupportedDataTypeExtensionStore.keySet()) {
            System.out.println(key + " = " + mSupportedDataTypeExtensionStore.get(key));
        }
    }

    /**
     * Test of testDirectoryAnalyser method, of class FileSystemAnalyzer.
     */
    @Test
    public void testDirectoryAnalyser() throws Exception {
        System.out.println("testDirectoryAnalyser");
        FileSystemAnalyzer instance = new FileSystemAnalyzer();
        //Update the property file
        testPropertyReader();
        String folderPath = "C:\\Youdyog\\PublicPhone";
        System.out.println(DataUtility.getCurrentTimeStamp() + ", Starting the folder enlister test code: " + folderPath);
        File file = new File(folderPath);
        instance.updateFolderMetaDataStore(file);
        System.out.println("--------");
        System.out.println(DataUtility.getCurrentTimeStamp() + ", Dirs  : " + lFolderMetaDataStore);
        System.out.println(DataUtility.getCurrentTimeStamp() + ", Files : " + lFileMetaDataStore.size());
        System.out.println("--------");
        instance.updateAnalyzedDirectoryDataStore(folderPath);
        
        //Fetch the updated dataset
        // get the mAnalyzedDataStore
        ArrayList<AnalyzedData> lAnalyzed=mAnalyzedDataStore.get(folderPath);
        
        for (AnalyzedData ad : lAnalyzed) {
            System.out.println("Group/Category Name: "+ad.dataGroupStatistics.dataType+", Size: "+ad.dataGroupStatistics.dataCount); 
           for(DataStats ds : ad.listDataSatistics){
               System.out.println("    -Ext: "+ds.dataType+", Size: "+ds.dataCount); 
           }
        }

        
    }
}