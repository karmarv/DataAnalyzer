DataAnalyzer
============

This application is a swing based app that can evaluate and give you the types of files in your file system based on the configured extension type properties

- Select a folder using the Source path 
- Click on Analyze to compute the data
- Modify properties to add more extensions corresponding to a data category

Optimizations & Features:
- Need to look for a better method for figuring out the file types, maybe a MIME type detector. 
  This should work if I rename a file to other format and check its type 
- Show graph and bar charts corresponding to this analysis
- Improve the performance by invoking the analysis in a separate thread
- Build a comparison interface that can give the diff of two folders based on count and file types

Bugs
- Fix the bugs in the logic to update the Data statistics for the file count and size etc
