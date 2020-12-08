#################################
# Author: Alex Keller
# Course: GEOG376
# Program name: lab01.py
# Date created: 07/10/2017
# Last edited: 8:50 PM 7/13/2017
# Variables used: desc_MD_Cities, sr_MD_Cities, desc_lspop2013, sr_MD_lspop2013
# This program prints out  dataset descriptions of MD_cities.shp and the grid lspop2013.
###################################

# Importing arcpy so this program can access its functions
import arcpy

# Reading in the files and setting their descriptions and spatial refrences
arcpy.env.workspace = r"C:\Users\akeller5\Desktop\Lab 1\lab01_data\lab01_data\wingIDE"

desc_MD_Cities = arcpy.Describe("MD_cities.shp")
sr_MD_Cities = desc_MD_Cities.spatialReference
desc_lspop2013 = arcpy.Describe("lspop2013")
sr_MD_lspop2013 = desc_lspop2013.spatialReference

# Printing out the dataset descriptions of MD_cities.shp
print 'Shapefile name: {}\nPath:{}\nDataType: {}'.format(desc_MD_Cities.name, desc_MD_Cities.catalogPath, desc_MD_Cities.DataType)
print 'Dataset Properties:\nDataset type: {}\nFeature type: {}'.format(desc_MD_Cities.datasetType, desc_MD_Cities.featureType)
print 'Shape type:\nSpatial Reference name: {}\nProjecton_PCS name: {}'.format(desc_MD_Cities.shapeType, sr_MD_Cities.name, sr_MD_Cities.PCSName)
print 'Projection_GCS name: {0}\nDataset extent: xmin: {1}, xmax: {2}, ymin: {3}, ymax: {4}'.format(sr_MD_Cities.GCS.GCSName, desc_MD_Cities.extent.XMin, desc_MD_Cities.extent.XMax, desc_MD_Cities.extent.YMin, desc_MD_Cities.extent.YMax)
print "\n"

# Printing out the dataset descriptions of the grid lspop2013
print 'Dataset {} properties:\nPath: {}\nDataType: {}'.format(desc_lspop2013.name, desc_lspop2013.catalogPath, desc_lspop2013.DataType)
print 'Dataset type: {}\nGrid format: {}\nNumber of bands: {}'.format(desc_lspop2013.datasetType, desc_lspop2013.format, desc_lspop2013.bandCount)
print 'Height: {}\nWidth: {}\nSpatial Reference name: {}'.format(desc_lspop2013.height, desc_lspop2013.width, sr_MD_lspop2013.name)
print 'Projecton_PCS name: {0}\nProjection_GCS name: {1}\nDataset extent: xmin: {2}, xmax: {3}, ymin: {4}, ymax: {5}'.format(sr_MD_lspop2013.PCSName, sr_MD_lspop2013.GCSName, desc_lspop2013.extent.XMin, desc_lspop2013.extent.XMax, desc_lspop2013.extent.YMin, desc_lspop2013.extent.YMax)
print "\n"

# Print statement that denotes the end of the program and that it ran correctly
print 'End of dataset description'
                                                             
